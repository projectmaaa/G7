package unittests;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import client.Client;
import controllers.LoginWindowController;
import junit.framework.TestCase;

/**
 * The class that tests the login cases.
 * 
 * @author Group 7
 *
 */
class LoginTests extends TestCase {

	private Client client;
	private String anatDahanID = "1";
	private String arkadyKoretskyID = "3";
	private String dvoraToledanoID = "1212";
	private LoginWindowController login;

	/**
	 * Initialize the client
	 */
	@BeforeEach
	protected void setUp() throws Exception {
		super.setUp();
		client = new Client("localhost", 5555);
		login = new LoginWindowController();
		login.setClient(new Client("localhost", 5555));
	}

	/**
	 * Test the login of teacher user
	 */
	@Test
	void testTeacherLogin() {
		client.setId(anatDahanID);
		login.loginCheck("1", "1");
		String ID = login.getClient().getId();
		Assert.assertTrue(ID.equals(client.getId()));
	}

	/**
	 * Test the login of student user
	 */
	@Test
	void testStudentLogin() {
		client.setId(arkadyKoretskyID);
		login.loginCheck("3", "3");
		String ID = login.getClient().getId();
		Assert.assertTrue(ID.equals(client.getId()));
	}

	/**
	 * Test the login of principal user
	 */
	@Test
	void testPrincipalLogin() {
		client.setId(dvoraToledanoID);
		login.loginCheck("1212", "1212");
		String ID = login.getClient().getId();
		Assert.assertTrue(ID.equals(client.getId()));
	}

	/**
	 * Test the already connected case
	 */
	@Test
	void testAlreadyConnected() {
		LoginWindowController expectedResult = new LoginWindowController();
		expectedResult.setConnectedFlag(true);
		login.loginCheck("6", "6");
		boolean connected = login.isConnectedFlag();
		Assert.assertTrue(connected == expectedResult.isConnectedFlag());
	}

	/**
	 * Test the Wrong user name and password case
	 */
	@Test
	void testWrongUserNameAndPassword() {
		LoginWindowController expectedResult = new LoginWindowController();
		expectedResult.setNoSuchUserFlag(true);
		login.loginCheck("5", "12");
		boolean notExist = login.isNoSuchUserFlag();
		Assert.assertTrue(notExist == expectedResult.isNoSuchUserFlag());
	}

} /* end of class */