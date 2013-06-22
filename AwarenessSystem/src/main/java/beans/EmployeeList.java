package beans;

import java.util.ArrayList;
import java.util.List;

import core.Employee;

public class EmployeeList {

	private List<String> employees;
	
	public EmployeeList() {
		this.employees = new ArrayList<String>();
		fillEmployees();
	}
	
	private void fillEmployees() {
		this.employees.add(new Employee(1,"Huber",null,null).getName());
		this.employees.add(new Employee(2,"Meier",null,null).getName());
		this.employees.add(new Employee(3,"Muster",null,null).getName());
		this.employees.add(new Employee(4,"Mann",null,null).getName());
	}
	
	public List<String> getEmployees() {
		return this.employees;
	}
}
