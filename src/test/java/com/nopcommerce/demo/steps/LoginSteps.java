package com.nopcommerce.demo.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LoginSteps {
    @Given("I am on homepage")
    public void iAmOnHomepage() {
        System.out.println("Hello");

    }

    @When("I click on login link")
    public void iClickOnLoginLink() {
        System.out.println("Hello again");
    }
}
