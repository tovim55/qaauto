package com.verifone.pages.mpPages;

import com.verifone.pages.BasePage;
import com.verifone.tests.BaseTest;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.core.env.SystemEnvironmentPropertySource;

public class CBAHomePage extends BasePage

{
    private final static String url = BaseTest.envConfig.getWebUrl() + "home";
    private final static String title = "Verifone Australia Sandbox | Verifone Australia" ;

    private By LogIn = By.xpath("//*[@class='ad-component--link '][@href='/login']");

    public CBAHomePage()
    {
        super(url, title);
        navigate();
    }

    public void clickOnLogInLink ()
     {
         click(LogIn);
     }

    public void whiteLabelingHome()
    {
       testLog.info(getCSSValue(LogIn, "color")) ;
       testLog.info(getCSSValue(LogIn, "background-color"));
       testLog.info(getCSSValue(LogIn, "font-family"));
       testLog.info(getCSSValue(LogIn, "font-size"));
    }
}
