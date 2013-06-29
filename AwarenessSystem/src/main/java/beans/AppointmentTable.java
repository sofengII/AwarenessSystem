package beans;

import google.Appointment;
import google.IAppointment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gdata.data.DateTime;

import core.EmployeeManager;
import core.IEmployee;
import core.IEmployeeManager;

public class AppointmentTable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<IAppointment> appointments;
	private Map<Integer, List<IAppointment>> appointmentMap = new HashMap<Integer, List<IAppointment>>(); 
	//private List<String> appointments;
	
	private IEmployeeManager employeeManager;
	
	private String startDay;
	
	public AppointmentTable() {
		this.employeeManager = new EmployeeManager();
	}
	
	public IEmployeeManager getEmployeeManager(){
		return this.employeeManager;
	}
	
	public void setEmployeeManager(IEmployeeManager employeeManager) {
		this.employeeManager = employeeManager;
	}
	
	public List<IAppointment> getAppointments(){
		return this.appointments;
	}
	
	public Map<Integer, List<IAppointment>> getAppointmentMap() {
		return this.appointmentMap;
	}
	
	public List<Integer> getSortedKeys(String startDate) {
		System.out.println("Keys sorted");
		int startDay = extractDay(startDate);
		if(startDay == -1)
			startDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
		Integer[] keys = new Integer[5];
		int index = 0;
		for(int i = 0; i < 7; i++) {
			int tmpDay = (startDay + i) % 7;
			if(tmpDay != 0 && tmpDay != 6) {
				keys[index] = tmpDay;
				index++;
			}
		}
		return Arrays.asList(keys);
	}
	
	private int extractDay(String startDate) {
		String dayAsString = startDate.split(" ")[0];
		switch(dayAsString){
			case "So": return 0;
			case "Mo": return 1;
			case "Di": return 2;
			case "Mi": return 3;
			case "Do": return 4;
			case "Fr": return 5;
			case "Sa": return 6;
			default: return -1;
		}
	}
	/*public void setAppointments(List<IAppointment> appointments) {
		this.appointments = appointments;
		 //this.appointmentBeginning = this.employeeManager.getAppointments(employees, startDate, duration);
	}*/
	
	public void setAppointments(List<String> employeeNames, String startDateString, String durationString) {
		System.out.println("Size of map: " + this.appointmentMap.size());
		this.appointmentMap.clear();
		try {
			List<IEmployee> employees = this.employeeManager.getEmployees();
			List<IEmployee> employeesToMatch = new ArrayList<IEmployee>();
			for(IEmployee employee: employees) {
				for(String name: employeeNames) {
					if(employee.getName().equals(name)) {
						employeesToMatch.add(employee);
						System.out.println(name);
						break;
					}						
				}
			}
			
			DateTime startDate;
			
			if(startDateString.equalsIgnoreCase("") || startDateString.equalsIgnoreCase("Now")) {
				
				startDate = new DateTime(new Date().getTime());
				
			} else {
				
				Calendar c = Calendar.getInstance();

				String[] splittedDate = startDateString.split(" ");
				this.startDay = splittedDate[0];
				String[] date = splittedDate[1].split("\\.");
				int day = Integer.parseInt(date[0]);
				int month = Integer.parseInt(date[1]);
				int year = Integer.parseInt(date[2]);

				c.set(year, month - 1, day, 0, 0, 0);
				c.set(Calendar.MILLISECOND, 0);
				
				startDate = new DateTime(c.getTimeInMillis());
			}	
			
			DateTime duration;
			
			if(durationString.split(" ")[0].equalsIgnoreCase("")) {
				
				duration = new DateTime(3600000);
	
			} else {
				
				duration = new DateTime(3600000 * Long.parseLong(durationString.split(" ")[0]));
			}
			
			List<IAppointment> appointments = this.employeeManager.getAppointments(employeesToMatch, startDate, duration);
			// Initialize map
			for(int i = 1; i < 6; i++) {
				List<IAppointment> defaultList = new ArrayList<IAppointment>();
				this.appointmentMap.put(i, defaultList);
			}
			for(IAppointment appointment: appointments) {
				Calendar date = Calendar.getInstance();
				date.setTime(new Date(appointment.getStartTime().getValue()));
				int day = date.get(Calendar.DAY_OF_WEEK) - 1;
				// If Saturday or Sunday skip
				if(day != 0 && day != 6) {
					List<IAppointment> appointmentList = (List<IAppointment>)this.appointmentMap.get(day);
					appointmentList.add(appointment);
					this.appointmentMap.put(day, appointmentList);
				}
			}
			
			
			/*if(this.appointments.get(this.appointments.size()-1).getEndTime().getValue() > startDate.getValue()+432000000){
				System.out.println("Letztes Appointment der setAppointments-Methode dauert bis:");
				System.out.println(this.appointments.get(this.appointments.size()-1).getEndTime());
				System.out.println("und wird deshalb aus der Liste entfernt.");
				this.appointments.remove(this.appointments.size()-1);
			}*/
			
			/*System.out.println("Appointments nach der setAppointments-Methode:");
			for(IAppointment test : this.appointments){
				System.out.println(test.getStartTime().toUiString() + " --- " + test.getEndTime().toUiString());
			}*/
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void setAppointments_Test() {
		System.out.println("Test");
		this.appointments = new ArrayList<IAppointment>();
		for(int i = 0; i < 5; i++) {
			Calendar calendarStart = Calendar.getInstance();
			calendarStart.set(Calendar.DAY_OF_MONTH, calendarStart.get(Calendar.DAY_OF_MONTH) + i);
			calendarStart.set(Calendar.HOUR_OF_DAY, 6+i);
			calendarStart.set(Calendar.MINUTE, 0);
			Calendar calendarEnd = calendarStart;
			calendarEnd.set(Calendar.HOUR_OF_DAY, 6+i+1);
			IAppointment app = new Appointment(new DateTime(calendarStart.getTime()),new DateTime(calendarEnd.getTime()));
			this.appointments.add(app);
			calendarStart.set(Calendar.HOUR_OF_DAY, 7+i);
			calendarStart.set(Calendar.HOUR_OF_DAY, 7+i+1);
			app = new Appointment(new DateTime(calendarStart.getTime()), new DateTime(calendarEnd.getTime()));
			this.appointments.add(app);
			calendarStart.set(Calendar.HOUR_OF_DAY, 8+i);
			calendarStart.set(Calendar.HOUR_OF_DAY, 8+i+1);
			app = new Appointment(new DateTime(calendarStart.getTime()), new DateTime(calendarEnd.getTime()));
			this.appointments.add(app);
			calendarStart.set(Calendar.HOUR_OF_DAY, 9+i);
			calendarStart.set(Calendar.HOUR_OF_DAY, 9+i+1);
			app = new Appointment(new DateTime(calendarStart.getTime()), new DateTime(calendarEnd.getTime()));
			this.appointments.add(app);
		}
	}
	
	public List<String> getAppointmentStrings(int dayOfWeek) {
		
		System.out.println("getAppointmentStrings mit " + dayOfWeek + " aufgerufen...");
		List<String> appointmentsAsString = new ArrayList<String>();
		
		for(IAppointment appointment: this.appointmentMap.get(dayOfWeek)) {
			System.out.println("Appointment added");
			String appointmentString = appointment.getStartTime().toUiString().split(" ")[1] + " - " + appointment.getEndTime().toUiString().split(" ")[1];
			appointmentsAsString.add(appointmentString);
		}
			
		return appointmentsAsString;
		/*
		List<String> dailyAppointments = new LinkedList<String>();
		Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("CEST"));
		
		for(IAppointment app : this.appointments){
			calendar.setTimeInMillis(app.getStartTime().getValue());
			if(calendar.get(Calendar.DAY_OF_WEEK) == dayOfWeek){
				String timeSlot = app.getStartTime().toUiString().substring(11) + " - " + app.getEndTime().toUiString().substring(11);
				dailyAppointments.add(timeSlot);
			}
		}
		*/
		
		
		/*Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("CEST"));
		for(IAppointment app: this.appointments) {
			calendar.setTimeInMillis(app.getStartTime().getValue());
			if(calendar.get(Calendar.DAY_OF_WEEK) == dayOfWeek){
				String timeSlot = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
				timeSlot += " - ";
				calendar.setTimeInMillis(app.getEndTime().getValue());
				timeSlot += calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
				dailyAppointments.add(timeSlot);
			}
		}*/
		
		/*
		System.out.println("Appointments nach der getAppointmentStrings-Methode:");
		for(String s : dailyAppointments){
			System.out.println(s);
		}
		
		return dailyAppointments;
		*/
	}
	
	public List<String> getAppointmentStrings_Test(int dayOfWeek) {
		List<String> appointments = new LinkedList<String>();
		for(int i = 0; i < 4; i++) {
				String timeSlot = (6+i) + ":00";
				timeSlot += " - ";
				timeSlot += (6+i+1) + ":00";
				appointments.add(timeSlot);
		}
		return appointments;
	}
	
	public int dayToInteger(String day) {
		System.out.println("Day " + day + "was gotten.");
		switch(day) {
			case "So": return 0;
			case "Mo": return 1;
			case "Di": return 2;
			case "Mi": return 3;
			case "Do": return 4;
			case "Fr": return 5;
			case "Sa": return 6;
			default: return -1;
		}
	}
	
	public String integerToDay(int day) {
		System.out.println("Day " + day + "was gotten.");
		switch(day) {
			case 0: return "So";
			case 1: return "Mo";
			case 2: return "Di";
			case 3: return "Mi";
			case 4: return "Do";
			case 5: return "Fr";
			case 6: return "Sa";
			default: return "Undefined";
		}
	}
}
