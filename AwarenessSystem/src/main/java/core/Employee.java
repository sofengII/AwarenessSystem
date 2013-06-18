package core;

import google.Calendar;
import google.IAppointment;
import google.ICalendar;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;

import com.google.gdata.data.DateTime;
import com.google.gdata.util.ServiceException;

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
	private avaliable avaliable;
	/**
	 * projectName of the employee
	 */
	private String projectName;
	/**
	 * google calendar of the employee
	 */
	private final ICalendar calendar;

	private List<IAppointment> freeAppointments = new ArrayList<IAppointment>();

	/**
	 * Constructor for an employee. Every new employee will be initialized with
	 * availability UNINITIALIZED.
	 * 
	 * @param employeeID
	 *            id of an employee
	 * @param name
	 *            name of an employee
	 * @param picturePath
	 *            path to the picture
	 * @param link
	 *            private link to google calendar of an employee
	 * @param startWeek
	 *            startDate of the visible Week
	 * @param endWeek
	 *            endDate of the visible Week
	 */
	public Employee(int employeeID, String name, String picturePath,
			String link, DateTime startWeek, DateTime endWeek) {
		this.employeeID = employeeID;
		this.name = name;
		this.thumbnail = new ImageIcon(picturePath).getImage();
		this.projectName = "";
		this.calendar = new Calendar(link, startWeek, endWeek);
		avaliable = IEmployee.avaliable.UNINITIALIZED;
	}

	/**
	 * Constructor for an employee. Every new employee will be initialized with
	 * availability UNINITIALIZED.
	 * 
	 * @param employeeID
	 *            id of an employee
	 * @param name
	 *            name of an employee
	 * @param picturePath
	 *            path to the picture
	 * @param link
	 *            link to google calendar of an employee
	 * @param projectName
	 *            name of the project
	 * @param startWeek
	 *            startDate of the visible Week
	 * @param endWeek
	 *            endDate of the visible Week
	 */
	public Employee(int employeeID, String name, String picturePath,
			String link, DateTime startWeek, DateTime endWeek,
			String projectName) {
		this.employeeID = employeeID;
		this.name = name;
		this.thumbnail = new ImageIcon(picturePath).getImage();
		this.projectName = projectName;
		this.calendar = new Calendar(link, startWeek, endWeek);
		avaliable = IEmployee.avaliable.UNINITIALIZED;
	}

	@Override
	public avaliable getAvaliable() {
		updateAvaliability();
		return avaliable;
	}
	
	/**
	 * This method checks whether a employee has an appointment or not 
	 * and updates his status
	 */
	private void updateAvaliability() {

		java.util.Calendar cal = java.util.Calendar.getInstance();
		
		//Zum testen, Zeit vorgeben
		/*cal.set(java.util.Calendar.DAY_OF_MONTH, 20);
		//+2 wegen Zeitzone
		cal.set(java.util.Calendar.HOUR_OF_DAY, 12);
		cal.set(java.util.Calendar.MINUTE, 10);
		cal.set(java.util.Calendar.SECOND,0);
		cal.set(java.util.Calendar.MILLISECOND,0);
		Date actualTime = cal.getTime();
		System.out.println("Date: " + actualTime.toString());
		*/
		
		cal.set(java.util.Calendar.HOUR_OF_DAY, cal.get(java.util.Calendar.HOUR_OF_DAY));
		Date actualTime = cal.getTime();
		
		// 15 min
		long fiftyMinutes = 900000;
		
		if(calendar.getAppointments().size() == 0) {
			System.out.println("blöd");
			avaliable = IEmployee.avaliable.FREE;
			return;
		}
		
		else {
			
			for(IAppointment appointment : calendar.getAppointments()) {
				
				// current time is in the 15 minutes gap before a appointment starts
				if (actualTime.getTime() < appointment.getStartTime().getValue()
						&& actualTime.getTime() >= appointment.getStartTime()
								.getValue() - fiftyMinutes) {
					avaliable = IEmployee.avaliable.SHORTLYFREE;
					return;
				}

				// current time is between two appointments
				if (appointment.getStartTime().getValue() <= actualTime
						.getTime()
						&& actualTime.getTime() <= appointment.getEndTime()
								.getValue() + fiftyMinutes) {
					avaliable = IEmployee.avaliable.BUSY;
					return;
				}
				
				
			}
			avaliable = IEmployee.avaliable.FREE;	
		}
	}

	@Override
	public Image getThumbnail() {
		return thumbnail;
	}

	@Override
	public List<IAppointment> getAppointments() {
		return calendar.getAppointments();
	}

	@Override
	public List<IAppointment> getAppointments(DateTime start, DateTime end)
			throws IOException, ServiceException {
		return calendar.getAppointments(start, end);
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

	@Override
	public List<IAppointment> getFreeAppointments() {
		return freeAppointments;
	}

	@Override
	public void addFreeAppointment(IAppointment app) {
		this.freeAppointments.add(app);
	}

	@Override
	public void resetFreeAppointments() {
		this.freeAppointments = null;
	}

}
