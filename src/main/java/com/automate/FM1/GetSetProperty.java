package com.automate.FM1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GetSetProperty {
	
	private static Properties prop;
	private static final String propertyFilePath = System.getProperty("user.dir")
			+ "\\src\\main\\java\\com\\heat\\utilities\\config.properties";
	private static String URL=null;
	
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
	
	public static String getApplicationUrl() {
		setPropertiesFileReaderPath();
			URL = prop.getProperty("websiteURL");
		if (URL != null)
			return URL;
		else
			throw new RuntimeException("URL not specified in the config.properties file.");

	}
	

}
