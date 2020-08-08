package com.selenium.testcases;
import java.util.List;

// passes object of webconnecter in both classes. Same object
// same object for 1 scenario
import com.selenium.webdriver.WebConnector;
import cucumber.api.java.en.And;
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
	
}
