package com.verifone.pages.eoPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
//--------------------------------------------------------------------------
/**
 * This class described all elements and actions can be executed on Users page.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
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
//--------------------------------------------------------------------------
	/**
	 * Method: Search User by parameter into Users table.
	 * Return Row Number
	 * @authors Yana Fridman
	 */
//--------------------------------------------------------------------------
    public int dataRow(String searchData) throws InterruptedException {
    	int row = 0;
    	int i;
    	for (i = 1; i<11; i++) {
    		By rowLoc = By.xpath("(//*[@class='vui-datagrid-body-row '])[" + i + "]");
    		if (getText(rowLoc).contains(searchData)) {
    			row = i;
    			break;
    		}
    	}
    	return row;
    }
//--------------------------------------------------------------------------
	/**
	 * Method: Search for EO Admin into Users table.
	 * Return EO Admin Email as string
	 * @authors Yana Fridman
	 */
//--------------------------------------------------------------------------
	public String EOAdminEmail() throws InterruptedException {
		int row = 0;
		int i;
		String D = "";
		for (i = 1; i<11; i++) {
			By rowLoc = By.xpath("(//*[@class='vui-datagrid-body-row '])[" + i + "]");
			if (getText(rowLoc).contains("EO Admin")) {
				D = getText(rowLoc);
				break;
			}
		}
		i = D.indexOf("\n");
		D = D.substring(i+1);
		i = D.indexOf("\n");
		D = D.substring(0,i);
		return D;
	}
//--------------------------------------------------------------------------
	/**
	 * Method: Search for Pending EO Admin into Users table.
	 * Return Row Number
	 * @authors Yana Fridman
	 */
//--------------------------------------------------------------------------
    public int pendingEOAdminRow() throws InterruptedException {
    	int row = 0;
    	int i;
    	for (i = 1; i<11; i++) {
    		By rowLoc = By.xpath("(//*[@class='vui-datagrid-body-row '])[" + i + "]");
    		if (getText(rowLoc).contains("EO Admin") & getText(rowLoc).contains("Pending")) {
    			row = i;
    			break;
    		}
    	}
    	return row;
    }
//--------------------------------------------------------------------------
	/**
	 * Method: Search for Pending Merchant Manager into Users table.
	 * Return Row Number
	 * @authors Yana Fridman
	 */
//--------------------------------------------------------------------------
    public int pendingMerchantManRow() throws InterruptedException {
    	int row = 0;
    	int i;
    	for (i = 1; i<11; i++) {
    		By rowLoc = By.xpath("(//*[@class='vui-datagrid-body-row '])[" + i + "]");
    		if (getText(rowLoc).contains("EO Merchant Manager") & getText(rowLoc).contains("Pending")) {
    			row = i;
    			break;
    		}
    	}
    	return row;
    }
//--------------------------------------------------------------------------
	/**
	 * Method: Search for Pending Device and Application Manager into Users table.
	 * Return Row Number
	 * @authors Yana Fridman
	 */
//--------------------------------------------------------------------------
    public int pendingDevAppManRow() throws InterruptedException {
    	int row = 0;
    	int i;
    	for (i = 1; i<11; i++) {
    		By rowLoc = By.xpath("(//*[@class='vui-datagrid-body-row '])[" + i + "]");
    		if (getText(rowLoc).contains("EO Device and App Manager") & getText(rowLoc).contains("Pending")) {
    			row = i;
    			break;
    		}
    	}
    	
    	return row;
    }
//--------------------------------------------------------------------------
	/**
	 * Method: Search for Active Device and Application Manager into Users table.
	 * Return Row Number
	 * @authors Yana Fridman
	 */
//--------------------------------------------------------------------------
    public int activeDevAppManRow() throws InterruptedException {
    	int row = 0;
    	int i;
    	for (i = 1; i<11; i++) {
    		By rowLoc = By.xpath("(//*[@class='vui-datagrid-body-row '])[" + i + "]");
    		if (getText(rowLoc).contains("EO Device and App Manager") & getText(rowLoc).contains("Active")) {
    			row = i;
    			break;
    		}
    	}
    	return row;
    }
