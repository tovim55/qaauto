package com.verifone.entities;

import com.relevantcodes.extentreports.LogStatus;
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

            case "GmailUser":
                user = new User(true);
                break;
        }
        System.out.println("User " + user.getUserName() + " Was created");
        BasePage.testLog.info("User " + user.getUserName() + " Was created");
        return user;
    }

}
