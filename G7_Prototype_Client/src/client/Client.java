package client;

import java.io.IOException;
import java.util.ArrayList;
import controllers.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ocsf.client.AbstractClient;
import resources.*;

/**
 * The main connector between the user interface and the server. This class
 * sends user requests to the server and handles the returned values from the
 * server.
 * 
 * @author Group 7
 *
 */
public class Client extends AbstractClient implements IScreenController {

	/******************** Attributes ********************/

	private ObservableList<Question> questionsFromDB = FXCollections.observableArrayList();

	private ObservableList<Exam> examsFromDB = FXCollections.observableArrayList();

	private ObservableList<String> subjectsFromDB = FXCollections.observableArrayList();

	private ObservableList<String> coursesFromDB = FXCollections.observableArrayList();

	private ObservableList<String> examsByAuthorFromDB = FXCollections.observableArrayList();

	private ObservableList<String> allStudentsFromDB = FXCollections.observableArrayList();

	private ObservableList<WaitingActiveExam> WaitingActiveExamsFromDB = FXCollections.observableArrayList();

	private ObservableList<CheckedExam> checkedExamsFromDB = FXCollections.observableArrayList();

	private ObservableList<ActiveExam> activatedUnlockedExams = FXCollections.observableArrayList();

	private ObservableList<ActiveExam> ActiveExamsBySubject = FXCollections.observableArrayList();

	private ObservableList<Student> studentsFromDB = FXCollections.observableArrayList();

	private ObservableList<Course> allCoursesFromDB = FXCollections.observableArrayList();

	private ObservableList<Teacher> allTeachersFromDB = FXCollections.observableArrayList();

	private ObservableList<StudentAnswerInQuestion> studnetAnswerInQuestionDB = FXCollections.observableArrayList();

	private ObservableList<ApprovedExamForStudent> approvedExamForStudentsDB = FXCollections.observableArrayList();

	private ObservableList<ApprovedExamForStudent> solvedExamOfStudentsDB = FXCollections.observableArrayList();

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

	/******************** Constructors ********************/

