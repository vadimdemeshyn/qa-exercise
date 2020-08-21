package com.valtech.Pages;

import com.asprise.ocr.Ocr;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.Selenide.$;
import static org.testng.AssertJUnit.assertTrue;

public class PublicSectorPage {

    WebDriver browser;
    JavascriptExecutor js;

    public PublicSectorPage(WebDriver browser){
        this.browser = browser;
        js = (JavascriptExecutor) browser;
    }

    public SelenideElement talkToUsButton = $(".next-block__container [href=\"/en-gb/about/contact-us/\"]");

    public PublicSectorPage getPartnersAsText(String... partners){

        SelenideElement logo = $(By.xpath("(//div[@class='image-block__container'])[2]//img"));
        String logoSRC = logo.getAttribute("src");

        URL imageURL = null;
        try {
            imageURL = new URL(logoSRC);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        BufferedImage saveImage = null;
        try {
            saveImage = ImageIO.read(imageURL);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ImageIO.write(saveImage, "png", new File("logo-image.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Ocr.setUp();
        Ocr ocr = new Ocr();
        ocr.startEngine("eng", Ocr.SPEED_FASTEST); // English
        String s = ocr.recognize(new File[] {new File("logo-image.png")}, Ocr.RECOGNIZE_TYPE_ALL, Ocr.OUTPUT_FORMAT_PLAINTEXT);
        System.out.println("Result: " + s);
        ocr.stopEngine();
        for (String partner:partners
             ) {
            assertTrue(s.contains(partner));
        }
        return this;
    }

    public PublicSectorPage proceedToTalkToUs(){
        js.executeScript("arguments[0].scrollIntoView(true);", talkToUsButton);
        talkToUsButton.click();
        return this;
    }


}
