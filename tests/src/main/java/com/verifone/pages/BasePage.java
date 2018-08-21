package com.verifone.pages;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public abstract class BasePage {

    public static WebDriver driver;
    private String url;
    private String title;
    public static ExtentTest testLog;
    protected By loader = By.className("vui-spinner");


    public BasePage(String url, String title) {
        this.url = url;
        this.title = title;
    }


    public void navigate() {
        driver.get(this.url);
    }

    protected void validateTitle() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(ExpectedConditions.titleIs(title));
        } catch (TimeoutException e) {
            testLog.error(String.format("Title should be %s, Browser on %S", title, driver.getTitle()));
            Assert.fail(String.format("Title should be %s, Browser on %S", title, driver.getTitle()), e);

        }
    }


    public void click(By loc) {
        WebElement element = getWebElement(loc, 50, ExpectedConditions.elementToBeClickable(loc));
        element.click();
        testLog.info("user clicks on:  " + loc.toString());
    }

    public void sendKeys(By loc, String text) {
        WebElement element = getWebElement(loc, 30, ExpectedConditions.elementToBeClickable(loc));
        element.clear();
        element.sendKeys(text);
        testLog.info("send keys " + text + "for : " + loc.toString());
    }

    public void sendKeysNoClear(By loc, String text) {
        WebElement element = getWebElement(loc, 30, ExpectedConditions.elementToBeClickable(loc));
        element.sendKeys(text);
        testLog.info("send keys " + text + "for : " + loc.toString());
    }


    public void select(By loc, String value) {
        WebElement element = getWebElement(loc, 30, ExpectedConditions.presenceOfElementLocated(loc));
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.display='block'", element);
        new Select(element).selectByValue(value);
    }

    protected String getText(By loc) {
        WebElement element = getWebElement(loc, 40, ExpectedConditions.presenceOfElementLocated(loc));
        String text = element.getText();
        testLog.info("User get text: " + text + " from this locator: " + loc.toString());
        return text;
    }

    protected String getTextFromTable(By loc) throws InterruptedException {
        Thread.sleep(6000);
        WebElement element = getWebElement(loc, 30, ExpectedConditions.presenceOfElementLocated(loc));
        String text = "";
        List<WebElement> tr = element.findElements(By.tagName("tr"));
        for (WebElement w : tr) {
            text += w.getText();
        }
        return text;
    }

    protected void switchToIframe(By loc) {
        WebElement element = getWebElement(loc, 10, ExpectedConditions.presenceOfElementLocated(loc));
//        driver.switchTo().defaultContent();
        driver.switchTo().frame(element);
    }

    protected void switchToPreviousTab() {
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        driver.close();
        driver.switchTo().window(tabs2.get(0));
    }

    protected void switchToNextTab() {
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
    }



    protected void waitForLoader(By loc){
        try {
            WebDriverWait element  = new WebDriverWait(driver,  15);
            element.until(ExpectedConditions.visibilityOfElementLocated(loc));
            WebDriverWait wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loc));
        }
        catch (TimeoutException e){

        }
    }

//    public static void restartDriver() {
//        driver.manage().deleteAllCookies();         // Clear Cookies on the browser
//        driver.quit();
//        driver = null;
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//
//    }

    protected void hoverAndClickOnElement(By loc) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Actions builder = new Actions(driver);
        WebElement element = driver.findElement(loc);
        builder.moveToElement(element).click().perform();
    }

    protected void hoverAndClickOnElement(WebElement element) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Actions builder = new Actions(driver);
        builder.moveToElement(element).click().perform();
    }


    protected WebElement getWebElement(By loc, int timeOut, ExpectedCondition<WebElement> expectedCon) {
        waitForElement(loc, timeOut, expectedCon);
        return driver.findElement(loc);
    }

    //    TODO complete method implementation
    protected void uploadFile(String filePath, WebElement element) throws IOException, AWTException, InterruptedException {
        element.click();
        StringSelection ss = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        Robot robot = new Robot();
        Thread.sleep(4000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        Thread.sleep(2000);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

    }

    protected WebElement getElementsByClassJs(String className, int index) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (WebElement) js.executeScript("return document.getElementsByClassName('" + className + "')[" + index + "]");
    }

    private void waitForElement(By loc, int timeOut, ExpectedCondition<WebElement> expectedCon) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOut);
            wait.until(expectedCon);
        } catch (TimeoutException e) {
            testLog.error("Element Not Found For Locator: " + loc.toString());
            Assert.fail("Element Not Found For Locator: " + loc.toString(), e);
        }
    }


//    public void setCheckBox(By loc){
//        WebDriverWait wait = new WebDriverWait(driver, 30);
//        wait.until(ExpectedConditions.presenceOfElementLocated(loc));
//        WebElement a;
//        if (!driver.findElement(loc).isSelected()){
//            a = driver.findElement(loc);
//            a.click();
//            System.out.println("asdas      " + driver.findElement(loc).isSelected());
//        }
//
//    }


