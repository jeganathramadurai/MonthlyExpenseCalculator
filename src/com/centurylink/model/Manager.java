package com.centurylink.model;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Employee {
	
	private String dept;
	private String reportingManager;
	
	
	public Manager(String empName) {
		super(empName);
		}
	
	public Manager(String empName, String empRole) {
		super(empName,empRole);
		}
	
	List<Employee> reportingEmployees = new ArrayList<Employee>();
	private int totalAllocation = RoleAndAllocation.getManagerallocation();
	
	
	public boolean addReportee(Employee emp){
		boolean added = false;
		
		if (emp != null){
			reportingEmployees.add(emp);		
			added = true;
		}
		
		return added;
	}
	
	public boolean deleteReportee(Employee emp){
		boolean deleted = false;
		
		if (emp != null){
			
			if (reportingEmployees.contains(emp)){
				reportingEmployees.remove(emp);
				deleted = true;
			}
	
		}
		
		return deleted;
	}
	
	//hashcode
	//equals
	

	@Override
	public String toString() {
		//return "Manager [ empName = " + getEmpName() + " Role :"+ getRole()+ "]";
		
		return "Manager [ empName = " + getEmpName() + " Role :"+ getRole()+ " dept=" + dept + ", reportingManager=" + reportingManager + ", reportingEmployees="
		+ reportingEmployees + ", totalAllocation=" + totalAllocation + "]";
	}

	public String getDept() {
		return dept;
	}

	public List<Employee> getReportingEmployees() {
		return reportingEmployees;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getReportingManager() {
		return reportingManager;
	}

	public void setReportingManager(String reportingManager) {
		this.reportingManager = reportingManager;
	}
	

}
