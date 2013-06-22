package core;

import exception.NoAppointmentsException;
import google.IAppointment;

import java.awt.Image;
import java.io.IOException;
import java.util.List;

import com.google.gdata.data.DateTime;
import com.google.gdata.util.ServiceException;

/**
 * This interface defines methods and an enum for the availability of employees. 
 * 
 * @authors Dominik, Maximilian, Daniela
 * @version 1.0
 */
public interface IEmployee {

	/**
	 * This enum specifies the availability of empolyees
	 * @author Dominik, Maximilian, Daniela
	 * @version 1.0
	 *
	 */
	public enum avaliable{
		FREE,
		SHORTLYFREE,
		BUSY,
		UNINITIALIZED;
	}

	/**
	 * Getter for the availability
	 * @return the aval
	 */
	public avaliable getAvaliable();
	
	/**
	 * Getter for the thumbnail
	 * @return the thumbnail
	 */
	public Image getThumbnail();
	
	/**
	 * Getter for the empolyeeID
	 * @return the employeeID
	 */
	public int getEmployeeID();

	/**
	 * Getter for the name
	 * @return the name
	 */
	public String getName();

	/**
	 * Getter for the projectName
	 * @return the projectName
	 */
	public String getProject();
	
	/**
	 * Setter for the projectName
	 * @param projectName
	 */
	public void setProject(String projectName);

	/**
	 * Setter for the calendar of the employee
	 * The period starts at startDate and ends five days later
	 * @param startDate Date of the calendar to start, if it
	 * is null, the current time will be used
	 */
	public void setCalendar(DateTime startDate);
	
	/**
	 * Getter for appointments
	 * @return list of appointments
	 */
	public List<IAppointment> getAppointments();
	
	/**
	 * Getter for appointments in a time slot from start to end
	 * @param start
	 * @param end
	 * @return
	 * @throws ServiceException 
	 * @throws IOException 
	 */
	public List<IAppointment> getAppointments(DateTime start, DateTime end) throws IOException, ServiceException, NoAppointmentsException;
	
	public List<IAppointment> getFreeAppointments();
	
	public void addFreeAppointment(IAppointment app);
	
	public void resetFreeAppointments();
	
	public String toString();
	
	
}
