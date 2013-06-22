package beans;

import google.Appointment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

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
			this.favorites.add(employee);
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

	@Override
	public void logIn() throws IOException, ClassNotFoundException {
		FileInputStream file = new FileInputStream(favoritesFile);
		ObjectInputStream ois = new ObjectInputStream(file);
		
		//Get the number of favorites and fetch them from the file.
		int numberOfFavorites = (int)ois.readObject();
		for(int i = 0; i < numberOfFavorites; i++){
			this.favorites.add((IEmployee)ois.readObject());
		}
		ois.close();
	}
	
	@Override
	public void logOff() throws IOException {
		FileOutputStream file = new FileOutputStream(favoritesFile);
		ObjectOutputStream oos = new ObjectOutputStream(file);
		
		//Save the number of favorites first to be able to read them more comfortable out of the file later.
		int numberOfFavorites = favorites.size();
		oos.writeObject(numberOfFavorites);
		
		for(IEmployee favorite : favorites){
			oos.writeObject(favorite);
		}
		oos.close();
	}
}
