package org.kmk.opencart.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath="//span[normalize-space()='My Account']")
    WebElement lnk_my_account;

    @FindBy(xpath="//a[normalize-space()='Register']")
    WebElement lnk_register;

    @FindBy(xpath="//a[normalize-space()='Login']")
    WebElement link_login;

    public void clickMyAccount()
    {
        lnk_my_account.click();
    }

    public void clickRegister()
    {
        lnk_register.click();
    }

    public void clickLogin()
    {
        link_login.click();
    }
}
