package resources;

import java.io.Serializable;

/**
 * The class that holds the file & the command what to do with the file. This
 * class is the message that sent between the client & the server.
 * 
 * @author Group 7
 *
 */
public class MyFileHandle implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private String command;

	private MyFile file;

	/******************** Constructors ********************/

	public MyFileHandle(String command, MyFile file) {
		this.command = command;
		this.file = file;
	}

	/******************** Getters & Setters ********************/

	/**
	 * 
	 * @return The message what to do with the file
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
	 * @return The file
	 */
	public MyFile getFile() {
		return file;
	}

	/**
	 * 
	 * @param file
	 */
	public void setFile(MyFile file) {
		this.file = file;
	}

} // end of class