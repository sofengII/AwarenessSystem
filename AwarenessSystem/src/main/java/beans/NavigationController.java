package beans;

public class NavigationController {

	private String navigateTo = "";
	
	public NavigationController() {
		
	}
	
	public String getNavigateTo(){
		return this.navigateTo;
	}
	
	public void setNavigateTo(String navigateTo) {
		this.navigateTo = navigateTo;
	}
}
