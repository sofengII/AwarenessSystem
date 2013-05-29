package core;

import java.util.Date;
import java.util.List;

import com.google.api.client.util.DateTime;

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
	
	//TODO exception geworfen wenn kein Termin in den nächsten 5 Tagen gefunden wird?
	/**
	 * This method is used to obtain an appointment without consideration of the duration
	 * @param employees List of employees for which a joint appointment must be found
	 * @return
	 * @throws NotFoundAppointmentException This exception is thrown if no appointment has been found in the next five days
	 */
	public IAppointment getAppointment (List<IEmployee> employees) throws NotFoundAppointmentException;
	
	/**
	 * This method is used to obtain an appointment with consideration of the duration
	 * @param employees List of employees for which a joint appointment must be found
	 * @param duration duration of the appointment
	 * @return
	 * @throws NotFoundAppointmentException This exception is thrown if no appointment has been found in the next five days
	 */
	public IAppointment getAppointment (List<IEmployee> employees, DateTime duration) throws NotFoundAppointmentException;
		
	/**
	 * This method is used to obtain an appointment with consideration of the start time and duration
	 * @param empolyees List of employees for which a joint appointment must be found
	 * @param startDate startDate for searching an appointment
	 * @param duration duration of the appointment
	 * @return
	 * @throws NotFoundAppointmentException This exception is thrown if no appointment has been found in the next five days starting at startDate
	 */
	public List<IAppointment> getAppointments (List<IEmployee> empolyees, Date startDate, DateTime duration) throws NotFoundAppointmentException;
	

	
}
