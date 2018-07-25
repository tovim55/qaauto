package com.verifone.pages.cpPages;


import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


public class DevUsersPage extends BasePage {

    private final static String url = BaseTest.envConfig.getWebUrl() + "developersupport#users";
    private final static String title = "[QA] Developer Support Console | Users";

    private By getSubTitle = By.className("section-title");
    private By addUserBtn = By.className("section-action-container");
    private By userFormTitle = By.className("content-header-top");
    private By firstNameField = By.name("firstName");
    private By lastNameField = By.name("lastName");
    private By emailField = By.name("userEmail");
    private By submitBtn= By.id("createBtn");
//    private By errorMessage= By.id("//*[@id=\"VUIForm266782\"]/div[2]/div[1]/div/div/span");



    public DevUsersPage() {
        super(url, title);
//        navigate();
    }


    public void getSubTitle() {
        System.out.println(getText(getSubTitle));
        Assert.assertEquals("Users", getText(getSubTitle));

    }

    public void addUser() {
////        TODO change the sleep later
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        waitTillClickable();
        getWebElement(addUserBtn, 10000, ExpectedConditions.visibilityOfElementLocated(addUserBtn)).click();
//        click(addUserBtn);
        Assert.assertEquals("Add DevSupport User", getText(userFormTitle));
//        System.out.println(getText(userFormTitle));

    }

    public void mandatoryFields(){
//        fillIncorrectForm();
        click(submitBtn);
        fillIncorrectForm();
        Assert.assertEquals("Add DevSupport User", getText(userFormTitle));
    }

    private void fillIncorrectForm() {
        sendKeys(firstNameField, "Test");
        click(lastNameField);
        sendKeys(lastNameField, "Test");
        click(emailField);
        sendKeys(emailField, "Test");

    }

}
