package com.verifone.utils.Mail;


import org.openqa.selenium.By;

import com.verifone.pages.BasePage;

import java.util.ArrayList;

public class InboxGetnada extends BasePage {

    private final static String url = "https://getnada.com/#";
    private final static String title = "Nada - temporary email";

    By addInboxBtn = By.xpath("//*[contains(@class, 'icon-plus')]");
    By emailField = By.xpath("//input[contains(@class, 'user_name')]");
    By domainField = By.xpath("//select[contains(@id, 'domain')]");
    By acceptBtn = By.linkText("ACCEPT");
    By firstMessage = By.xpath("//div[contains(@class, 'subject ')]");
//    By messageContant = By.partialLinkText("Thank you for completing your Verifone");
    By acceptLink = By.linkText("Activate Account");
    By acceptGermany = By.linkText("Konto aktivieren");
    By iframe = By.id("idIframe");
    By confirmPassword2 = By.id("confirmPassword2");
    By confirmPassword = By.id("confirmPassword");
    By checkBox1= By.xpath("//*[@id=\"tandc_container\"]/div[1]/div/label/span[1]/span");
    By checkBox2= By.xpath("//*[@id=\"tandm_container\"]/div[1]/div/label/span/span");


    public InboxGetnada() {
        super(url, title);
        navigate();
    }


    public String getLastMessage(String emailAddr) {
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        emailAddr = emailAddr.split("@")[0];
        click(addInboxBtn);
        sendKeys(emailField, emailAddr);
        click(domainField);
        sendKeysNoClear(domainField, "getnada.com");
        click(acceptBtn);
        click(firstMessage);
        switchToIframe(iframe);
        String message = driver.findElement(acceptLink).getText();
        driver.findElement(acceptLink).click();
        switchToPreviosTab();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        click(confirmPassword);
        click(confirmPassword2);
        click(checkBox1);
        click(checkBox2);

        return message;
    }



}

