package core;

import exception.NoAppointmentsException;
import exception.NotFoundAppointmentException;
import google.Appointment;
import google.IAppointment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import com.google.gdata.data.DateTime;
import com.google.gdata.util.ServiceException;

/**
 * This class implements methods for managing and searching appointments of
 * employees. This methods are used to find appointments
 * 
 * @authors Dominik, Maximilian, Daniela
 * @version 1.0
 */
public class EmployeeManager implements IEmployeeManager {

	/**
	 * Start of daily work 08:00
	 */
	private static final int START_OF_WORK = 8;
	/**
	 * End of daily work 20:00
	 */
	private static final int END_OF_WORK = 20;

	@Override
	public List<IAppointment> getAppointments(List<IEmployee> employees)
			throws NotFoundAppointmentException, IOException, ServiceException {

		Calendar cal = Calendar.getInstance();

		// get the current time
		DateTime start = new DateTime(cal.getTimeInMillis() + 7200000);

		// default duration is one hour
		cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) + 1);

		// +7200000, because its CEST time zone
		DateTime dur = new DateTime(cal.getTimeInMillis() + 7200000);

		return this.getAppointments(employees, start, dur);
	}

	@Override
	public List<IAppointment> getAppointments(List<IEmployee> employees,
			DateTime duration) throws NotFoundAppointmentException,
			IOException, ServiceException {

		Calendar cal = Calendar.getInstance();

		// get the current time
		DateTime start = new DateTime(cal.getTimeInMillis() + 7200000);

		return this.getAppointments(employees, start, duration);
	}

	@Override
	public List<IAppointment> getAppointments(List<IEmployee> employees,
			DateTime startDate, DateTime duration)
			throws NotFoundAppointmentException, IOException, ServiceException {

		// Calculate the free appointments for all the given employees
		calculateFreeAppointments(employees, startDate, duration);

		List<IAppointment> allFreeAppointments = new ArrayList<IAppointment>();

		List<IEmployee> temp = new ArrayList<IEmployee>();
		temp.addAll(employees);

		for (IEmployee employee : employees) {

			List<IEmployee> remaining = new ArrayList<IEmployee>();
			remaining.addAll(temp);
			remaining.remove(employee);

			List<IAppointment> freeAppointments = findMatches(duration,
					employee, remaining);

			// Check if there were found any other free appointments that
			// weren't found before...
			for (IAppointment a : freeAppointments) {
				boolean found = false;
				for (IAppointment allA : allFreeAppointments) {
					if (allA.getStartTime().equals(a.getStartTime())
							&& allA.getEndTime().equals(a.getEndTime())) {
						found = true;
					}
				}
				// ... and if so...
				if (!found) {
					// ... add it/them.
					allFreeAppointments.add(a);
				}
			}
		}

		// Check whether a appointment takes longer than a day
		List<IAppointment> newAppointments = new ArrayList<IAppointment>();

		for (IAppointment app : allFreeAppointments) {

			Date dStart = new Date(app.getStartTime().getValue());
			Date dEnd = new Date(app.getEndTime().getValue());

			Calendar c = Calendar.getInstance();

			int dayStart = Integer.valueOf(new SimpleDateFormat("dd")
					.format(dStart));
			int dayEnd = Integer.valueOf(new SimpleDateFormat("dd")
					.format(dEnd));

			// System.out.println("DayStart = " + dayStart + ", DayEnd = " + dayEnd);
			
			
			if (dayStart != dayEnd) {

				IAppointment newAppointment;

				switch (dayEnd - dayStart) {

				case 1:
					System.out.println("Freie Zeit geht über einen Tag.");
					changeFirstPartOfAppointment(app, dStart, c);

					c.set(Calendar.HOUR_OF_DAY, START_OF_WORK);
					c.set(Calendar.MINUTE, 0);
					c.set(Calendar.SECOND, 0);
					c.set(Calendar.DAY_OF_MONTH,
							c.get(Calendar.DAY_OF_MONTH) + 1);

					newAppointment = new Appointment(new DateTime(
							c.getTimeInMillis() + 7200000), new DateTime(
							dEnd.getTime()));

					if (newAppointment.getStartTime().getValue() < dEnd
							.getTime() && ((dEnd.getTime() - newAppointment.getStartTime().getValue()) >= duration.getValue())) {
						newAppointments.add(newAppointment);
					}

					break;
					
				case 2:

					System.out.println("Freie Zeit geht über 2 Tage.");
					changeFirstPartOfAppointment(app, dStart, c);
					
					//create two more appointments
					
					//first one:
					splitAppointment(newAppointments, dStart, null, duration.getValue(),1);
					
					//second one:
					splitAppointment(newAppointments, dStart, dEnd, duration.getValue(),2);
					
					break;
					
				case 3:
					
					System.out.println("Freie Zeit geht über 3 Tage.");
					
					changeFirstPartOfAppointment(app, dStart, c);
					
					//create three more appointments
					
					splitAppointment(newAppointments, dStart, null, duration.getValue(),1);
					splitAppointment(newAppointments, dStart, null, duration.getValue(),2);
					splitAppointment(newAppointments, dStart, dEnd, duration.getValue(),3);
					
					break;
					
				case 4:
					System.out.println("Freie Zeit geht über 4 Tage.");

					changeFirstPartOfAppointment(app, dStart, c);
					
					//create four more appointments
					
					splitAppointment(newAppointments, dStart, null, duration.getValue(),1);
					splitAppointment(newAppointments, dStart, null, duration.getValue(),2);
					splitAppointment(newAppointments, dStart, null, duration.getValue(),3);
					splitAppointment(newAppointments, dStart, dEnd, duration.getValue(),4);
					
					break;
					
				case 5:
					System.out.println("Freie Zeit geht über 5 Tage.");
					
					changeFirstPartOfAppointment(app, dStart, c);
					
					//create five more appointments
					
					splitAppointment(newAppointments, dStart, null, duration.getValue(),1);
					splitAppointment(newAppointments, dStart, null, duration.getValue(),2);
					splitAppointment(newAppointments, dStart, null, duration.getValue(),3);
					splitAppointment(newAppointments, dStart, null, duration.getValue(),4);
					splitAppointment(newAppointments, dStart, dEnd, duration.getValue(),5);
					
					break;
				case 6: 
					System.out.println("Freie Zeit geht über 6 Tage.");
					
					changeFirstPartOfAppointment(app, dStart, c);
					
					//create six more appointments
					
					splitAppointment(newAppointments, dStart, null, duration.getValue(),1);
					splitAppointment(newAppointments, dStart, null, duration.getValue(),2);
					splitAppointment(newAppointments, dStart, null, duration.getValue(),3);
					splitAppointment(newAppointments, dStart, null, duration.getValue(),4);
					splitAppointment(newAppointments, dStart, null, duration.getValue(),5);
					splitAppointment(newAppointments, dStart, dEnd, duration.getValue(),6);
					
					break;
				case 7:
					System.out.println("Freie Zeit geht über 7 Tage.");
					
					changeFirstPartOfAppointment(app, dStart, c);
					
					//create seven more appointments
					
					splitAppointment(newAppointments, dStart, null, duration.getValue(),1);
					splitAppointment(newAppointments, dStart, null, duration.getValue(),2);
					splitAppointment(newAppointments, dStart, null, duration.getValue(),3);
					splitAppointment(newAppointments, dStart, null, duration.getValue(),4);
					splitAppointment(newAppointments, dStart, null, duration.getValue(),5);
					splitAppointment(newAppointments, dStart, null, duration.getValue(),6);
					splitAppointment(newAppointments, dStart, dEnd, duration.getValue(),7);
					
					break;
				}

			}
		}

		allFreeAppointments.addAll(newAppointments);

		// sort freeAppointmentList
		Collections.sort(allFreeAppointments, new Comparator<IAppointment>() {
			public int compare(IAppointment o1, IAppointment o2) {
				if (o1.getStartTime() == null || o1.getEndTime() == null
						|| o2.getStartTime() == null
						|| o2.getStartTime() == null)
					return 0;
				return o1.getStartTime().compareTo(o2.getStartTime());
			}
		});

		// in the end: reset all free appointment lists of the employees to
		// guarantee a correct calculation for the next time
		for (IEmployee reset : employees) {
			reset.resetFreeAppointments();
		}

		return allFreeAppointments;
	}

	/**
	 * Private methods splits the rest of the big Appointment by the end of a working day.
	 * @param newAppointments
	 * @param c
	 * @param end
	 * @param dur
	 */
	private void splitAppointment(List<IAppointment> newAppointments,Date start, Date end, long dur, int differenceOfDays) {
		IAppointment newAppointment;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(start);
		
		cal.set(Calendar.HOUR_OF_DAY, START_OF_WORK);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		
		cal.set(Calendar.DAY_OF_MONTH, day+differenceOfDays);
		
		DateTime startOfNewAppointment = new DateTime(cal.getTimeInMillis() + 7200000);
		
		//last split
		if(end != null){
			newAppointment = new Appointment(new DateTime(
					cal.getTimeInMillis() + 7200000), new DateTime(
					end.getTime()));
			
			//only add if the new Appointment's duration is still >= the required duration
			if (newAppointment.getStartTime().getValue() < end
					.getTime() && ((end.getTime() - newAppointment.getStartTime().getValue()) >= dur)) {
				newAppointments.add(newAppointment);
			}
		}
		
		//"middle day" of the splits
		else{
			
			cal.set(Calendar.HOUR_OF_DAY, END_OF_WORK);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.DAY_OF_MONTH, day+differenceOfDays);
			
			DateTime endOfNewAppointment = new DateTime(cal.getTimeInMillis() + 7200000);
			
			newAppointment = new Appointment(startOfNewAppointment, endOfNewAppointment);
			
			//only add if the new Appointment's duration is still >= the required duration
			if ((newAppointment.getEndTime().getValue() - newAppointment.getStartTime().getValue()) >= dur) {
				newAppointments.add(newAppointment);
				//System.out.println("Appointment was splitted: " + newAppointment.getStartTime() + " --- " + newAppointment.getEndTime());
			}
		}
	}

	private void changeFirstPartOfAppointment(IAppointment app, Date dStart,
			Calendar c) {
		
		c.setTime(dStart);

		c.set(Calendar.HOUR_OF_DAY, END_OF_WORK);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		//c.set(Calendar.DAY_OF_MONTH,c.get(Calendar.DAY_OF_MONTH) - 1);

		// newEnd = new DateTime();
		app.setEndTime(new DateTime(c.getTimeInMillis() + 7200000));
	}

	/**
	 * This method compares the free appointments of the "first" employee with
	 * all other employees' free appointments and returns the matches.
	 * 
	 * @param duration
	 * @param first
	 *            The Employee whose free appointments are compared.
	 * @param remaining
	 * @return A List of all matches that were found.
	 */
	private List<IAppointment> findMatches(DateTime duration, IEmployee first,
			List<IEmployee> remaining) {

		List<IAppointment> freeAppointments = new ArrayList<IAppointment>();

		// Check all free appointments of the first employee
		for (IAppointment free : first.getFreeAppointments()) {

			DateTime latestStartTime = free.getStartTime();
			DateTime earliestEndTime = free.getEndTime();

			boolean match = true;

			for (IEmployee em : remaining) { // Check for all the other employees...

				boolean emMatch = false;

				for (IAppointment emFree : em.getFreeAppointments()) { // ... for all of his free appointments...

					if (free.matches(emFree, duration)) { // ... if there is a match, remember it
						emMatch = true;
						if (emFree.getStartTime().getValue() > latestStartTime
								.getValue()) { // if emFree begins later
							latestStartTime = emFree.getStartTime(); // its startTime is the new latestStartTime
						}
						if (emFree.getEndTime().getValue() < earliestEndTime
								.getValue()) { // if emFree ends earlier
							earliestEndTime = emFree.getEndTime(); // its endTime is the new earliestEndTime
						}
						break;
					}
				}

				if (!emMatch) { // if there weren't any matches with this employee's free appointments
					match = false;
					break; // the loop of this free appointment can be left
				}
			}

			if (match) { // if the appointment free matches with every employee
							// add a new appointment to the freeAppointments list

				freeAppointments.add(new Appointment(latestStartTime,
						earliestEndTime));
			}
		}
		return freeAppointments;
	}

	/**
	 * This method calculates for every employee his free appointments.
	 * 
	 * @param employees
	 * @param startDate
	 * @param duration
	 * @throws IOException
	 * @throws ServiceException
	 */
	private void calculateFreeAppointments(List<IEmployee> employees,
			DateTime startDate, DateTime duration) throws IOException,
			ServiceException {
		List<IAppointment> appointments = new ArrayList<IAppointment>();

		long sevenDays = 604800000;
		long fifteenMinutes = 900000;
		// timezone
		long twoHours = 7200000;

		DateTime endDate = new DateTime(startDate.getValue() + sevenDays);
		IAppointment app;

		// For every employee...
		for (IEmployee em : employees) {

			try {
				appointments = em.getAppointments(startDate, endDate);				
				
				app = appointments.get(0);

				IAppointment freeApp;

				// If there is only one appointment, this takes care of the free times
				if (appointments.size() == 1) {

					if (app.getStartTime().getValue() - fifteenMinutes
							- startDate.getValue() > duration.getValue()) {

						DateTime start = new DateTime(startDate.getValue()
								+ twoHours);
						DateTime end = new DateTime(app.getStartTime()
								.getValue() - fifteenMinutes + twoHours);

						freeApp = new Appointment(start, end);

						em.addFreeAppointment(freeApp);

						if (endDate.getValue() - app.getEndTime().getValue()
								+ fifteenMinutes > duration.getValue()) {

							freeApp = new Appointment(new DateTime(app
									.getEndTime().getValue()
									+ fifteenMinutes
									+ twoHours), new DateTime(
									endDate.getValue() + twoHours));
							em.addFreeAppointment(freeApp);
						}
						continue;
					}

					if (endDate.getValue() - app.getEndTime().getValue()
							+ fifteenMinutes > duration.getValue()) {

						freeApp = new Appointment(new DateTime(app.getEndTime()
								.getValue() + fifteenMinutes + twoHours),
								endDate);
						em.addFreeAppointment(freeApp);

						if (app.getStartTime().getValue() - fifteenMinutes
								- startDate.getValue() > duration.getValue()) {

							em.addFreeAppointment(new Appointment(startDate,
									new DateTime(app.getStartTime().getValue()
											- fifteenMinutes + twoHours)));

							continue;

						}
					}
				}
				appointments.remove(0);

			} catch (NoAppointmentsException | NullPointerException e) {
				IAppointment freeApp = new Appointment(startDate, endDate);
				em.addFreeAppointment(freeApp);
				continue;
			}
			boolean first = true;
			// ... search for free appointments
			for (IAppointment nextApp : appointments) {

				if (first) {
					if (app.getStartTime().getValue() - fifteenMinutes
							- startDate.getValue() > duration.getValue()) {
						em.addFreeAppointment(new Appointment(new DateTime(
								startDate.getValue() + twoHours), new DateTime(
								app.getStartTime().getValue() - fifteenMinutes
										+ twoHours)));
					}
					first = false;
				}

				// If the time slot between this appointment and the next one is
				// bigger or equal duration...
				if (nextApp.getStartTime().getValue() - fifteenMinutes
						- app.getEndTime().getValue() + fifteenMinutes > duration
							.getValue()) {

					// Create a new free appointment (take care of the
					// "+ 15 minutes + GMT+2 (+2h)" = "+ 8.100.000 ms"
					// Bei getValue 2h verloren, da andere Zeitzone! GMT +2
					DateTime startOfFreeApp = new DateTime(app.getEndTime()
							.getValue() + fifteenMinutes + twoHours);
					// 6.300.000 ms = 2 hours - 15 minutes
					DateTime endOfFreeApp = new DateTime(nextApp.getStartTime()
							.getValue()
							- fifteenMinutes
							+ twoHours
							- duration.getValue() + 3600000);

					// endOfFreeApp is equal to startOfFreeApp, if the duration
					// matches exactly the free time between two Appointments
					if (startOfFreeApp.compareTo(endOfFreeApp) == 1) {
						endOfFreeApp = startOfFreeApp;
					}

					IAppointment freeApp = new Appointment(startOfFreeApp,
							endOfFreeApp);
					em.addFreeAppointment(freeApp);

				}
				app = nextApp;
			}

			if (endDate.getValue() - app.getEndTime().getValue()
					+ fifteenMinutes > duration.getValue()) {
				em.addFreeAppointment(new Appointment(new DateTime(app
						.getEndTime().getValue() + fifteenMinutes + twoHours),
						endDate));
			}

		}
	}

	@Override
	public List<IEmployee> getEmployees() throws IOException {

		List<IEmployee> employees = new ArrayList<IEmployee>();

		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String realPath = ctx.getRealPath("/");

		File employeesFile = new File(realPath + "/resources/employees.dat");
		
		//File employeesFile = new File("employees.dat");
		FileReader file = new FileReader(employeesFile);

		BufferedReader br = new BufferedReader(file);

		int id;
		String name;
		String link;
		String picture;

		while (true) {
			String line = br.readLine();

			if (line == null) {
				break;
			}

			String[] data = line.split(",");

			id = Integer.parseInt(data[0]);
			name = data[1];
			picture = data[2];
			link = data[3];

			employees.add(new Employee(id, name, picture, link));

		}
		br.close();
		file.close();

		return employees;
	}
}
