package com.centurylink.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import com.centurylink.model.Developer;
import com.centurylink.model.Employee;
import com.centurylink.model.Manager;
import com.centurylink.model.QATester;
import com.centurylink.model.RoleAndAllocation;
import com.centurylink.parser.CompanyITDeptXMLParser;

public class MonthlyExpenseCalculatorImpl implements MonthlyExpenseCalcFactory {
	private int managerCount = 0;
	private int developerCount = 0;
	private int qaTesterCount = 0;
	private int monthlyAllocationAmount = 0;
	List<Employee> reportingEmployees = null;
	int allocationAmount;
	Manager mgr = null;

	List<Employee> EmployeeListByRole = new ArrayList<Employee>();

	/**
	 * @param mgr
	 * @return
	 * @throws Exception
	 */
	private int calculateAllocationByManager(Manager mgr) throws Exception {
		List<Employee> reportingEmployees = null;
		CompanyITDeptXMLParser test = new CompanyITDeptXMLParser();
		String mgrName = mgr.getEmpName();

		reportingEmployees = test.findManagerId(mgrName);

		boolean flag = false;
		try{
			for (Employee emp : reportingEmployees) {
				if (emp.getEmpName().equalsIgnoreCase(mgrName)) {
					flag = true;
				}
			}
			if (!flag)
				throw new Exception("Not valid record: " + mgr.getEmpName());
	
			Manager manager = decideModelObj(mgrName, reportingEmployees);
			if (manager != null)
				allocationAmount = 0;
	
			if (manager != null && manager.getReportingEmployees() != null && manager.getReportingEmployees().size() > 0) {
				for (Employee e : manager.getReportingEmployees()) {
					if ("Developer".equalsIgnoreCase(e.getRole())) {
						developerCount++;
					} else if ("QATester".equalsIgnoreCase(e.getRole())) {
						qaTesterCount++;
					} else if ("Manager".equalsIgnoreCase(e.getRole())) {
						managerCount++;
					}
				}
			} else
				return allocationAmount;
			}catch(Exception e){}
		return calculateAllocationAmount();
	}

	/**
	 * @param mgrName
	 * @param reportingEmployees
	 * @return
	 * @throws Exception
	 */
	private Manager decideModelObj(String mgrName, List<Employee> reportingEmployees) throws Exception {

		String ManagerEmpId = null;
		List<String> mgrEmpId = new ArrayList<String>();
		try{
		for (Employee e : reportingEmployees) {
			if (mgrName.equals(e.getEmpName()) && e.getRole().equalsIgnoreCase("Manager")) {
				ManagerEmpId = e.getEmpId();
				mgrEmpId.add(ManagerEmpId);
			}
			if (e.getRole().equalsIgnoreCase("Manager") && mgrEmpId.contains(e.getManagerId())) {
				mgrEmpId.add(e.getEmpId());
			}

		}

		for (Employee e : reportingEmployees) {
			if (mgrName.equals(e.getEmpName()) && "Manager".equals(e.getRole())) {
				mgr = new Manager(e.getEmpName());
				mgr.addReportee(new Manager(e.getEmpName(), e.getRole()));
				EmployeeListByRole.add(mgr);
			} else if (ManagerEmpId != null && "Manager".equals(e.getRole()) && mgrEmpId.contains(e.getManagerId())) {
				mgr.addReportee(new Manager(e.getEmpName(), e.getRole()));
				EmployeeListByRole.add(mgr);
			} else if (mgrEmpId.size() > 0 && "Developer".equals(e.getRole()) && mgrEmpId.contains(e.getManagerId())) {
				mgr.addReportee(new Manager(e.getEmpName(), e.getRole()));
				EmployeeListByRole.add(mgr);
			} else if (mgrEmpId.size() > 0 && "QATester".equals(e.getRole()) && mgrEmpId.contains(e.getManagerId())) {
				mgr.addReportee(new Manager(e.getEmpName(), e.getRole()));
				EmployeeListByRole.add(mgr);
			}
		}
		}catch(Exception e){}
		return mgr;
	}

