package com.ex.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;

import java.util.ArrayList;
import java.util.List;

public class WaitersSeleniumSuite extends BaseTest {

    @Test(enabled = false)
    public void testSleep() {
        webDriver.get("http://automationpractice.com/index.php?controller=authhentification&back=my-account");
        findElement(webDriver, By.xpath("//input[@id='email_create']")).sendKeys("someemaill@email.com");
        findElement(webDriver, By.xpath("//button[@id='SubmitCreate']")).click();
        boolean isD = waitForElementClickable(webDriver, By.xpath("//button[@id='submitAccount']")).isDisplayed();
        waitForElementClickable(webDriver, By.xpath("//button[@id='submitAccount']")).click();
    }

    @Test
    public void testScrolling() {
        webDriver.get("https://www.gurock.com/testrail/");
        WebElement el = webDriver.findElement(By.xpath("//a[text()='Legal']"));
        scrollToElement(webDriver, el);
        el.click();

        List<String> tabsBrowser = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabsBrowser.get(1));
        findElement(webDriver, By.xpath("//a[@id='ui-id-3']")).click();
    }

    @Test
    public void testLinkAboutGurock() {
        webDriver.get("https://www.gurock.com/testrail/");
        WebElement link = webDriver.findElement(By.xpath("//ul[@id='menu-footer-menu-gurock']//a[text()='About Gurock']"));
        scrollToElement(webDriver, link);
        waitForElementClickable(webDriver, link).click();
        WebElement pageHeader = webDriver.findElement(By.xpath("//h1"));
        Assert.assertTrue(pageHeader.getText().contains("Story"));
    }

    @Test
    public void testLinkJobs() {
        webDriver.get("https://www.gurock.com/testrail/");
        WebElement link = webDriver.findElement(By.xpath("//ul[@id='menu-footer-menu-gurock']//a[text()='Jobs']"));
        scrollToElement(webDriver, link);
        waitForElementClickable(webDriver, link).click();
        WebElement pageHeader = webDriver.findElement(By.xpath("//h1"));
        Assert.assertTrue(pageHeader.getText().contains("teams love"));
    }

    @Test
    public void testLinkContact() {
        webDriver.get("https://www.gurock.com/testrail/");
        WebElement link = webDriver.findElement(By.xpath("//ul[@id='menu-footer-menu-gurock']//a[text()='Contact']"));
        scrollToElement(webDriver, link);
        waitForElementClickable(webDriver, link).click();
        WebElement pageHeader = webDriver.findElement(By.xpath("//h1"));
        Assert.assertTrue(pageHeader.getText().contains("Contact"));
    }

    @Test
    public void testLinkCompliance() {
        webDriver.get("https://www.gurock.com/testrail/");
        WebElement link = webDriver.findElement(By.xpath("//ul[@id='menu-footer-menu-gurock']//a[text()='Compliance']"));
        scrollToElement(webDriver, link);
        waitForElementClickable(webDriver, link).click();
        WebElement pageHeader = webDriver.findElement(By.xpath("//h1"));
        Assert.assertTrue(pageHeader.getText().contains("Compliance"));
    }

    @Test
    public void testLinkLegal() {
        webDriver.get("https://www.gurock.com/testrail/");
        WebElement link = webDriver.findElement(By.xpath("//ul[@id='menu-footer-menu-gurock']//a[text()='Legal']"));
        scrollToElement(webDriver, link);
        waitForElementClickable(webDriver, link).click();

        List<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));

        List<WebElement>  appTabs = webDriver.findElements(By.xpath("//a[@class='ui-tabs-anchor']"));
        int delta = appTabs.size() - 1;
        int index = (int) (Math.random() * ++delta);
        String selectedTab = appTabs.get(index).getText();
        waitForElementClickable(webDriver, appTabs.get(index)).click();

        switch (selectedTab) {
            case "Contact & Legal Inquiries":
                findElement(webDriver, By.xpath("//div[@role='tabpanel']//li[contains(text(), 'General Legal inquiries')]"));
                break;
            case "Legal Terms & Customer Agreements":
                findElement(webDriver, By.xpath("//div[@role='tabpanel']//a[contains(text(), 'Product Licenses')]"));
                break;
            case "Policies & Procedures":
                findElement(webDriver, By.xpath("//div[@role='tabpanel']//a[contains(text(), 'Data Storage Policy')]"));
                break;
            case "Trademarks & Intellectual Property":
                findElement(webDriver, By.xpath("//div[@role='tabpanel']//a[contains(text(), 'Trademark usage')]"));
                break;
            default:
                System.out.println("No such tab!");
                break;
        }

        webDriver.close();
        webDriver.switchTo().window(tabs.get(0));

        waitForElementClickable(webDriver, By.xpath("//a[contains(@href, 'newsletter')]")).click();
        findElement(webDriver, By.xpath("//h1[contains(text(), 'Subscribe to TestRail News')]"));
    }

    @Test
    public void testFeaturePageStatus() {
        webDriver.get("https://www.gurock.com/testrail/");
        WebElement linkToCheck = webDriver.findElement(By.xpath("//li[@id='menu-item-2474']/a"));
        scrollToElement(webDriver, linkToCheck);
        String href = linkToCheck.getAttribute("href");
        int statusCode = RestAssured.get(href).statusCode();
        Assert.assertTrue(statusCode == 200);
    }

}
