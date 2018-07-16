package com.verifone.tests.cpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.cpPages.CreateMerchantPage;
import com.verifone.tests.BaseTest;
import com.verifone.utils.Mail.InboxGetnada;
import org.testng.annotations.Test;

import static com.verifone.tests.cpTests.MerchantGetCongMailUI.addNewEmail;

public class CreateMerchantTest extends BaseTest {

    @Test(testName = "CreateMerchantTest", description = "CP dev Portal - check Create Merchant and activate", groups = {"CP-portal"})
    public void createMerchantUI() throws Exception {
        User dev = EntitiesFactory.getEntity("getEOMerchant");
        CreateMerchantPage devHome = (CreateMerchantPage) PageFactory.getPage("CreateMerchantPage");
        devHome.login(dev);
        devHome.merchantClick();
        String emailName = devHome.fillForm();
        InboxGetnada email = (InboxGetnada) PageFactory.getPage("InboxGetnada");
        System.out.println(emailName);
        email.getLastMessage(emailName);
//        DevUsersPage devUsers = (DevUsersPage) PageFactory.getPage("DevUsersPage");
//        devUsers.getSubTitle();
    }
}
