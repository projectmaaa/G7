package controllers;

/**
 * The interface for the each screen controller
 * 
 * @author Group 7
 *
 */
public interface IScreenController {

	/**
	 * This method will allow the injection of the Parent ScreenPane
	 * 
	 * @param screenPage
	 */
	public void setScreenParent(ScreensController screenPage);

} // end of interface