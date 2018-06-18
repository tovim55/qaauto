package com.verifone.tests.api.tests;

import com.google.gson.JsonObject;
import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.cpPages.LoginPage;
import com.verifone.pages.eoPages.HomePage;
import com.verifone.tests.BaseTest;
import com.verifone.utils.apiClient.BaseApi;
import com.verifone.utils.apiClient.createMerchant.CreateMerchant;
import com.verifone.utils.apiClient.getToken.GetTokenApi;
import com.verifone.utils.apiClient.getEoeadminData.GetEoadminDataApi;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class PubSubTest extends BaseTest {


    @Test(groups = {"Pub Sub"})
    public void GetTokenTestUI() throws IOException, InterruptedException {
        starTestLog("Pub Sub Get Token", "get token and EOadmin data calls");
        User user = EntitiesFactory.getEntity("EOAdminSupport");
        GetTokenApi getTokenApi = new GetTokenApi("testId");
        String accessToken = getTokenApi.getToken();
        GetEoadminDataApi getEoadminDataApi = new GetEoadminDataApi(accessToken, "testId");
        String mId = new CreateMerchant(accessToken, "testId").createMerchant(getEoadminDataApi.getData());
        LoginPage loginPage = new LoginPage();
        loginPage.loginPageCp(user);
        System.out.println(mId);
        assertTextContains(mId, new HomePage().getMerchants());
    }


}
