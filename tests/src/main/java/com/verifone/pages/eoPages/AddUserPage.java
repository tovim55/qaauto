package com.verifone.pages.eoPages;

import com.verifone.pages.BasePage;
import com.verifone.utils.apiClient.createMerchant.CreateMerchantDE;

import org.openqa.selenium.By;

public class AddUserPage extends BasePage {

    private final static String url = "";
    private final static String title = "Sign Up with Verifone Identity Server";


    private By firstNameLoc = By.xpath("//*[@class='form-control i-validator-text input-firstName']");
    private By lastNameLoc = By.xpath("//*[@class='form-control i-validator-text input-lastName']");
    private By emailLoc = By.xpath("//*[@class='form-control i-validator-text input-userEmail']");
    private By errorFirstNameLoc = By.xpath("//*[@class='help-block']");
    private By errorLastNameLoc = By.xpath("(//*[@class='help-block'])[2]");
    private By errorEmailLoc = By.xpath("(//*[@class='help-block'])[3]");
    private By dropdnRoleLoc = By.xpath("//*[@class='dropdownjs']");
    private By dropdnRoleListLoc = By.xpath("//*[@placement='top-left']");
    private By dropdnRoleEOMMLoc = By.xpath("(//*[@value='EO_MERCHANT_MANAGER'])[2]");
    private By dropdnRoleEODALoc = By.xpath("(//*[@value='EO_DEVICE_AND_APP_MANAGER'])[2]");
    private By dropdnRoleEOAdmLoc = By.xpath("(//*[@value='EO_ADMIN'])[2]");
    private By btnCancelLoc = By.id("cancelBtn");
    private By btnSubmitLoc = By.id("createBtn");
    
    private By titleLoc = By.xpath("//*[@class='content-header-top']");
    private By titleDescLoc = By.xpath("//*[@class='content-header-body']");
    private By titlePanelLoc = By.xpath("//*[@class='panel-title pull-left']");
    private By hintFNameLoc = By.xpath("(//*[@class='control-label'])[1]");
    private By hintLNameLoc = By.xpath("(//*[@class='control-label'])[2]");
    private By hintEmailLoc = By.xpath("(//*[@class='control-label'])[3]");
    private By helpEmailLoc = By.xpath("(//*[@class='help-block'])[4]");
    private By titleRoleLoc = By.xpath("//*[@class='panel-title']");
    
    private By msgErrorLoc = By.xpath("//*[@class='message error']");
    

    public AddUserPage() {
        super(url, title);
    }

  public String msgErrorText () throws Exception {
	  if (isExists(msgErrorLoc,5)) {
		  return getText(msgErrorLoc);
		  } else {
			  return "Not found!";
		  }
  }
    
  public String titleText () throws InterruptedException {
	  return getText(titleLoc);
  }
  
  public String titleDescText () throws InterruptedException {
	  return getText(titleDescLoc);
  }
  
  public String titlePanelText () throws InterruptedException {
	  return getText(titlePanelLoc);
  }
  
  public String hintFName () throws Exception {
	  if (isExists(hintFNameLoc,5)) {
		  return getText(hintFNameLoc);
	  } else {
		  return "Not found!";
	  }
  }
   
  public String hintLName () throws Exception {
	  if (isExists(hintLNameLoc,5)) {
	  return getText(hintLNameLoc);
	  } else {
		  return "Not found!";
	  }
  }
  
  public String hintEmail () throws InterruptedException {
	  return getText(hintEmailLoc);
  }
  
  public String helpEmail () throws InterruptedException {
	  return getText(helpEmailLoc);
  }
  
  public String titleRole () throws InterruptedException {
	  return getText(titleRoleLoc);
  }
  
  public String dropdnRoleText () throws InterruptedException {
	  return getText(dropdnRoleLoc);
  }
  
  public String btnCancelLabel () throws InterruptedException {
	  return getText(btnCancelLoc);
  }
  
  public String btnSubmitLabel () throws InterruptedException {
	  return getText(btnSubmitLoc);
  }
  
  public void inputFirstName(String uFName)  throws Exception {
      sendKeys(firstNameLoc, uFName);
  }
  public void inputLastName(String uLName)  throws Exception {
      sendKeys(lastNameLoc, uLName);
  }
  public void inputEmail(String uEmail)  throws Exception {
      sendKeys(emailLoc, uEmail);
  }
//    public String getMerchants() throws InterruptedException {
//        return getTextFromTable(merchantTable);
//    }
//    
    public String errorFirstName () throws InterruptedException {
    	return getText(errorFirstNameLoc);
    } 
    public String errorLastName () throws InterruptedException {
    	return getText(errorLastNameLoc);
    }
    public String errorEmail () throws Exception {
    	int count = 0;
    	while (!isExists(errorEmailLoc,5) | getText(errorEmailLoc).length() <= 1 & count < 5000) {
    		count = count + 1000;
  		  }
    	return getText(errorEmailLoc);
    }
    public void clickLastNameFld () throws InterruptedException {
        click(lastNameLoc);
    } 
    public void clickEmailFld () throws InterruptedException {
        click(emailLoc);
    }
    public void clickDropDn () throws InterruptedException {
        click(dropdnRoleLoc);
    }
    
    public void clickDropDnItem (String itemName) throws Exception {
    	switch (itemName) {
        case "EO Admin":
        	if (isExists(dropdnRoleEOAdmLoc,5)) {
        	click(dropdnRoleEOAdmLoc);
        	}
        	break;
	    case "EO Merchant Manager":
	    	if (isExists(dropdnRoleEOMMLoc,5)) {
	    	click(dropdnRoleEOMMLoc);
	    	}
	    	break;
	    	
	    case "EO Device and App Manager":
	    	if (isExists(dropdnRoleEODALoc,5)) {
	    	click(dropdnRoleEODALoc);
	    	}
	    	break;
		}
    }
    public void clickCancelBtn () throws InterruptedException {
        click(btnCancelLoc);
    }
    public void clickSubmitBtn () throws InterruptedException {
        click(btnSubmitLoc);
    }
}


