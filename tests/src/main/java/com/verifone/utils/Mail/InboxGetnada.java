package com.verifone.utils.Mail;


import org.openqa.selenium.By;

import com.verifone.pages.BasePage;

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
    By iframe = By.id("idIframe");


    public InboxGetnada() {
        super(url, title);
        navigate();
    }


    public String getLastMessage(String emailAddr) {
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
        return message;
    }



}

