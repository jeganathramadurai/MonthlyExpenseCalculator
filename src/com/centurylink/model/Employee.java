package com.centurylink.model;

import java.util.List;

public class Employee {

	
	private String empId;
	private String empName;
	private String isManager;
	private String role;
	private String managerId;
	
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getIsManager() {
		return isManager;
	}
	public void setIsManager(String isManager) {
		this.isManager = isManager;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", isManager=" + isManager + ", role=" + role
				+ ", managerId=" + managerId + "]";
	}
	public Employee(String empId, String empName, String isManager, String role, String managerId) {
		super();
		this.empName = empName;
		this.role = role;
		this.empId = empId;
		this.isManager = isManager;
		this.managerId = managerId;
	}
	
	public Employee(String empName, String role) {
		super();
		this.empName = empName;
		this.role = role;
		
	}
	public Employee(String empName2) {
		super();
		this.empName = empName2;
	}

}
