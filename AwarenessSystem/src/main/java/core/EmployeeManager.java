package core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.api.client.util.DateTime;

import exception.NotFoundAppointmentException;
import google.Appointment;
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
	public List<IAppointment> getAppointments(List<IEmployee> employees,
			DateTime startDate, DateTime duration)
			throws NotFoundAppointmentException {
		
		List<IAppointment> freeAppointments = new ArrayList<IAppointment>();
		List<IAppointment> appointments = new ArrayList<IAppointment>();
		
		//For every employee...
		for(IEmployee em : employees){
			appointments = em.getAppointments();
			
			//... search for free appointments
			for(IAppointment app : appointments){
				//If (startTime of nextAppointment) - (endTime of app) >= duration
				//IAppointment freeApp = new Appointment(endTime of app + 15 minutes, startTime of nextAppointment + 15 minutes)
				//freeAppointments.add(freeApp);
			}
		}
		
		//Compare all the free appointments of the employees if there is one (or more) at the same time
		
		
		// TODO Auto-generated method stub
		return null;
	}

	

}
