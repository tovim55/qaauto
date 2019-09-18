package com.verifone.pages.mpPages;

//--------------------------------------------------------------------------
/**
 * This class described all elements and actions can be executed on Create Product page in Product tab.
 * @authors Sergey Vinickiy
 */
//--------------------------------------------------------------------------


import com.verifone.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;


public class ProdTabCreateProductPage extends BasePage {

    private final static String url = "";
    private final static String title = "Create New Product";
    private List<WebElement> dashboardList;
    private List<WebElement> smalldashboardList;
    private List<WebElement> modelFamilies;

    public ProdTabCreateProductPage() {
        super(url, title);
    }

    private By ProductName = By.xpath("//*[@id='appNameField']");
    private By SubmitButton = By.xpath("//*[@name='createButton']");
    private By Platforms = By.xpath("//*[@class='adb-link__nav adb-stack--item_content adb-stack--item_content__nesting adb-js-toggle-link collapsed']");
    private By AddPlatforms = By.xpath("//*[@class='add adb-button adb-button__neutral adb-button__small']");
    private By PublishButton = By.xpath("//*[@id='id213']");
    private By SelectPlatformPopup = By.xpath("//*[@class='adb-modal--header']");
    private By SelectorInput = By.xpath("//*[@class='adb-selector--input']");
    private By SelectButton = By.xpath("//*[@class='adb-button adb-toolbar--item adb-button__primary']");
    private By AddProductVersionButton = By.xpath("//*[@class='adb-button']");
    private By ListOfModelFamilies = By.xpath("//*[@class='adb-selector--value js-value-selector']");
    private By EventsButton = By.xpath("//*[@id=\"id199\"]");

    public void fillProductName(){
        sendKeys(ProductName, "autoCreatedProduct");
    }

    public void ClickOnSubmitButton(){
        click(SubmitButton);
        waitForLoader(PublishButton);
    }

    public void ClickOnPlatforms() throws InterruptedException {

        dashboardList = getWebElements(Platforms, 500, ExpectedConditions.presenceOfElementLocated(Platforms));
        scrollToElement(dashboardList.get(2));
        dashboardList.get(2).click();

    }

    public void ClickOnAddPlatforms() throws InterruptedException {
        waitForLoader(EventsButton);
        //Thread.sleep(2000);
        smalldashboardList = getWebElements(AddPlatforms, 500, ExpectedConditions.presenceOfElementLocated(AddPlatforms));
        smalldashboardList.get(2).click();
    }

    public void SelectPlatformPopup(){
        click(SelectPlatformPopup);
        click(SelectorInput);
        click(SelectButton);
        waitForLoader(AddProductVersionButton);
    }

    public void ClickOnAddVersionButton(){
        click(AddProductVersionButton);
    }

    public List<WebElement> getModelFamiliesList(){
        modelFamilies = getWebElements(ListOfModelFamilies, 500, ExpectedConditions.presenceOfElementLocated(ListOfModelFamilies));
        return modelFamilies;
    }

}
