package view;

import java.util.List;

import core.EmployeeManager;

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
	 * This method is used to get the password
	 * @return password
	 */
	public String getPassword();

	/**
	 * This method is used to add a favorite to the favorites list
	 * @param empoyee favorite to add
	 * @return true if successful, false otherwise
	 */
	public boolean addFavorite (EmployeeManager empoyee);
	
	/**
	 * This method is used to get the favorites list
	 * @return favorites list
	 */
	public List<EmployeeManager> getFavorites();
}
