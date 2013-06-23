package beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import core.EmployeeManager;
import core.IEmployee;
import core.IEmployeeManager;

public class EmployeeList {

	private List<String> employees;
	
	private String currentSelectedEmployee;
	
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
	
	public String getCurrentSelectedEmployee() {
		System.out.println("CurrentSelectedEmployee gotten");
		if(this.currentSelectedEmployee != null)
			System.out.println(this.currentSelectedEmployee);
		else
			System.out.println("is null");
		return this.currentSelectedEmployee;
	}
	
	public void setCurrentSelectedEmployee(String employee) {
		System.out.println("CurrentSelectedEmployee set to " + employee);
		this.currentSelectedEmployee = employee;
	}
	
	/*public void changeCurrentSelectedEmployee(ValueChangeEvent event) {
		System.out.println("CurrentSelectedEmployee changed to " + ((IEmployee)event.getNewValue()).getName());
		this.currentSelectedEmployee = (IEmployee) event.getNewValue();
	}*/
	
	public void changeCurrentSelectedEmployee(AjaxBehaviorEvent event) {
		System.out.println("Ajax selection call");
	}
}
