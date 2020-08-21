package com.valtech.Configuration;

import com.codeborne.selenide.WebDriverRunner;
import com.valtech.Pages.*;
import com.valtech.Util.CaptureScreenShotOnFailureListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import static com.codeborne.selenide.Selenide.open;

@Listeners({CaptureScreenShotOnFailureListener.class})
public class BaseTest {

    WebDriver driver;
    protected MenuPage menuPage;
    protected PartnersPage partnersPage;
    protected HomePage homePage;
    protected PublicSectorPage publicSectorPage;
    protected ContactUsPage contactUsPage;


    @BeforeMethod
    public void initiate(){
        String browserType = null;

        try {
            browserType = System.getProperty("driver").toLowerCase();
        } catch (Exception e) {
        }

        if (browserType != null){
            if (browserType.equals("chrome")) driver = new ChromeDriverManager().createDriver();
            if (browserType.equals("firefox")) driver = new FirefoxDriverManager().createDriver();
            if (browserType.equals("edge")) driver = new EdgeDriverManager().createDriver();
        }
        else driver = new ChromeDriverManager().createDriver(); // if no specified, can be used for local runs too

        menuPage = new MenuPage(driver);
        partnersPage = new PartnersPage(driver);
        homePage = new HomePage(driver);
        publicSectorPage = new PublicSectorPage(driver);
        contactUsPage = new ContactUsPage(driver);
        open("");
        homePage.acceptCookies();
    }

    @AfterMethod
    public void afterMethod() {
        WebDriverRunner.getWebDriver().quit();
    }
}
