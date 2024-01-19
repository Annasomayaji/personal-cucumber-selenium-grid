package com.nopcommerce.demo.utilities;

import com.google.common.base.Function;
import com.nopcommerce.demo.browserfactory.ManageBrowser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class Utility extends ManageBrowser {

    public int generateRandomNumber() {
        return (int) (Math.random() * 5000 + 1);
    }

    public String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * length);
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    public void clickOnElement(WebElement element) {
        element.click();
    }

    public String getTextFromElement(WebElement element) {
        return element.getText();
    }

    public void sendTextToElement(WebElement element, String text) {
        element.sendKeys(text);
    }

    //********************Alert methods**********************
    public void switchToAlert() {
        driver.switchTo().alert();
    }

    public void acceptAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public String getTextFromAlert() {
        return driver.switchTo().alert().getText();
    }

    public void sendTextToAlert(String text) {
        driver.switchTo().alert().sendKeys(text);
    }

    // ***************************Select class methods*************

    public void selectByVisibleText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    public void selectByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }

    public void selectByIndex(WebElement element, int index) {
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    public void selectByContainsText(WebElement element, String text) {
        Select select = new Select(element);
        List<WebElement> allOptions = select.getOptions();
        for (WebElement option : allOptions) {
            if (option.getText().contains(text)) {
                option.click();
            }
        }
    }

    //*********************Windows handle method*******************
    public void closeAllWindows(List<String> hList, String parentWindow) {
        for (String handle : hList) {
            if (!handle.equals(parentWindow)) {
                driver.switchTo().window(handle).close();
            }
        }
    }

    public void switchToParentWindow(String parentWindow) {
        driver.switchTo().window(parentWindow);

    }

    public boolean switchToRightWindow(List<String> hList, String windowTitle) {
        for (String str : hList) {
            String title = driver.switchTo().window(str).getTitle();
            if (title.contains(windowTitle)) {
                System.out.println("Found the right window");
                return true;
            }
        }
        return false;
    }

    //*************************Action methods****************
    public void moveMouseToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    public void selectAndClearInputField(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
        actions.keyDown(Keys.CONTROL).sendKeys("d").keyUp(Keys.CONTROL).build().perform();

    }

    //************************Waits method**************
    public WebElement waitUntilVisibilityOfElementLocated(WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementWithFluentWait(By by, int time, int pollingTime) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(time))
                .pollingEvery(Duration.ofSeconds(pollingTime))
                .ignoring(NoSuchElementException.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(by);
            }
        });
        return element;
    }

    //***************************** Is Display Methods **********************************************//

    public boolean verifyThatElementIsDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public boolean verifyThatTextIsDisplayed(WebElement element, String text) {
        if (text.equals(element.getText())) {
            return true;
        } else {
            return false;
        }
    }

    //************************** ScreenShot Methods *********************************************//

    public static String currentTimeStamp() {
        Date d = new Date();
        return d.toString().replace(":", "_").replace(" ", "_");

    }

    public static String takeScreenShot(String fileName) {
        String filePath = System.getProperty(("user.dir") + "/test-output/html");
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File scr1 = screenshot.getScreenshotAs(OutputType.FILE);
        String imageName = fileName + currentTimeStamp() + ".jpg";
        String destination = fileName + imageName;

        try {
            FileUtils.copyFile(scr1, new File(destination));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }

    public static byte[] getScreenShot(){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

}
