package com.verifone.pages.eoPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
//--------------------------------------------------------------------------
/**
 * This class described all elements and actions can be executed on Edit Business page.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
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
    private By webSiteLoc = By.name("site");

    private By errorNameLoc = By.xpath("//*[@class='help-block']");
    private By errorIndCodeLoc = By.xpath("(//*[@class='help-block'])[2]");
    private By errorStreetLoc = By.xpath("(//*[@class='help-block'])[4]");
    private By errorCityLoc = By.xpath("(//*[@class='help-block'])[6]");
    private By errorStateLoc = By.xpath("(//*[@class='help-block'])[7]");
    private By errorZIPCodeLoc = By.xpath("(//*[@class='help-block'])[8]");
    private By errorPhoneCodeLoc = By.xpath("(//*[@class='help-block'])[9]");
    private By errorPhoneNumberLoc = By.xpath("(//*[@class='help-block'])[10]");
    private By errorWebSiteLoc = By.xpath("(//*[@class='help-block'])[11]");
    private By errorMailLoc = By.xpath("(//*[@class='help-block'])[12]");

    private By btnCancelLoc = By.xpath("//*[@class='btn btn-primary cancel-button']");
    private By btnSaveLoc = By.xpath("//*[@class='btn btn-primary btn-raised submit-button']");

    private By btnCancelMsgYes = By.id("modalAffirmId");


    public EditBusinessPage() {
        super(url, title);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Email value.
     * Return Email value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getEmail() throws InterruptedException {
        return driver.findElement(mailLoc).getAttribute("value");
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get page Title.
     * Return page Title as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getTitle() throws InterruptedException {
        return getText(titleLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Name value.
     * Return Name value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getName() throws InterruptedException {
        return driver.findElement(nameLoc).getAttribute("value");
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Industry Code value.
     * Return ndustry Code value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getIndCode() throws InterruptedException {
        return driver.findElement(indCodeLoc).getAttribute("value");
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get MID value.
     * Return MID value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getMID() throws InterruptedException {
        return getText(midLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Country value.
     * Return Country value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getCountry() throws InterruptedException {
        return getText(countryLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Street value.
     * Return Street value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getStreet() throws InterruptedException {
        return driver.findElement(streetLoc).getAttribute("value");
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get City value.
     * Return City value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getCity() throws InterruptedException {
        return driver.findElement(cityLoc).getAttribute("value");
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get State value.
     * Return State value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getState() throws InterruptedException {
        return driver.findElement(stateLoc).getAttribute("value");
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get ZIP Code value.
     * Return ZIP Code value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getZipCode() throws InterruptedException {
        return driver.findElement(zipCodeLoc).getAttribute("value");
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Phone Code value.
     * Return Phone Code value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getPhoneCode() throws InterruptedException {
        return driver.findElement(phoneCodeLoc).getAttribute("value");
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Phone Number value.
     * Return Phone Number value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getPhoneNumber() throws InterruptedException {
        return driver.findElement(phoneNumberLoc).getAttribute("value");
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Website value.
     * Return Website value as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getWebSite() throws InterruptedException {
        return driver.findElement(webSiteLoc).getAttribute("value");
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Cancel button.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickBtnCancel() throws InterruptedException {
        click(btnCancelLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Save button.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickBtnSave() throws InterruptedException {
        click(btnSaveLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Industry Code field.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickFldIndCode() throws InterruptedException {
        click(indCodeLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Name field.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickFldName() throws InterruptedException {
        click(nameLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Street field.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickFldStreet() throws InterruptedException {
        click(streetLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Input Name.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void inputName(String bName)  throws Exception {
        sendKeys(nameLoc, bName);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Input Industry Code.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void inputIndCode(String bIndCode)  throws Exception {
        sendKeys(indCodeLoc, bIndCode);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Input Country.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void updateCountry(String uCountry)  throws Exception {
        select(countryLoc, uCountry);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Input Street.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void inputStreet(String bStreet)  throws Exception {
        sendKeys(streetLoc, bStreet);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Input City.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void inputCity(String bCity)  throws Exception {
        sendKeys(cityLoc, bCity);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Input State.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void inputState(String bState)  throws Exception {
        sendKeys(stateLoc, bState);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Input ZIP Code.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void inputPostalCode(String bPostalCode)  throws Exception {
        sendKeys(zipCodeLoc, bPostalCode);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Input Phone Code.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void inputPhoneCode(String bPhoneCode)  throws Exception {
        sendKeys(phoneCodeLoc, bPhoneCode);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Input Phone Number.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void inputPhoneNumber(String bPhoneNumber)  throws Exception {
        sendKeys(phoneNumberLoc, bPhoneNumber);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Input Website.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void inputWebSite(String bWebSite)  throws Exception {
        sendKeys(webSiteLoc, bWebSite);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Input Email.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void inputMail(String bMail)  throws Exception {
        sendKeys(mailLoc, bMail);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Name validation error.
     * Return Name validation error as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String errorName () throws InterruptedException {
        return getText(errorNameLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Industry Code validation error.
     * Return Industry Code validation error as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String errorIndCode () throws InterruptedException {
        return getText(errorIndCodeLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Street validation error.
     * Return Street validation error as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String errorStreet () throws InterruptedException {
        return getText(errorStreetLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get City validation error.
     * Return City validation error as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String errorCity () throws InterruptedException {
        return getText(errorCityLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get State validation error.
     * Return State validation error as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String errorState () throws InterruptedException {
        return getText(errorStateLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get ZIP validation error.
     * Return ZIP validation error as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String errorZIPCode () throws InterruptedException {
        return getText(errorZIPCodeLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Phone Code validation error.
     * Return Phone Code validation error as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String errorPhoneCode () throws InterruptedException {
        return getText(errorPhoneCodeLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Phone Number validation error.
     * Return Phone Number validation error as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String errorPhoneNumber () throws InterruptedException {
        return getText(errorPhoneNumberLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Email validation error.
     * Return Email validation error as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String errorMail () throws InterruptedException {
        return getText(errorMailLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Website validation error.
     * Return Website validation error as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String errorWebSite () throws InterruptedException {
        return getText(errorWebSiteLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Check if MID field enabled.
     * Return True if enabled
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean fldMIDEnabled () throws Exception {
        return isEnabled(midLoc, 20);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click Yes on message dialog window.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickCancelMsgYes() throws Exception {
        if (isExists(btnCancelMsgYes,40)) {
            click(btnCancelMsgYes);
        }
    }
}


