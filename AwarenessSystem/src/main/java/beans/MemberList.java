package beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.EmployeeManager;
import core.IEmployee;
import core.IEmployeeManager;

public class MemberList {
	
	private List<String> members;
	
	public MemberList() {
		this.members = new ArrayList<String>();
	}
	
	public List<String> getMembers() {
		return this.members;
	}
	
	public void addMember(String name) {
		System.out.println(name);
		System.out.println("addMember");
		IEmployeeManager manager = new EmployeeManager();
		boolean alreadyAdded = false;
		for(String member: this.members) {
			if(member.equals(name)){
				alreadyAdded = true;
				break;
			}				
		}
		if(!alreadyAdded) {
			try {
				for(IEmployee employee: manager.getEmployees()){
					if(employee.getName().equals(name)) {
						this.members.add(name);
						System.out.println(name + " added");
						break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}		
		} else {
			System.out.println(name + " already added.");
		}
		System.out.println("addMember left");
		System.out.println("Member's size: " + this.members.size());
	}
}
