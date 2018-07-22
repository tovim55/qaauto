package com.verifone.pages.eoPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UsersPage extends BasePage {

    private final static String url = "";
    private final static String title = "Sign Up with Verifone Identity Server";


    private By titleUsersLoc = By.xpath("//*[@class='section-title']");
    private By btnAddUserLoc = By.xpath("//*[@class='btn btn-raised btn-primary section-action']");
    private By tblUsersLoc = By.xpath("//*[@class='vui-datagrid-body-elements']");
    private By tblUsersFirstLineEmailLoc = By.xpath("(//*[@class='vui-datagrid-body-row-column '])[2]");
    private By pgarrowlUsersLoc = By.xpath("//*[@class='navigation']");
    private By pglimitUsersLoc = By.xpath("//*[@class='limit-wrapper']");
    private By pgintervalUsersLoc = By.xpath("//*[@class='interval ']");


    public UsersPage() {
        super(url, title);
    }

//    public String getMerchants() throws InterruptedException {
//        return getTextFromTable(merchantTable);
//    }
//    
    public String titleUsers () throws InterruptedException {
    	return getText(titleUsersLoc);
    } 
    public void clickAddUserBtn () throws InterruptedException {
        click(btnAddUserLoc);
    } 
    public String tblUsersText () throws InterruptedException {
    	return getTextFromTable(tblUsersLoc);
    }
    public String tblUsersFirstLineEmailText () throws InterruptedException {
    	WebElement element = getWebElement(tblUsersFirstLineEmailLoc, 30, ExpectedConditions.visibilityOfElementLocated(tblUsersFirstLineEmailLoc));
    	return element.getText(); 
    }
    public boolean tblUsersExists () throws Exception {
    	return isExists(tblUsersLoc, 10);
    } 
    public boolean btnAddUserExists () throws Exception {
    	return isExists(btnAddUserLoc, 5);
    } 
    public boolean pgUsersExists () throws Exception {
    	if (isExists(pgarrowlUsersLoc,5) & isExists(pglimitUsersLoc, 5) & isExists(pglimitUsersLoc, 5)) {
    		return true;
    	} else {
    		return false;
    	}
    }
}

