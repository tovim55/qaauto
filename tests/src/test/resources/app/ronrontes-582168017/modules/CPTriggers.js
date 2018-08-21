//This method will be helpful to get the triggers payload data and show it on html page.
exports.cpNotifyTriggers = function(paramsJson) {
    var paramNotifyApp = JSON.parse(paramsJson);
    ARGV.triggerName = paramNotifyApp.Trigger.Name;
    ARGV.setPayloadData = JSON.stringify(paramNotifyApp);
}

exports.cpRequestApi = function(paramsJson) {
    var paramNotifyApp = JSON.parse(paramsJson);

    //This code is used to sent the api request
    var responseData = {};
    var requsetObj = {};
    requsetObj.API = {};
    requsetObj.API.Name = ARGV.apiname;
    requsetObj.API.Handle = "handle";
    requsetObj.API.CP_App_Id = ARGV.cp_appId;
    requsetObj.API.CP_App_Version = ARGV.cp_appVersion;
    requsetObj.API.Payload = {};

    console.log("api name" + ARGV.apiname);

    //Based on the Api name, we will send payload data to get the appropriate respose.
    if (ARGV.apiname === "CP_APP_REQUESTS_CARD_DATA") {

        requsetObj.API.Payload.Card_Type = ["MAG_STRIPE", "CHIP", "CTLS", "CTLS_CARD", "CTLS_PHONE", "MANUAL"];

        // send notification and wait for response for 30 seconds
        console.log("1.html : request data" + JSON.stringify(requsetObj));
        responseData = notifyAndWait(ARGV.cp_notifyFrom, "CP_APP_REQUESTS_CARD_DATA", requsetObj, 0, "CP_APP_RECEIVES_CARD_DATA", 30);
        ARGV.printResp = JSON.stringify(responseData);
        ARGV.apiname = "";

    } else if (ARGV.apiname === "CP_APP_REQUESTS_ENCRYPTED_CARD") {

        requsetObj.API.Payload.PAN_Handle = paramNotifyApp.Trigger.Payload.PAN_Handle;
        requsetObj.API.Payload.Output = "PAN";

        // send notification and wait for response for 30 seconds
        console.log(JSON.stringify(requsetObj));
        responseData = notifyAndWait(ARGV.cp_notifyFrom, "CP_APP_REQUESTS_ENCRYPTED_CARD", requsetObj, 0, "CP_APP_RECEIVES_ENCRYPTED_CARD", 30);
        ARGV.printResp = JSON.stringify(responseData);
        ARGV.apiname = "";

    } else if (ARGV.apiname === "CP_APP_REQUESTS_APPEND_RECEIPT") {

        requsetObj.API.Payload.HTML = "<html><title>Free Drink!</title><body><h1>Get free large soda with your next order</h1><p>Offer valid for two weeks</p><p>Reference: 89583-40984-JZ</p></body></html>";
        requsetObj.API.Payload.Type = "CARDHOLDER_RECEIPT_FOOTER";

        // send notification and wait for response for 30 seconds
        console.log("Calling CP API: " + requsetObj.API.Name, "With", JSON.stringify(requsetObj));
        responseData = notifyAndWait(ARGV.cp_notifyFrom, "CP_APP_REQUESTS_APPEND_RECEIPT", requsetObj, 0, "CP_APP_RECEIVES_APPEND_RECEIPT", 30);
        ARGV.printResp = JSON.stringify(responseData);
        ARGV.apiname = "";

    } else if (ARGV.apiname === "CP_APP_REQUESTS_PRINT") {

        requsetObj.API.Payload.HTML = "<html><title>Free Drink!</title><body><h1>Get free large soda with your next order</h1><p>Offer valid for two weeks</p><p>Reference: 89583-40984-JZ</p></body></html>";

        // send notification and wait for response for 30 seconds
        console.log("Calling CP API: " + requsetObj.API.Name, "With", JSON.stringify(requsetObj));
        responseData = notifyAndWait(ARGV.cp_notifyFrom, "CP_APP_REQUESTS_PRINT", requsetObj, 0, "CP_APP_RECEIVES_PRINT", 30);
        ARGV.printResp = JSON.stringify(responseData);
        ARGV.apiname = "";

    } else if (ARGV.apiname === "CP_APP_REQUESTS_PAYMENT_TRANSACTION_START") {
        if (ARGV.state === "purchase") {
            console.log("purchase transaction");
            requsetObj.API.Payload.Transaction_Type = "PAYMENT";
            requsetObj.API.Payload.Trans_Handle = "000102030405060708090A0B0C0D0E20";
            requsetObj.API.Payload.Trans_Amount = "45.72";
            requsetObj.API.Payload.Currency = "USD";
            requsetObj.API.Payload.Gratuity_Amount = "10.00";
            requsetObj.API.Payload.Manual_Entry = false;

        } else if (ARGV.state === "void") {
            console.log("void transaction");
            requsetObj.API.Payload.Transaction_Type = "VOID";
            requsetObj.API.Payload.Trans_Handle = "000102030405060708090A0B0C0D0E20";
        }

        console.log(JSON.stringify(requsetObj));

        // send notification and wait for response for 30 seconds
        responseData = notifyAndWait(ARGV.cp_notifyFrom, "CP_APP_REQUESTS_PAYMENT_TRANSACTION_START", requsetObj, 0, "CP_APP_RECEIVES_PAYMENT_TRANSACTION_START_STATUS", 30);
        ARGV.printResp = JSON.stringify(responseData);
        ARGV.state = "";
        ARGV.apiname = "";

    }
}

