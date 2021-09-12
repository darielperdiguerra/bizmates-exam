package com.flowkl.login;

import com.flowkl.manager.TestListener;
import com.flowkl.pages.ForgotPasswordPage;
import com.flowkl.pages.LoginPage;
import com.flowkl.pages.navbar.TopMenu;
import com.flowkl.webdriver.Base;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class ForgotPasswordTest extends Base {

    @BeforeMethod
    public void setUp() {
        initializeBrowser("chrome", "93");
    }

    @Test(description = "Verify forgot password with unregistered account")
    public void verifyForgotPasswordPageWithUnregisteredAccount() {
        TopMenu topMenu = getPageFactory().getTopMenu();
        topMenu.navigateToLoginPage();

        LoginPage loginPage = getPageFactory().getLoginPage();
        loginPage.navigateToForgotPassword();

        ForgotPasswordPage forgotPasswordPage = getPageFactory().getForgotPasswordPage();

        forgotPasswordPage.validateForgotPasswordForm();
        forgotPasswordPage.resetPassword("test_flowkl" + randomNum + "@mailinator.com");
        forgotPasswordPage.validateForgotPasswordForm();
        forgotPasswordPage.validateForgotPasswordErrorMessage("We can't find a user with that email address.");

    }

}
