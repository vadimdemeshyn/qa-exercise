package com.valtech.Configuration;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class ChromeDriverManager extends BaseTest {

    public WebDriver createDriver() {

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setPlatform(Platform.ANY);
        ChromeOptions options  = new ChromeOptions();
        options.addArguments("-incognito");
        options.addArguments("--disable-popup-blocking");
        Configuration.timeout = 10000;
        options.merge(capabilities);
        Configuration.baseUrl = "https://www.valtech.com/en-gb/";
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
