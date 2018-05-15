package com.verifone.tests;
import java.sql.Driver;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPageNew {
	
	WebDriver driver;
	public LoginPageNew(WebDriver ldriver) {
		this.driver = ldriver;
		}
	 
	@FindBy(how = How.ID,using ="username")
	@CacheLookup
	public WebElement txtUsername;
	
	
	@FindBy(how = How.ID,using ="password")
	@CacheLookup
	public WebElement txtPassword;

	
	public void loginPageCp(String uid, String pass){
		Actions builder = new Actions(driver);
		Actions typeTextUser = builder.moveToElement(txtUsername).click().sendKeys(txtUsername, uid);
		Actions typeTextPass = builder.moveToElement(txtPassword).click().sendKeys(txtPassword, pass);
		typeTextUser.perform();
		typeTextPass.perform();
        txtPassword.submit();
	}
}
