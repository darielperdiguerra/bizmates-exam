package com.flowkl.webdriver;

import com.aventstack.extentreports.Status;
import com.flowkl.factory.BasePageFactory;
import com.flowkl.manager.ExtentTestManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class Page {
    //This page also serves as our utilities
    private static final Logger LOGGER = LogManager.getLogger(Page.class);

    public WebDriver driver;
    BasePageFactory pageFactory;

    public Page() {
        if (driver == null) {
            this.driver = Base.driver;
            pageFactory = new Base().getPageFactory();
        }
    }

    public WebElement waitForElementToBeDisplayed(final WebElement element, int timeOutPeriod) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeOutPeriod);
        webDriverWait.pollingEvery(Duration.ofMillis(1));

        return webDriverWait.until(new ExpectedCondition<WebElement>() {

            public WebElement apply(WebDriver driver) {
                try {
                    if (element.isDisplayed()) {
                        return element;
                    } else
                        return null;
                } catch (NoSuchElementException ex) {
                    return null;
                } catch (StaleElementReferenceException ex) {
                    return null;
                } catch (NullPointerException ex) {
                    return null;
                }
            }

        });
    }

    public void waitForPageLoad(int timeOutPeriod) {
        try {
            FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
            fluentWait.pollingEvery(Duration.ofSeconds(5));
            fluentWait.withTimeout(Duration.ofSeconds(timeOutPeriod));

            fluentWait.until(new Function<WebDriver,Boolean>() {
                public Boolean apply(WebDriver driver1) {
                    return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                }
            });
        } catch (Exception e) {
            System.out.println(e + " Exception");
        }
    }

    public void sendKeys(WebElement element, String value, String elemValue) {
        element.sendKeys(value);
        LOGGER.info(value+" is entered to " + elemValue + " field");
        ExtentTestManager.getTest().log(Status.INFO, value+" is entered to " + elemValue + " field");
    }

    public void click(WebElement pageElement, String elemValue) {
        pageElement.click();
        LOGGER.info("Clicked action on: " + elemValue);
        ExtentTestManager.getTest().log(Status.INFO, "Clicked action on: " + elemValue);
    }
    public void jsClick(WebElement pageElement, String elemValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", pageElement);
        ExtentTestManager.getTest().log(Status.INFO, "Clicked action on: " + elemValue);
    }


    public String getText(WebElement pageElement) {
        String test = "";
        try {
            test = pageElement.getText();
        } catch (Exception exception) {
            LOGGER.error( "Verify text is present, Text should be present " +catchException(exception));
            ExtentTestManager.getTest().log(Status.FAIL,"Verify text is present, Text should be present " +catchException(exception));
        }
        return test;
    }

    public void isTextPresent(WebElement pageElement, String logMessage) {

        boolean isTextPresent = false;
            String text = pageElement.getText();
            isTextPresent = text.contains(logMessage);
            System.out.println("pageElement.getText() " + text);
            if (isTextPresent) {
                LOGGER.info("Verify text " + logMessage, "--", logMessage, text);
                ExtentTestManager.getTest().log(Status.PASS, "Verify text " + logMessage);
            } else {
                LOGGER.error("Verify text " + logMessage);
                ExtentTestManager.getTest().log(Status.FAIL, "Verify text " + logMessage);
            }
    }

    public boolean isElementDisplayed(WebElement pageElement, String logMessage) {
        boolean isElementDisplayed = false;
        isElementDisplayed = pageElement.isDisplayed() || pageElement.isEnabled();
        LOGGER.info("Verify element " + logMessage + " is Displayed");
        ExtentTestManager.getTest().log(Status.PASS, "Verify element " + logMessage + " is Displayed");
        return isElementDisplayed;

    }

    public String catchException(Exception exception) {

        String sTemp1 = exception.getMessage();
        String[] sTemp2;
        if (sTemp1 != null && sTemp1.contains("WARN")) {
            sTemp2 = sTemp1.split("WARN");
            if (sTemp2.length > 0)
                sTemp2[0] = new String(sTemp2[0].substring(0, (sTemp2[0].length()) - 1));
        }

        else if (sTemp1 != null && sTemp1.contains("Timed out")) {
            sTemp2 = sTemp1.split(":");
            if (sTemp2.length > 0) {
                String replaceStr = sTemp2[1].replace("Build info", "");
                sTemp2[0] = new String(sTemp2[0] + " " + replaceStr);
            }
        }

        else {
            String sTemp3 = exception.toString();
            return sTemp3;
        }
        sTemp1 = null;
        return sTemp2[0];
    }

}
