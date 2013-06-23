package beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

import core.EmployeeManager;
import core.IEmployee;
import core.IEmployeeManager;

public class EmployeeList {

	private List<String> employees;
	
	private IEmployee currentSelectedEmployee = null;
	
	public EmployeeList() {
		this.employees = new ArrayList<String>();
		//fillEmployees();
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
	
	public IEmployee getCurrentSelectedEmployee() {
		System.out.println("CurrentSelectedEmployee gotten");
		return this.currentSelectedEmployee;
	}
	
	public void changeCurrentSelectedEmployee(ValueChangeEvent event) {
		System.out.println("CurrentSelectedEmployee changed");
		this.currentSelectedEmployee = (IEmployee) event.getNewValue();
	}
}
