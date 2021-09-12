package com.flowkl.factory;

import com.flowkl.pages.ForgotPasswordPage;
import com.flowkl.pages.HomePage;
import com.flowkl.pages.LoginPage;
import com.flowkl.pages.navbar.TopMenu;

public interface BasePageFactory {

	TopMenu getTopMenu();
	HomePage getHomePage();
	LoginPage getLoginPage();
	ForgotPasswordPage getForgotPasswordPage();
}