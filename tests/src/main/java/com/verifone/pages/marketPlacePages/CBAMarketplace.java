package com.verifone.pages.marketPlacePages;

import com.verifone.pages.BasePage;

public class CBAMarketplace extends BasePage
{
    private final static String url = "https://testverifone.appdirect.com/home";
    private final static String title = "Test Verifone US  | Verifone";

    public CBAMarketplace()
    {
        super(url, title);
        validateTitle();
    }
}
