package org.kmk.opencart.testCases;

import org.kmk.opencart.pageObjects.HomePage;
import org.kmk.opencart.pageObjects.LoginPage;
import org.kmk.opencart.pageObjects.MyAccountPage;
import org.kmk.opencart.testBase.BaseClass;
import org.kmk.opencart.utilities.DataProviders;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC003_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class,groups = "DataDriven")//getting data provider from different class
    public void verifyLoginDDT(String email,String password,String expected)
    {
        logger.info("***** Starting TC003_LoginDDT *****");
        try
        {
            HomePage homePage=new HomePage(driver);
            homePage.clickMyAccount();
            homePage.clickLogin();

            LoginPage loginPage=new LoginPage(driver);
            loginPage.setEmail(email);
            loginPage.setPassword(password);
            loginPage.clickLogin();

            MyAccountPage myAccountPage=new MyAccountPage(driver);
            boolean pageExists=myAccountPage.isMyAccountPageExists();

            //Data is valid -login success-test pass -logout
            //              -login failed -test fail
            //Data is invalid -login success- test fail-logout
            //                -login failed- test pass

            if(expected.equalsIgnoreCase("valid"))
            {
                if(pageExists)
                {
                    myAccountPage.clickMyAccount();
                    myAccountPage.clickLogout();
                    Assert.assertTrue(true);
                }
                else
                {
                    Assert.fail();
                }
            }

            if(expected.equalsIgnoreCase("invalid"))
            {
                if(pageExists)
                {
                    myAccountPage.clickMyAccount();
                    myAccountPage.clickLogout();
                    Assert.fail();
                }
                else
                {
                    Assert.assertTrue(true);
                }
            }
        }
        catch(Exception exp)
        {
            Assert.fail();
        }
        logger.info("***** Finished TC003_LoginDDT");
    }
}
