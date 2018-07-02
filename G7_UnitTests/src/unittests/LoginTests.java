package unittests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;
import resources.Message;

/**
 * The tests to run on the log in process
 * 
 * @author Group 7
 *
 */
public class LoginTests extends TestCase {

	/**
	 * The analyzer that contains the manager to test
	 */
	private LoginAnalyzer analyzer;

	/**
	 * Load the data base for testing.
	 */
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		analyzer = new LoginAnalyzer();
	}

	/**
	 * Teacher logged in successfully.
	 */
	@Test
	public void testTeacherLogin() {
		String userName = "1";
		String password = "1";
		Assert.assertEquals(Message.teacher, analyzer.checkLogin(userName, password));
	}

	/**
	 * Student logged in successfully.
	 */
	@Test
	public void testStudentLogin() {
		String userName = "3";
		String password = "3";
		Assert.assertEquals(Message.student, analyzer.checkLogin(userName, password));
	}

	/**
	 * Principal logged in successfully.
	 */
	@Test
	public void testPrincipalLogin() {
		String userName = "1212";
		String password = "1212";
		Assert.assertEquals(Message.principal, analyzer.checkLogin(userName, password));
	}

	/**
	 * User already logged in.
	 */
	@Test
	public void testAlreadyLogged() {
		String userName = "007";
		String password = "007";
		Assert.assertEquals(Message.userAlreadyConnected, analyzer.checkLogin(userName, password));
	}

	/**
	 * Wrong user name or password.
	 */
	@Test
	public void testWrongUserNameOrPassword() {
		String userName = "007";
		String password = "1212";
		Assert.assertEquals(Message.noSuchUser, analyzer.checkLogin(userName, password));
	}

	/**
	 * Both fields missing.
	 */
	@Test
	public void testBothFieldsMissing() {
		String userName = "";
		String password = "";
		Assert.assertEquals(Message.oneOfTheFieldsIsMissing, analyzer.checkLogin(userName, password));
	}

	/**
	 * User name field missing.
	 */
	@Test
	public void testUserNameFieldMissing() {
		String userName = "";
		String password = "007";
		Assert.assertEquals(Message.oneOfTheFieldsIsMissing, analyzer.checkLogin(userName, password));
	}

	/**
	 * Password field missing.
	 */
	@Test
	public void testPasswordFieldMissing() {
		String userName = "007";
		String password = "";
		Assert.assertEquals(Message.oneOfTheFieldsIsMissing, analyzer.checkLogin(userName, password));
	}

} /* end of class */