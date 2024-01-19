package com.nopcommerce.demo.browserfactory;

import com.nopcommerce.demo.propertyreader.PropertyReader;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class ManageBrowser {
    public static WebDriver driver;

    static String baseUrl = PropertyReader.getInstance().getProperty("baseUrl");
    static int implicitwait = Integer.parseInt(PropertyReader.getInstance().getProperty("implicitlyWait"));
    static String runOn = PropertyReader.getInstance().getProperty("runOn");
    static String gridUrl = PropertyReader.getInstance().getProperty("gridUrl");

    public ManageBrowser(){
        PageFactory.initElements(driver,this);
        PropertyConfigurator.configure(System.getProperty("user.dir")+"/src/test/resources/propertiesfile/log4j2.properties");
    }

    public void selectBrowser(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (runOn.equalsIgnoreCase("grid")) {
                System.out.println("#########TEST RUNNING ON GRID ==> the Browser is " + browser);
                try {
                    driver = new RemoteWebDriver(new URL(gridUrl), options);
                } catch (MalformedURLException e) {
                    System.out.println(e.getMessage());
                }
            } else if (runOn.equalsIgnoreCase("local")) {
                System.out.println("#########TEST RUNNING ON Local ==> the Browser is " + browser);
                driver = new ChromeDriver();
            }

        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            if (runOn.equalsIgnoreCase("grid")) {
                System.out.println("#########TEST RUNNING ON GRID ==> the Browser is " + browser);
                try {
                    driver = new RemoteWebDriver(new URL(gridUrl), options);
                } catch (MalformedURLException e) {
                    System.out.println(e.getMessage());
                }
            } else if (runOn.equalsIgnoreCase("local")) {
                System.out.println("#########TEST RUNNING ON Local ==> the Browser is " + browser);
                driver = new FirefoxDriver();
            }


        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options = new EdgeOptions();
            if (runOn.equalsIgnoreCase("grid")) {
                System.out.println("#########TEST RUNNING ON GRID ==> the Browser is " + browser);
                try {
                    driver = new RemoteWebDriver(new URL(gridUrl), options);
                } catch (MalformedURLException e) {
                    System.out.println(e.getMessage());
                }
            } else if (runOn.equalsIgnoreCase("local")) {
                System.out.println("#########TEST RUNNING ON Local ==> the Browser is " + browser);
                driver = new EdgeDriver();
            }

        } else {
            System.out.println("Wrong browser name.");
        }

        driver.get(baseUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitwait));
    }

    public void closeBrowser(){
        if(driver!=null){
            driver.quit();
        }
    }

}
