package beans;

import google.Appointment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import core.EmployeeManager;
import core.Employee;
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
	
	//TODO Sollte final sein, Username über Ctor erstellen.
	/**
	 * name of the user
	 */
	private String name;
	//TODO Sollte final sein, Userpassword über Ctor erstellen.
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
		this.favoritesFile = name + "_favorites";
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
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
	public boolean addFavorite(IEmployee employee) {
		
		try {
			if(this.favorites.size()<5){
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
		for(IEmployee e: this.favorites) {
			e.getAvaliable();
		}
		return this.favorites;
	}

	@Override
	public void removeFavorite(IEmployee employee) {
		this.favorites.remove(employee);
	}
	
	public String checkLogin() {
		if(name.equals("") || password.equals(""))
			return "fail";
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
	public void logOff() throws IOException {
		
		
		
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
	}
	
	@Override
	public String toString() {
		return "User: " + this.name;
	}
}
