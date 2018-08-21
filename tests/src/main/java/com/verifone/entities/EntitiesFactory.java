package com.verifone.entities;

import com.relevantcodes.extentreports.LogStatus;
import com.verifone.infra.Company;
import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;

public class EntitiesFactory {


    public static User getEntity(String entity) {
        User user = null;
        switch (entity) {
            case "EOAdminSupport":
                user = BaseTest.envConfig.getCredentials().getEOAdminSupport();
                break;
            case "getEOMerchant":
                user = BaseTest.envConfig.getCredentials().getEOMerchant();
                break;

            case "CGPortal":
                user = BaseTest.envConfig.getCredentials().getCGPortal();
                break;

            case "EOAdmin":
                user = BaseTest.envConfig.getCredentials().getEOAdmin();
                break;

            case "DevAdmin":
                user = BaseTest.envConfig.getCredentials().getDevAdmin();
                break;

            case "DevSupportAdmin":
                user = BaseTest.envConfig.getCredentials().getDevSupportAdmin();
                break;

            case "NewUser":
                user = new User();
                break;

            case "Company":
                user = new Company();
                break;

            case "GmailUser":
                user = new User(true);
                break;
        }
        System.out.println("User is: " + user.getUserName());
        BasePage.testLog.info("User is: " + user.getUserName());
        return user;
    }

}
