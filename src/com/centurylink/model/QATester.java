package com.centurylink.model;

public class QATester extends Employee{

	private String reportingManager;

	public QATester(String empName, String role) {
		super(empName, role);
		this.reportingManager = reportingManager;
	}

	public String getReportingManager() {
		return reportingManager;
	}

	public void setReportingManager(String reportingManager) {
		this.reportingManager = reportingManager;
	}
	
}
