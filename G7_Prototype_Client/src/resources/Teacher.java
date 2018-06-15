package resources;

import java.io.Serializable;

public class Teacher extends User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Teacher(String id, String firstName, String lastName) {
		super(id, firstName, lastName);
	}

}
