package com.flowkl.login;

import com.flowkl.manager.TestListener;
import com.flowkl.pages.HomePage;
import com.flowkl.pages.LoginPage;
import com.flowkl.pages.navbar.TopMenu;
import com.flowkl.webdriver.Base;
import org.testng.annotations.*;

@Listeners(TestListener.class)
public class LoginTest extends Base {

    @BeforeMethod
    public void setUp() {
        initializeBrowser("chrome", "93");
    }

    @Test(description = "TC01 – Flowkl User login")
    public void validateUserLogin() {
        TopMenu topMenu = getPageFactory().getTopMenu();
        topMenu.navigateToLoginPage();

        LoginPage loginPage = getPageFactory().getLoginPage();
        loginPage.validateLoginPage();
        loginPage.login(property.getProperty("email"), property.getProperty("password"));

        HomePage homePage = getPageFactory().getHomePage();
        homePage.validateHomePage();
    }


    @Test(priority = 1, description = "TC02 – Flowkl User Logout")
    public void validateLogout() {
        TopMenu topMenu = getPageFactory().getTopMenu();
        topMenu.navigateToLoginPage();

        LoginPage loginPage = getPageFactory().getLoginPage();
        loginPage.validateLoginPage();
        loginPage.login(property.getProperty("email"), property.getProperty("password"));

        HomePage homePage = getPageFactory().getHomePage();
        homePage.validateHomePage();

        topMenu.validateUserAccountDropdown();
        topMenu.selectAccountDropdown("Logout");
        topMenu.validateNavBarLinks();

    }

}
