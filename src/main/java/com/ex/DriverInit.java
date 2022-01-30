package com.ex;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class DriverInit {

    private static WebDriver webDriver;

    private DriverInit(Browser browser) {
        setWebDriver(browser);
        webDriver.manage().window().setSize(new Dimension(1280, 1024));
    }

    public static WebDriver getWebDriver(Browser browser) {
        if (webDriver == null) {
            new DriverInit(browser);
        }
        return webDriver;
    }

    private void setWebDriver(Browser browserName) {
        switch (browserName) {
            case CHROME:
                webDriver = WebDriverManager.chromedriver().create();
                break;
            case FF:
                webDriver = WebDriverManager.firefoxdriver().create();
                break;
        }
    }

    public enum Browser {
        CHROME,
        FF
    }
}
