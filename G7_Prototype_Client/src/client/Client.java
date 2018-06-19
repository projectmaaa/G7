package client;

import java.io.IOException;
import java.util.ArrayList;
import controllers.IScreenController;
import controllers.LoginWindowController;
import controllers.PrincipalWindowController;
import controllers.ScreensController;
import controllers.StudentWindowController;
import controllers.TeacherWindowController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ocsf.client.AbstractClient;
import resources.*;

public class Client extends AbstractClient implements IScreenController {

	// Class variables *************************************************

	// region Constants

	// end region -> Constants

	// region Fields

	private ObservableList<Question> questionsFromDB = FXCollections.observableArrayList();

	private ObservableList<Exam> examsFromDB = FXCollections.observableArrayList();

	private ObservableList<String> subjectsFromDB = FXCollections.observableArrayList();

	private ObservableList<String> coursesFromDB = FXCollections.observableArrayList();

	private ObservableList<String> examsByAuthorFromDB = FXCollections.observableArrayList();

	private ObservableList<WaitingActiveExam> WaitingActiveExamsFromDB = FXCollections.observableArrayList();

	private ObservableList<CheckedExam> checkedExamsFromDB = FXCollections.observableArrayList();

	private ObservableList<ActiveExam> activatedUnlockedExams = FXCollections.observableArrayList();

	private ObservableList<Student> studentsFromDB = FXCollections.observableArrayList();

	private ObservableList<Course> allCoursesFromDB = FXCollections.observableArrayList();

	private ObservableList<Teacher> allTeachersFromDB = FXCollections.observableArrayList();

	private ScreensController controller;

	private LoginWindowController loginWindowController;

	private TeacherWindowController teacherWindowController;

	private StudentWindowController studentWindowController;

	private PrincipalWindowController principalWindowController;

	private Question question;

	private String firstName = "";

	private String lastName = "";

	private String id = "";

	private boolean executionCodeExistFlag;

	// end region -> Fields

