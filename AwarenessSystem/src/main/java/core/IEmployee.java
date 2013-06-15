package core;

import google.IAppointment;
import google.ICalendar;

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
		UNINITIALIZED
	}

	/**
	 * Getter for the availability
	 * @return the aval
	 */
	public avaliable getAvaliable();

	/**
	 * Setter for the availability
	 * @param aval the aval to set
	 */
	public void setAvaliable(avaliable aval);

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
	public List<IAppointment> getAppointments(DateTime start, DateTime end) throws IOException, ServiceException;
	
	public List<IAppointment> getFreeAppointments();
	
	public void addFreeAppointment(IAppointment app);
	
	public void resetFreeAppointments();
}
