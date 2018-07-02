package unittests;

import java.util.ArrayList;
import resources.*;

/**
 * This class contains array list of users which simulates the data base table
 * and checks the login progress with this table.
 * 
 * @author Group 7
 *
 */
public class LoginCheck implements IUserManager {

	/**
	 * Simulating the data base table that saves the user data
	 */
	private ArrayList<User> usersTableInTheDataBase;

	/**
	 * 
	 * @param usersTableInTheDataBase
	 *            The table of users in the data base
	 */
	public LoginCheck(ArrayList<User> usersTableInTheDataBase) {
		setUsersTableInTheDataBase(usersTableInTheDataBase);
	}

	@Override
	public String checkUser(String userName, String password) {
		if (userName.isEmpty() || password.isEmpty()) // one of the fields is missing
			return Message.oneOfTheFieldsIsMissing;
		User user = null;
		for (User userFromDataBase : usersTableInTheDataBase) {
			if (userFromDataBase.getUserName().equals(userName) && userFromDataBase.getPassword().equals(password)) {
				user = userFromDataBase;
				break;
			}
		}
		if (user != null) {
			if (user.isConnected()) // if the user already connected
				return Message.userAlreadyConnected;
			switch (user.getType()) {
			case "principal":
				return Message.principal;
			case "teacher":
				return Message.teacher;
			case "student":
				return Message.student;
			}
		}
		return Message.noSuchUser; // if the user does'nt exist in the data base
	}

	/**
	 * 
	 * @return The table of users in the data base
	 */
	public ArrayList<User> getUsersTableInTheDataBase() {
		return usersTableInTheDataBase;
	}

	/**
	 * 
	 * @param usersTableInTheDataBase
	 *            The table of users in the data base
	 */
	public void setUsersTableInTheDataBase(ArrayList<User> usersTableInTheDataBase) {
		this.usersTableInTheDataBase = usersTableInTheDataBase;
	}

} /* end of class */