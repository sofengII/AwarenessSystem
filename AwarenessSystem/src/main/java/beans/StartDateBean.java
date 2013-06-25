package beans;

import javax.faces.event.AjaxBehaviorEvent;

public class StartDateBean {

	private String chosenDate;
	
	public StartDateBean() {}
	
	public String getChosenDate() {
		System.out.println("date gotten: " + chosenDate);
		return this.chosenDate;
	}
	
	public void setChosenDate(String chosenDate) {
		System.out.println("date set to: " + chosenDate);
		this.chosenDate = chosenDate;
	}
	
	public void changeChosenStartDate(AjaxBehaviorEvent event) {
		System.out.println("date ajax called");
	}
}
