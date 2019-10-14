package com.verifone.pages;

import com.aventstack.extentreports.ExtentTest;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class BasePage {

    public WebDriver driver = BaseTest.webDrivers.get(Thread.currentThread().getId());
    private String url;
    private String title;
    public static ExtentTest testLog;
    protected By loader = By.className("vui-spinner");


    public BasePage(String url, String title) {
        this.url = url;
        this.title = title;
    }


    public WebDriver getDriver() {
        return driver;
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
        testLog.info("User clicks on: " + loc.toString());
    }

    public void sendKeys(By loc, String text) {
        WebElement element = getWebElement(loc, 30, ExpectedConditions.elementToBeClickable(loc));
        element.clear();
        element.sendKeys(text);
        testLog.info("Send keys: " + text + " for: " + loc.toString());
    }

    public void sendKeysNoClear(By loc, String text) {
        WebElement element = getWebElement(loc, 30, ExpectedConditions.elementToBeClickable(loc));
        element.sendKeys(text);
        testLog.info("Send keys: " + text + " for: " + loc.toString());
    }

    public boolean isDisplay(By loc) {
        boolean results = driver.findElement(loc).isDisplayed();
        testLog.info("Check is element: " + loc + " display: " + results);
        return results;
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

    protected String getValue(By loc) {
        WebElement element = getWebElement(loc, 40, ExpectedConditions.presenceOfElementLocated(loc));
        String text = element.getAttribute("Value");
        testLog.info("User get text: " + text + " from this locator: " + loc.toString());
        return text;
    }

    protected String getCSSValue(By loc, String value) {
        WebElement element = getWebElement(loc, 40, ExpectedConditions.presenceOfElementLocated(loc));
        String cssValue = element.getCssValue(value);
        testLog.info("User get : " + value + " " + cssValue + " from this locator: " + loc.toString());
        return cssValue;
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

    protected int getRowNumberFromTable(By loc, String name) throws InterruptedException {
        Thread.sleep(2000);
        int i = 0;
        WebElement element = getWebElement(loc, 30, ExpectedConditions.presenceOfElementLocated(loc));
        String text = "";
        List<WebElement> tr = element.findElements(By.tagName("tr"));
        for (WebElement w : tr) {
            if (w.getText().contains(name)) {
                return i;
            }
            i++;
        }
        return 0;
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


    protected void waitForLoader(By loc) {
        try {
            WebDriverWait element = new WebDriverWait(driver, 18);
            element.until(ExpectedConditions.visibilityOfElementLocated(loc));
            WebDriverWait wait = new WebDriverWait(driver, 18);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loc));
        } catch (TimeoutException e) {

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
    protected void fileUpload(String path) throws AWTException {

        Robot robot = new Robot();
        robot.setAutoDelay(2000);
        StringSelection stringSelection = new StringSelection(path);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        robot.setAutoDelay(2000);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);

        robot.setAutoDelay(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    protected void hoverOnElement(By loc) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Actions builder = new Actions(driver);
        WebElement element = driver.findElement(loc);
        builder.moveToElement(element).perform();

    }

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

    protected void dragAndDrop(By draggable, By droppable) {
        WebElement sourse = driver.findElement(draggable);
        WebElement target = driver.findElement(droppable);
        Actions builder = new Actions(driver);
        builder.dragAndDrop(sourse, target).build().perform();

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

    protected List<WebElement> getWebElements(By loc, int timeOut, ExpectedCondition<WebElement> expectedCon) {
        waitForElement(loc, timeOut, expectedCon);
        return driver.findElements(loc);
    }

    protected WebElement getNestedWebElement(WebElement elem, By loc, int timeOut, ExpectedCondition<WebElement> expectedCon) {
        waitForElement(loc, timeOut, expectedCon);
        return elem.findElement(loc);
    }

    //    TODO complete method implementation
    protected void uploadFile(String filePath, WebElement dropZoneClass, String fileType) throws IOException {

        String id = dropZoneClass.getAttribute("id");
        String[] path = filePath.split("\\\\");
        String fileName = path[path.length - 1];
        String base64IFile = convertFileToBase64String(filePath);
        ((JavascriptExecutor) driver).executeScript("var myZone = Dropzone.forElement('#" + id + "');" +
                "base64Image = '" + base64IFile + "';" +
                "function base64toBlob(b64Data, contentType, sliceSize) {  \n" +
                "    contentType = contentType || '';\n" +
                "    sliceSize = sliceSize || 512;\n" +
                "\n" +
                "    var byteCharacters = atob(b64Data);\n" +
                "    var byteArrays = [];\n" +
                "\n" +
                "    for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {\n" +
                "        var slice = byteCharacters.slice(offset, offset + sliceSize);\n" +
                "\n" +
                "        var byteNumbers = new Array(slice.length);\n" +
                "        for (var i = 0; i < slice.length; i++) {\n" +
                "            byteNumbers[i] = slice.charCodeAt(i);\n" +
                "        }\n" +
                "\n" +
                "        var byteArray = new Uint8Array(byteNumbers);\n" +
                "\n" +
                "        byteArrays.push(byteArray);\n" +
                "    }\n" +
                "\n" +
                "    var blob = new Blob(byteArrays, {type: contentType});\n" +
                "    return blob;\n" +
                "}" +
                "var blob = base64toBlob(base64Image, '" + fileType + "');" +
                "blob.name = '" + fileName + "';" +
                "myZone.addFile(blob);  "
        );
    }

    public static String convertFileToBase64String(String fileName) throws IOException {

        File file = new File(fileName);
        int length = (int) file.length();
        BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
        byte[] bytes = new byte[length];
        reader.read(bytes, 0, length);
        reader.close();
        String encodedFile = Base64.getEncoder().encodeToString(bytes);

        return encodedFile;
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

    /**
     * @method : Method describe wait for 20 sec to visible the element
     */
    protected void waitUntilPageLoad(By loc) {
        WebDriverWait element = new WebDriverWait(driver, 20);
        try {
            element.until(ExpectedConditions.visibilityOfElementLocated(loc));
        } catch (Exception e) {
            //ignore
        }
    }

    /**
     * @method : Method return date and time in GMT +03:00 Time zone.
     * This helps to find the created jobs in VHQ
     */
    protected String getDownloadScheduleTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy hh:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+03:00"));
        return dateFormat.format(new Date());
    }

    /**
     * @method : This Method delete the values from the text box by selecting all the elements
     */
    protected void clearTextBoxValue(By loc) {
        WebElement element = driver.findElement(loc);
        try {
            element.sendKeys(Keys.CONTROL + "a");
            element.sendKeys(Keys.DELETE);
        } catch (Exception e) {
            //ignore
        }
    }

    /**
     * @method : This method describe scroll to the bottom of the page
     */
    protected void scrollToHeight() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        } catch (Exception e) {
            //ignore error
        }
    }

    /**
     * @method : This method check the details and return false if it is not present
     */
    protected static boolean assertRowContains(String expectedResult, String actual) {
        if (!actual.contains(expectedResult)) {
            testLog.info(" ----- Text expected: " + expectedResult + " Was: " + actual + " -----");
            return false;
        }
        return true;
    }

    /**
     * @Method : This method verify the exact text from the list of details
     */
    protected static boolean isContain(String source, String subItem) {
        String pattern = "\\b" + subItem + "\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(source);
        return m.find();
    }
}
