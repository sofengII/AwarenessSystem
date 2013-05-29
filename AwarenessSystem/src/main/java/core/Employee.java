package core;

import google.Calendar;
import google.IAppointment;
import google.ICalendar;

import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

/**
 * This class implements methods for an employee. 
 * 
 * @authors Dominik, Maximilian, Daniela
 * @version 1.0
 */
public class Employee implements IEmployee {

	/**
	 * ID of the employee
	 */
	private final int employeeID;
	/**
	 * name of the employee
	 */
	private final String name;
	/**
	 * picture of the employee
	 */
	private final Image thumbnail;
	
	/**
	 * availability of the employee
	 */
	private avaliable aval;
	/**
	 * projectName of the employee
	 */
	private String projectName;
	/**
	 * google calendar of the employee
	 */
	private final ICalendar calendar;
	
	/**
	 * Constructor for an employee. Every new employee will be initialized with availability UNINITIALIZED.
	 * @param employeeID id of an employee
	 * @param name name of an employee
	 * @param picturePath path to the picture
	 * @param link link to google calendar of an employee
	 */
	public Employee(int employeeID, String name, String picturePath, String link){		
		this.employeeID = employeeID;
		this.name = name;
		this.thumbnail = new ImageIcon(picturePath).getImage();
		this.projectName = "";
		this.calendar = new Calendar(link);
		this.setAvaliable(avaliable.UNINITIALIZED);
	}

	/**
	 * Constructor for an employee. Every new employee will be initialized with availability UNINITIALIZED.
	 * @param employeeID id of an employee
	 * @param name name of an employee
	 * @param picturePath path to the picture
	 * @param link link to google calendar of an employee
	 * @param projectName name of the project 
	 */
	public Employee(int employeeID, String name, String picturePath, String link, String projectName){	
		this.employeeID = employeeID;
		this.name = name;
		this.thumbnail = new ImageIcon(picturePath).getImage();
		this.projectName = projectName;
		this.calendar = new Calendar(link);
		this.setAvaliable(avaliable.UNINITIALIZED);
	}

	@Override
	public avaliable getAvaliable() {
		return aval;
	}

	@Override
	public void setAvaliable(avaliable aval) {
		this.aval = aval;
	}

	@Override
	public Image getThumbnail() {
		return thumbnail;
	}

	@Override
	public List<IAppointment> getAppointments() {
		//TODO calendar.getAppointments....
		return null;
	}

	@Override
	public int getEmployeeID() {
		return employeeID;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getProject() {
		return projectName;
	}
	
	@Override
	public void setProject(String projectName) {
		this.projectName = projectName;
	}
}
