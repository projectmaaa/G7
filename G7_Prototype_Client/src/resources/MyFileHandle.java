package resources;

import java.io.Serializable;

public class MyFileHandle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String command;

	private MyFile file;

	public MyFileHandle(String command, MyFile file) {
		super();
		this.command = command;
		this.file = file;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public MyFile getFile() {
		return file;
	}

	public void setFile(MyFile file) {
		this.file = file;
	}

}
