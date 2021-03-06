package resources;

import java.io.Serializable;

/**
 * The class that handles with the execution code
 * 
 * @author Group 7
 *
 */
public class ExecutionCodeHandle implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private String command;

	private String code;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param command
	 *            What To Do
	 * @param code
	 *            Execution Code
	 */
	public ExecutionCodeHandle(String command, String code) {
		this.command = command;
		this.code = code;
	}

	/******************** Getters and Setters ********************/

	/**
	 * 
	 * @return The message what to do
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * 
	 * @param command
	 *            The message what to do
	 */
	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * 
	 * @return The execution code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @param code
	 *            The execution code
	 */
	public void setCode(String code) {
		this.code = code;
	}

} // end of class