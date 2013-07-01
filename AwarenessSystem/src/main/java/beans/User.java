package beans;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import core.Employee;
import core.EmployeeManager;
import core.IEmployee;

/**
 * This class implements methods for users. 
 * The users can register themselves or login into the awareness system.
 * After this step they see their favorites and can use the program.
 * 
 * @authors Dominik, Maximilian, Daniela
 * @version 1.0
 */
public class User implements IUser{
	
	/**
	 * name of the user
	 */
	private String name;
	/**
	 * password of the user
	 */
	private String password;
	/**
	 * path of the file that contains the User's favorites
	 */
	private String favoritesFile;
	/**
	 * favorite list of the user
	 */
	private List<IEmployee> favorites = new ArrayList<IEmployee>(5);
	
	public User() {}
	
	public User(String name){
		this.name = name;
		
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance()
	            .getExternalContext().getContext();
		String realPath = ctx.getRealPath("/");
		
		this.favoritesFile = realPath + "/resources/" + name + "_favorites";
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
		
		ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance()
	            .getExternalContext().getContext();
		String realPath = ctx.getRealPath("/");
		
		this.favoritesFile = realPath + "/resources/" + name + "_favorites";
	}
	
	@Override
	public String getPassword() {
		return this.password;
	}
	
	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean addFavorite(String employeeName) {
		System.out.println("in add favorite with: " + employeeName);
		IEmployee employee = null;
		List<IEmployee> employees = null;
		try {
			employees = (new EmployeeManager()).getEmployees();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for(IEmployee e: employees) {
			if(e.getName().equals(employeeName))
				employee = e;
		}
		try {
			if(this.favorites.size()<5){
				System.out.println("Favorite added: " + employeeName);
				this.favorites.add(employee);
			}
			else{
				return false;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public List<IEmployee> getFavorites() {
		return this.favorites;
	}

	public IEmployee getFavorite(int index) {
		IEmployee employee;
		try {
			employee = this.favorites.get(index);
		} catch (Exception ex) {
			employee = new Employee(0, "Kein Favorit", "/resources/images/person_icon.png","");
		}
		return employee;
	}
	
	@Override
	public void removeFavorite(String employeeName) {
		System.out.println("in remove with: " + employeeName);
		IEmployee employee = null;
		for(IEmployee favorite: this.favorites) {
			if(favorite.getName().equals(employeeName)) {
				System.out.println(employeeName + " removed.");
				employee = favorite;
				break;
			}
		}
		if(employee != null)
			this.favorites.remove(employee);
	}
	
	public String checkLogin() {
		if(name.equals("") || password.equals(""))
			return "fail";
		try {
			logIn();
		} catch (ClassNotFoundException e) {
			System.out.println("login failed: " + e.getMessage());
			return "fail";
		} catch (IOException e) {
			System.out.println("login failed: " + e.getMessage());
			return "fail";
		}
		return "success";
	}
	
	@Override
	public void logIn() throws IOException, ClassNotFoundException {
		FileReader file = new FileReader(favoritesFile);
		BufferedReader br = new BufferedReader(file);
		
		int employeeID; 
		String name; 
		String picturePath; 
		String link;
		
		while (true) {
			String line = br.readLine();

			if (line == null) {
				break;
			}

			String[] data = line.split(",");
			employeeID = Integer.parseInt(data[0]);
			name = data[1];
			picturePath = data[2];
			link = data[3];

			this.favorites.add(new Employee(employeeID, name, picturePath, link));
		}
		br.close();
		file.close();
		System.out.println("Favorites were fetched from the file.");
	}
	
	@Override
	public String logOff() throws IOException {
		
		
		
		FileWriter file = new FileWriter(favoritesFile);
		BufferedWriter bw = new BufferedWriter(file);
		
		for(IEmployee favorite : favorites){
			bw.write(favorite.getEmployeeID()+",");
			bw.write(favorite.getName()+",");
			bw.write(favorite.getPicturePath()+",");
			bw.write(favorite.getLink()+"\n");
		}
		bw.flush();
		bw.close();
		file.close();
		
		//FÜR TESTZWECKE LISTE LÖSCHEN
		this.favorites.clear();
				
		System.out.println("Favorites were saved in the file.");
		return "logOut";
	}
	
	@Override
	public String toString() {
		return "User: " + this.name;
	}

}
