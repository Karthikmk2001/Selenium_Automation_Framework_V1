package org.kmk.opencart.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h2[normalize-space()='My Account']")
    WebElement msg_heading;

    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement lnk_myAccount;

    @FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']")
    WebElement lnk_logout;

    public boolean isMyAccountPageExists()
    {
        try
        {
            return (msg_heading.isDisplayed());
        }
        catch (Exception exp){
          return false;
        }
    }

    public void clickMyAccount()
    {
        lnk_myAccount.click();
    }

    public void clickLogout()
    {
        lnk_logout.click();
    }
}
