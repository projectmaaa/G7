package resources;

import java.io.Serializable;

/**
 * Teacher Class
 * 
 * @author Group 7
 *
 */
public class Teacher extends User implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param id
	 *            Teacher's ID
	 * @param firstName
	 *            Teacher's First Name
	 * @param lastName
	 *            Teacher's last Name
	 */
	public Teacher(String id, String firstName, String lastName) {
		super(id, firstName, lastName);
	}

} // end of class