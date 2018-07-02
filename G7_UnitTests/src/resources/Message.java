package resources;

/**
 * The class that contains the messages that to return from the data base.
 * 
 * @author Group 7
 *
 */
public abstract class Message {

	/**
	 * Principal logged in successfully
	 */
	public static final String principal = "Principal";

	/**
	 * Teacher logged in successfully
	 */
	public final static String teacher = "Teacher";

	/**
	 * Student logged in successfully
	 */
	public final static String student = "Student";

	/**
	 * Wrong user name or password - no such user with in the data base with that
	 * user name and password
	 */
	public final static String noSuchUser = "No Such User";

	/**
	 * User already logged in
	 */
	public final static String userAlreadyConnected = "User Already Connected";

	/**
	 * The user name or the password (or both) fields left blank
	 */
	public final static String oneOfTheFieldsIsMissing = "One of the Fields is Missing";

} /* end of class */