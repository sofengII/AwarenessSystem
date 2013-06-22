package core;

import java.io.IOException;
import java.util.List;

import com.google.gdata.data.DateTime;
import com.google.gdata.util.ServiceException;

import exception.NotFoundAppointmentException;
import google.IAppointment;

/**
 * This interface defines methods for managing and searching appointments of employees. 
 * This methods are used to find appointments
 * 
 * @authors Dominik, Maximilian, Daniela
 * @version 1.0
 */
public interface IEmployeeManager {
	
	/**
	 * This method is used to obtain an appointment without consideration of the duration
	 * @param employees List of employees for which a joint appointment must be found
	 * @return
	 * @throws NotFoundAppointmentException This exception is thrown if no appointment has been found in the next five days
	 * @throws ServiceException 
	 * @throws IOException 
	 */
	public List<IAppointment> getAppointments (List<IEmployee> employees) throws NotFoundAppointmentException, IOException, ServiceException;
	
	/**
	 * This method is used to obtain an appointment with consideration of the duration
	 * @param employees List of employees for which a joint appointment must be found
	 * @param duration duration of the appointment
	 * @return
	 * @throws NotFoundAppointmentException This exception is thrown if no appointment has been found in the next five days
	 * @throws ServiceException 
	 * @throws IOException 
	 */
	public List<IAppointment> getAppointments (List<IEmployee> employees, DateTime duration) throws NotFoundAppointmentException, IOException, ServiceException;
		
	/**
	 * This method is used to obtain an appointment with consideration of the start time and duration
	 * @param empolyees List of employees for which a joint appointment must be found
	 * @param startDate startDate for searching an appointment
	 * @param duration duration of the appointment
	 * @return
	 * @throws NotFoundAppointmentException This exception is thrown if no appointment has been found in the next five days starting at startDate
	 * @throws ServiceException 
	 * @throws IOException 
	 */
	public List<IAppointment> getAppointments (List<IEmployee> employees, DateTime startDate, DateTime duration) throws NotFoundAppointmentException, IOException, ServiceException;
	

	/**
	 * This method is used to get all employees 
	 * @return a list of all employees
	 * @throws IOException
	 */
	public List<IEmployee> getEmployees() throws IOException;

}
