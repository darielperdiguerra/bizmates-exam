package com.flowkl.pages;

import com.flowkl.webdriver.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HomePage extends Page {

    @FindBy(css="div.panel-default div.panel-heading")
    public WebElement blogLatestPosts;
    @FindBy(css="div.panel-default h2")
    public WebElement blogLatestHeader;

    public void validateHomePage() {
       waitForElementToBeDisplayed(blogLatestPosts,10);
       Assert.assertEquals(getText(blogLatestHeader),"Latest Posts");
    }

}
