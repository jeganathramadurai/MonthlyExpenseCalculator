package com.centurylink.parser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.centurylink.model.Developer;
import com.centurylink.model.Employee;
import com.centurylink.model.Manager;
import com.centurylink.model.QATester;

public class CompanyITDeptXMLParser {
	Document doc = null;
	private List<Employee> empList = new ArrayList();
	private Employee emp = null;

	public List<Employee> getEmpList() {
		return empList;
	}

	public List<Employee> callMethod(String managerName) throws ParserConfigurationException {
		CompanyITDeptXMLParser t = new CompanyITDeptXMLParser();

		t.readXml();
		return t.findManagerId(managerName);
	}

	public List<Employee> findManagerId(String mgrName) throws ParserConfigurationException {
		try {
			readXml();
		} catch (Exception e) {
		}

		NodeList nList = doc.getElementsByTagName("Employee");

		String empId = null;
		String empName = null;
		String role = null;
		String isManager = null;
		String managerId = null;
		Element eElement = null;

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);

			eElement = (Element) nNode;
			empId = eElement.getAttribute("Employee_ID");
			empName = eElement.getAttribute("name");
			role = eElement.getAttribute("role");
			isManager = eElement.getAttribute("isManager");
			managerId = eElement.getAttribute("manager_ID");

			emp = new Employee(empId, empName, isManager, role, managerId);
			/*
			 * emp.setEmpId(empId); emp.setEmpName(empName); emp
			 * emp.setRole(role);
			 */

			empList.add(emp);

		}

		return empList;

	}

	File fXmlFile = null;

	public void readXml() throws ParserConfigurationException {

		fXmlFile = new File("C:/workspace/MonthlyExpenseCalculator/src/company_IT_dept.xml");

		//System.out.println("Test Project");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		try {
			doc = dBuilder.parse(fXmlFile);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("Employee");

	}

}