//This method will be helpful to get the payload data from request triggers and send response to the payment app
exports.cpRequestTriggers = function(paramsJson) {
    console.log("state of the buttons" + ARGV.state);
    var paramNotifyApp = JSON.parse(paramsJson);
    ARGV.triggerName = paramNotifyApp.Trigger.Name;
    ARGV.setPayloadData = JSON.stringify(paramNotifyApp);

    if ((ARGV.state === "submit") || (ARGV.state === "close")) {
        console.log("button state is valid");

        if (ARGV.triggerName === "CP_SYSTEM_REQUESTS_BASKET_ADJUSTMENT") {
            ARGV.triggerName = "";
            console.log("if");
            if (ARGV.state === "submit") {
                console.log("CP_SYSTEM_REQUESTS_BASKET_ADJUSTMENT");
                cpRequestBasketTriggers();
            } else {
                console.log("else");
                cpExitTriggers("CP_SYSTEM_RECEIVES_BASKET_ADJUSTMENT");
            }
        } else if (ARGV.triggerName === "CP_SYSTEM_REQUESTS_LOYALTY") {

            ARGV.triggerName = "";
            if (ARGV.state === "submit") {
                console.log("CP_SYSTEM_REQUESTS_LOYALTY");
                cpRequestLoyltyTriggers();
            } else {
                console.log("else");
                cpExitTriggers("CP_SYSTEM_RECEIVES_LOYALTY");
            }

        } else if (ARGV.triggerName === "CP_SYSTEM_REQUESTS_AMOUNT_ADJUSTMENT") {
            ARGV.triggerName = "";
            if (ARGV.state === "submit") {
                cpRequestAmountTriggers();
            } else {
                cpExitTriggers("CP_SYSTEM_RECEIVES_AMOUNT_ADJUSTMENT");
            }
        } else if (ARGV.triggerName === "CP_SYSTEM_REQUESTS_PAYMENT_AMOUNT_ADJUSTMENT") {
            ARGV.triggerName = "";
            if (ARGV.state === "submit") {
                cpRequestPaymentAmountTriggers();
            } else if (ARGV.state === "close") {
                console.log("exit payment amount");
                cpExitTriggers("CP_SYSTEM_RECEIVES_PAYMENT_AMOUNT_ADJUSTMENT");
            }

        } else if (ARGV.triggerName === "CP_SYSTEM_REQUESTS_ALT_PAYMENT") {
            ARGV.transAmt = paramNotifyApp.Trigger.Payload.Trans_Amount;
            console.log("Inside CP_SYSTEM_REQUESTS_ALT_PAYMENT");
            console.log("Transaction Amount" + ARGV.transAmt);
            console.log("alt payment key" + ARGV.key);

            ARGV.triggerName = "";
            if (ARGV.key === "getBitcoin") {
                console.log("Inside getBitcoing");
                var paymentMethod = "BITCOIN";
                getAlternatePayment(paymentMethod, ARGV.transAmt);
            } else if (ARGV.key === "getPaypal") {
                var paymentMethod = "PAYPAL";
                getAlternatePayment(paymentMethod, ARGV.transAmt);
            } else {
                cpExitTriggers("CP_SYSTEM_RECEIVES_ALT_PAYMENT");
            }

        } else if (ARGV.triggerName === "CP_SYSTEM_REQUESTS_RECEIPT_MANAGEMENT") {
            ARGV.triggerName = "";
            if (ARGV.state === "submit") {
                cpRequestReceiptManagementTriggers();
            } else if (ARGV.state === "close") {
                cpExitTriggers("CP_SYSTEM_RECEIVES_RECEIPT_MANAGEMENT");
            }

        }
    } else {
        console.log("button state is not valid");
        ARGV.state = ''
    }
}



