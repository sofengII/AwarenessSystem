package beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.EmployeeManager;
import core.IEmployee;
import core.IEmployeeManager;

public class EmployeeList {

	private List<String> employees;
	
	public EmployeeList() {
		this.employees = new ArrayList<String>();
		fillEmployees();
	}
	
	private void fillEmployees() {
		IEmployeeManager manager = new EmployeeManager();
		try {
			List<IEmployee> employeesList = manager.getEmployees();
			for(IEmployee employee: employeesList) {
				this.employees.add(employee.getName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getEmployees() {
		return this.employees;
	}
}