	/**
	 * @return
	 */
	private int calculateAllocationAmount() {
		int developerAllocation = 0;
		int qaTesterAllocation = 0;
		int managerAllocation = 0;

		if (developerCount > 0) {
			developerAllocation = developerCount * RoleAndAllocation.getDeveloperallocation();
		}
		if (qaTesterCount > 0) {
			qaTesterAllocation = qaTesterCount * RoleAndAllocation.getQatesterallocation();
		}
		if (managerCount > 0) {
			managerAllocation = managerCount * RoleAndAllocation.getManagerallocation();
		}
		return developerAllocation + qaTesterAllocation + managerAllocation;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {

		try {
			MonthlyExpenseCalcFactory test = new MonthlyExpenseCalculatorImpl();
			// Employee emp = new Employee("Ondrej");

			// Manager mgr = new Manager("Jainag");
			// Manager mgr = new Manager("Subhash Kapoor");
			// Manager mgr = new Manager("SSS");
			// Manager mgr = new Manager("Willam Hoffman");
			// Manager mgr = new Manager("Dinesh Raj");
			// Manager mgr = new Manager("Selva");
			// Manager mgr = new Manager("Selva");
			// mgr.addReportee(emp);
			// test.calculateAllocationByManager(mgr);
			String name = "";
			
			Scanner scanner = new Scanner(System.in);

		    //  prompt for the user's name
		    System.out.println("Enter Manager name: \n");
		    System.out.println(" Current Employee List : ");
		    System.out.println(" ---------------------------------------------------------------------------------------------------------------------------------------------");
		    System.out.println(""
		    		+ "<Employee_Info>\n"+
		    		
 "<Employee  Employee_ID=\"100\"  name= \"Willam\" 	isManager = 'Y'  role = \"Manager\" 	manager_ID = \"-1\"></Employee>\n" +
 "<Employee  Employee_ID=\"200\"  name= \"Jainag\" 			isManager = 'Y'  role = \"Manager\" 	manager_ID = \"100\"></Employee>\n"  +
" <Employee  Employee_ID=\"300\"  name=\"Dinesh\"  		isManager = 'N'  role = \"Developer\"  	manager_ID = \"200\"></Employee> \n" +  
" <Employee  Employee_ID=\"400\" name = \"AnushPogosova\" 	isManager = 'N'  role = \"QATester\" 	manager_ID = \"200\"></Employee> \n" + 
" <Employee  Employee_ID=\"500\" name=\"Ondrej\" 			isManager = 'N'  role = \"Developer\"  	manager_ID = \"100\"></Employee> \n" +  
" <Employee  Employee_ID=\"600\" name=\"Emil\" 				isManager = 'N'  role = \"QATester\" 	manager_ID = \"100\"></Employee> \n" +  
" <Employee  Employee_ID=\"700\" name=\"Kay\"  				isManager = 'N'  role = \"QATester\"  	manager_ID = \"100\"></Employee> \n" + 
" <Employee  Employee_ID=\"800\" name= \"Subhash\" 	isManager = 'Y'  role = \"Manager\" 	manager_ID = \"-1\"></Employee>\n " + 
" <Employee  Employee_ID=\"900\" name= \"Selva\" 			isManager = 'Y'  role = \"Manager\" 	manager_ID = \"200\"></Employee> \n" 
     +
"</Employee_Info>"
		    		+ "");
		    System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
		    // get their input as a String
		    name = scanner.next();

		    // prompt for their age
		    System.out.print("Manager name given : " + name + "\n");

			
			System.out.println(	"Monthly Allocation for Manager '" + name + "' is " + test.calculateAllocationByManager(name));
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * List<Employee> reportingEmployees = null; CompanyITDeptXMLParser test
		 * = new CompanyITDeptXMLParser(); reportingEmployees =
		 * test.callMethod();
		 */
	}

	@Override
	public int calculateAllocationByManager(String managerName) throws Exception {
		Manager mgr = new Manager(managerName);
		// Manager mgr = new Manager("Willam Hoffman");
		return calculateAllocationByManager(mgr);
	}

}
