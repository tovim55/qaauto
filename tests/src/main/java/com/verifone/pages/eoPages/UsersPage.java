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
    
    public int dataRow(String searchData) throws InterruptedException {
    	int row = 1000;
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
    
    public int pendingEOAdminRow() throws InterruptedException {
    	int row = 1000;
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
    
    public int pendingMerchantManRow() throws InterruptedException {
    	int row = 1000;
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
    
    public int pendingDevAppManRow() throws InterruptedException {
    	int row = 1000;
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
    
    public int activeDevAppManRow() throws InterruptedException {
    	int row = 1000;
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
    
    public int disableDevAppManRow() throws InterruptedException {
    	int row = 1000;
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
    
    public int activeEOAdminRow() throws InterruptedException {
    	int row = 1000;
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
    
    public int disableEOAdminRow() throws InterruptedException {
    	int row = 1000;
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
    
    public int activeEOMerchantManRow() throws InterruptedException {
    	int row = 1000;
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

	public int activeEOMerchantManRow_ParameterSearch(String param) throws InterruptedException {
		int row = 1000;
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
    
    public int disableEOMerchantManRow() throws InterruptedException {
    	int row = 1000;
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
	public int disableEOMerchantManRow_ParameterSearch(String param) throws InterruptedException {
		int row = 1000;
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
    public void clickOnRow(int r) throws InterruptedException {
    		By rowLoc = By.xpath("(//*[@class='vui-datagrid-body-row '])[" + r + "]");
    		click(rowLoc);
    }

    public String getUsers() throws InterruptedException {
//        return getTextFromTable(tblUsersLoc);
    	return getText(tblUsersLoc);
    }
    
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

