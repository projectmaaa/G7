package resources;

/**
 * This class contains the user name and the password.
 * 
 * @author Group 7
 *
 */
public class User {

	/**
	 * User's ID
	 */
	private String userName;

	/**
	 * User's password
	 */
	private String password;

	/**
	 * User's type: Teacher\Student\Principal
	 */
	private String type;

	/**
	 * If the user is already logged in or not
	 */
	private boolean isConnected;

	/**
	 * 
	 * @param userName
	 *            User's ID
	 * @param password
	 *            User's password
	 * @param type
	 *            User's type: Teacher\Student\Principal
	 * @param isConnected
	 *            If the user is already logged in or not
	 */
	public User(String userName, String password, String type, boolean isConnected) {
		setUserName(userName);
		setPassword(password);
		setType(type);
		setConnected(isConnected);
	}

	/**
	 * 
	 * @return User's ID
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 
	 * @param userName
	 *            User's ID
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 
	 * @return User's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password
	 *            User's password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return User's type: Teacher\Student\Principal
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 *            User's type: Teacher\Student\Principal
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 * @return If the user is already logged in or not
	 */
	public boolean isConnected() {
		return isConnected;
	}

	/**
	 * 
	 * @param isConnected
	 *            If the user is already logged in or not
	 */
	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

} /* end of class */