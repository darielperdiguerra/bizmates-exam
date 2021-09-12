package com.flowkl.pages;

import com.flowkl.webdriver.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class LoginPage extends Page {

    @FindBy(id="email")
    public WebElement emailInput;
    @FindBy(id="password")
    public WebElement passwordInput;
    @FindBy(css="button[type='submit']")
    public WebElement loginButton;
    @FindBy(id="remember")
    public WebElement rememberMeCheckbox;
    @FindBy(xpath="//a[contains(text(),'Forgot Your Password?')]")
    public WebElement forgotPasswordLink;
    @FindBy(css="div.card-header")
    public WebElement resetPasswordHeader;
    @FindBy(css="div.alert-danger ul li")
    public WebElement errorMessage;


    public void login(String email,String password){
        sendKeys(emailInput,email,"Email");
        sendKeys(passwordInput,password,"Password");
        click(loginButton,"Login");
        waitForPageLoad(10);
    }

    public void validateLoginPage(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(isElementDisplayed(emailInput,"Email"));
        softAssert.assertTrue(isElementDisplayed(passwordInput,"Password"));
        softAssert.assertTrue(isElementDisplayed(forgotPasswordLink,"Forgot Password Link"));
        softAssert.assertTrue(isElementDisplayed(rememberMeCheckbox,"Remember Me"));
        softAssert.assertAll();
    }

    public void validateLoginErrorMessage(String message){
        Assert.assertEquals(getText(errorMessage),message);
    }

    public void clickRememberMe(){
       click(rememberMeCheckbox,"Remember Me");
    }

    public void navigateToForgotPassword(){
        click(forgotPasswordLink,"Forgot Your Password?");
        waitForPageLoad(10);
        Assert.assertEquals(getText(resetPasswordHeader),"Reset Password");
    }

    public void validateEmailMaxLenght(String email){
        sendKeys(emailInput,email,"Email");
        String typedValue = emailInput.getAttribute("value");
        int size = typedValue.length();
        if (size == 80) {
            System.out.println("Max characters is set");
        }
        else {
            Assert.assertTrue(false);
        }
    }

    public void validatePasswordMaxLenght(String password){
    sendKeys(passwordInput,password,"Password");
    String typedValue = passwordInput.getAttribute("value");
        int size = typedValue.length();
        if (size == 80) {
            System.out.println("Max characters is set");
        }
        else {
            Assert.assertTrue(false);
        }
    }

}
