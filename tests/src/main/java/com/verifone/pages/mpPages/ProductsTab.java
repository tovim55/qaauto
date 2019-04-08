package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
//--------------------------------------------------------------------------

/**
 * This class described all elements and actions can be executed on EO Home page.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class ProductsTab extends BasePage {

    private final static String url = "";
    private final static String title = "Segment Groups";

    private By menuSegmentGroupsLoc = By.xpath("//a[text()='Segment Groups']");
    private By titleSegmentGroupsLoc = By.xpath("//div[text()='Segment Groups']");
    private By btnCreateSegmentGroupsLoc = By.xpath("//*[@data-auto-action='redirect:create-company-group']");
    private By titleSegmentGroupLoc = By.xpath("//th[text()='Segment group']");
    private By btnCreateSegmentGroupLoc = By.xpath("//span[text()='Create Segment Group']");
    private By tblSegmentGroupsLoc = By.xpath("//*[@style='border-collapse: collapse; border-spacing: 0px; width: 100%;']");
//    private By menuContextEditSegmentLoc = By.xpath("(//a[text()='Edit'])[5]");

    private By dlgDeleteSegmentGroupLoc = By.xpath("//*[@role='dialog']");
    private By dlgDeleteSegmentGroupBtnYesLoc = By.xpath("//span[text()='Yes']");


    private By msgConfirmationLoc = By.xpath("//*[@class='adb-local_alert--content']");
    private By msgConfirmationLoc1 = By.xpath("//*[@class='feedbackPanelINFO']");

    public ProductsTab() {
        super(url, title);
    }

//--------------------------------------------------------------------------
    /**
     * Method: Click on Segment Groups menu.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickMenuSegmentGroups () throws InterruptedException {

        click(menuSegmentGroupsLoc);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Title 'Segment Groups' exists.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean existsTitleSegmentGroups () throws Exception {
        return isExists(titleSegmentGroupsLoc, 20);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Button 'Create Segment Group' exists.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean existsBtnCreateSegmentGroups () throws Exception {
        return isExists(btnCreateSegmentGroupsLoc, 20);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Button 'Create Segment Group' exists.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean existsTitleSegmentGroup () throws Exception {
        return isExists(titleSegmentGroupLoc, 20);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Click on Create Segment Groups button.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickBtnCreateSegmentGroup () throws InterruptedException {

        click(btnCreateSegmentGroupLoc);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Get Message Text.
     * Return Text as string
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String msgConfirmationText () throws InterruptedException {
        return getText(msgConfirmationLoc);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Get Message Text.
     * Return Text as string
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String msgConfirmationText1 () throws InterruptedException {
        return getText(msgConfirmationLoc1);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Search for Segment Group by Name.
     * Return row number
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public int getTblRowSegmentGroups(String name) throws InterruptedException {
        System.out.println(getRowNumberFromTable(tblSegmentGroupsLoc, name));
        return getRowNumberFromTable(tblSegmentGroupsLoc, name);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Get Segment Groups table content as Text.
     * Return string
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String getTblSegmentGroupsText() throws InterruptedException {

        return getTextFromTable(tblSegmentGroupsLoc);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Click on Segment Groups Menu Trigger.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickMenuTriggerSegmentGroup (int row) throws InterruptedException {
        String a = "/div/div[2]/div/div[2]/div[3]/div[4]/div/div/div/table/tbody/tr[" + row + "]/td[2]/div/menu/button/span/i[2]";
        By menuTriggerSegmentGroupLoc = By.xpath("//*[@id=\"main\"]"+a);
        click(menuTriggerSegmentGroupLoc);
    }
//--------------------------------------------------------------------------

//--------------------------------------------------------------------------
    /**
     * Method: Click on Context Segment Menu: Edit.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickMenuContextEditSegment (String ind) throws InterruptedException {
        String a = "(//a[text()='Edit'])";
        By menuContextEditSegmentLoc = By.xpath(a+"["+ind+"]");

        click(menuContextEditSegmentLoc);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Click on Context Segment Menu: Delete.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickMenuContextDeleteSegment (String ind) throws InterruptedException {
        String a = "(//a[text()='Delete'])";
        By menuContextDeleteSegmentLoc = By.xpath(a+"["+ind+"]");

        click(menuContextDeleteSegmentLoc);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Dialog window 'Delete Segment Groups' exists.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean existsDlgDeleteSegmentGroup () throws Exception {
        return isExists(dlgDeleteSegmentGroupLoc, 20);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Click on Yes button on 'Delete Segment Groups' dialog
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickDlgDeleteSegmentGroupBtnYes () throws InterruptedException {

        click(dlgDeleteSegmentGroupBtnYesLoc);
    }
//--------------------------------------------------------------------------
}

