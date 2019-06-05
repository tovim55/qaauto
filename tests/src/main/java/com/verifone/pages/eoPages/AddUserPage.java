package com.verifone.pages.eoPages;

import com.verifone.pages.BasePage;
import com.verifone.utils.apiClient.createMerchant.CreateMerchantDE;

import org.openqa.selenium.By;
//--------------------------------------------------------------------------
/**
 * This class described all elements and actions can be executed on Add User page.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
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

//--------------------------------------------------------------------------
    /**
     * Method: Get Error message text.
     * Return Error message text as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
  public String msgErrorText () throws Exception {
	  if (isExists(msgErrorLoc,5)) {
		  return getText(msgErrorLoc);
		  } else {
			  return "Not found!";
		  }
  }
//--------------------------------------------------------------------------
    /**
     * Method: Get page Title.
     * Return page Title as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
  public String titleText () throws InterruptedException {
	  return getText(titleLoc);
  }
//--------------------------------------------------------------------------
    /**
     * Method: Get page Description.
     * Return page Description as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
  public String titleDescText () throws InterruptedException {
	  return getText(titleDescLoc);
  }
//--------------------------------------------------------------------------
    /**
     * Method: Get panel Title.
     * Return panel Title as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
  public String titlePanelText () throws InterruptedException {
	  return getText(titlePanelLoc);
  }
//--------------------------------------------------------------------------
    /**
     * Method: Get First Name hint.
     * Return First Name hint as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
  public String hintFName () throws Exception {
	  if (isExists(hintFNameLoc,5)) {
		  return getText(hintFNameLoc);
	  } else {
		  return "Not found!";
	  }
  }
//--------------------------------------------------------------------------
    /**
     * Method: Get Last Name hint.
     * Return Last Name hint as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
  public String hintLName () throws Exception {
	  if (isExists(hintLNameLoc,5)) {
	  return getText(hintLNameLoc);
	  } else {
		  return "Not found!";
	  }
  }
//--------------------------------------------------------------------------
    /**
     * Method: Get Email hint.
     * Return Email hint as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
  public String hintEmail () throws InterruptedException {
	  return getText(hintEmailLoc);
  }
//--------------------------------------------------------------------------
    /**
     * Method: Get Email help.
     * Return Email help as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
  public String helpEmail () throws InterruptedException {
	  return getText(helpEmailLoc);
  }
//--------------------------------------------------------------------------
    /**
     * Method: Get Role section title.
     * Return Role section title as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
  public String titleRole () throws InterruptedException {
	  return getText(titleRoleLoc);
  }
//--------------------------------------------------------------------------
    /**
     * Method: Get Role drop down element.
     * Return Role drop down element as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
  public String dropdnRoleText () throws InterruptedException {
	  return getText(dropdnRoleLoc);
  }
//--------------------------------------------------------------------------
    /**
     * Method: Get Cancel button label.
     * Return Cancel button label as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
  public String btnCancelLabel () throws InterruptedException {
	  return getText(btnCancelLoc);
  }
//--------------------------------------------------------------------------
    /**
     * Method: Get Submit button label.
     * Return Submit button label as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
  public String btnSubmitLabel () throws InterruptedException {
	  return getText(btnSubmitLoc);
  }
//--------------------------------------------------------------------------
    /**
     * Method: Input First Name.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
  public void inputFirstName(String uFName)  throws Exception {
      sendKeys(firstNameLoc, uFName);
  }
//--------------------------------------------------------------------------
    /**
     * Method: Input Last Name.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
  public void inputLastName(String uLName)  throws Exception {
      sendKeys(lastNameLoc, uLName);
  }
//--------------------------------------------------------------------------
    /**
     * Method: Input Email.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
  public void inputEmail(String uEmail)  throws Exception {
      sendKeys(emailLoc, uEmail);
  }
//--------------------------------------------------------------------------
    /**
     * Method: Get First Name validation error.
     * Return First Name validation error as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String errorFirstName () throws InterruptedException {
    	return getText(errorFirstNameLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Last Name validation error.
     * Return Last Name validation error as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String errorLastName () throws InterruptedException {
    	return getText(errorLastNameLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Wait for Email validation error for 5 min.
     * Return Email validation error as String
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String errorEmail () throws Exception {
    	int count = 0;
    	while (!isExists(errorEmailLoc,5) | getText(errorEmailLoc).length() <= 1 & count < 5000) {
    		count = count + 1000;
  		  }
    	return getText(errorEmailLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Title field.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickTitleFld () throws InterruptedException {
        click(titleLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Last Name field.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickLastNameFld () throws InterruptedException {
        click(lastNameLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Email field.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickEmailFld () throws InterruptedException {
        click(emailLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Role drop down element.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickDropDn () throws InterruptedException {
        click(dropdnRoleLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Select item from Role list.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickDropDnItem (String itemName) throws Exception {
    	switch (itemName) {
        case "EO Admin":
        	if (isExists(dropdnRoleEOAdmLoc,5)) {
                Thread.sleep(1000);
        	click(dropdnRoleEOAdmLoc);
        	}
        	break;
	    case "EO Merchant Manager":
	    	if (isExists(dropdnRoleEOMMLoc,5)) {
                Thread.sleep(1000);
	    	click(dropdnRoleEOMMLoc);
	    	}
	    	break;
	    	
	    case "EO Device and App Manager":
	    	if (isExists(dropdnRoleEODALoc,5)) {
                Thread.sleep(1000);
	    	click(dropdnRoleEODALoc);
	    	}
	    	break;
		}
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Cancel button.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickCancelBtn () throws InterruptedException {
        click(btnCancelLoc);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Click on Submit button.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickSubmitBtn () throws InterruptedException {
        click(btnSubmitLoc);
    }
}


