package com.verifone.utils.Mail;


import com.verifone.pages.BasePage;
import org.openqa.selenium.By;

public class InboxGetnada extends BasePage {

    private final static String url = "https://getnada.com/#";
    private final static String title = "Nada - temporary email";

    By addInboxBtn = By.xpath("//*[contains(@class, 'icon-plus')]");
    By emailField = By.xpath("//input[contains(@class, 'user_name')]");
    By domainField = By.xpath("//select[contains(@id, 'domain')]");
    By acceptBtn = By.linkText("ACCEPT");
    By firstMessage = By.xpath("//div[contains(@class, 'subject ')]");
    By acceptLink = By.linkText("Activate Account");
    //    By acceptGermany = By.linkText("Konto aktivieren");
    By iframe = By.id("idIframe");
    By confirmPassword2 = By.name("confirmPassword2");
    By confirmPassword = By.name("confirmPassword");
    By checkBox1 = By.xpath("//*[@id=\"tandc_container\"]/div[1]/div/label/span[1]/span");
    By checkBox2 = By.xpath("//*[@id=\"tandm_container\"]/div[1]/div/label/span/span");
    By agree1 = By.xpath("//*[@class=\"btn btn-primary btn-raised btn-accept\"][1]");
    By agree2 = By.xpath("(//*[@class=\"btn btn-primary btn-raised btn-accept\"])[2]");
    By subBtn = By.id("btnSubmit");


    public InboxGetnada() {
        super(url, title);
        navigate();
    }


    public String getLastMessage(String emailAddress, String routing) {
        emailAddress = emailAddress.split("@")[0];
        click(addInboxBtn);
        sendKeys(emailField, emailAddress);
        click(domainField);
        sendKeysNoClear(domainField, "getnada.com");
        click(acceptBtn);
        waitSimple(4000);
        click(firstMessage);
        switchToIframe(iframe);
        String message = driver.findElement(acceptLink).getText();
        driver.findElement(acceptLink).click();
        pageRouting(routing);
        return message;
    }

    private void pageRouting(String routing) {
        if (routing.equals("Previous"))
            switchToPreviousTab();
        if (routing.equals("Next"))
            switchToNextTab();
    }

    public void confirmPass() {
        click(confirmPassword);
        sendKeys(confirmPassword, "Veri1234");
        click(confirmPassword2);
        sendKeys(confirmPassword2, "Veri1234");
        click(checkBox1);
        click(agree1);
        waitSimple(1000);
        click(checkBox2);
        click(agree2);
        waitSimple(1000);
        click(subBtn);
    }

    private void waitSimple(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

