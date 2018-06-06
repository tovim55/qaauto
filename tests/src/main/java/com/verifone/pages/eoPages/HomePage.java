package com.verifone.pages.eoPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private final static String url = "";
    private final static String title = "Sign Up with Verifone Identity Server";


    private By merchantTable = By.id("MODULE_ID_UNDEFINED");

    public HomePage() {
        super(url, title);
    }

    public String getMerchantTable(){
        System.out.println(getText(merchantTable));
        return getText(merchantTable);
    }
}