//
//    /**
//     * Navigate to the page
//     **/
//    public abstract void navigate();
//
//    /**
//     * returns title of the page
//     **/
//    public abstract String getTitle();
//
//    /**
//     * returns link of the page
//     **/
//    public abstract WebElement getPageLink();
//
//    /**
//     * Sets user name and password for login
//     *
//     * @param text
//     * @param text2
//     */
//    public abstract void initPage(String text, String text2);
//
//    /**
//     * Returns grid cell text
//     *
//     * @param trow
//     * @param tcolumn
//     * @return String
//     */
//    public abstract String getGridCellText(int trow, int tcolumn);
//
//    /**
//     * returns number of rows in the grid
//     **/
//    public abstract int gridRows();
//
//    /**
//     * returns number of columns in the grid
//     **/
//    public abstract int gridColumns();
//
//    /**
//     * Set text in grid cell
//     */
//    public abstract void setGridCellText(int trow, int tcolumn, String text) throws Exception;
//
//    /**
//     * Click Add button
//     *
//     * @throws Exception
//     **/
//    public abstract void clickAdd() throws Exception;
//
//    public abstract void clickSave() throws Exception;
//
//    public abstract void clickCancel() throws Exception;
//
//    public abstract void clickMultiActivate() throws Exception;
//
//    public abstract void clickUpload() throws Exception;
//
//    //Methods that set values in form fields
//    public abstract void setApplicationId(String text);
//
//    public abstract void setVersion(String text);
//
//    public abstract void setStatus(String text);
//
//    public abstract void setType(String text);
//
//    public abstract void setValue(String text);
//
//    public abstract void setNote(String text);
//
//    public abstract int numberOfErrors();
//
//    public abstract String getDisplayedError();
//
//    public abstract void selectApplicationId(String text) throws Exception;
//
//    public abstract void selectVersion(String text) throws Exception;
//
//    public abstract void selectStatus(String text) throws Exception;
//
//    public abstract void selectType(String text) throws Exception;


    /**
     * Method selects option if exists from the dropdown
     *
     * @param dropdown - arrow icon with class attribute 'k-icon k-i-arrow-s comboArrowClick'
     * @param option   to select
     * @throws Exception
     */
    protected void dropdownSelect(WebElement dropdown, String option) throws Exception {

        dropdown.click();
        String optionsIdentifier = dropdown.getAttribute("aria-controls");

        List<WebElement> options = driver.findElements(By.xpath("//*[contains(@id, '" + optionsIdentifier + "')]//li"));

        for (Iterator<WebElement> iterator = options.iterator(); iterator.hasNext(); ) {
            WebElement webElement = (WebElement) iterator.next();
            if (webElement.getAttribute("innerText").equals(option)) {
                webElement.click();
                return;
            }
        }
    }

    /**
     * Method checks if option exists in dropdown
     *
     * @param dropdown - arrow icon with class attribute 'k-icon k-i-arrow-s comboArrowClick'
     * @param option
     * @return true if exists, false otherwise
     * @throws Exception
     */
    protected boolean isDropdownOptionExists(WebElement dropdown, String option) throws Exception {

        dropdown.click();
        String optionsIdentifier = dropdown.getAttribute("aria-controls");

        List<WebElement> options = driver.findElements(By.xpath("//*[contains(@id, '" + optionsIdentifier + "')]//li"));

        for (Iterator<WebElement> iterator = options.iterator(); iterator.hasNext(); ) {
            WebElement webElement = (WebElement) iterator.next();
            if (webElement.getAttribute("innerText").equals(option)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Checks if field is labeled as required.
     * The method uses the fact that kandoo ui assigns speciffic class to required field's lables
     *
     * @param fieldLable without asterisk
     * @return true if field is required, false otherwise
     */
    public boolean isRequired(String fieldLable) {
//		FindBy(how = How.XPATH, using = "//label[contains(@class, 'ng-binding required-asterisk')]")
//		static WebElement requiredLabels;

        List<WebElement> requiredFields = driver.findElements(By.xpath("//label[contains(@class, 'ng-binding required-asterisk')]"));
        for (WebElement required : requiredFields) {
            if (required.getAttribute("innerText").equals(fieldLable))
                return true;
        }
        return false;
    }

    /**
     * Method waits for web element identified by locator to appear and clicks on it
     *
     * @param locator
     * @param maxTimeout
     * @return WebElement
     * @throws Exception
     * @author FredS3
     */
    protected WebElement actionClick(By locator, int maxTimeout) throws Exception {

        WebElement webEl = driver.findElement(locator);

        //scrollToElement(locator);
        Actions builder = new Actions(driver);
        while (!webEl.getAttribute("class").contains("k-edit-cell"))
            builder.moveToElement(webEl).click().perform();

        return webEl;
    }

    /**
     * Method waits for web element to appear and clicks on it
     *
     * @param element
     * @param maxTimeout
     * @return element
     * @throws Exception
     * @author FredS3
     */
    protected WebElement actionClick(WebElement element, int maxTimeout) throws Exception {
        //scrollToElement(element);
        Actions builder = new Actions(driver);
        //try to click on grid cell until it's selected
        while (!element.getAttribute("class").contains("k-edit-cell"))
            builder.moveToElement(element).click().perform();

        return element;
    }

    /**
     * Method check within maxTimeout if element identified by locator enabled
     *
     * @param locator
     * @param maxTimeout
     * @return true if element enabled, false otherwise
     * @throws Exception
     * @author FredS3
     */
    protected boolean isEnabled(By locator, int maxTimeout) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, maxTimeout);
        WebElement webEl = driver.findElement(locator);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            if (webEl.isEnabled() && webEl.getAttribute("class").equals("k-state-disabled"))
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Method check within maxTimeout if element identified by locator exists
     *
     * @param locator
     * @param maxTimeout
     * @return true if element exists, false otherwise
     * @throws Exception
     * @author FredS3
     */
    protected boolean isExists(By locator, int maxTimeout) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, maxTimeout);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Method wait's till element is clickable
     *
     * @param element
     * @param maxTimeout
     */
    protected void waitTillClickable(WebElement element, int maxTimeout) {
        WebDriverWait wait = new WebDriverWait(driver, maxTimeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * The method scrolls to given element by locator
     *
     * @param locator
     * @author FredS3
     */
    protected void scrollToElement(By locator) {

        int yCoordinate;
        WebElement webEl = driver.findElement(locator);

        yCoordinate = webEl.getLocation().getY();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js = (JavascriptExecutor) driver;
        try {
            js.executeScript("window.scrollTo(0," + yCoordinate + ")");
        } catch (Exception e) {
            //ignore error
        }
    }

    /**
     * The method scrolls to given web element
     *
     * @param element
     * @author FredS3
     */
    protected void scrollToElement(WebElement element) {

        int yCoordinate;
        WebElement webEl = element;

        yCoordinate = webEl.getLocation().getY();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js = (JavascriptExecutor) driver;
        try {
            js.executeScript("window.scrollTo(0," + yCoordinate + ")");
        } catch (Exception e) {
            //ignore error
        }
    }

    /**
     * Method sets text in web element identified by any locator
     *
     * @param locator    of type By
     * @param text       to set
     * @param maxTimeout time to wait for element to apear
     */
    protected void setTextBoxText(By locator, String text, int maxTimeout) {

        WebDriverWait wait = new WebDriverWait(driver, maxTimeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        //scrollToElement(locator);
        WebElement elem = driver.findElement(locator);
        Actions builder = new Actions(driver);
        try {
            elem.clear();
        } catch (Exception e) {
            //ignore
        }
        Actions typeText = builder.moveToElement(elem).click().sendKeys(elem, text);
        typeText.perform();
        driver.switchTo().defaultContent();
    }

    /**
     * Method sets text in web element
     *
     * @param element
     * @param text
     * @param maxTimeout
     */
    protected void setTextBoxText(WebElement element, String text, int maxTimeout) {

        WebDriverWait wait = new WebDriverWait(driver, maxTimeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(toByVal(element)));

        //scrollToElement(locator);
        WebElement elem = element;
        Actions builder = new Actions(driver);
        try {
            elem.clear();
        } catch (Exception e) {
            //ignore
        }
        Actions typeText = builder.moveToElement(elem).click().sendKeys(elem, text);
        typeText.perform();
        driver.switchTo().defaultContent();
    }


    /**
     * Receives web element and returns its By type
     *
     * @param we of type WebElement
     * @return ByType of WebElement
     */
    protected By toByVal(WebElement we) {
        // By format = "[foundFrom] -> locator: term"
        // see RemoteWebElement toString() implementation
        String str = we.toString().split(" -> ")[1];
        String[] data = str.substring(0, str.length() - 1).split(": "); //remove last char
        //String[] data = we.toString().split(" -> ")[1].replace("]", "").split(": ");
        String locator = data[0];
        String term = data[1];
        // remove possible duplication of ] in term
        while (term.endsWith("]]")) {
            term = term.substring(0, term.length() - 1);
        }

        switch (locator) {
            case "xpath":
                return By.xpath(term);
            case "css selector":
                return By.cssSelector(term);
            case "id":
                return By.id(term);
            case "tag name":
                return By.tagName(term);
            case "name":
                return By.name(term);
            case "link text":
                return By.linkText(term);
            case "class name":
                return By.className(term);
        }
        return (By) we;
    }
}
