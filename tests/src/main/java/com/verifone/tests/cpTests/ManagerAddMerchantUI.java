package com.verifone.tests.cpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.cpPages.LoginPage;
import com.verifone.pages.eoPages.HomePage;
import com.verifone.pages.PageFactory;
import com.verifone.tests.BaseTest;
import com.verifone.utils.apiClient.createMerchant.CreateMerchant;
import com.verifone.utils.apiClient.getEoeadminData.GetEoadminDataApi;
import com.verifone.utils.apiClient.getToken.GetTokenApi;

import java.io.IOException;

import org.testng.annotations.*;



public class ManagerAddMerchantUI extends BaseTest {

    @Test(testName = "Pub Sub Get Token", description = "get token and EOadmin data calls", groups = {"CP-portal"})
    public void ManagerAddMerchantUI() throws IOException, InterruptedException  {
        User user = EntitiesFactory.getEntity("EOAdminSupport");
        GetTokenApi getTokenApi = new GetTokenApi("testId");
        String accessToken = getTokenApi.getToken();
        GetEoadminDataApi getEoadminDataApi = new GetEoadminDataApi(accessToken,"testId");
        String mId = new CreateMerchant(accessToken, "testId").createMerchant(getEoadminDataApi.getData());
//        LoginPage loginPage = new LoginPage();
//        loginPage.login(user);
//        System.out.println(mId);
//        if (!new DevHomePage().getMerchants().contains(mId)) {
//            org.testng.Assert.fail("test failed");
//        }

    }

}
