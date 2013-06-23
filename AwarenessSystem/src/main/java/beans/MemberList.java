package beans;

import java.util.ArrayList;
import java.util.List;

import core.IEmployee;

public class MemberList {
	
	private List<IEmployee> members;
	
	public MemberList() {
		this.members = new ArrayList<IEmployee>();
	}
	
	public List<IEmployee> getMembers() {
		return this.members;
	}
	
	public void addMember(IEmployee employee) {
		System.out.println("Member added");
		this.members.add(employee);
	}
}
