package com.verifone.pages.eoPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditUserPage extends BasePage {

    private final static String url = "";
    private final static String title = "";

    private By titleLoc = By.xpath("//*[@class='content-header']");
    private By firstNameLoc = By.name("firstName");
    private By lastNameLoc = By.name("lastName");
    private By emailLoc = By.xpath("//*[@class='control-value']");
//    private By firstNameLoc = By.xpath("//*[@class='form-group label-floating required']");
    private By errorFirstNameLoc = By.xpath("//*[@class='help-block']");
    private By errorLastNameLoc = By.xpath("(//*[@class='help-block'])[2]");

    private By btnCancelLoc = By.id("cancelBtn");
    private By btnSaveLoc = By.id("createBtn");


    public EditUserPage() {
        super(url, title);
    }

    public String getEmail() throws InterruptedException {
        return getText(emailLoc);
    }

    public String getTitle() throws InterruptedException {
        return getText(titleLoc);
    }

    public String getfirstName() throws InterruptedException {
        return BasePage.driver.findElement(firstNameLoc).getAttribute("value");
    }

    public String getlastName() throws InterruptedException {
        return BasePage.driver.findElement(lastNameLoc).getAttribute("value");
    }
    


    public void clickBtnCancel() throws InterruptedException {
        click(btnCancelLoc);
    }

    public void clickBtnSave() throws InterruptedException {
        click(btnSaveLoc);
    }

    public void updateFirstName(String uFName)  throws Exception {
        sendKeysNoClear(firstNameLoc, uFName);
    }

    public void inputFirstName(String uFName)  throws Exception {
        sendKeys(firstNameLoc, uFName);
    }

    public void updateLastName(String uLName)  throws Exception {
        sendKeysNoClear(lastNameLoc, uLName);
    }

    public void inputLastName(String uLName)  throws Exception {
        sendKeys(lastNameLoc, uLName);
    }

    public void clickFirstNameFld () throws InterruptedException {
        click(firstNameLoc);
    }

    public void clickLastNameFld () throws InterruptedException {
        click(lastNameLoc);
    }

    public String errorFirstName () throws InterruptedException {
        return getText(errorFirstNameLoc);
    }

    public String errorLastName () throws InterruptedException {
        return getText(errorLastNameLoc);
    }

}


