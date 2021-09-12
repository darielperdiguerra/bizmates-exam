package com.flowkl.login;

import com.flowkl.manager.TestListener;
import com.flowkl.pages.navbar.TopMenu;
import com.flowkl.pages.LoginPage;
import com.flowkl.webdriver.Base;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class BadLoginTest extends Base {

    @BeforeMethod
    public void setUp() {
        initializeBrowser("chrome","93");
    }

    @Test(description = "Validate Login function with invalid credentials")
    public void validateLoginWithInvalidCredentials() {
        TopMenu topMenu = getPageFactory().getTopMenu();
        topMenu.navigateToLoginPage();

        LoginPage loginPage = getPageFactory().getLoginPage();
        loginPage.login("unregistered@gmail.com","TestPwd");
        loginPage.validateLoginErrorMessage("These credentials do not match our records.");
    }

    @Test(description = "validate login with blank fields")
    public void validateLoginWithBlankFields() {
        TopMenu topMenu = getPageFactory().getTopMenu();
        topMenu.navigateToLoginPage();

        LoginPage loginPage = getPageFactory().getLoginPage();
        loginPage.validateLoginPage();
        loginPage.login("","");

        loginPage.validateLoginPage();

    }

    //Assuming on the requirement has max limit for this field
    @Test(description = "Validate email field accepts more than 100 chars")
    public void validateEmailFieldWithLongCharacters () {
        TopMenu topMenu = getPageFactory().getTopMenu();
        topMenu.navigateToLoginPage();

        LoginPage loginPage = getPageFactory().getLoginPage();
        loginPage.validateEmailMaxLenght("test123test123test123test123test123test123test123test123test123tes12313t123test123test123test123test123test123test123test123test123@email.com");
    }

    //Assuming on the requirement has max limit for this field
    @Test(description = "Validate password field max char limit")
    public void validatePasswordFieldWithLongCharacters () {
        TopMenu topMenu = getPageFactory().getTopMenu();
        topMenu.navigateToLoginPage();

        LoginPage loginPage = getPageFactory().getLoginPage();
        loginPage.validatePasswordMaxLenght("Password123Password123Password123Password123Password123Password123Password123Password123Password123Password123Password123Password123Password123Password123Password123");
    }

}
