package com.centurylink.model;

public class RoleAndAllocation {
	private static final int managerId = 1;
	private static final int developerId = 2;
	private static final int qaTesterId = 3;
	
	private static final int managerAllocation = 300;
	private static final int developerAllocation = 1000;
	private static final int qaTesterAllocation = 500;
	
	
	public static int getManagerid() {
		return managerId;
	}
	public static int getDeveloperid() {
		return developerId;
	}
	public static int getQatesterid() {
		return qaTesterId;
	}
	public static int getManagerallocation() {
		return managerAllocation;
	}
	public static int getDeveloperallocation() {
		return developerAllocation;
	}
	public static int getQatesterallocation() {
		return qaTesterAllocation;
	}

}
