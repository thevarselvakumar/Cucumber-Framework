package com.selenium.webdriver;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.selenium.reports.ExtentManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.assertj.core.api.Assertions.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class WebConnector {
	
	WebDriver driver;
	public String name;
	public Properties prop;
	public ExtentReports rep;
	public ExtentTest scenario;
	
	public WebConnector() {
		//name="A";
		if(prop==null) {
			
			try {
				prop= new Properties();
				FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\project.properties");
				prop.load(fs);
			} catch (Exception e) {
				e.printStackTrace();
				// report
			}
		}
	}
	
	
	public void openBrowser(String browserName) {

			if(browserName.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver","C:\\Users\\selva\\Documents\\New folder (2)\\selenium driver\\New folder\\New folder\\chromedriver.exe");
				driver= new ChromeDriver();

			}else if(browserName.equals("ie")) {
					//code for ie
			}else {
					//code for firefox
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	//	infoLog("Opened Browser");
	}

	public void navigate(String urlKey) {
		//System.out.println(prop.getProperty(urlKey));
		driver.get(prop.getProperty(urlKey));
	}

	public void click(String objectKey) {
		getObject(objectKey).click();
	}
	
	public void type(String objectKey,String data) {
		getObject(objectKey).sendKeys(data);
	}
	public void select(String objectKey,String data) {
		Select s= new Select(getObject(objectKey));
		s.selectByVisibleText(data);
	}
	
	public void clear(String objectKey) {
		getObject(objectKey).clear();
	}
	
	// central function to extract objects
	public WebElement getObject(String objectKey) {
		WebElement e = null;
		WebDriverWait wait  =  new WebDriverWait(driver, 10);

		try {
			if(objectKey.endsWith("_xpath")) {
				e = driver.findElement(By.xpath(prop.getProperty(objectKey)));// present
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(prop.getProperty(objectKey))));
			}else if(objectKey.endsWith("_id")) {
					e = driver.findElement(By.id(prop.getProperty(objectKey)));// present
					wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(prop.getProperty(objectKey))));
			}
			else if(objectKey.endsWith("_name")) {
				e = driver.findElement(By.name(prop.getProperty(objectKey)));// present
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(prop.getProperty(objectKey))));
			}
			else if(objectKey.endsWith("_css")) {
				e = driver.findElement(By.cssSelector(prop.getProperty(objectKey)));// present
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(prop.getProperty(objectKey))));
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			reportFailure("Unable to extract Object "+objectKey);
		}
		return e;
	}
	// true - present
	// false - not present
	public boolean isElementPresent(String objectKey) {
		List<WebElement> e=null;
		
		if(objectKey.endsWith("_xpath")) {
			e = driver.findElements(By.xpath(prop.getProperty(objectKey)));// present
		}else if(objectKey.endsWith("_id")) {
				e = driver.findElements(By.id(prop.getProperty(objectKey)));// present
		}
		else if(objectKey.endsWith("_name")) {
			e = driver.findElements(By.name(prop.getProperty(objectKey)));// present
		}
		else if(objectKey.endsWith("_css")) {
			e = driver.findElements(By.cssSelector(prop.getProperty(objectKey)));// present
		}
		if(e.size()==0)
			return false;
		else
			return true;
	}
	
	public void validateLogin(String expectedResult) {
		boolean result=isElementPresent("portfolioSelection_id");
		String actualResult="";
		
		if(result)
			actualResult="success";
		else
			actualResult="failure";

		infoLog("ExpectedResult was " + expectedResult);
		infoLog("Got actualt Result as " + actualResult);
		if(!expectedResult.equals(actualResult)) {
			reportFailure("Scenario Failure"); // reporting failure
		}
	}

	public void login(String username, String password) {
		type("username_id", username);
		click("confirmEmailButton_xpath");
		type("password_id",password);
		click("loginSubmit_xpath");
		acceptAlertIfPresent();
		waitForPageToLoad();
	}
	
	
	public void select(String data) {
		
		select("abc_xpath", data);
	}
	
	
public void select_destinatin(String data) {
		
		select("xyz_xpath", data);
	}
	public void acceptAlertIfPresent() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
		}catch(Exception e) {
			// not present
		}
	}
	
	public void clickAndWait(String xpathExpTarget, String xpathExpWait, int maxTime){
		for(int i=0;i<maxTime;i++){
			// click
			getObject(xpathExpTarget).click();
			// check presence of other ele
			if(isElementPresent(xpathExpWait) && driver.findElement(By.id(prop.getProperty(xpathExpWait))).isDisplayed()){
				// if present - success.. return
				return;
			}else{
				// else wait for 1 sec
				wait(1);
			}
			
		}
		// 10 seconds over - for loop - comes here
		reportFailure("Target element coming after clicking on "+xpathExpTarget );
	}
	

	
		public void waitForPageToLoad(){
		JavascriptExecutor js = (JavascriptExecutor)driver;
			int i=0;
			
			while(i!=10){
			String state = (String)js.executeScript("return document.readyState;");
			System.out.println(state);

			if(state.equals("complete"))
				break;
			else
				wait(2);

			i++;
			}
			// check for jquery status
			i=0;
			while(i!=10){
		
				Long d= (Long) js.executeScript("return jQuery.active;");
				System.out.println(d);
				if(d.longValue() == 0 )
				 	break;
				else
					 wait(2);
				 i++;
					
				}
			
	}
    

	public void wait(int time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**********logging**************/
	public void infoLog(String msg) {
		scenario.log(Status.INFO, msg);
	}
	
	public void reportFailure(String errMsg) {
		// fail in extent reports
		scenario.log(Status.FAIL, errMsg);
		takeSceenShot();
		// take screenshot and put in repots
		// fail in cucumber as well
		assertThat(false);
	}
	
	public void takeSceenShot(){
		// fileName of the screenshot
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		// take screenshot
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			//get the dynamic folder name
			FileUtils.copyFile(srcFile, new File(ExtentManager.screenshotFolderPath+screenshotFile));
			//put screenshot file in reports
			scenario.log(Status.FAIL,"Screenshot-> "+ scenario.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath+screenshotFile));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**************Reporting******************/
	public void quit() {
		if(rep!=null)
			rep.flush();
		if(driver !=null)
			driver.quit();
	}
	public void initReports(String scenarioName) {
		rep = ExtentManager.getInstance(prop.getProperty("reportPath"));
		scenario = rep.createTest(scenarioName);
		scenario.log(Status.INFO, "Starting " +scenarioName);
	}


}
