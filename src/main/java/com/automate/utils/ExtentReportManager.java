package com.automate.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class ExtentReportManager {

	public static ExtentReports report;
	public static ExtentHtmlReporter htmlReport;
	
	public static ExtentReports getReports() {
		
		if (htmlReport == null && report ==null) {
			String reportname=Dateutils.GetTimeStamp() + ".html";
			htmlReport = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\test-output\\"+reportname);
			report = new ExtentReports();
			report.attachReporter(htmlReport);
			
			report.setSystemInfo("OS", "Windows 10 Home");
			report.setSystemInfo("Environment", "QA");
			report.setSystemInfo("Browser", "Chrome");
			
			htmlReport.config().setDocumentTitle("Test Report");
			htmlReport.config().setReportName("Rediff Home Page Test");
		}
		return report;
	}
	
		
		
}
