package com.verifone.infra;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class User {

    private String userName;
    private String password;
    private String firstName;
    private String lastName;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User() {
        this.userName = generateUserName();
        this.password = generatePassword();
        this.firstName = generateNames();
        this.lastName = generateNames();
    }


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    private String generateUserName() {
//        return randomAlphabetic(5).toLowerCase() + "@getnada.com";
        return "tov4545@gmail.com";

    }

    private String generatePassword() {
        return random(15, true, true);
    }

    private String generateNames() {
        return "Auto Test " + randomAlphabetic(5).toLowerCase();
    }

}
