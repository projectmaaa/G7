package unittests;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
class LoginTests extends TestCase {

	private Client client;
	private String anatDahanID;
	private String arkadyKoretskyID;
	private String dvoraToledanoID;
	private String jamesBondID;
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
		anatDahanID = "1";
		arkadyKoretskyID = "3";
		dvoraToledanoID = "1212";
		jamesBondID = "007";
	}

	/**
	 * Test the login of teacher user
	 */
	@Test
	void testTeacherLogin() {
		client.setMessageFromServer(Message.teacher);
		login.loginCheck(anatDahanID, anatDahanID);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String messageFromServer = login.getClient().getMessageFromServer();
		Assert.assertTrue(messageFromServer.equals(client.getMessageFromServer()));
	}

	/**
	 * Test the login of student user
	 */
	@Test
	void testStudentLogin() {
		client.setMessageFromServer(Message.studnet);
		login.loginCheck(arkadyKoretskyID, arkadyKoretskyID);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String messageFromServer = login.getClient().getMessageFromServer();
		Assert.assertTrue(messageFromServer.equals(client.getMessageFromServer()));
	}

	/**
	 * Test the login of principal user
	 */
	@Test
	void testPrincipalLogin() {
		client.setMessageFromServer(Message.principal);
		login.loginCheck(dvoraToledanoID, dvoraToledanoID);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String messageFromServer = login.getClient().getMessageFromServer();
		Assert.assertTrue(messageFromServer.equals(client.getMessageFromServer()));
	}

	/**
	 * Test the already connected case
	 */
	@Test
	void testAlreadyConnected() {
		client.setMessageFromServer(Message.userAlreadyConnected);
		login.loginCheck(jamesBondID, jamesBondID);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String messageFromServer = login.getClient().getMessageFromServer();
		Assert.assertTrue(messageFromServer.equals(client.getMessageFromServer()));
	}

	/**
	 * Test the wrong user name and password case
	 */
	@Test
	void testWrongUserNameAndPassword() {
		client.setMessageFromServer(Message.noSuchUser);
		login.loginCheck(jamesBondID, dvoraToledanoID);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String messageFromServer = login.getClient().getMessageFromServer();
		Assert.assertTrue(messageFromServer.equals(client.getMessageFromServer()));
	}

} /* end of class */