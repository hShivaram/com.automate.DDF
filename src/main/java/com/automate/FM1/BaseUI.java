package com.automate.FM1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseUI {

	public WebDriver $driver=null;
	private WebDriverWait _wait = null;
	private int defaultTimeOut = 30;
	
	public void invokeBrowser(String browser) {
		
		if(browser.equals("Chrome")) {
			WebDriverManager.chromedriver().setup();
			//$options.addArguments("--Incognito");
			$driver=new ChromeDriver();
			$driver.manage().window().maximize();
		}
		else if(browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//$options.addArguments("--Incognito");
			$driver=new FirefoxDriver();
			$driver.manage().window().maximize();
		}
		else {
			System.out.println("We dont have other browser driver Info");
		}
	}
	
	public void openURL(String weburl) {
		
		$driver.get(weburl);
		$driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		
	}
	
	public void closetab() {
		$driver.close();
	}
	public void quitBrowser() {
		
		$driver.quit();
		
	}
	public void entertext(WebElement element,String text) {
		
		_wait = new WebDriverWait($driver, defaultTimeOut);
		_wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
		
	}
	
	public void clickelement(WebElement element) {
		_wait = new WebDriverWait($driver, defaultTimeOut);
		_wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
		
	}
	
	@SuppressWarnings("deprecation")
	public void WaitForPageLoad(WebDriver driver) {
		_wait = new WebDriverWait(driver, 60);
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			
		public Boolean apply(WebDriver driver) 	{
			
			return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
					.equals("complete");
		}
	};
		_wait.until(expectation);
}
}
