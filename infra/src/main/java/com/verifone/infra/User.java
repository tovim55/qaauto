package com.verifone.infra;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class User {

    private String userName;
    private String password;
    private String name;
    private String securityAnswer;
    private String firstName;
    private String lastName;
    private String vhqCustomerId;
    private String internalId;


    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.firstName = generateNames();
        this.lastName = generateNames();
        this.vhqCustomerId = getNUmAsStr(10);
    }

    public User(String userName, String name, String password, String securityAnswer) {
        this.userName = userName;
        this.name = userName;
        this.securityAnswer = securityAnswer;
        this.password = password;
        this.firstName = generateNames();
        this.lastName = generateNames();
        this.vhqCustomerId = getNUmAsStr(10);
    }

    public User() {
        this.userName = generateUserName();
        this.password = generatePassword();
        this.firstName = generateNames();
        this.lastName = generateNames();
        this.vhqCustomerId = getNUmAsStr(10);
    }

    public User(boolean gmailUser) {
        this.userName = "tov4545@gmail.com";
        this.password = generatePassword();
        this.firstName = generateNames();
        this.lastName = generateNames();
        this.vhqCustomerId = getNUmAsStr(10);
    }


    public String getVhqCustomerId() {
        return vhqCustomerId;
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
    public String getName() {
        return name;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

    private String generateUserName() {
        return randomAlphabetic(8).toLowerCase() + "@getnada.com";
    }


    protected String generatePassword() {
        return "Veri1234";
    }

    protected String generateNames() {
        return "Auto Test " + randomAlphabetic(5).toLowerCase();
    }

    protected int getRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(100000);
    }

    private String getNUmAsStr(int digits){
        long min = (long) Math.pow(10, digits - 1);
        return String.valueOf(ThreadLocalRandom.current().nextLong(min, min * 10));
    }
}
