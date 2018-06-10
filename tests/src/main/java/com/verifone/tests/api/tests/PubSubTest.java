package com.verifone.tests.api.tests;

import com.verifone.pages.cpPages.LoginPage;
import com.verifone.pages.eoPages.HomePage;
import com.verifone.tests.BaseTest;
import com.verifone.utils.apiClient.createMerchant.CreateMerchant;
import com.verifone.utils.apiClient.getToken.GetTokenApi;
import com.verifone.utils.apiClient.getEoeadminData.GetEoadminDataApi;
import org.testng.annotations.Test;

import java.io.IOException;

public class PubSubTest extends BaseTest {


    public PubSubTest() {
        propFilePath = "api\\tests\\userData.properties";
    }



    @Test(groups = {"Pub Sub"})
    public void GetTokenTestUI() throws IOException, InterruptedException {
        starTestLog("Pub Sub Get Token", "get token and EOadmin data calls");
        GetTokenApi getTokenApi = new GetTokenApi("Giora test!! VIP!!");
        String accessToken = getTokenApi.getToken();
        GetEoadminDataApi getEoadminDataApi = new GetEoadminDataApi(accessToken,"testId");
        new CreateMerchant(accessToken, "testId").createMerchant(getEoadminDataApi.getData());



//        CGLoginPage loginPage = new CGLoginPage();
//        loginPage.loginPageCp(
//                prop.getProperty("user_name"),
//                prop.getProperty("password"));
//        if (!new HomePage().getMerchants().contains("AutomationTest7@cmail.club")) {
//            org.testng.Assert.fail("test failed");
//        }

    }

}
