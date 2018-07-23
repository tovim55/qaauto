package com.verifone.tests.cpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.cpPages.CreateMerchantPage;
import com.verifone.tests.BaseTest;
import com.verifone.utils.Mail.InboxGetnada;
import org.testng.annotations.Test;

import static com.verifone.utils.Assertions.assertTextEqual;

public class CreateMerchantTest extends BaseTest {

    @Test(testName = "CreateMerchantTest_", description = "CP dev Portal - check Create Merchant and activate", groups = {"CP-portal"})
    public void createMerchantUI() {
        User dev = EntitiesFactory.getEntity("getEOMerchant");
        CreateMerchantPage EOHome = (CreateMerchantPage) PageFactory.getPage("CreateMerchantPage");
        EOHome.login(dev);
        EOHome.merchantClick();
        String emailName = EOHome.fillForm();
        InboxGetnada email = (InboxGetnada) PageFactory.getPage("InboxGetnada");
        email.getLastMessage(emailName, "Next");
        email.confirmPass();
        CreateMerchantPage login = (CreateMerchantPage) PageFactory.getPage("CreateMerchantPage");
        assertTextEqual("Active", login.merchantClick());
    }
}
