package com.flowkl.webdriver;

import com.flowkl.common.RndNum;
import com.flowkl.factory.BasePageFactory;
import com.flowkl.factory.WebPageFactory;
import com.flowkl.manager.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base implements ITestListener {

    public static WebDriver driver;
    public static Properties property;
    public BasePageFactory pageFactory;
    private static final Logger LOGGER = LogManager.getLogger(Page.class);
    RndNum randomNumberGen = RndNum.getRandomNumberUtil();
    public int randomNum = randomNumberGen.getRandomNumber();

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite()  {
        readConfigData();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        killDriverInstProcess("chromedriver");
        killDriverInstProcess("geckodriver");
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMMyyyyHH.mm.ss");

        if (ITestResult.FAILURE == result.getStatus()) {
            String testClassName = (result.getInstanceName()).trim();
            String timeStamp = formatter.format(currentDate.getTime());
            String screenShotName = result.getName() + timeStamp;
            String reportsPath = System.getProperty("user.dir")+"\\"+"test-report"+"\\"+"screenshots";

            try {
                File file = new File(reportsPath+"\\"+testClassName);
                if (!file.exists()) {
                    if (file.mkdirs()) {
                        System.out.println("Directory: "+file.getAbsolutePath()+" is created!");
                    } else {
                        System.out.println("Failed to create directory: "+file.getAbsolutePath());
                    }
                }
                try {
                    TakesScreenshot srcFile = (TakesScreenshot) driver;
                    File source = srcFile.getScreenshotAs(OutputType.FILE);
                    FileHandler.copy(source, new File(reportsPath+"\\"+testClassName+"\\"+screenShotName+".png"));
                    System.out.println("Screenshot taken");
                } catch (Exception e) {
                    System.out.println("Exception while taking screenshot " + e.getMessage());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        driver.quit();
    }

    public static WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public BasePageFactory getPageFactory() {
        pageFactory = new WebPageFactory(getDriver());
        return pageFactory;
    }

    @Parameters({"browsername","browserversion"})
    public static void initializeBrowser(String browserName, String browserVersion) {
        if(browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().version(browserVersion).setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("disable-popup-blocking");
            driver = new ChromeDriver(chromeOptions);
        }else if(browserName.equalsIgnoreCase("chrome-headless")){
            WebDriverManager.chromedriver().version(browserVersion).setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("disable-popup-blocking");
            chromeOptions.addArguments("--headless");
            driver = new ChromeDriver(chromeOptions);
        }else if(browserName.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().version(browserVersion).setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            driver = new FirefoxDriver(firefoxOptions);
        }
        System.out.println("Browser: "+browserName+ ", Version: "+browserVersion);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        getApplicationURL();
    }

    public static void getApplicationURL() {
        String baseUrl = property.getProperty("web_url");
        try {
            driver.navigate().to(baseUrl);
            System.out.println("Navigate to " + baseUrl + " URL");
        } catch (Exception exception) {
            System.out.println("Unable to get " + baseUrl + " URL");
        }
    }

    private void readConfigData(){
        File file = new File("src\\main\\resources\\config\\config.properties");
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        property = new Properties();
        try {
            property.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void killDriverInstProcess(String strDriverInstName){
        try{
            Runtime.getRuntime().exec(new String[]{"cmd", "/k", "start", "taskkill.exe", "/F", "/IM", strDriverInstName +".exe", "/T"});
        }
        catch (Exception ex){
            System.out.println("error desc : " + ex.getMessage());
        }
    }


}
