package com.verifone.pages.mpPages;


import com.verifone.pages.BasePage;
import org.openqa.selenium.By;

//--------------------------------------------------------------------------
/**
 * This class described all elements and actions can be executed on Staging Catalog.
 * @authors Sergey Vinickiy
 */
//--------------------------------------------------------------------------

public class ProductsTabStagingCatalogPage extends BasePage {

    private final static String url = "";
    private final static String title = "Staging Catalog";

    public ProductsTabStagingCatalogPage() {
        super(url, title);
    }

    private By CreateProductButton = By.xpath("//*[@id='id109']");


    //--------------------------------------------------------------------------
    public void clickBtnCreateProduct()  throws InterruptedException {
        waitForLoader(CreateProductButton);
        click(CreateProductButton);
    }
    //--------------------------------------------------------------------------


}
