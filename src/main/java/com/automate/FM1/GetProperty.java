package com.automate.FM1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GetProperty {

	private static Properties prop;
	private static final String propertyFilePath = System.getProperty("user.dir")
			+ "\\src\\test\\resources\\objectRepository\\config.properties";
	private static String URL = null;
	private static String username = null;
	private static String password = null;
	private static String browser = null;
	private static String element = null;
	
	//BaseUI base=new BaseUI();
	public static void setPropertiesFileReaderPath() {
		BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			prop = new Properties();
			try {
				prop.load(reader);
				reader.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}
	}

	public static String getBrowserdetails(String brow) {
		setPropertiesFileReaderPath();
		browser = prop.getProperty(brow);
		if (browser != null)
			return browser;
		else
			throw new RuntimeException("Browser not specified in the config.properties file.");

	}

	public static String getApplicationUrl(String url) {
		setPropertiesFileReaderPath();
		URL = prop.getProperty(url);
		if (URL != null)
			return URL;
		else
			throw new RuntimeException("URL not specified in the config.properties file.");

	}

	public static String getUserName(String name) {
		setPropertiesFileReaderPath();
		username = prop.getProperty(name);
		if (username != null)
			return username;
		else
			throw new RuntimeException("Username not specified in the Configuration.properties file.");
	}

	public static String getPassword(String pass) {
		setPropertiesFileReaderPath();
		password = prop.getProperty(pass);

		if (password != null)
			return password;
		else
			throw new RuntimeException("Password not specified in the Configuration.properties file.");
	}
	
	public static String elementProperty(String elem) {
		setPropertiesFileReaderPath();
		element = prop.getProperty(elem);

		if (element != null)
			return element;
		else
			throw new RuntimeException("Element not specified in the Configuration.properties file.");
	}

	

}
