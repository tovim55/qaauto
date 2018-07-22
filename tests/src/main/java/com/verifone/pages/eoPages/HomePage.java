package com.verifone.pages.eoPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    private final static String url = "";
    private final static String title = "Sign Up with Verifone Identity Server";


    private By merchantTable = By.className("vui-table ");
    private By merchantBtn = By.id("merchants");
    private By headerMenuLoc = By.xpath("//*[@class='header-menu__item header-menu__item--last-child']");
    private By userMenuLoc = By.id("users");

    public HomePage() {
        super(url, title);
    }

    public String getMerchants() throws InterruptedException {
        return getTextFromTable(merchantTable);
    }
    
    
    public void clickHeaderMenu () throws InterruptedException {
    	boolean f1 = false;
    	int t = 0;
    	WebDriverWait wait = new WebDriverWait(BasePage.driver, 20);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='header-menu__item header-menu__item--last-child']")));
    	Thread.sleep(3000);
    	while (f1 == false & t < 20000){
    		f1 = BasePage.driver.findElement(headerMenuLoc).isEnabled();
    		t=t+1000;
    	}
        click(headerMenuLoc);
    } 
    public void clickUserMenu () throws InterruptedException {
    	
        click(userMenuLoc);
    } 
    public boolean menuUserExists () throws Exception {
    	return isExists(userMenuLoc, 10);
    }
}
