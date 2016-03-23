package com.adenisov.model;

public class InputRecord {

	private String key;
	private String value;

	public InputRecord() {
	}

	public InputRecord(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return getKey() + "," + getValue();
	}
}
