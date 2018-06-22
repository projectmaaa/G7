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

	public Teacher(String id, String firstName, String lastName) {
		super(id, firstName, lastName);
	}

} // end of class