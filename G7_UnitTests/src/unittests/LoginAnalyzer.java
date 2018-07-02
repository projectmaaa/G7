package unittests;

import java.util.ArrayList;
import resources.User;

/**
 * The analyzer that checks the users that trying to log in.
 * 
 * @author Group 7
 *
 */
public class LoginAnalyzer {

	/**
	 * The manger that contains the checking method
	 */
	private IUserManager userManager;

	/**
	 * Load the data base.
	 */
	public LoginAnalyzer() {
		ArrayList<User> usersTable = new ArrayList<User>();
		usersTable.add(new User("1", "1", "teacher", false)); // Anat Dahan
		usersTable.add(new User("1212", "1212", "principal", false)); // Dvora Toledano
		usersTable.add(new User("3", "3", "student", false)); // Arkady Koretsky
		usersTable.add(new User("007", "007", "teacher", true)); // James Bond
		userManager = new LoginCheck(usersTable);
	}

	/**
	 * 
	 * @return The manger that contains the checking method
	 */
	public IUserManager getUserManager() {
		return userManager;
	}

	/**
	 * 
	 * @param userManager
	 *            The manger that contains the checking method
	 */
	public void setUserManager(IUserManager userManager) {
		this.userManager = userManager;
	}

	/**
	 * 
	 * @param userName
	 *            User's ID
	 * @param password
	 *            User's password
	 * @return The type(Teacher\Student\Principal) if the user logged in
	 *         successfully or the reason of failure if the user failed to log in.
	 */
	public String checkLogin(String userName, String password) {
		return userManager.checkUser(userName, password);
	}

} /* end of class */