//--------------------------------------------------------------------------
	/**
	 * Method: Search for Disabled Device and Application Manager into Users table.
	 * Return Row Number
	 * @authors Yana Fridman
	 */
//--------------------------------------------------------------------------
    public int disableDevAppManRow() throws InterruptedException {
    	int row = 0;
    	int i;
    	for (i = 1; i<11; i++) {
    		By rowLoc = By.xpath("(//*[@class='vui-datagrid-body-row '])[" + i + "]");
    		if (getText(rowLoc).contains("EO Device and App Manager") & getText(rowLoc).contains("Disabled")) {
    			row = i;
    			break;
    		}
    	}
    	return row;
    }
//--------------------------------------------------------------------------
	/**
	 * Method: Search for Active EO Admin into Users table.
	 * Return Row Number
	 * @authors Yana Fridman
	 */
//--------------------------------------------------------------------------
    public int activeEOAdminRow() throws InterruptedException {
    	int row = 0;
    	int i;
    	for (i = 1; i<11; i++) {
    		By rowLoc = By.xpath("(//*[@class='vui-datagrid-body-row '])[" + i + "]");
    		if (getText(rowLoc).contains("EO Admin") & getText(rowLoc).contains("Active")) {
    			row = i;
    			break;
    		}
    	}
    	return row;
    }
//--------------------------------------------------------------------------
	/**
	 * Method: Search for Disable EO Admin into Users table.
	 * Return Row Number
	 * @authors Yana Fridman
	 */
//--------------------------------------------------------------------------
    public int disableEOAdminRow() throws InterruptedException {
    	int row = 0;
    	int i;
    	for (i = 1; i<11; i++) {
    		By rowLoc = By.xpath("(//*[@class='vui-datagrid-body-row '])[" + i + "]");
    		if (getText(rowLoc).contains("EO Admin") & getText(rowLoc).contains("Disabled")) {
    			row = i;
    			break;
    		}
    	}
    	return row;
    }
//--------------------------------------------------------------------------
	/**
	 * Method: Search for Active Merchant Manager into Users table.
	 * Return Row Number
	 * @authors Yana Fridman
	 */
//--------------------------------------------------------------------------
    public int activeEOMerchantManRow() throws InterruptedException {
    	int row = 0;
    	int i;
    	for (i = 1; i<11; i++) {
    		By rowLoc = By.xpath("(//*[@class='vui-datagrid-body-row '])[" + i + "]");
    		if (getText(rowLoc).contains("EO Merchant Manager") & getText(rowLoc).contains("Active")) {
    			row = i;
    			break;
    		}
    	}
    	return row;
    }
//--------------------------------------------------------------------------
	/**
	 * Method: Search for Active Merchant Manager by additional parameter into Users table.
	 * Return Row Number
	 * @authors Yana Fridman
	 */
//--------------------------------------------------------------------------
	public int activeEOMerchantManRow_ParameterSearch(String param) throws InterruptedException {
		int row = 0;
		int i;
		for (i = 1; i<11; i++) {
			By rowLoc = By.xpath("(//*[@class='vui-datagrid-body-row '])[" + i + "]");
			if (getText(rowLoc).contains("EO Merchant Manager") & getText(rowLoc).contains("Active") & getText(rowLoc).contains(param)) {
				row = i;
				break;
			}
		}
		return row;
	}
//--------------------------------------------------------------------------
	/**
	 * Method: Search for Disable Merchant Manager into Users table.
	 * Return Row Number
	 * @authors Yana Fridman
	 */
//--------------------------------------------------------------------------
    public int disableEOMerchantManRow() throws InterruptedException {
    	int row = 0;
    	int i;
    	for (i = 1; i<11; i++) {
    		By rowLoc = By.xpath("(//*[@class='vui-datagrid-body-row '])[" + i + "]");
    		if (getText(rowLoc).contains("EO Merchant Manager") & getText(rowLoc).contains("Disabled")) {
    			row = i;
    			break;
    		}
    	}
    	return row;
    }
