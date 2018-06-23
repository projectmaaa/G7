package resources;

import java.io.Serializable;

/**
 * Student class
 * 
 * @author Group 7
 *
 */
public class Student extends User implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param id
	 *            Student's ID
	 * @param firstName
	 *            Student's First Name
	 * @param lastName
	 *            Student's Last Name
	 */
	public Student(String id, String firstName, String lastName) {
		super(id, firstName, lastName);
	}

} // end of class