//Exit Triggers
function cpExitTriggers(receivesTriggerName) {
    console.log("state of trigger:" + receivesTriggerName);

    var requestData = {};
    requestData.Trigger = {};
    requestData.Trigger.Name = receivesTriggerName;
    requestData.Trigger.Handle = "handle";
    requestData.Trigger.CP_App_Id = ARGV.cp_appId;
    requestData.Trigger.CP_App_Version = ARGV.cp_appVersion;
    requestData.Trigger.Payload = {};

    console.log("blank trigger response" + JSON.stringify(requestData));
    // send the notification and exit the CP application

    if (receivesTriggerName === "CP_SYSTEM_RECEIVES_BASKET_ADJUSTMENT") {
        requestData.Trigger.Payload.Basket_Adjusted = false;
        notify("1", "CP_SYSTEM_RECEIVES_BASKET_ADJUSTMENT", requestData);
    } else if (receivesTriggerName === "CP_SYSTEM_RECEIVES_LOYALTY") {
        notify("1", "CP_SYSTEM_RECEIVES_LOYALTY", requestData);
    } else if (receivesTriggerName === "CP_SYSTEM_RECEIVES_AMOUNT_ADJUSTMENT") {
        notify("1", "CP_SYSTEM_RECEIVES_AMOUNT_ADJUSTMENT", requestData);
    } else if (receivesTriggerName === "CP_SYSTEM_RECEIVES_PAYMENT_AMOUNT_ADJUSTMENT") {
        console.log("1");
        notify("1", "CP_SYSTEM_RECEIVES_PAYMENT_AMOUNT_ADJUSTMENT", requestData);
    } else if (receivesTriggerName === "CP_SYSTEM_RECEIVES_ALT_PAYMENT") {
        notify("1", "CP_SYSTEM_RECEIVES_ALT_PAYMENT", requestData);
        ARGV.key = '';
    } else if (receivesTriggerName === "CP_SYSTEM_RECEIVES_RECEIPT_MANAGEMENT") {
        notify("1", "CP_SYSTEM_RECEIVES_RECEIPT_MANAGEMENT", requestData);
    }
    ARGV.state = '';
    exit('return 0');
}

//Basket adjusment trigger reponse
function cpRequestBasketTriggers() {
    var requestData = {};
    var Trigger = {};

    Trigger.Name = "CP_SYSTEM_RECEIVES_BASKET_ADJUSTMENT";
    Trigger.Handle = "handle";
    Trigger.CP_App_Id = ARGV.cp_appId;
    Trigger.CP_App_Version = ARGV.cp_appVersion;

    var Payload = {};
    Payload.Basket_Adjusted = true;

    var Offers = [];

    var offerObjOne = {};
    offerObjOne.Offer_Id = "7836938109";
    offerObjOne.Program_Id = "MCD-1601";
    offerObjOne.Offer_Type = "MERCHANT_COUPON";
    offerObjOne.Offer_Description = "Get 20% off your Purchase";
    offerObjOne.Offer_Refundable = false;
    offerObjOne.Offer_Combinable = true;
    offerObjOne.Offer_Percent_Discount = 0.2;
    offerObjOne.Merchant_Offer_Code = "931343";
    offerObjOne.Product_Code = "ZM9475L";

    Offers.push(offerObjOne);

    Payload.Offers = Offers;

    Trigger.Payload = Payload;
    requestData.Trigger = Trigger;
    console.log("requestData basket" + JSON.stringify(requestData));

    // send the notification and exit the CP application
    notify("1", "CP_SYSTEM_RECEIVES_BASKET_ADJUSTMENT", requestData);
    ARGV.state = '';
    exit("return 0");
}

//Loylty trigger reponse
function cpRequestLoyltyTriggers() {
    var requestData = {};
    var Trigger = {};

    Trigger.Name = "CP_SYSTEM_RECEIVES_LOYALTY";
    Trigger.Handle = "handle";
    Trigger.CP_App_Id = ARGV.cp_appId;
    Trigger.CP_App_Version = ARGV.cp_appVersion;

    var Payload = {};
    Payload.Publisher = "vwallet";
    Payload.Publisher_Id = "878343322";

    var Loyalty_Identifier = [];
    var identifierObj = {};
    identifierObj.Program_Id = "JK223123";
    identifierObj.Customer_Phone_Number = "678-555-1212";
    identifierObj.Customer_Name = "John Doe";
    identifierObj.Customer_Email = "jd@redmail.com";
    identifierObj.Loyalty_Points_Balance = "30000";
    identifierObj.Consumer_Id = "IP6304034";

    var Customer_Loyalty_Id = ["404770678", "720940JSN"];
    identifierObj.Customer_Loyalty_Id = Customer_Loyalty_Id;
    Loyalty_Identifier.push(identifierObj);

    Payload.Loyalty_Identifier = Loyalty_Identifier;

    Trigger.Payload = Payload;
    requestData.Trigger = Trigger;

    console.log("requestData " + requestData);

    // send the notification and exit the CP application
    notify("1", "CP_SYSTEM_RECEIVES_LOYALTY", requestData);
    ARGV.state = '';
    //receivesTriggerName = '';
    exit('return 0');
}

