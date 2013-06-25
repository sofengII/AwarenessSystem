package beans;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import core.EmployeeManager;
import core.IEmployee;

/**
 * This interface defines methods for users. 
 * The users can register themselves or login into the awareness system.
 * After this step they see their favorites and can use the program.
 * 
 * @authors Dominik, Maximilian, Daniela
 * @version 1.0
 */
public interface IUser {

	/**
	 * This method is used to get the username
	 * @return username
	 */
	public String getName();
	
	/**
	 * Sets the name of the user.
	 * @param name the name of the user
	 */
	public void setName(String name);

	/**
	 * This method is used to get the password
	 * @return password
	 */
	public String getPassword();

	/**
	 * Sets the password of the user.
	 * @param password the password of the user
	 */
	public void setPassword(String password);
	
	/**
	 * This method is used to add a favorite to the favorites list
	 * @param empoyee favorite to add
	 * @return true if successful, false otherwise
	 */
	public boolean addFavorite (String employee);
	
	/**
	 * Removes 
	 * @param employee
	 */
	public void removeFavorite(String employee);
	
	/**
	 * This method is used to get the favorites list
	 * @return favorites list
	 */
	public List<IEmployee> getFavorites();
	
	/**
	 * This method is called, when the User logs in.
	 * It fetches the favorites of the User from a file.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public void logIn() throws IOException, ClassNotFoundException;
	
	/**
	 * This method is called, when the User logs off.
	 * It saves his favorites list into a file.
	 * @throws IOException 
	 */
	public void logOff() throws FileNotFoundException, IOException;
	
}
