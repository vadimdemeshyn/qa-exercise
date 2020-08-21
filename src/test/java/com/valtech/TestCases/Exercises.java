package com.valtech.TestCases;

import com.valtech.Configuration.BaseTest;
import org.testng.annotations.Test;

import java.util.List;

public class Exercises extends BaseTest {

    @Test(description = "Exercise 2")
    public void exercise2(){
        homePage.openHamburgerMenu();
        menuPage.verifyPartnersSectionIsDisplayed()
                .navigateToPartners();
        List<String> partners = partnersPage.getListOfPartners();
        partners.forEach(System.out::println);
    }

    @Test(description = "Exercise 3")
    public void exercise3(){
        homePage.openHamburgerMenu();
        menuPage.navigateToPublic();
        publicSectorPage.getPartnersAsText("National Statistics", "Justice", "Driver & Vehicle", "Licensing Executive",
                "Local Government", "Greater Manchester", "Civil Service Department")
                .proceedToTalkToUs();
        contactUsPage.verifyUserIsOnContactUsPage();
    }
}
