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
	
	public String navigate(String nav) {
		System.out.println("drin: " + nav);
		return nav;
	}
}
