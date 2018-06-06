package com.verifone.pages.cpPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;

public class SignUpPage extends BasePage {

    private final static String url = "";
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
    }

    public String getMessege(){
        return getText(messeegeText);
    }

    public void signUp(String firstName, String lastName, String email, String password){
        click(signUpButton);
        click(chkBox);
        sendKeys(this.firstName, firstName);
        sendKeys(this.lastName, lastName);
        sendKeys(this.email, email);
        sendKeys(this.password, password);
        click(nextBtn);
        click(agreementBtn);

    }

}
