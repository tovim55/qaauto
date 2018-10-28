package com.verifone.pages.eoPages;

import com.verifone.pages.BasePage;
import org.openqa.selenium.By;

public class EditRolePage extends BasePage {

    private final static String url = "";
    private final static String title = "";

    private By titleLoc = By.xpath("//*[@class='content-header']");
    private By roleLoc = By.xpath("//*[@class='form-control i-validator select-role']");

    private By btnCancelLoc = By.id("cancelBtn");
    private By btnSaveLoc = By.id("createBtn");


    public EditRolePage() {
        super(url, title);
    }

    public String getTitle() throws InterruptedException {
        return getText(titleLoc);
    }

    public String getRole() throws InterruptedException {
        return getText(roleLoc);
    }

    


    public void clickBtnCancel() throws InterruptedException {
        click(btnCancelLoc);
    }

    public void clickBtnSave() throws InterruptedException {
        click(btnSaveLoc);
    }

    public void updateRole(String uRole)  throws Exception {
        select(roleLoc, uRole);
    }

}


