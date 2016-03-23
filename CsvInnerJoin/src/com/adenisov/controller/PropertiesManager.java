package com.adenisov.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.adenisov.model.ConfigBean;

public class PropertiesManager {

	private static final String PROPERTIES_FILE = "config.properties";
	
	public ConfigBean loadProperties() throws IOException {
		
		Properties properties = new Properties();
		ConfigBean configBean = new ConfigBean();
		
		File file = new File(PROPERTIES_FILE);
		
		if (file.exists() && !file.isDirectory()) {
			
			try (FileReader reader = new FileReader(file)) {
				
				properties.load(reader);
				configBean.setFileA(properties.getProperty("fileA"));
				configBean.setFileB(properties.getProperty("fileB"));
				configBean.setResultFile(properties.getProperty("resultFile"));
				configBean.setNewLineSeparator(properties.getProperty("newLineSeparator"));
			}
		}
		
		return configBean;
	}

}
