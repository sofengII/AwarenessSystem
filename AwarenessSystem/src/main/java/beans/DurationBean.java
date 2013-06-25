package beans;

import javax.faces.event.AjaxBehaviorEvent;

public class DurationBean {

	private String duration;
	
	public DurationBean() {}
	
	public String getDuration() {
		return this.duration;
	}
	
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public void changeDuration(AjaxBehaviorEvent event) {
		System.out.println("duration ajax called");
	}
}
