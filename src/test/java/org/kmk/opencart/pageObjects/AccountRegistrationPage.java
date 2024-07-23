package org.kmk.opencart.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

    public AccountRegistrationPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath="//input[@id='input-firstname']")
    WebElement txt_firstname;

    @FindBy(xpath="//input[@id='input-lastname']")
    WebElement txt_lastname;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement txt_email;

    @FindBy(xpath="//input[@id='input-telephone']")
    WebElement txt_telephone;

    @FindBy(xpath="//input[@id='input-password']")
    WebElement txt_password;

    @FindBy(xpath="//input[@id='input-confirm']")
    WebElement txt_confirm_password;

    @FindBy(xpath="//input[@name='agree']")
    WebElement chk_privacy_policy;

    @FindBy(xpath="//input[@value='Continue']")
    WebElement btn_continue;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msg_confirmation;

    public void setFirstName(String firstname)
    {
        txt_firstname.sendKeys(firstname);
    }

    public void setLastname(String lastname)
    {
        txt_lastname.sendKeys(lastname);
    }

    public  void setEmail(String email)
    {
        txt_email.sendKeys(email);
    }

    public void setTelephone(String telephone)
    {
        txt_telephone.sendKeys(telephone);
    }

    public void setPassword(String password)
    {
        txt_password.sendKeys(password);
    }

    public void setConfirmPassword(String password)
    {
        txt_confirm_password.sendKeys(password);
    }

    public void checkPrivacyPolicy()
    {
        chk_privacy_policy.click();
    }

    public void clickContinue()
    {
        //Solution1
        btn_continue.click();

        //Solution2
        //btnContinue.submit();

        //Solution3
        //Actions actions=new Actions(driver);
        //actions.moveToElement(btn_continue).click().perform();

        //Solution4
        //btn_continue.sendKeys(Keys.RETURN);

        //Solution5
        //JavascriptExecutor js=(JavascriptExecutor) driver;
        //js.executeScript("arguments[0].click();",btn_continue);

        //Solution6
        //WebDriverWait explicit_wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        //explicit_wait.until(ExpectedConditions.elementToBeClickable(btn_continue)).click();
    }

    public String getConfirmationMessage()
    {
        try{
            return (msg_confirmation.getText());
        }catch (Exception exp){
            return (exp.getMessage());
        }
    }
}
