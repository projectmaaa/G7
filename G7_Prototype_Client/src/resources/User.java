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
	 * @param firstName
	 * @param lastName
	 */
	public User(String id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/******************** Getters & Setters ********************/

	/**
	 * 
	 * @return The user ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id
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
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

} // end of class