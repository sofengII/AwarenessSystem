package beans;

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
	public boolean addFavorite (IEmployee employee);
	
	/**
	 * Removes 
	 * @param employee
	 */
	public void removeFavorite(IEmployee employee);
	
	/**
	 * This method is used to get the favorites list
	 * @return favorites list
	 */
	public List<IEmployee> getFavorites();
}
