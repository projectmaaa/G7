package resources;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class will have method for general use
 * 
 *
 */
public class Utilities {

	// region Public Methods

	/**
	 * This method will return the date
	 * 
	 * @return String date format (dd/MM/yyyy)
	 */
	public static String setDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return (dateFormat.format(date));
	}

	// end region -> Public Methods

}
