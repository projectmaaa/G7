package resources;

import java.io.Serializable;

public class Student extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 */
	public Student(String id, String firstName, String lastName) {
		super(id, firstName, lastName);
	}

}
