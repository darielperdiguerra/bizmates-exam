package com.flowkl.pages;

import com.flowkl.webdriver.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

public class ForgotPasswordPage extends Page {

    @FindBy(css="input.form-control")
    public WebElement resetPasswordEmail;
    @FindBy(xpath="//button[contains(text(),'Send Password Reset Link')]")
    public WebElement resetPasswordLink;
    @FindBy(css="div.alert-danger ul li")
    public WebElement errorMessage;


    public void validateForgotPasswordForm(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(isElementDisplayed(resetPasswordEmail,"Email"));
        softAssert.assertTrue(isElementDisplayed(resetPasswordLink,"Password"));
        softAssert.assertAll();
    }

    public void resetPassword(String email){
        sendKeys(resetPasswordEmail,email,"E-Mail Address");
        click(resetPasswordLink,"Send Password Reset Link");
    }

    public void validateForgotPasswordErrorMessage(String message){
        isTextPresent(errorMessage,message);
    }

}
