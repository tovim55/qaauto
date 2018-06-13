package com.verifone.entities;

import com.verifone.infra.User;
import com.verifone.tests.BaseTest;

public class EntitiesFactory {


    public static User getEntity(String entity){
        switch (entity) {
            case "EOAdminSupport":
                return BaseTest.envConfig.getCredentials().getEOAdminSupport();

            case "EOAdmin":
                return BaseTest.envConfig.getCredentials().getEOAdmin();

            case "DevAdmin":
                return BaseTest.envConfig.getCredentials().getDevAdmin();

            case "NewUser":
                return new User();
        }
    return null;
    }

}
