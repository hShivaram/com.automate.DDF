package com.automate.FM1;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automate.utils.Dateutils;
import com.automate.utils.ExtentReportManager;
import com.aventstack.extentreports.ExtentReports;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseUI {

	public WebDriver $driver = null;
	private WebDriverWait _wait = null;
	private int defaultTimeOut = 30;
	private static String elem = null;
	private static Properties prop;
	ExtentReports report= ExtentReportManager.getReports();
	GetProperty $property=new GetProperty();
	Dateutils date=new Dateutils();


	public void invokeBrowser(String browser) {
		System.out.println("Inside method");
		String brow = GetProperty.getBrowserdetails(browser);
		if (brow.equalsIgnoreCase("chrome")) {
			System.out.println("Inside first if statement");
			WebDriverManager.chromedriver().setup();
			// $options.addArguments("--Incognito");
			$driver = new ChromeDriver();

		} else if (brow.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			// $options.addArguments("--Incognito");
			$driver = new FirefoxDriver();
		} else {
			System.out.println("We dont have other browser driver Info");
		}
		$driver.manage().window().maximize();
	}

	public void openURL(String weburl) {
		$driver.get(GetProperty.getApplicationUrl(weburl));
		$driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

	}

	public void closetab() {
		$driver.close();
	}

	public void quitBrowser() {

		$driver.quit();

	}

	public void entertext(String ele, String text) {

		_wait = new WebDriverWait($driver, defaultTimeOut);
		WebElement element = getElement(ele);
		_wait.until(ExpectedConditions.visibilityOf(element));
		if (text.contains("user")) {
			element.sendKeys(GetProperty.getUserName(text));
		} else
			element.sendKeys(GetProperty.getUserName(text));
	}

	public void clickelement(String ele) {
		_wait = new WebDriverWait($driver, defaultTimeOut);
		WebElement element = getElement(ele);
		_wait.until(ExpectedConditions.visibilityOf(element));
		element.click();

	}

	@SuppressWarnings("deprecation")
	public void WaitForPageLoad(WebDriver driver) {
		_wait = new WebDriverWait(driver, 60);
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver driver) {

				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		_wait.until(expectation);
	}
	
	public  WebElement getElement(String element) {
		GetProperty.setPropertiesFileReaderPath();
		System.out.println(element);
		System.out.println(GetProperty.elementProperty(element));
		elem = GetProperty.elementProperty(element);
		try {
		if (element.contains("xpath")) {
			return $driver.findElement(By.xpath(elem));
		} else if (element.contains("id")) {
			return $driver.findElement(By.id(elem));
		} else if (element.contains("css")) {
			return $driver.findElement(By.cssSelector(elem));
		} else if (element.contains("linktext")) {
			return $driver.findElement(By.linkText(elem));
		}else
			throw new RuntimeException("Element not specified in the Configuration.properties file.");
		}catch(Exception E) {
			reportFail(E.getMessage());
			
		}
		return null;
	}
	
	public void reportFail(String reportMessage) {
		
	}

	public void reportPass(String reportMessage) {
		
	}

	public void takeScreenshotFailure() {
		TakesScreenshot takescreenshot= (TakesScreenshot) $driver;
		File sourfile=takescreenshot.getScreenshotAs(OutputType.FILE);
		
		File dest=new File(System.getProperty("user.dir")+"\\screenshots\\"+ date.GetTimeStamp()+".png");
		
	}

}
