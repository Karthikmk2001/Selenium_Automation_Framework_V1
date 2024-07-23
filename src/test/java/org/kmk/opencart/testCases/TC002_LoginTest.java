package org.kmk.opencart.testCases;

import org.kmk.opencart.pageObjects.HomePage;
import org.kmk.opencart.pageObjects.LoginPage;
import org.kmk.opencart.pageObjects.MyAccountPage;
import org.kmk.opencart.testBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC002_LoginTest extends BaseClass {

    @Test(groups ={"Sanity","Master"})
    public void verifyLogin()
    {
        logger.info("***** Starting TC002_LoginTest *****");

        try
        {
            HomePage homePage=new HomePage(driver);
            homePage.clickMyAccount();
            homePage.clickLogin();

            LoginPage loginPage=new LoginPage(driver);
            loginPage.setEmail(properties.getProperty("email"));
            loginPage.setPassword(properties.getProperty("password"));
            loginPage.clickLogin();

            MyAccountPage myAccountPage=new MyAccountPage(driver);
            Boolean pageExists=myAccountPage.isMyAccountPageExists();

            Assert.assertTrue(pageExists);
        }
        catch (Exception exp)
        {
            Assert.fail();
        }


        logger.info("***** Starting TC002_LoginTest *****");
    }
}
