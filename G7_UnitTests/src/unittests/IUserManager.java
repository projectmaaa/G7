package unittests;

/**
 * This interface contains the method that checks the user.
 * 
 * @author Group 7
 *
 */
public interface IUserManager {

	/**
	 * This method checks user's status.
	 * 
	 * @param userName
	 *            User's ID
	 * @param password
	 *            User's password
	 * @return The type(Teacher\Student\Principal) if the user logged in
	 *         successfully or the reason of failure if the user failed to log in.
	 */
	public String checkUser(String userName, String password);

} /* end of class */