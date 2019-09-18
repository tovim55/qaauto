package com.verifone.tests.mpTests;

import com.verifone.entities.EntitiesFactory;
import com.verifone.infra.User;
import com.verifone.pages.PageFactory;
import com.verifone.pages.mpPages.*;
import com.verifone.tests.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.List;
import static com.verifone.pages.BasePage.testLog;
import static com.verifone.tests.steps.mpPortal.Steps.loginMPPortal;
import static com.verifone.tests.steps.mpPortal.Steps.navigateCBAHome;
import static org.testng.Assert.assertEquals;

public class CarbonFamilyVerificationUI extends BaseTest {

    List<WebElement> ModelFamiliesList;

    @Test(priority = 1, testName = "LogIn & Check Carbon Mobile  family", description = "log into CBA marketPlace and check appearance of carbon mobile family")
    public void VerifyCarbonFamilyTestUI() throws Exception {



        testLog.info(" 1. Start connection and login to MP");
        User EOAdminSupport = EntitiesFactory.getEntity("EOAdminSupport");
        String EOAdminSupportMail = EOAdminSupport.getUserName();
        String EOAdminSupportPwd = EOAdminSupport.getPassword();
        String EOAdminSupportAnsw = EOAdminSupport.getSecurityAnswer();
        navigateCBAHome();
        loginMPPortal(EOAdminSupportMail, EOAdminSupportPwd, EOAdminSupportAnsw);



        testLog.info(" 2. Moving to Product Tab");
        MPHomePage MPHomePage = new MPHomePage();
        MPHomePage.clickHeaderManageMenu();
        MPHomePage.clickMarketplaceSubMenu();
        ManageMarketplacePage manageMarketplacePage = (ManageMarketplacePage) PageFactory.getPage("ManageMarketplacePage");
        manageMarketplacePage.clickTabProduct();
        ProductsTab productsTab = (ProductsTab) PageFactory.getPage("ProductsTab");
        productsTab.clickMenuStagingCatalog();



        testLog.info(" 3. Staging Catalog");
        ProductsTabStagingCatalogPage productsTabStagingCatalogPage = PageFactory.getStagingCatalog();
        productsTabStagingCatalogPage.clickBtnCreateProduct();



        testLog.info(" 4. Creation of New Product");
        ProdTabCreateProductPage prodTabCreateProductPage = PageFactory.getCreateProduct();
        prodTabCreateProductPage.fillProductName();
        prodTabCreateProductPage.ClickOnSubmitButton();
        prodTabCreateProductPage.ClickOnPlatforms();
        prodTabCreateProductPage.ClickOnAddPlatforms();
        prodTabCreateProductPage.SelectPlatformPopup();
        prodTabCreateProductPage.ClickOnAddVersionButton();



        testLog.info(" 5. Carbon Mobile family validation");
        ModelFamiliesList = prodTabCreateProductPage.getModelFamiliesList();
        for (WebElement name : ModelFamiliesList) {
            if (name.getText().equals("Carbon Mobile")) {
                assertEquals("Carbon Mobile",name.getText());
                testLog.info(name.getText() + " exists in the Model Families list");
                break;
           }
        }
    }
}
