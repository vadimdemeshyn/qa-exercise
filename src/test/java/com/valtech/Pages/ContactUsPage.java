package com.valtech.Pages;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.assertTrue;

public class ContactUsPage {

    WebDriver browser;
    JavascriptExecutor js;

    public ContactUsPage(WebDriver browser){
        this.browser = browser;
        js = (JavascriptExecutor) browser;
    }

    public ContactUsPage verifyUserIsOnContactUsPage(){
        assertTrue(WebDriverRunner.url().contains("contact-us"));
        return this;
    }
}
