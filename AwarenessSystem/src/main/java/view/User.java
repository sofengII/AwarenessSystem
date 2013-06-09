package view;

import google.Appointment;

import java.util.List;

import core.EmployeeManager;

/**
 * This class implements methods for users. 
 * The users can register themselves or login into the awareness system.
 * After this step they see their favorites and can use the program.
 * 
 * @authors Dominik, Maximilian, Daniela
 * @version 1.0
 */
public class User implements IUser{
	
	//TODO Sollte final sein, Username über Ctor erstellen.
	/**
	 * name of the user
	 */
	private String name;
	//TODO Sollte final sein, Userpassword über Ctor erstellen.
	/**
	 * password of the user
	 */
	private String password;
	/**
	 * favorite list of the user
	 */
	private List<EmployeeManager> empolyees;
	
	public User() {}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getPassword() {
		return this.password;
	}
	
	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean addFavorite(EmployeeManager empoyee) {
		
		try {
			empolyees.add(empoyee);
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public List<EmployeeManager> getFavorites() {
		return empolyees;
	}
}
