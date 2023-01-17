package com.flowkl.login;

import com.flowkl.manager.TestListener;
import com.flowkl.pages.HomePage;
import com.flowkl.pages.LoginPage;
import com.flowkl.pages.navbar.TopMenu;
import com.flowkl.webdriver.Base;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class Testing123 extends Base {

    @BeforeMethod
    public void setUp() {
        initializeBrowser("chrome", "108");
    }

    @Test(description = "TC01 – Flowkl User login")
    public void validateUserLogin() {
        System.out.println("Testing123");
    }

}
