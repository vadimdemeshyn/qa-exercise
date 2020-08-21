package com.valtech.Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    WebDriver browser;
    JavascriptExecutor js;

    public HomePage(WebDriver browser){
        this.browser = browser;
        js = (JavascriptExecutor) browser;
    }

    //hamburger menu
    private SelenideElement hamburgerMenu = $(".icon-menu");
    private SelenideElement acceptCookiesButton = $("[onclick=\"Cookiebot.dialog.submitConsent()\"]");


    public HomePage openHamburgerMenu(){
        hamburgerMenu.click();
        return this;
    }

    public HomePage acceptCookies(){
        acceptCookiesButton.click();
        return this;
    }
}
