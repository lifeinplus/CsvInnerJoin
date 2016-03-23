package com.adenisov.model;

public class ConfigBean {

	private String fileA;
	private String fileB;
	private String resultFile;
	private String newLineSeparator;
	
	public ConfigBean() {
	}

	public String getFileA() {
		return fileA;
	}
	public void setFileA(String fileA) {
		this.fileA = fileA;
	}
	public String getFileB() {
		return fileB;
	}
	public void setFileB(String fileB) {
		this.fileB = fileB;
	}
	public String getResultFile() {
		return resultFile;
	}
	public void setResultFile(String resultFile) {
		this.resultFile = resultFile;
	}
	public String getNewLineSeparator() {
		return newLineSeparator;
	}
	public void setNewLineSeparator(String newLineSeparator) {
		this.newLineSeparator = newLineSeparator;
	}

}
