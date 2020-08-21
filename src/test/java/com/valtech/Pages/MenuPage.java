package com.valtech.Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.$;


public class MenuPage {

    WebDriver browser;
    JavascriptExecutor js;

    public MenuPage(WebDriver browser){
        this.browser = browser;
        js = (JavascriptExecutor) browser;
    }

    public SelenideElement partnersTab = $(By.xpath("//button[.='Partners']"));
    public SelenideElement publicTab = $(By.xpath("//button[.='Public']"));

    public MenuPage navigateToPartners(){
        partnersTab.click();
        return this;
    }

    public MenuPage navigateToPublic(){
        publicTab.click();
        return this;
    }

    public MenuPage verifyPartnersSectionIsDisplayed(){
        partnersTab.shouldBe(Condition.visible);
        return this;
    }

}
