package com.verifone.pages.eoPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;

public class EditBusinessPage extends BasePage {

    private final static String url = "";
    private final static String title = "";

    private By titleLoc = By.xpath("//*[@class='content-header']");
    private By nameLoc = By.name("name");
    private By indCodeLoc = By.name("industryCode");
    private By midLoc = By.xpath("//*[@class='control-value']");
    private By countryLoc = By.name("countryCode");
    private By streetLoc = By.xpath("//*[@class='form-control i-validator-text input-addressLineOne']");
    private By cityLoc = By.name("city");
    private By stateLoc = By.name("state");
    private By zipCodeLoc = By.name("zipCode");
    private By phoneCodeLoc = By.name("phoneCountryCode");
    private By phoneNumberLoc = By.name("phoneNumber");
    private By mailLoc = By.name("mail");

    private By errorNameLoc = By.xpath("//*[@class='help-block']");
    private By errorIndCodeLoc = By.xpath("(//*[@class='help-block'])[2]");
    private By errorStreetLoc = By.xpath("(//*[@class='help-block'])[4]");
    private By errorCityLoc = By.xpath("(//*[@class='help-block'])[6]");
    private By errorStateLoc = By.xpath("(//*[@class='help-block'])[7]");
    private By errorZIPCodeLoc = By.xpath("(//*[@class='help-block'])[8]");
    private By errorPhoneCodeLoc = By.xpath("(//*[@class='help-block'])[9]");
    private By errorPhoneNumberLoc = By.xpath("(//*[@class='help-block'])[10]");
    private By errorMailLoc = By.xpath("(//*[@class='help-block'])[12]");

    private By btnCancelLoc = By.xpath("//*[@class='btn btn-primary cancel-button']");
    private By btnSaveLoc = By.xpath("//*[@class='btn btn-primary btn-raised submit-button']");

    private By btnCancelMsgYes = By.id("modalAffirmId");


    public EditBusinessPage() {
        super(url, title);
    }

    public String getEmail() throws InterruptedException {
        return BasePage.driver.findElement(mailLoc).getAttribute("value");
    }

    public String getTitle() throws InterruptedException {
        return getText(titleLoc);
    }

//    public String getfirstName() throws InterruptedException {
//        return BasePage.driver.findElement(firstNameLoc).getAttribute("value");
//    }

    public String getName() throws InterruptedException {
        return BasePage.driver.findElement(nameLoc).getAttribute("value");
//        return getText(nameLoc);
    }

    public String getIndCode() throws InterruptedException {
        return BasePage.driver.findElement(indCodeLoc).getAttribute("value");
    }
    public String getMID() throws InterruptedException {
        return getText(midLoc);
    }
    public String getCountry() throws InterruptedException {
        return getText(countryLoc);
    }
    public String getStreet() throws InterruptedException {
        return BasePage.driver.findElement(streetLoc).getAttribute("value");
    }
    public String getCity() throws InterruptedException {
        return BasePage.driver.findElement(cityLoc).getAttribute("value");
    }
    public String getState() throws InterruptedException {
        return BasePage.driver.findElement(stateLoc).getAttribute("value");
    }
    public String getZipCode() throws InterruptedException {
        return BasePage.driver.findElement(zipCodeLoc).getAttribute("value");
    }
    public String getPhoneCode() throws InterruptedException {
        return BasePage.driver.findElement(phoneCodeLoc).getAttribute("value");
    }
    public String getPhoneNumber() throws InterruptedException {
        return BasePage.driver.findElement(phoneNumberLoc).getAttribute("value");
    }


    public void clickBtnCancel() throws InterruptedException {
        click(btnCancelLoc);
    }

    public void clickBtnSave() throws InterruptedException {
        click(btnSaveLoc);
    }

    public void clickFldIndCode() throws InterruptedException {
        click(indCodeLoc);
    }

    public void clickFldName() throws InterruptedException {
        click(nameLoc);
    }

    public void inputName(String bName)  throws Exception {
        sendKeys(nameLoc, bName);
    }

    public void inputIndCode(String bIndCode)  throws Exception {
        sendKeys(indCodeLoc, bIndCode);
    }

    public void updateCountry(String uCountry)  throws Exception {
        select(countryLoc, uCountry);
    }

    public void inputStreet(String bStreet)  throws Exception {
        sendKeys(streetLoc, bStreet);
    }

    public void inputCity(String bCity)  throws Exception {
        sendKeys(cityLoc, bCity);
    }

    public void inputState(String bState)  throws Exception {
        sendKeys(stateLoc, bState);
    }

    public void inputPostalCode(String bPostalCode)  throws Exception {
        sendKeys(zipCodeLoc, bPostalCode);
    }

    public void inputPhoneCode(String bPhoneCode)  throws Exception {
        sendKeys(phoneCodeLoc, bPhoneCode);
    }

    public void inputPhoneNumber(String bPhoneNumber)  throws Exception {
        sendKeys(phoneNumberLoc, bPhoneNumber);
    }

    public void inputMail(String bMail)  throws Exception {
        sendKeys(mailLoc, bMail);
    }

    public String errorName () throws InterruptedException {
        return getText(errorNameLoc);
    }

    public String errorIndCode () throws InterruptedException {
        return getText(errorIndCodeLoc);
    }
    public String errorStreet () throws InterruptedException {
        return getText(errorStreetLoc);
    }
    public String errorCity () throws InterruptedException {
        return getText(errorCityLoc);
    }
    public String errorState () throws InterruptedException {
        return getText(errorStateLoc);
    }
    public String errorZIPCode () throws InterruptedException {
        return getText(errorZIPCodeLoc);
    }
    public String errorPhoneCode () throws InterruptedException {
        return getText(errorPhoneCodeLoc);
    }
    public String errorPhoneNumber () throws InterruptedException {
        return getText(errorPhoneNumberLoc);
    }
    public String errorMail () throws InterruptedException {
        return getText(errorMailLoc);
    }
    public boolean fldMIDEnabled () throws Exception {
        return isEnabled(midLoc, 20);
    }

    public void clickCancelMsgYes() throws Exception {
        if (isExists(btnCancelMsgYes,40)) {
            click(btnCancelMsgYes);
        }
    }
}


