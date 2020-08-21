package com.valtech.Pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import static com.codeborne.selenide.Selenide.$$;

public class PartnersPage {

    WebDriver browser;
    JavascriptExecutor js;

    public PartnersPage(WebDriver browser){
        this.browser = browser;
        js = (JavascriptExecutor) browser;
    }

    public List<String> getListOfPartners(){
        List<SelenideElement> listOfPartners = $$(".site-nav__main__item__title");
        List<String> listOfPartnersNames = new ArrayList<>();
        listOfPartners.forEach(item -> listOfPartnersNames.add(item.getAttribute("textContent"))); //used this getAttribute as I had no task which element text I should extract - only visible or all the elements
        return listOfPartnersNames;
    }

}