	/**
	 * Constructs an instance of the chat client.
	 *
	 * @param host
	 *            The server to connect to.
	 * @param port
	 *            The port number to connect on.
	 */
	public Client(String host, int port) {
		super(host, port); // Call the superclass constructor
		try {
			openConnection();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/******************** Getters and Setters ********************/

	/**
	 * set Screen Parent
	 */
	public void setScreenParent(ScreensController screenParent) {
		controller = screenParent;
	}

	/**
	 * get Login Window Controller
	 * 
	 * @return LoginWindowController
	 */
	public LoginWindowController getLoginWindowController() {
		return loginWindowController;
	}

	/**
	 * set Login Window Controller
	 * 
	 * @param loginWindowController
	 *            The login window
	 */
	public void setLoginWindowController(LoginWindowController loginWindowController) {
		this.loginWindowController = loginWindowController;
	}

	/**
	 * get Teacher Window Controller
	 * 
	 * @return TeacherWindowController
	 */
	public TeacherWindowController getTeacherWindowController() {
		return teacherWindowController;
	}

	/**
	 * set Teacher Window Controller
	 * 
	 * @param teacherWindowController
	 *            The teacher window
	 */
	public void setTeacherWindowController(TeacherWindowController teacherWindowController) {
		this.teacherWindowController = teacherWindowController;
	}

	/**
	 * get Student Window Controller
	 * 
	 * @return StudentWindowController
	 */
	public StudentWindowController getStudentWindowController() {
		return studentWindowController;
	}

	/**
	 * set Student Window Controller
	 * 
	 * @param studentWindowController
	 *            The student window
	 */
	public void setStudentWindowController(StudentWindowController studentWindowController) {
		this.studentWindowController = studentWindowController;
	}

	/**
	 * get Principal Window Controller
	 * 
	 * @return PrincipalWindowController
	 */
	public PrincipalWindowController getPrincipalWindowController() {
		return principalWindowController;
	}

	/**
	 * set Principal Window Controller
	 * 
	 * @param principalWindowController
	 *            The principal window
	 */
	public void setPrincipalWindowController(PrincipalWindowController principalWindowController) {
		this.principalWindowController = principalWindowController;
	}

	/**
	 * get Questions From DB
	 * 
	 * @return Observable list of questions that returned from the data base
	 */
	public ObservableList<Question> getQuestionsFromDB() {
		return questionsFromDB;
	}

	/**
	 * set Questions From DB
	 * 
	 * @param questions
	 *            The questions from the data base
	 */
	public void setQuestionsFromDB(ArrayList<Question> questions) {
		questionsFromDB.setAll(questions);
	}

	/**
	 * get Question
	 * 
	 * @return Question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * get Exams From DB
	 * 
	 * @return Observable List of exams from the data base
	 */
	public ObservableList<Exam> getExamsFromDB() {
		return examsFromDB;
	}

	/**
	 * set Exams From DB
	 * 
	 * @param exams
	 *            Exams from the data base
	 */
	public void setExamsFromDB(ArrayList<Exam> exams) {
		examsFromDB.setAll(exams);
	}

	/**
	 * set Question
	 * 
	 * @param question
	 *            The requested question
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

	/**
	 * get First Name of client
	 * 
	 * @return String
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * set First Name of client
	 * 
	 * @param firstName
	 *            The first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * get Last Name of client
	 * 
	 * @return String
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * set Last Name of client
	 * 
	 * @param lastName
	 *            The last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * get Subjects From DB
	 * 
	 * @return Observable List of subjects from the data base
	 */
	public ObservableList<String> getSubjectsFromDB() {
		return subjectsFromDB;
	}

	/**
	 * set Subjects From DB
	 * 
	 * @param subjects
	 *            The list of subjects from the data base
	 */
	public void setSubjectsFromDB(ArrayList<String> subjects) {
		Platform.runLater(() -> {
			subjectsFromDB.setAll(subjects);
		});
	}

	/**
	 * get Approved Exam For Students DB
	 * 
	 * @return Observable List of approved Exam For Student from the data base
	 */
	public ObservableList<ApprovedExamForStudent> getApprovedExamForStudentsDB() {
		return approvedExamForStudentsDB;
	}

	/**
	 * set Approved Exam For Students DB
	 * 
	 * @param approvedExamForStudentsDB
	 *            The list of approved Exam For Student from the data base
	 */
	public void setApprovedExamForStudentsDB(ArrayList<ApprovedExamForStudent> approvedExamForStudentsDB) {
		Platform.runLater(() -> {
			this.approvedExamForStudentsDB.setAll(approvedExamForStudentsDB);
		});
	}

	/**
	 * get Courses From DB
	 * 
	 * @return Observable List of courses from the data base
	 */
	public ObservableList<String> getCoursesFromDB() {
		return coursesFromDB;
	}

	/**
	 * set Courses From DB
	 * 
	 * @param courses
	 *            The list of courses from the data base
	 */
	public void setCoursesFromDB(ArrayList<String> courses) {
		Platform.runLater(() -> {
			coursesFromDB.setAll(courses);
		});
	}

	/**
	 * get Exams By Author From DB
	 * 
	 * @return Observable List of exams filtered by author
	 */
	public ObservableList<String> getExamsByAuthorFromDB() {
		return examsByAuthorFromDB;
	}

	/**
	 * set Exams By Author From DB
	 * 
	 * @param examsByAuthorFromDB
	 *            The list of exams filtered by author
	 */
	public void setExamsByAuthorFromDB(ArrayList<String> examsByAuthorFromDB) {
		Platform.runLater(() -> {
			this.examsByAuthorFromDB.setAll(examsByAuthorFromDB);
		});
	}

	/**
	 * get All Students From DB
	 * 
	 * @return Observable List of students from the data base
	 */
	public ObservableList<String> getAllStudentsFromDB() {
		return allStudentsFromDB;
	}

	/**
	 * set All Students From DB
	 * 
	 * @param allStudentsFromDB
	 *            The List of students from the data base
	 */
	public void setAllStudentsFromDB(ArrayList<String> allStudentsFromDB) {
		Platform.runLater(() -> {
			this.allStudentsFromDB.setAll(allStudentsFromDB);
		});
	}

	/**
	 * get Student Answer In Question DB
	 * 
	 * @return Observable List of student answers from the data base
	 */
	public ObservableList<StudentAnswerInQuestion> getStudnetAnswerInQuestionDB() {
		return studnetAnswerInQuestionDB;
	}

	/**
	 * set Student Answer In Question DB
	 * 
	 * @param studnetAnswerInQuestionDB
	 *            The list of student answers from the data base
	 */
	public void setStudnetAnswerInQuestionDB(ArrayList<StudentAnswerInQuestion> studnetAnswerInQuestionDB) {
		this.studnetAnswerInQuestionDB.setAll(studnetAnswerInQuestionDB);
	}

	/**
	 * get client Id
	 * 
	 * @return String The client ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * set client ID
	 * 
	 * @param id
	 *            The client ID
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * get Waiting Active Exams From DB
	 * 
	 * @return Observable List of requests to approve\reject by the principal
	 */
	public ObservableList<WaitingActiveExam> getWaitingActiveExamsFromDB() {
		return WaitingActiveExamsFromDB;
	}

	/**
	 * set Waiting Active Exams From DB
	 * 
	 * @param waitingActiveExamsFromDB
	 *            The list of requests to approve\reject by the principal
	 */
	public void setWaitingActiveExamsFromDB(ArrayList<WaitingActiveExam> waitingActiveExamsFromDB) {
		Platform.runLater(() -> {
			WaitingActiveExamsFromDB.setAll(waitingActiveExamsFromDB);
		});
	}

	/**
	 * get Execution Code Exist Flag
	 * 
	 * @return True if Execution Code Exist
	 */
	public boolean getExecutionCodeExistFlag() {
		return executionCodeExistFlag;
	}

	/**
	 * set Execution Code Exist Flag
	 * 
	 * @param executionCodeExistFlag
	 *            Flag if the execution code exists in the data base
	 */
	public void setExecutionCodeExistFlag(boolean executionCodeExistFlag) {
		this.executionCodeExistFlag = executionCodeExistFlag;
	}

	/**
	 * get Checked Exams From DB
	 * 
	 * @return Observable List of checked exams from the data base
	 */
	public ObservableList<CheckedExam> getCheckedExamsFromDB() {
		return checkedExamsFromDB;
	}

	/**
	 * set Checked Exams From DB
	 * 
	 * @param checkedExamsFromDB
	 *            The list of checked exams from the data base
	 */
	public void setCheckedExamsFromDB(ArrayList<CheckedExam> checkedExamsFromDB) {
		Platform.runLater(() -> {
			this.checkedExamsFromDB.setAll(checkedExamsFromDB);
		});
	}

	/**
	 * get Activated Unlocked Exams
	 * 
	 * @return Observable List of active exams from the data base
	 */
	public ObservableList<ActiveExam> getActivatedUnlockedExams() {
		return activatedUnlockedExams;
	}

	/**
	 * set Activated Unlocked Exams
	 * 
	 * @param activatedUnlockedExams
	 *            The list of active exams from the data base
	 */
	public void setActivatedUnlockedExams(ArrayList<ActiveExam> activatedUnlockedExams) {
		Platform.runLater(() -> {
			this.activatedUnlockedExams.setAll(activatedUnlockedExams);
		});
	}

	/**
	 * get Students From DB
	 * 
	 * @return Observable List of students from the data base
	 */
	public ObservableList<Student> getStudentsFromDB() {
		return studentsFromDB;
	}

	/**
	 * set Students From DB
	 * 
	 * @param studentsFromDB
	 *            The list of students from the data base
	 */
	public void setStudentsFromDB(ArrayList<Student> studentsFromDB) {
		Platform.runLater(() -> {
			this.studentsFromDB.setAll(studentsFromDB);
		});
	}

	/**
	 * get All Courses From DB
	 * 
	 * @return Observable List of courses from the data base
	 */
	public ObservableList<Course> getAllCoursesFromDB() {
		return allCoursesFromDB;
	}

	/**
	 * set all Courses From DB
	 * 
	 * @param allCoursesFromDB
	 *            The List of courses from the data base
	 */
	public void setAllCoursesFromDB(ArrayList<Course> allCoursesFromDB) {
		Platform.runLater(() -> {
			this.allCoursesFromDB.setAll(allCoursesFromDB);
		});
	}

	/**
	 * get All Teachers From DB
	 * 
	 * @return Observable List of teachers from the data base
	 */
	public ObservableList<Teacher> getAllTeachersFromDB() {
		return allTeachersFromDB;
	}

	/**
	 * set All Teachers From DB
	 * 
	 * @param allTeachersFromDB
	 *            The List of teachers from the data base
	 */
	public void setAllTeachersFromDB(ArrayList<Teacher> allTeachersFromDB) {
		Platform.runLater(() -> {
			this.allTeachersFromDB.setAll(allTeachersFromDB);
		});
	}

	/**
	 * get Active Exams By Subject
	 * 
	 * @return Observable List of active exams from the data base
	 */
	public ObservableList<ActiveExam> getActiveExamsBySubject() {
		return ActiveExamsBySubject;
	}

	/**
	 * set Active Exams By Subject
	 * 
	 * @param activeExamsBySubject
	 *            The list of active exams from the data base
	 */
	public void setActiveExamsBySubject(ArrayList<ActiveExam> activeExamsBySubject) {
		Platform.runLater(() -> {
			this.ActiveExamsBySubject.setAll(activeExamsBySubject);
		});
	}

	/**
	 * Get approved Exam Of Students DB
	 * 
	 * @return Observable List of approved exams from the data base
	 */
	public ObservableList<ApprovedExamForStudent> getSolvedExamOfStudentsDB() {
		return solvedExamOfStudentsDB;
	}

	/**
	 * Set approved Exam Of Students DB
	 * 
	 * @param solvedExamOfStudentsDB
	 *            The list of approved exams from the data base
	 */
	public void setSolvedExamOfStudentsDB(ArrayList<ApprovedExamForStudent> solvedExamOfStudentsDB) {
		this.solvedExamOfStudentsDB.setAll(solvedExamOfStudentsDB);
	}

	/******************** Methods ********************/

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
			} else if (questionsHandle.getCommand().equals("QuestionsInExam")) {
				setQuestionsFromDB(questionsHandle.getQuestionArray());
			}
		} else if (msg instanceof ActiveExamHandle) {
			ActiveExamHandle activeExamsHandle = (ActiveExamHandle) msg;
			if (activeExamsHandle.getCommand().equals("ActiveExam"))
				studentWindowController.setActiveExam(activeExamsHandle.getActiveExam());
			else if (activeExamsHandle.getCommand().equals("All"))
				setActivatedUnlockedExams(activeExamsHandle.getActiveExams());
			else if (activeExamsHandle.getCommand().equals("ActiveExamsBySubject"))
				setActiveExamsBySubject(activeExamsHandle.getActiveExams());
		} else if (msg instanceof ExamHandle) {
			ExamHandle examsHandle = (ExamHandle) msg;
			switch (examsHandle.getCommand()) {
			case "Courses":
				setExamsFromDB(examsHandle.getExams());
				break;
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
			case "Students":
				setAllStudentsFromDB(typeHandle.getTypes());
				break;
			}
		} else if (msg instanceof WaitingActiveExamHandle) {
			WaitingActiveExamHandle waitingActiveExamHandle = (WaitingActiveExamHandle) msg;
			if (waitingActiveExamHandle.getCommand().equals("AllWaiting"))
				setWaitingActiveExamsFromDB(waitingActiveExamHandle.getWaitingActiveExams());
		} else if (msg instanceof CheckedExamHandle) {
			CheckedExamHandle checkedExamHandle = (CheckedExamHandle) msg;
			if (checkedExamHandle.getCommand().equals("AllCheckedExams")) {
				setCheckedExamsFromDB(checkedExamHandle.getCheckedExams());
			}
		} else if (msg instanceof StudentHandle) {
			StudentHandle studentHandle = (StudentHandle) msg;
			if (studentHandle.getCommand().equals("Students")) {
				setStudentsFromDB(studentHandle.getStudents());
			}
			// The teacher gets all the students that had copied in the exam
			else if (studentHandle.getCommand().equals("Copiers")) {
				System.out.println("Copiers");
				teacherWindowController.setStudentHandle(studentHandle);
				teacherWindowController.setHadCopied(true);
			}
		} else if (msg instanceof ReportAboutStudent) {
			ReportAboutStudent studentReport = (ReportAboutStudent) msg;
			if (studentReport.getCommand().equals("StudentStatistic")) {
				Double avg = studentReport.getAverage();
				Integer med = studentReport.getMedian();
				principalWindowController.getAverageTextFieldInStudentReport().setText(avg.toString());
				principalWindowController.getMedianTextFieldInStudentReport().setText(med.toString());
				principalWindowController.setGradesWithExam(studentReport.getGrades());
			}
		} else if (msg instanceof ReportAboutCourse) {
			ReportAboutCourse courseReport = (ReportAboutCourse) msg;
			if (courseReport.getCommand().equals("CourseStatistic")) {
				Double avg = courseReport.getAverage();
				Integer med = courseReport.getMedian();
				principalWindowController.getAverageTextFieldInCourseReport().setText(avg.toString());
				principalWindowController.getMedianTextFieldInCourseReport().setText(med.toString());
				principalWindowController.setGrades(courseReport.getGrades());
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
				principalWindowController.setGrades(teacherReport.getGrades());
			}
		} else if (msg instanceof ReportAboutExam) {
			ReportAboutExam reportAboutExam = (ReportAboutExam) msg;
			Double avg = reportAboutExam.getAverage();
			Integer med = reportAboutExam.getMedian();
			Integer started = reportAboutExam.getStudentStarted();
			Integer finished = reportAboutExam.getStudentFinished();
			Integer forced = reportAboutExam.getStudentForced();
			teacherWindowController.getAverageTextFieldInTeacherReport().setText(avg.toString());
			teacherWindowController.getMedianTextFieldInTeacherReport().setText(med.toString());
			teacherWindowController.getStartedTextFieldInTeacherReport().setText(started.toString());
			teacherWindowController.getFinishedTextFieldInTeacherReport().setText(finished.toString());
			teacherWindowController.getForcedTextFieldInTeacherReport().setText(forced.toString());
			teacherWindowController.setGrades(reportAboutExam.getGrades());
		} else if (msg instanceof Boolean) {
			boolean codeExist = (boolean) msg;
			setExecutionCodeExistFlag(codeExist);
		} else if (msg instanceof MyFileHandle) {
			MyFileHandle fileHandle = (MyFileHandle) msg;
			if (fileHandle.getCommand().equals("StudnetExam")) {
				Utilities_Client.writeWordFile(fileHandle.getFile(), true);
			}
		} else if (msg instanceof StudentAnswerInQuestionHandle) {
			StudentAnswerInQuestionHandle studentAnswerInQuestionHandle = (StudentAnswerInQuestionHandle) msg;
			if (studentAnswerInQuestionHandle.getCommand().equals("Answers")) {
				setStudnetAnswerInQuestionDB(studentAnswerInQuestionHandle.getStudentAnswers());
			}
		} else if (msg instanceof ApprovedExamForStudentHandle) {
			ApprovedExamForStudentHandle approvedExamForStudentHandle = (ApprovedExamForStudentHandle) msg;
			if (approvedExamForStudentHandle.getCommand().equals("ApprovedExamForStudent")) {
				setApprovedExamForStudentsDB(approvedExamForStudentHandle.getApprovedExamForStudentsArray());
			} else if (approvedExamForStudentHandle.getCommand().equals("SolvedExamsByStudent")) {
				setSolvedExamOfStudentsDB(approvedExamForStudentHandle.getApprovedExamForStudentsArray());
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

} // end of class