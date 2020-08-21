package com.valtech.Configuration;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class FirefoxDriverManager extends BaseTest{

    public WebDriver createDriver() {
        System.setProperty("webdriver.gecko.driver",
                "src/bin/win/geckodriver.exe");
//        Configuration.timeout = 20000;
//        Configuration.baseUrl = "https://www.valtech.com/en-gb/";
//        driver = new FirefoxDriver();
//        driver.manage().window().maximize();
//        WebDriverRunner.setWebDriver(driver); //passing driver to selenide

        Configuration.timeout = 20000;
        Configuration.baseUrl = "https://www.valtech.com/en-gb/";
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setPlatform(Platform.ANY);
        capabilities.setBrowserName("firefox");
        capabilities.setCapability("marionette", true);
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver); //passing driver to selenide
        return driver;
    }
}
