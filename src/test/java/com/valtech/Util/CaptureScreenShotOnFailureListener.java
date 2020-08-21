package com.valtech.Util;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CaptureScreenShotOnFailureListener extends TestListenerAdapter implements ISuiteListener {

   static String methodName;
   static LoggingPreferences logPrefs = new LoggingPreferences();

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test STARTED: " + result.getTestClass().getName() + " " + result.getName());
        methodName = result.getName();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            saveScreenshot();
        } catch (Exception ignored) {
        }
        saveID();
        System.out.println("Test FAILED: " + result.getTestClass().getName() + " " + result.getName());
//        WebDriverRunner.getWebDriver().quit();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        try {
            saveScreenshot();
        } catch (Exception e) {
        }
        System.out.println("Test SKIPPED: " + result.getTestClass().getName() + " " + result.getName());
        saveID();
        WebDriverRunner.getWebDriver().quit();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        try {
            saveScreenshot();
        } catch (Exception ignored) {
        }
        System.out.println("Test SUCCESS: " + result.getTestClass().getName() + " " + result.getName());
        saveID();
        WebDriverRunner.getWebDriver().quit();
    }


    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] saveScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    //, fileExtension = "mp4"
    @Attachment(value ="Video")
    private byte[] saveVideo(){
        File file = null;
        System.out.println(System.getProperty("user.dir"));
        String x = (WebDriverRunner.getWebDriver().toString());
        String sessionId = String.valueOf(WebDriverRunner.getWebDriver()).substring(x.indexOf("(")+1, x.indexOf(")")-1);
        System.out.println(sessionId);
        try (Stream<Path> walk = Files.walk(Paths.get("/home/realm/.aerokube/selenoid/video"))) {

            List<String> result = walk.filter(Files::isRegularFile)
                    .map(Path::toString).collect(Collectors.toList());

            for (String s: result
            ) {
                if (s.contains(sessionId)){
                    file = new File(s) ;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] fileContent = null;

        try {
            assert file != null;
            fileContent =  Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileContent;
    }

    @Attachment( value = "Session ID", type = "text/plain" )
    private static String saveID()
    {
        return "Session id: " +WebDriverRunner.getWebDriver();
    }

    @Attachment( value = "Link to Video", type = "text/plain" )
    private static String videoInHtml()
    {
        String x = (WebDriverRunner.getWebDriver().toString());
        System.out.println("http://54.149.97.212:4444/video/"+x.substring(x.indexOf("(")+1, x.indexOf(")"))+ ".mp4");
        return "http://54.149.97.212:4444/video/"+x.substring(x.indexOf("(")+1, x.indexOf(")"))+ ".mp4";
    }

    @Override
    public void onStart(ISuite iSuite) {
    }

    @Override
    public void onFinish(ISuite iSuite) {

    }
}
