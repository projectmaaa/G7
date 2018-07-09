package unittests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Runs the login test cases.
 * 
 * @author Group 7
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ LoginTests.class })
public class AllTests {
	/**
	 * 
	 * @return The suite with the log in tests
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(LoginTests.class);
		return suite;
	}
}