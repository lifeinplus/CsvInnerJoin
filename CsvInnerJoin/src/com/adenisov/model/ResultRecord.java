package com.adenisov.model;

public class ResultRecord {

	private String key;
	private String firstValue;
	private String secondValue;

	public ResultRecord() {
	}
	
	public ResultRecord(String key, String firstValue, String secondValue) {
		this.key = key;
		this.firstValue = firstValue;
		this.secondValue = secondValue;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getFirstValue() {
		return firstValue;
	}

	public void setFirstValue(String firstValue) {
		this.firstValue = firstValue;
	}

	public String getSecondValue() {
		return secondValue;
	}

	public void setSecondValue(String secondValue) {
		this.secondValue = secondValue;
	}

	@Override
	public String toString() {
		return getKey() + "," + getFirstValue() + "," + getSecondValue();
	}
}
