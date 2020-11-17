package com.automate.utils;

import java.util.Date;

public class Dateutils {

	public static String GetTimeStamp() {
		Date date=new Date();
		return date.toString().replaceAll(":", "_").replaceAll(" ", "_");
		
	}
}
