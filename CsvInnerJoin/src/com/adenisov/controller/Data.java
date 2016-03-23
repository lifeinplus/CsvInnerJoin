package com.adenisov.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.adenisov.model.ConfigBean;
import com.adenisov.model.InputRecord;
import com.adenisov.model.ResultRecord;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

public class Data {

	private ConfigBean config;
	
	public Data() {
		try {
			PropertiesManager properties = new PropertiesManager();
			this.config = properties.loadProperties();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<InputRecord> readAndParseFileToList(String filename) {
		
		List<InputRecord> records = null;
		
		try (CSVReader reader = new CSVReader(new BufferedReader(new FileReader(filename)))) {
			
			CsvToBean<InputRecord> csvToBean = new CsvToBean<>();
			records = csvToBean.parse(getMappingStrategy(), reader);
			
		} catch (IOException e) {
			System.out.println("ERROR:  " + e.getMessage());
		}
		
		return records;
	}
	
	private ColumnPositionMappingStrategy<InputRecord> getMappingStrategy() {
		
		ColumnPositionMappingStrategy<InputRecord> strategy = new ColumnPositionMappingStrategy<>();
		strategy.setType(InputRecord.class);
		strategy.setColumnMapping(new String[] { "key", "value" });
		
		return strategy;
	}

	private List<ResultRecord> compareData(List<InputRecord> recordsA, List<InputRecord> recordsB) throws Exception {
		
		List<ResultRecord> resultRecords = new ArrayList<>();
		
		if (recordsA.isEmpty() || recordsB.isEmpty()) {
			throw new Exception("ERROR: Отсутствуют данные для сравнения.");
		}
		
		for (InputRecord recordB : recordsB) {
			for (InputRecord recordA : recordsA) {
				if (recordB.getKey().equals(recordA.getKey())) {
					resultRecords.add(new ResultRecord(recordA.getKey(), recordA.getValue(), recordB.getValue()));
				}
			}
		}
		
		return resultRecords;
	}

	private void writeDataToResultFile(String resultFile, List<ResultRecord> resultRecords) throws Exception {
		
		if (resultRecords.isEmpty()) {
			throw new Exception("ERROR: Сравнение не выявило одинаковых ключей.");
		}
		
		try (FileWriter fileWriter = new FileWriter(resultFile)) {

			// Попытка избежать пустой строки из-за перехода каретки в результирующем файле 
			fileWriter.append(resultRecords.get(0).toString());
			for (int i = 1; i < resultRecords.size(); i++) {
				fileWriter.append(config.getNewLineSeparator());
				fileWriter.append(resultRecords.get(i).toString());
			}
			
			fileWriter.flush();
		} catch (IOException e) {
			System.out.println("ERROR:  " + e.getMessage());
		}
	}
	
	public void produceInnerJoin() {
		List<InputRecord> recordsA = readAndParseFileToList(config.getFileA());
		List<InputRecord> recordsB = readAndParseFileToList(config.getFileB());
		
		try {
			List<ResultRecord> resultRecords = compareData(recordsA, recordsB);
			writeDataToResultFile(config.getResultFile(), resultRecords);
			System.out.println("INFO: Сравнение завершено!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