//Amount Adjustment trigger
function cpRequestAmountTriggers() {
    var requestData = {};
    requestData.Trigger = {};
    requestData.Trigger.Name = "CP_SYSTEM_RECEIVES_AMOUNT_ADJUSTMENT";
    requestData.Trigger.Handle = "handle";
    requestData.Trigger.CP_App_Id = ARGV.cp_appId;
    requestData.Trigger.CP_App_Version = ARGV.cp_Version;

    requestData.Trigger.Payload = {};
    requestData.Trigger.Payload.Adjustment_Value = "- 1.00";
    requestData.Trigger.Payload.Description = "Sample app";

    console.log("requestData basket" + JSON.stringify(requestData));

    // send the notification and exit the CP application
    notify("1", "CP_SYSTEM_RECEIVES_AMOUNT_ADJUSTMENT", requestData);
    ARGV.state = '';
    exit("return 0");
}

//Payment Amount adjustment
function cpRequestPaymentAmountTriggers() {
    var requestData = {};
    requestData.Trigger = {};
    requestData.Trigger.Name = "CP_SYSTEM_RECEIVES_PAYMENT_AMOUNT_ADJUSTMENT";
    requestData.Trigger.Handle = "handle";
    requestData.Trigger.CP_App_Id = ARGV.cp_appId;
    requestData.Trigger.CP_App_Version = ARGV.cp_appVersion;

    requestData.Trigger.Payload = {};
    requestData.Trigger.Payload.Adjustment_Value = "-2.00";
    requestData.Trigger.Payload.Description = "Dev Trigger";

    console.log("requestData basket" + JSON.stringify(requestData));

    // send the notification and exit the CP application
    notify("1", "CP_SYSTEM_RECEIVES_PAYMENT_AMOUNT_ADJUSTMENT", requestData);
    ARGV.state = '';
    exit("return 0");
}

//Alt Payment Trigger
function getAlternatePayment(paymentMethod, amount) {

    console.log("---var value--" + amount);
    console.log("---paymentMethod--" + paymentMethod);

    var requestData = {};
    var Trigger = {};
    Trigger.Name = "CP_SYSTEM_RECEIVES_ALT_PAYMENT";
    Trigger.Handle = "handle";
    Trigger.CP_App_Id = ARGV.cp_appId;
    Trigger.CP_App_Version = ARGV.cp_appVersion;

    var Payload = {};
    Trigger.Payload = {};
    Payload.Alt_Approved_Amount = "" + -amount + "";
    Payload.Auth_Code = "78787";
    Payload.Method_Of_Payment = paymentMethod;
    Payload.Redeemed_Units = "0.174";
    Trigger.Payload = Payload;
    requestData.Trigger = Trigger;
    console.log("Request Data" + JSON.stringify(requestData));
    ARGV.printResp = JSON.stringify(requestData);
    // send the notification and exit the CP application
    notify("1", "CP_SYSTEM_RECEIVES_ALT_PAYMENT", requestData);
    ARGV.state = '';
    ARGV.key = '';
    exit("return 0");
}

//Receipt Management
function cpRequestReceiptManagementTriggers() {
    var requestData = {};
    requestData.Trigger = {};
    requestData.Trigger.Name = "CP_SYSTEM_RECEIVES_RECEIPT_MANAGEMENT";
    requestData.Trigger.Handle = "handle";
    requestData.Trigger.CP_App_Id = ARGV.cp_appId;
    requestData.Trigger.CP_App_Version = ARGV.cp_appVersion;

    requestData.Trigger.Payload = {};
    requestData.Trigger.Payload.Provision = "PHYSICAL";

    console.log("requestData basket" + JSON.stringify(requestData));

    // send the notification and exit the CP application
    notify("1", "CP_SYSTEM_RECEIVES_RECEIPT_MANAGEMENT", requestData);
    ARGV.state = '';
    exit("return 0");
}