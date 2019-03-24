package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
//--------------------------------------------------------------------------

/**
 * This class described all elements and actions can be executed on EO Home page.
 * @authors Yana Fridman
 */
//--------------------------------------------------------------------------
public class CreateSegmentGroupPage extends BasePage {

    private final static String url = "";
    private final static String title = "Create Segment Groups";

    private By fldSegmentGroupNameLoc = By.name("name");
    private By titleCreateSegmentGroupLoc = By.xpath("//div[text()='Create Segment Group']");
    private By btnSaveLoc = By.xpath("//span[text()='Save']");

    private By msgConfirmationLoc = By.xpath("//*[@class='adb-local_alert--content']");

    public CreateSegmentGroupPage() {
        super(url, title);
    }

//--------------------------------------------------------------------------
    /**
     * Method: Input Segment Group Name.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void inputSegmentGroupName(String SName)  throws Exception {
        sendKeys(fldSegmentGroupNameLoc, SName);
    }
//--------------------------------------------------------------------------
    /**
     * Method: Title 'Create Segment Groups' exists.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public boolean existsTitleCreateSegmentGroup () throws Exception {
        return isExists(titleCreateSegmentGroupLoc, 20);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Click on Save button.
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public void clickBtnSave () throws InterruptedException {

        click(btnSaveLoc);
    }
//--------------------------------------------------------------------------
//--------------------------------------------------------------------------
    /**
     * Method: Get Message Text.
     * Return Text as string
     * @authors Yana Fridman
     */
//--------------------------------------------------------------------------
    public String msgConfirmationText() throws InterruptedException {
        return getText(msgConfirmationLoc);
    }

}

