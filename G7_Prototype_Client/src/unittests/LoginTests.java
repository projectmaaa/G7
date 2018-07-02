package unittests;

import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import client.Client;
import controllers.LoginWindowController;
import junit.framework.TestCase;
import resources.Message;

/**
 * The class that tests the login cases.
 * 
 * @author Group 7
 *
 */
public class LoginTests extends TestCase {

	/**
	 * Teacher's ID - not logged in
	 */
	private String anatDahanID;

	/**
	 * Student's ID
	 */
	private String arkadyKoretskyID;

	/**
	 * Principal's ID
	 */
	private String dvoraToledanoID;

	/**
	 * Teacher's ID - logged in
	 */
	private String jamesBondID;

	/**
	 * The class with the testing method
	 */
	private LoginWindowController login;

	/**
	 * Initialize the controller and the ID's
	 */
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		login = new LoginWindowController();
		login.setClient(new Client("localhost", 5555));
		anatDahanID = "1";
		arkadyKoretskyID = "3";
		dvoraToledanoID = "1212";
		jamesBondID = "007";
	}

	/**
	 * Test the login of teacher user
	 */
	@Test
	public void testTeacherLogin() {
		login.loginCheck(anatDahanID, anatDahanID);
		login.getClient().handleMessageFromClientUI(Message.logout); // Initialize back the value in the data base
		try {
			TimeUnit.SECONDS.sleep(1); // Force the context switch
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String messageFromServer = login.getClient().getMessageFromServer();
		Assert.assertTrue(messageFromServer.equals(Message.teacher));
	}

	/**
	 * Test the login of student user
	 */
	@Test
	public void testStudentLogin() {
		login.loginCheck(arkadyKoretskyID, arkadyKoretskyID);
		login.getClient().handleMessageFromClientUI(Message.logout); // Initialize back the value in the data base
		try {
			TimeUnit.SECONDS.sleep(1); // Force the context switch
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String messageFromServer = login.getClient().getMessageFromServer();
		Assert.assertTrue(messageFromServer.equals(Message.studnet));
	}

	/**
	 * Test the login of principal user
	 */
	@Test
	public void testPrincipalLogin() {
		login.loginCheck(dvoraToledanoID, dvoraToledanoID);
		login.getClient().handleMessageFromClientUI(Message.logout); // Initialize back the value in the data base
		try {
			TimeUnit.SECONDS.sleep(1); // Force the context switch
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String messageFromServer = login.getClient().getMessageFromServer();
		Assert.assertTrue(messageFromServer.equals(Message.principal));
	}

	/**
	 * Test the already connected case
	 */
	@Test
	public void testAlreadyConnected() {
		login.loginCheck(jamesBondID, jamesBondID);
		try {
			TimeUnit.SECONDS.sleep(1); // Force the context switch
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String messageFromServer = login.getClient().getMessageFromServer();
		Assert.assertTrue(messageFromServer.equals(Message.userAlreadyConnected));
	}

	/**
	 * Test the wrong user name and password case
	 */
	@Test
	public void testWrongUserNameAndPassword() {
		login.loginCheck(jamesBondID, dvoraToledanoID);
		try {
			TimeUnit.SECONDS.sleep(1); // Force the context switch
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String messageFromServer = login.getClient().getMessageFromServer();
		Assert.assertTrue(messageFromServer.equals(Message.noSuchUser));
	}

} /* end of class */