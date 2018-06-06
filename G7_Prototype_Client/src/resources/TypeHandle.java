package resources;

import java.io.Serializable;
import java.util.ArrayList;

public class TypeHandle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String command;

	private ArrayList<String> types;

	public TypeHandle(String command, ArrayList<String> types) {
		setCommand(command);
		setTypes(types);
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public ArrayList<String> getTypes() {
		return types;
	}

	public void setTypes(ArrayList<String> types) {
		this.types = types;
	}

}
