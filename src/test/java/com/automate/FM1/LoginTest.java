package com.automate.FM1;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.automate.utils.ExtentReportManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class LoginTest extends BaseUI{
	
	//BaseUI $base=new BaseUI();
	//GetProperty $obj=new GetProperty();
	//WebDriver $driver=null;
	
	
	@Test
	public void login() {
		
		ExtentTest logger=report.createTest("First Test");
		
		logger.log(Status.INFO, "Invoke browser");
		invokeBrowser("browser");
		logger.log(Status.INFO, "Open websiteURL");
		openURL("websiteURL");
		logger.log(Status.INFO, "Click the Sign In button");
		clickelement("SignIn_xpath");
		
		logger.log(Status.INFO, "Enter the details");
		entertext("username_xpath", "username");
		entertext("password_xpath", "password");
		logger.log(Status.PASS, "TestCase passed");
		//logger.addScreenCaptureFromPath(imagePath)
		quitBrowser();
	}
	
	@AfterTest
	public void endReport() {
		report.flush();
	}

}
