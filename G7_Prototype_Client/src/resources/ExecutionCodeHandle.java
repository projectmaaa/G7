package resources;

import java.io.Serializable;

public class ExecutionCodeHandle implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String command;

	private String code;

	public ExecutionCodeHandle(String command, String code) {
		this.command = command;
		this.code = code;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
} // end of class