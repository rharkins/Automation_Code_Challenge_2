package com.stgconsulting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Created by Richard Harkins on 6/20/2016.
 */


public class WebPageTest {

    private WebDriver driver;
    static String baseWebPageURL = "http://www.skiutah.com";
    private boolean browserStarted = false;

    public void startBrowser()
    {
        //File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
        //FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
        //FirefoxProfile firefoxProfile = new FirefoxProfile();
        //driver = new FirefoxDriver(ffBinary,firefoxProfile);
        //driver = new FirefoxDriver();
        File file = new File("C:\\ChromeDriver\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    private void VerifyPageTitle(String webPageURL, String titleStringToTest)
    {
        // startBrowser
        if (browserStarted == false)
        {
            startBrowser();
            browserStarted = true;
        }
        // Open Webpage URL
        driver.get(webPageURL);
        // Get page title of current page
        String pageTitle = driver.getTitle();
        // Print page title of current page
        System.out.println("Page title of current page is: " + pageTitle);
        // Print title string to test
        System.out.println("Title String to Test is: " + titleStringToTest);
        // Test that the titleStringToTest = title of current page
        Assert.assertTrue(pageTitle.equals(titleStringToTest), "Current Page Title is not equal to the expected page title value");
        // If there is no Assertion Error, Print out that the Current Page Title = Expected Page Title
        System.out.println("Current Page Title = Expected Page Title");

    }

    private void VerifyNavigation(String navigationMenu)
    {
        // Build CSS Selector based on navigation menu user wants to click on
        String cssSelectorText = "a[title='" + navigationMenu + "']";
        // Find menu WebElement based on CSS Selector
        WebElement navigationMenuWebElement = driver.findElement(By.cssSelector(cssSelectorText));
        // Get href attributte from menu WebElement
        String navigationMenuURL = navigationMenuWebElement.getAttribute("href");
        // Navigate to href and validate page title
        VerifyPageTitle(navigationMenuURL, "Ski and Snowboard The Greatest Snow on Earth® - Ski Utah");
    }

    @Test
    private void TestLauncher()
    {
        // Verify PageTitle and Navigation
        VerifyPageTitle(baseWebPageURL, "Ski Utah - Ski Utah");
        VerifyNavigation("Deals");
    }
}