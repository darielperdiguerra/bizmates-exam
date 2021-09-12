package com.flowkl.factory;


import com.flowkl.pages.ForgotPasswordPage;
import com.flowkl.pages.HomePage;
import com.flowkl.pages.LoginPage;
import com.flowkl.pages.navbar.TopMenu;
import com.flowkl.webdriver.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class WebPageFactory implements BasePageFactory {

	private WebDriver driver;

	public WebPageFactory(WebDriver driver) {
		this.driver = driver;
	}

	@Override
	public TopMenu getTopMenu() {
		return PageFactory.initElements(new Page().driver, TopMenu.class);
	}
	@Override
	public HomePage getHomePage() {
		return PageFactory.initElements(new Page().driver, HomePage.class);
	}

	@Override
	public LoginPage getLoginPage() {
		return PageFactory.initElements(new Page().driver, LoginPage.class);
	}
	@Override
	public ForgotPasswordPage getForgotPasswordPage() {
		return PageFactory.initElements(new Page().driver, ForgotPasswordPage.class);
	}

}