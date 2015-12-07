package com.centurylink.factory;

//import com.centurylink.model.Manager;

/**
 * @author JR2728
 *
 */
public interface MonthlyExpenseCalcFactory {
	int calculateAllocationByManager(String managerName) throws Exception;
	
	//int calculateAllocationByManager(Manager mgr) throws Exception;
	//String[] getListOfManagersGT5000Limit() throws Exception;
	//boolean hasReportees(String managerName);
	//String[] reportingManagerList(String managerName);
 }


