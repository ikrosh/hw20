package com.ex.ui;

import com.ex.DriverInit;
import com.ex.WebHelpers;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest extends WebHelpers{
    protected static WebDriver webDriver;

    @BeforeSuite
    public void initWebDriver() {
        String browser = System.getProperty("browser");

        switch (browser) {
            case "chrome":
                webDriver = DriverInit.getWebDriver(DriverInit.Browser.CHROME);
                break;
            case "ff":
                webDriver = DriverInit.getWebDriver(DriverInit.Browser.FF);
                break;
            default:
                System.err.println("You have to chose from 'chrome' and 'ff'");
        }
    }

    @AfterSuite
    public void closeWebDriver() {
        webDriver.quit();
    }
}
