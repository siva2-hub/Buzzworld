package com.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class Configurator {
	
	public static String readData(String key) throws Exception {
		Properties prop=new Properties();
		FileInputStream ip= new FileInputStream("config.properties");
		prop.load(ip);
		return prop.getProperty(key);
	}

}
