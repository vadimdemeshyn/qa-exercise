package com.valtech.Configuration;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class EdgeDriverManager extends BaseTest {

    public WebDriver createDriver() {
        System.setProperty("webdriver.edge.driver", "src/bin/win/msedgedriver.exe");
        driver = new EdgeDriver();
        Configuration.timeout = 20000;
        Configuration.baseUrl = "https://www.valtech.com/en-gb/";
        WebDriverRunner.setWebDriver(driver); //passing driver to selenide
        return driver;
    }
}