//--------------------------------------------------------------------------
	/**
	 * Method: Search for Disable Merchant Manager by additional parameter into Users table.
	 * Return Row Number
	 * @authors Yana Fridman
	 */
//--------------------------------------------------------------------------
	public int disableEOMerchantManRow_ParameterSearch(String param) throws InterruptedException {
		int row = 0;
		int i;
		for (i = 1; i<11; i++) {
			By rowLoc = By.xpath("(//*[@class='vui-datagrid-body-row '])[" + i + "]");
			if (getText(rowLoc).contains("EO Merchant Manager") & getText(rowLoc).contains("Disabled") & getText(rowLoc).contains(param)) {
				row = i;
				break;
			}
		}
		return row;
	}
//--------------------------------------------------------------------------
	/**
	 * Method: Click on Row in Users table.
	 * @authors Yana Fridman
	 */
//--------------------------------------------------------------------------
    public void clickOnRow(int r) throws InterruptedException {
    		By rowLoc = By.xpath("(//*[@class='vui-datagrid-body-row '])[" + r + "]");
    		click(rowLoc);
    }
//--------------------------------------------------------------------------
	/**
	 * Method: Get Users table content.
	 * Return Users table content as string.
	 * @authors Yana Fridman
	 */
//--------------------------------------------------------------------------
    public String getUsers() throws InterruptedException {
    	return getText(tblUsersLoc);
    }
//--------------------------------------------------------------------------
	/**
	 * Method: Get page Title.
	 * Return page Title as string.
	 * @authors Yana Fridman
	 */
//--------------------------------------------------------------------------
    public String titleUsers () throws InterruptedException {
    	return getText(titleUsersLoc);
    }
//--------------------------------------------------------------------------
	/**
	 * Method: Click on Add User button.
	 * @authors Yana Fridman
	 */
//--------------------------------------------------------------------------
    public void clickAddUserBtn () throws InterruptedException {
        click(btnAddUserLoc);
    }
//--------------------------------------------------------------------------
	/**
	 * Method: Get Users table content.
	 * Return Users table content as string.
	 * @authors Yana Fridman
	 */
//--------------------------------------------------------------------------
    public String tblUsersText () throws InterruptedException {
    	return getTextFromTable(tblUsersLoc);
    }
//--------------------------------------------------------------------------
	/**
	 * Method: Get Email value from Forst line into Users table.
	 * Return Email as string.
	 * @authors Yana Fridman
	 */
//--------------------------------------------------------------------------
    public String tblUsersFirstLineEmailText () throws InterruptedException {
    	WebElement element = getWebElement(tblUsersFirstLineEmailLoc, 30, ExpectedConditions.visibilityOfElementLocated(tblUsersFirstLineEmailLoc));
    	return element.getText(); 
    }
//--------------------------------------------------------------------------
	/**
	 * Method: Check if table Users displayed.
	 * Return True if displayed.
	 * @authors Yana Fridman
	 */
//--------------------------------------------------------------------------
    public boolean tblUsersExists () throws Exception {
    	return isExists(tblUsersLoc, 10);
    }
//--------------------------------------------------------------------------
	/**
	 * Method: Check if Add User button displayed.
	 * Return True if displayed.
	 * @authors Yana Fridman
	 */
//--------------------------------------------------------------------------
    public boolean btnAddUserExists () throws Exception {
    	return isExists(btnAddUserLoc, 5);
    }
//--------------------------------------------------------------------------
	/**
	 * Method: Check if Pager element displayed.
	 * Return True if displayed.
	 * @authors Yana Fridman
	 */
//--------------------------------------------------------------------------
    public boolean pgUsersExists () throws Exception {
    	if (isExists(pgarrowlUsersLoc,5) & isExists(pglimitUsersLoc, 5) & isExists(pglimitUsersLoc, 5)) {
    		return true;
    	} else {
    		return false;
    	}
    }
}

