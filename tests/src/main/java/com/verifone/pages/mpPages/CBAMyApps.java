package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CBAMyApps extends BasePage
{
    private final static String url = BaseTest.envConfig.getWebUrl() + "myapps";
    private final static String title = "MyApps | Verifone Australia";

    private static String appName = BaseTest.envConfig.getAppName();
    private By titleList = By.cssSelector("p[class='js-name-region adb-myapp--content']");
    private By success =  By.xpath("//*[contains(text(),'successfully')]");
    private By moreInfo =  By.xpath("//a[contains(text(),'More Info')]");

    private List<WebElement> appList;
    private List<String>names;
    private WebElement myApp;

    public CBAMyApps()
    {
        super(url, title);
        validateTitle();
    }

    public void verifyAppSubcribed() {

        appList = getWebElements(titleList, 500, ExpectedConditions.presenceOfElementLocated(titleList));
        int appsNumber = appList.size();

        for (int i = 0; i < appsNumber; i++) {

            testLog.info(appList.get(i).getText() + " app is subscribed");
        }

        for (WebElement name : appList) {
            if (appList.contains(name.getText().equals(appName))) {
                testLog.info(appName + " exists in the MyApps list");
                break;
            }
        }
    }

    public void verifyMessage() throws InterruptedException {
        ExpectedConditions.presenceOfElementLocated(success);
        Thread.sleep(5000);
        testLog.info(getText(success));
    }

    public void whiteLabelingMyApps()
    {
        testLog.info(getCSSValue(moreInfo, "color")) ;
        testLog.info(getCSSValue(moreInfo, "background-color"));
        testLog.info(getCSSValue(moreInfo, "font-family"));
        testLog.info(getCSSValue(moreInfo, "font-size"));
    }


}
