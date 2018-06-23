package resources;

import java.io.Serializable;

/**
 * This class contains the user details.
 * 
 * @author Group 7
 *
 */
public class User implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private String id;

	private String firstName;

	private String lastName;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param id
	 *            ID
	 * @param firstName
	 *            First Name
	 * @param lastName
	 *            Last Name
	 */
	public User(String id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/******************** Getters and Setters ********************/

	/**
	 * 
	 * @return The user's ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 *            The user's ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return The user's first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @param firstName
	 *            The user's first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * 
	 * @return The user's last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @param lastName
	 *            The user's last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

} // end of class