package com.valtech.Util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverEventListener;

/**
 * @author Sergii Moroz
 *         </p>
 *         Summary:
 *         Implementation of a listener for logging events which caused by WebDriver.
 *         <p/>
 *         Dependencies:
 *         1. Requires an instance of the browsers web driver as this class calls the WebDriverManager to get the
 *         browsers web driver instance.
 *         2. Requires 'org.uncommons.reportng.escape-output' system property variable set fo 'false' in the pom.xml to
 *         show HTML tags in the ReportNG reports
 */

public class LoggingEventListener implements WebDriverEventListener {

    private Log log = LogFactory.getLog(this.getClass());

    //Last By item in the chain of findElement() items. (e.g. "by2" in chain: driver.findElement(By.id("by1").findElement("by2")))
    private By lastFindBy;
    private String originalValue;

    @Override
    public void beforeAlertAccept(WebDriver webDriver) {
    }

    @Override
    public void afterAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        log.info("Navigating to:'" + url + "'");
    }

    public void beforeChangeValueOf(WebElement element, WebDriver driver) {
        originalValue = element.getAttribute("value");
        if (originalValue == null){
            originalValue = element.getText();
        }
    }


    public void afterChangeValueOf(WebElement element, WebDriver driver) {
        String newValue = element.getAttribute("value");
        if (newValue == null){
            newValue = element.getText();
        }
        log.info("Changing value in element found " + lastFindBy + " from '" + originalValue + "' to '" + newValue + "'");
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        lastFindBy = by;
    }

    @Override
    public void onException(Throwable error, WebDriver driver) {
        if (error.getClass().equals(NoSuchElementException.class)) {
            log.error("WebDriver error: Element not found " + lastFindBy);
        } else if (error.getClass().equals(StaleElementReferenceException.class)){
            log.error("WebDriver error: StaleElementReferenceException occurred while finding " + lastFindBy);
        } else {
            log.error("WebDriver error:", error);
        }
    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {

    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {

    }

    @Override
    public void beforeGetText(WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void afterGetText(WebElement webElement, WebDriver webDriver, String s) {

    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        log.info("- - Ok! Element, found " + lastFindBy + " clicked successfully");
    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {

    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {

    }

    @Override
    public void afterNavigateBack(WebDriver driver) {
        log.info("Opened page from history: '" + driver.getCurrentUrl() + "'");
    }

    @Override
    public void afterNavigateForward(WebDriver driver) {
        log.info("Opened page from history: '" + driver.getCurrentUrl() + "'");
    }

    @Override
    public void beforeNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {

    }

    @Override
    public void afterScript(String url, WebDriver driver) {

    }

    @Override
    public void beforeSwitchToWindow(String s, WebDriver webDriver) {

    }

    @Override
    public void afterSwitchToWindow(String s, WebDriver webDriver) {

    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        log.info("Clicking on " + lastFindBy);
    }


    @Override
    public void beforeNavigateBack(WebDriver driver) {
        log.info("Navigating back");
    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {
        log.info("Navigating forward");
    }

    @Override
    public void beforeScript(String script, WebDriver driver) {
        log.info("Executing script: '" + script + "' with element, found " + lastFindBy);
    }


}

