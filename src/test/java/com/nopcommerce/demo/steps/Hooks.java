package com.nopcommerce.demo.steps;

import com.nopcommerce.demo.propertyreader.PropertyReader;
import com.nopcommerce.demo.runners.RunCukeTest;
import com.nopcommerce.demo.utilities.Utility;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.testng.annotations.Parameters;

public class Hooks extends Utility {

    String browser= PropertyReader.getInstance().getProperty("browser");

    @Before
    @Parameters("browser")
    public void setUp(){
        String browser= RunCukeTest.BROWSER.get();
        selectBrowser(browser);

    }

    @After
    public void tearDown(Scenario scenario){
        if(scenario.isFailed()){
           final byte[] screenshot=getScreenShot();
           scenario.attach(screenshot,"image/png",scenario.getName());
        }
        closeBrowser();
    }
}
