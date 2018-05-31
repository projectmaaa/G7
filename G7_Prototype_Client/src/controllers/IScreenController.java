package controllers;


public interface IScreenController {

	// region Public Methods

	/**
	 * This method will allow the injection of the Parent ScreenPane
	 * 
	 * @param screenPage
	 */
	public void setScreenParent(ScreensController screenPage);

	// end region -> Public Methods
}
