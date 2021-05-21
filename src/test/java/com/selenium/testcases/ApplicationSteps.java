package com.selenium.testcases;
import java.util.List;

// passes object of webconnecter in both classes. Same object
// same object for 1 scenario
import com.selenium.webdriver.WebConnector;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class ApplicationSteps{
	
	WebConnector con;
	
	public ApplicationSteps(WebConnector con) {
		this.con=con;
	}
	
	@Then("^Login should be (.*)$")
	public void validateLogin(String expectedResult) {
		con.validateLogin(expectedResult);
	}

	@And("^I Login inside application$")
	public void login(List<String> data) {
		System.out.println(data.get(0));
		System.out.println(data.get(1));
		con.login(data.get(0), data.get(1));


	}
	
	//@And("^I go to (.*)$")
@Given("^I select depaturecity as (.*)$")
public void i_select_depaturecity_as(String data) {
   
	con.select(data);
}

@Given("I select destinationcity as (.*)")
public void i_select_destinationcity_as(String data) {
   
	con.select_destinatin(data);
	
	
}


	
}
