package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StartDayList {

	private List<String> startDays;
	
	public StartDayList() {
		startDays = new ArrayList<String>();
		fillStartDaysList();
	}
	
	private void fillStartDaysList() {
		Date today = new Date();
		for(int i = 0; i < 14; i++) {
			Date nextDate = new Date();
			nextDate.setDate(today.getDate() + i);
			startDays.add(turnDateIntoRelevantString(nextDate));
		}
	}
	
	private String turnDateIntoRelevantString(Date date) {
		String dayInWeek;
		switch(date.getDay()){
			case 0: dayInWeek = "So"; break;
			case 1: dayInWeek = "Mo"; break;
			case 2: dayInWeek = "Di"; break;
			case 3: dayInWeek = "Mi"; break;
			case 4: dayInWeek = "Do"; break;
			case 5: dayInWeek = "Fr"; break;
			case 6: dayInWeek = "Sa"; break;
			default: dayInWeek = "";
		}
		String day = String.valueOf(date.getDate());
		String month = String.valueOf(date.getMonth() + 1);
		String year = String.valueOf(date.getYear() + 1900);
		
		return dayInWeek + " " + day + "." + month + "." + year;
	}
	
	public List<String> getStartDays() {
		return this.startDays;
	}
}
