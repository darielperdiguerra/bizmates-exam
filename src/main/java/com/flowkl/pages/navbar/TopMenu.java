package com.flowkl.pages.navbar;

import com.flowkl.webdriver.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class TopMenu extends Page {

    @FindBy(linkText="Login")
    public WebElement loginLink;
    @FindBy(linkText="Register")
    public WebElement registerLink;
    @FindBy(linkText="Home")
    public WebElement homeLink;
    @FindBy(css="a.navbar-brand")
    public WebElement brandLink;
    @FindBy(css="ul.navbar-right li.dropdown")
    public WebElement accountDropdown;
    @FindBy(css="div.card-header")
    public WebElement pageHeader;
    @FindBy(css="ul.navbar-right li.dropdown a[role='button'] ")
    public WebElement accountSessionName;

    public void navigateToLoginPage() {
        click(loginLink,"Login");
        waitForPageLoad(10);
        Assert.assertEquals(getText(pageHeader),"Login");
    }

    public void validateNavBarLinks() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loginLink.isDisplayed());
        softAssert.assertTrue(registerLink.isDisplayed());
        softAssert.assertTrue(homeLink.isDisplayed());
        softAssert.assertTrue(brandLink.isDisplayed());
        softAssert.assertAll();
    }

    public void validateUserAccountDropdown() {
        waitForElementToBeDisplayed(accountSessionName,10);
        Assert.assertTrue(accountDropdown.isDisplayed());
    }

    public void selectAccountDropdown(String option) {
        click(accountDropdown,"Account Dropdown");

        List<WebElement> options = accountDropdown.findElements(By.cssSelector("li"));
        for (int i = 0; i < options.size(); i++) {
            if(options.get(i).getText().equalsIgnoreCase(option)){
                options.get(i).click();
                break;
            }
        }
    }

}
