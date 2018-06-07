package com.verifone.tests.api.tests;

import com.verifone.pages.cpPages.LoginPage;
import com.verifone.pages.eoPages.HomePage;
import com.verifone.tests.BaseTest;
import com.verifone.utils.apiClient.createMerchant.CreateMerchant;
import com.verifone.utils.apiClient.getToken.GetTokenApi;
import com.verifone.utils.apiClient.getEoeadminData.GetEoadminDataApi;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class PubSubTest extends BaseTest {


    public PubSubTest() {
        propFilePath = "api\\tests\\userData.properties";
    }

    @Test(groups = {"Pub Sub"})
    public void GetTokenTestUI() throws IOException, InterruptedException {
        starTestLog("Pub Sub Get Token", "get token and EOadmin data calls");
        GetTokenApi getTokenApi = new GetTokenApi(prop.getProperty("getToken.contentType"), prop.getProperty("getToken.authorization"),
                "testId");
        String accessToken = getTokenApi.getToken(prop.getProperty("getToken.requestData"));
        GetEoadminDataApi getEoadminDataApi = new GetEoadminDataApi(accessToken, prop.getProperty("edminData.contentType"),
                prop.getProperty("edminData.authorization"), "testId", prop.getProperty("edminData.accept"));

        new CreateMerchant(accessToken, prop.getProperty("edminData.contentType"),
                prop.getProperty("edminData.authorization"), "testId").createMerchant(getEoadminDataApi.getData());

        LoginPage loginPage = new LoginPage();
        loginPage.loginPageCp(prop.getProperty("user_name"), prop.getProperty("password"));
        if (! new HomePage().getMerchantTable().contains("AutomationTest5@cmail.club")){
            new AssertionError("Merchant not created in UI");
        }

    }

}
