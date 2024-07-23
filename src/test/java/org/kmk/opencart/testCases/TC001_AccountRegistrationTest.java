package org.kmk.opencart.testCases;

import org.kmk.opencart.pageObjects.AccountRegistrationPage;
import org.kmk.opencart.pageObjects.HomePage;
import org.kmk.opencart.testBase.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC001_AccountRegistrationTest extends BaseClass {

    @Test(groups = {"Regression","Master"})
    public void verifyAccountRegistration()
    {
        logger.info("***** Started TC001_AccountRegistrationTest *****");
        try{
            HomePage homePage=new HomePage(driver);
            homePage.clickMyAccount();
            logger.info("Clicked on MyAccount Link");
            homePage.clickRegister();
            logger.info("Clicked on Register Link");

            logger.info("Providing customer details");
            AccountRegistrationPage accountRegistrationPage=new AccountRegistrationPage(driver);
            accountRegistrationPage.setFirstName(randomString().toUpperCase());
            accountRegistrationPage.setLastname(randomString().toUpperCase());
            accountRegistrationPage.setEmail(randomString()+"@gmail.com");
            accountRegistrationPage.setTelephone(randomNumber());

            String password=randomAlphaNumeric();
            accountRegistrationPage.setPassword(password);
            accountRegistrationPage.setConfirmPassword(password);
            accountRegistrationPage.checkPrivacyPolicy();
            accountRegistrationPage.clickContinue();

            logger.info("Validating expected message");
            String msg_confirm=accountRegistrationPage.getConfirmationMessage();
            if(msg_confirm.equals("Your Account Has Been Created!"))
            {
                Assert.assertTrue(true);
            }
            else {
                logger.error("Test Failed");
                logger.debug("Debug logs");
                Assert.fail();
            }
        }
        catch (Exception exp){
            Assert.fail();
        }
        logger.info("***** Finished TC001_AccountRegistrationTest *****");
    }
}
