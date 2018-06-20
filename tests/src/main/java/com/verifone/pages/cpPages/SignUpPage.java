package com.verifone.pages.cpPages;

import com.relevantcodes.extentreports.LogStatus;
import com.verifone.infra.User;
import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;

public class SignUpPage extends BasePage {

    private final static String url = BaseTest.envConfig.getWebUrl() + "docs/overview/get-started/";
    private final static String title = "Sign Up with Verifone Identity Server";


    By signUpButton = By.xpath("(//*[@class='btn btn-default signup' and @data-text='Sign up'])");

    By firstName = By.id("givenname");
    By lastName = By.id("lastname");
    By email = By.id("username");
    By password = By.id("credential");
    By chkBox= By.xpath("//span[@class='check']");
    By nextBtn= By.id("btnNext");
    By agreementBtn= By.xpath("//*[@class='btn btn-primary btn-raised btn-accept']");

    public By messeegeText = By.xpath("//*[@class='instruction']");



    public SignUpPage() {
        super(url, title);
        navigate();
    }

    public String getMessege(){
        return getText(messeegeText);
    }

    public void signUp(User user){
        click(signUpButton);
        click(chkBox);
        sendKeys(this.firstName, user.getFirstName());
        sendKeys(this.lastName, user.getLastName());
        sendKeys(this.email, user.getUserName());
        sendKeys(this.password, user.getPassword());
        click(nextBtn);
        click(agreementBtn);

    }

}
