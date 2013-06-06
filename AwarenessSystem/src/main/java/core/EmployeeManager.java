package core;

import java.util.Date;
import java.util.List;

import com.google.api.client.util.DateTime;

import exception.NotFoundAppointmentException;
import google.IAppointment;

/**
 * This class implements methods for managing and searching appointments of employees. 
 * This methods are used to find appointments
 * 
 * @authors Dominik, Maximilian, Daniela
 * @version 1.0
 */
public class EmployeeManager implements IEmployeeManager {

	@Override
	public IAppointment getAppointment(List<IEmployee> employees)
			throws NotFoundAppointmentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IAppointment getAppointment(List<IEmployee> employees,
			DateTime duration) throws NotFoundAppointmentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IAppointment> getAppointments(List<IEmployee> empolyees,
			Date startDate, DateTime duration)
			throws NotFoundAppointmentException {
		// TODO Auto-generated method stub
		return null;
	}

	

}