	// region Constructors

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the chat client.
	 *
	 * @param host
	 *            The server to connect to.
	 * @param port
	 *            The port number to connect on.
	 * @param clientUI
	 *            The interface type variable.
	 */
	public Client(String host, int port) {
		super(host, port); // Call the superclass constructor
		try {
			openConnection();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	// region Setters

	public void setScreenParent(ScreensController screenParent) {
		controller = screenParent;
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

	public StudentWindowController getStudentWindowController() {
		return studentWindowController;
	}

	public void setStudentWindowController(StudentWindowController studentWindowController) {
		this.studentWindowController = studentWindowController;
	}

	public PrincipalWindowController getPrincipalWindowController() {
		return principalWindowController;
	}

	public void setPrincipalWindowController(PrincipalWindowController principalWindowController) {
		this.principalWindowController = principalWindowController;
	}

	public ObservableList<Question> getQuestionsFromDB() {
		return questionsFromDB;
	}

	public void setQuestionsFromDB(ArrayList<Question> questions) {
		questionsFromDB.setAll(questions);
	}

	public Question getQuestion() {
		return question;
	}

	public ObservableList<Exam> getExamsFromDB() {
		return examsFromDB;
	}

	public void setExamsFromDB(ArrayList<Exam> exams) {
		examsFromDB.setAll(exams);
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ObservableList<String> getSubjectsFromDB() {
		return subjectsFromDB;
	}

	public void setSubjectsFromDB(ArrayList<String> subjects) {
		Platform.runLater(() -> {
			subjectsFromDB.setAll(subjects);
		});
	}

	public ObservableList<String> getCoursesFromDB() {
		return coursesFromDB;
	}

	public void setCoursesFromDB(ArrayList<String> courses) {
		Platform.runLater(() -> {
			coursesFromDB.setAll(courses);
		});
	}

	public ObservableList<String> getExamsByAuthorFromDB() {
		return examsByAuthorFromDB;
	}

	public void setExamsByAuthorFromDB(ArrayList<String> examsByAuthorFromDB) {
		Platform.runLater(() -> {
			this.examsByAuthorFromDB.setAll(examsByAuthorFromDB);
		});
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ObservableList<WaitingActiveExam> getWaitingActiveExamsFromDB() {
		return WaitingActiveExamsFromDB;
	}

	public void setWaitingActiveExamsFromDB(ArrayList<WaitingActiveExam> waitingActiveExamsFromDB) {
		Platform.runLater(() -> {
			WaitingActiveExamsFromDB.setAll(waitingActiveExamsFromDB);
		});
	}

	public boolean getExecutionCodeExistFlag() {
		return executionCodeExistFlag;
	}

	public void setExecutionCodeExistFlag(boolean executionCodeExistFlag) {
		this.executionCodeExistFlag = executionCodeExistFlag;
	}

	public ObservableList<CheckedExam> getCheckedExamsFromDB() {
		return checkedExamsFromDB;
	}

	public void setCheckedExamsFromDB(ArrayList<CheckedExam> checkedExamsFromDB) {
		Platform.runLater(() -> {
			this.checkedExamsFromDB.setAll(checkedExamsFromDB);
		});
	}

	public ObservableList<ActiveExam> getActivatedUnlockedExams() {
		return activatedUnlockedExams;
	}

	public void setActivatedUnlockedExams(ArrayList<ActiveExam> activatedUnlockedExams) {
		Platform.runLater(() -> {
			this.activatedUnlockedExams.setAll(activatedUnlockedExams);
		});
	}

	public ObservableList<Student> getStudentsFromDB() {
		return studentsFromDB;
	}

	public void setStudentsFromDB(ArrayList<Student> studentsFromDB) {
		Platform.runLater(() -> {
			this.studentsFromDB.setAll(studentsFromDB);
		});
	}

	public ObservableList<Course> getAllCoursesFromDB() {
		return allCoursesFromDB;
	}

	public void setAllCoursesFromDB(ArrayList<Course> allCoursesFromDB) {
		Platform.runLater(() -> {
			this.allCoursesFromDB.setAll(allCoursesFromDB);
		});
	}

	public ObservableList<Teacher> getAllTeachersFromDB() {
		return allTeachersFromDB;
	}

	public void setAllTeachersFromDB(ArrayList<Teacher> allTeachersFromDB) {
		Platform.runLater(() -> {
			this.allTeachersFromDB.setAll(allTeachersFromDB);
		});
	}

	// end region -> Setters

	// region Public Methods

	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg
	 *            The message from the server.
	 */
	@Override
	public void handleMessageFromServer(Object msg) {
		if (msg == null) {
			return;
		} else if (msg instanceof String) {
			String str = (String) msg;
			String[] strArray = str.split(" ");
			switch (strArray[0]) {
			case Message.principal:
				firstName = strArray[1];
				lastName = strArray[2];
				controller.setScreen(MainAppClient.principalScreenID);
				principalWindowController.setNameAndLastName(firstName, lastName);
				break;
			case Message.teacher:
				firstName = strArray[1];
				lastName = strArray[2];
				controller.setScreen(MainAppClient.teacherScreenID);
				teacherWindowController.setNameAndLastName(firstName, lastName);
				break;
			case Message.studnet:
				firstName = strArray[1];
				lastName = strArray[2];
				id = strArray[3];
				controller.setScreen(MainAppClient.studentScreenID);
				studentWindowController.setFirstName(firstName);
				studentWindowController.setLastName(lastName);
				studentWindowController.setName();
				break;
			case "#LockExam":
				if (studentWindowController.getActiveExam() != null) {
					if (studentWindowController.getActiveExam().getExecutionCode().equals(strArray[1])) {
						studentWindowController.setSecondTimer(0);
					}
				}
				break;
			case "#ChangeTime":
				if (studentWindowController.getActiveExam() != null) {
					if (studentWindowController.getActiveExam().getExecutionCode().equals(strArray[1])) {
						System.out.println(this.firstName + " " + this.lastName);
						studentWindowController.getActiveExam().setDuration(Integer.parseInt(strArray[2]));
						studentWindowController.changeSecondTimer();
					}
				}
				break;
			case "#TableSaved":
				System.out.println("Data Base Updated successfully");
				break;
			case Message.userAlreadyConnected:
				loginWindowController.setLoginStatus("User Already Connected");
				break;
			case Message.noSuchUser:
				loginWindowController.setLoginStatus("Wrong Username or Pasword");
				break;
			case Message.getQuestionBySubject:
				break;
			case Message.requestRejected:
				if (this.getId().equals(strArray[1]))
					teacherWindowController.setRejectionFlag(true);
				break;
			case Message.requestApproved:
				if (this.getId().equals(strArray[1]))
					teacherWindowController.setAcceptionFlag(true);
				break;
			default:
				break;
			}
		} else if (msg instanceof QuestionHandle) {
			QuestionHandle questionsHandle = (QuestionHandle) msg;
			if (questionsHandle.getQuestionArray().isEmpty()) // if the table in the DB is empty
				return;
			else if (questionsHandle.getCommand().equals("All")) /* if it's from the questions table */
				setQuestionsFromDB(questionsHandle.getQuestionArray());
			else if (questionsHandle.getCommand().equals("Subject")) {
				setQuestionsFromDB(questionsHandle.getQuestionArray());
			}
		} else if (msg instanceof ActiveExamHandle) {
			ActiveExamHandle activeExamsHandle = (ActiveExamHandle) msg;
			if (activeExamsHandle.getCommand().equals("ActiveExam"))
				studentWindowController.setActiveExam(activeExamsHandle.getActiveExam());
			else if (activeExamsHandle.getCommand().equals("All"))
				setActivatedUnlockedExams(activeExamsHandle.getActiveExams());
		} else if (msg instanceof ExamHandle) {
			ExamHandle examsHandle = (ExamHandle) msg;
			if (examsHandle.getCommand().equals("Subject")) {
				setExamsFromDB(examsHandle.getExams());
			}
		} else if (msg instanceof TypeHandle) {
			TypeHandle typeHandle = (TypeHandle) msg;
			switch (typeHandle.getCommand()) {
			case "Subjects":
				setSubjectsFromDB(typeHandle.getTypes());
				break;
			case "Courses":
				setCoursesFromDB(typeHandle.getTypes());
				break;
			case "ExamNumbers":
				setExamsByAuthorFromDB(typeHandle.getTypes());
				break;
			}
		} else if (msg instanceof WaitingActiveExamHandle) {
			WaitingActiveExamHandle waitingActiveExamHandle = (WaitingActiveExamHandle) msg;
			if (waitingActiveExamHandle.getCommand().equals("AllWaiting"))
				setWaitingActiveExamsFromDB(waitingActiveExamHandle.getWaitingActiveExams());
		} else if (msg instanceof CheckedExamHandle) {
			CheckedExamHandle checkedExamHandle = (CheckedExamHandle) msg;
			if (checkedExamHandle.getCommand().equals("AllCheckedExams")
					|| (checkedExamHandle.getCommand().equals("CheckExamsByStudent"))) {
				setCheckedExamsFromDB(checkedExamHandle.getCheckedExams());
			}
		} else if (msg instanceof StudentHandle) {
			StudentHandle studentHandle = (StudentHandle) msg;
			if (studentHandle.getCommand().equals("Students")) {
				setStudentsFromDB(studentHandle.getStudents());
			}
		} else if (msg instanceof ReportAboutStudent) {
			ReportAboutStudent studentReport = (ReportAboutStudent) msg;
			if (studentReport.getCommand().equals("StudentStatistic")) {
				Double avg = studentReport.getAverage();
				Integer med = studentReport.getMedian();
				principalWindowController.getAverageTextFieldInStudentReport().setText(avg.toString());
				principalWindowController.getMedianTextFieldInStudentReport().setText(med.toString());
			}
		} else if (msg instanceof ReportAboutCourse) {
			ReportAboutCourse courseReport = (ReportAboutCourse) msg;
			if (courseReport.getCommand().equals("CourseStatistic")) {
				Double avg = courseReport.getAverage();
				Integer med = courseReport.getMedian();
				principalWindowController.getAverageTextFieldInCourseReport().setText(avg.toString());
				principalWindowController.getMedianTextFieldInCourseReport().setText(med.toString());
			} else if (courseReport.getCommand().equals("AllCourses")) {
				setAllCoursesFromDB(courseReport.getCourses());
			}
		} else if (msg instanceof ReportAboutTeacher) {
			ReportAboutTeacher teacherReport = (ReportAboutTeacher) msg;
			if (((ReportAboutTeacher) msg).getCommand().equals("AllTeachers")) {
				setAllTeachersFromDB(teacherReport.getTeachers());
			}
			if (((ReportAboutTeacher) msg).getCommand().equals("TeacherStatistic")) {
				Double avg = teacherReport.getAverage();
				Integer med = teacherReport.getMedian();
				principalWindowController.getAverageTextFieldInTeacherReport().setText(avg.toString());
				principalWindowController.getMedianTextFieldInTeacherReport().setText(med.toString());

			}
		} else if (msg instanceof Boolean) {
			boolean codeExist = (boolean) msg;
			setExecutionCodeExistFlag(codeExist);
		} else if (msg instanceof MyFileHandle) {
			MyFileHandle fileHandle = (MyFileHandle) msg;
			if (fileHandle.getCommand().equals("StudnetExam")) {
				Utilities_Client.writeWordFile(fileHandle.getFile(), true);
			}
		}
	}

	/**
	 * This method handles all data coming from the UI
	 *
	 * @param message
	 *            The message from the UI.
	 */
	public void handleMessageFromClientUI(Object message) {
		try {
			sendToServer(message);
		} catch (IOException e) {
			quit();
		}
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}

	// end region -> Public Methods

}