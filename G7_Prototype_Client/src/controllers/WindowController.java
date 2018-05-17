package controllers;

public class WindowController{

	private LoginWindowController loginWindowController;
	private TeacherWindowController teacherWindowController;

	public WindowController(LoginWindowController loginWindowController,
			TeacherWindowController teacherWindowController) {
		super();
		this.loginWindowController = loginWindowController;
		this.teacherWindowController = teacherWindowController;
	}

	public LoginWindowController getLoginWindowController() {
		return loginWindowController;
	}

	public void setLoginWindowController(LoginWindowController loginWindowController) {
		this.loginWindowController = loginWindowController;
	}

	public TeacherWindowController getTeacherWindowController() {
		return teacherWindowController;
	}

	public void setTeacherWindowController(TeacherWindowController teacherWindowController) {
		this.teacherWindowController = teacherWindowController;
	}

}
