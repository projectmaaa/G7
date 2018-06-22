package resources;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class is for the combo boxes in different screens. The type can be
 * subject\course\student.
 * 
 * @author Group 7
 *
 */
public class TypeHandle implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private String command;

	private ArrayList<String> types;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param command
	 * @param types
	 */
	public TypeHandle(String command, ArrayList<String> types) {
		setCommand(command);
		setTypes(types);
	}

	/******************** Getters & Setters ********************/

	/**
	 * 
	 * @return Which type it is
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * 
	 * @param command
	 */
	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * 
	 * @return The list of the relevant type
	 */
	public ArrayList<String> getTypes() {
		return types;
	}

	/**
	 * 
	 * @param types
	 */
	public void setTypes(ArrayList<String> types) {
		this.types = types;
	}

} // end of class