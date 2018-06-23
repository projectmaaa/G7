package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.Collections;
import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import resources.*;

/**
 * This class for general SQL: strings, methods ,utilities.
 * 
 * @author Group 7
 *
 */
public class SqlUtilities {

	/******************** Attributes ********************/

	public final static String SELECT_All_FROM_Questions_by_author = "SELECT * FROM Questions WHERE author=? AND subjectID=?;";

	public final static String Login_SELECT_UserID_From_Users = "SELECT idUsers FROM Users WHERE idUsers=? AND passWord=?;";

	public final static String Login_getlog_Status = "SELECT logged FROM Users WHERE idUsers=? AND passWord=?;";

	public final static String Login_UpdateUser_logStatus_Connected = "UPDATE Users SET logged=1 WHERE idUsers=? AND passWord=?;";

	public final static String Login_UpdateUser_logStatus_DisConnected = "UPDATE Users SET logged=0 WHERE idUsers=? AND passWord=?;";

	public final static String UPDATE_Questions_Table = "UPDATE Questions SET questionText=?, firstAnswer=?, secondAnswer=?, thirdAnswer=?, fourthAnswer=?, correctAnswer=? WHERE subjectID=? AND questionNum=?;";

	public final static String INSERT_Question = "INSERT INTO Questions VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

	public final static String REMOVE_Questions = "DELETE FROM Questions WHERE subjectID=? AND questionNum=?;";

	public final static String INSERET_EXAM = "INSERT INTO Exam VALUES (?, ?, ?, ?, ?, ?, ?);";

	public final static String INSERET_QUESTION_IN_EXAM = "INSERT INTO QuestionInExam VALUES (?, ?, ?, ?, ?);";

	public final static String SELECT_QUESTION_IN_EXAM = "SELECT * FROM QuestionInExam WHERE subjectID=? AND courseID=? AND examNum=?;";

	public final static String SELECT_FROM_Subject = "SELECT questionsCount FROM Subject WHERE subjectID=?;";

	public final static String UPDATE_Subject = "UPDATE Subject SET questionsCount=? WHERE subjectID=?;";

	public final static String GetTypeAndUserNameAndLastName = "SELECT type, firstName, lastName FROM Users WHERE idUsers=?;";

	public final static String GetQuestionBySubject = "SELECT * FROM Questions WHERE subjectID=?;";

	public final static String GetQuestionBySubjectIDAndQuestionNum = "SELECT * FROM Questions WHERE subjectID=? AND questionNum=?;";

	public final static String SELECT_FROM_Course = "SELECT examsCount FROM Course Where courseID=?;";

	public final static String UPDATE_Course = "UPDATE Course SET examsCount=? WHERE courseID=?;";

	public final static String SELECT_subjectID_FROM_Subject = "SELECT subjectID FROM Subject WHERE subjectName=?;";

	public final static String SELECT_courseID_FROM_Course = "SELECT courseID FROM Course WHERE courseName=?;";

	public final static String SELECT_Exam_BY_CourseID = "SELECT * FROM Exam WHERE courseID=?;";

	public final static String SELECT_Exam_BY_Subject_CourseID_ExamID = "SELECT * FROM Exam WHERE subjectID=? AND courseID=? AND examNum=?;";

	public final static String SELECT_ActiveExam = "SELECT * FROM ActiveExam WHERE executionCode=?;";

	public final static String INSERT_ActiveExam = "INSERT INTO ActiveExam VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

	public final static String SELECT_Subjects = "SELECT subjectName FROM Subject";

	public final static String SELECT_Subjects_By_Teacher_ID = "SELECT subjectName FROM TeacherSubjects WHERE userID=?;";

	public final static String SELECT_Courses_BY_SubjectID = "SELECT courseName FROM Course WHERE subjectID=?;";

	public final static String LOCK_Exam = "UPDATE ActiveExam SET locked=1 WHERE subjectID=? AND courseID=? AND examNum=? AND executionCode=?;";

	public final static String INSERT_WaitingActiveExam = "INSERT INTO WaitingActiveExam VALUES (?, ?, ?, ?, ?, ?, ?);";

	public final static String SELECT_All_WaitingActiveExam = "SELECT * FROM WaitingActiveExam";

	public final static String CHANGE_ActiveExamDuration = "UPDATE ActiveExam SET duration=? WHERE subjectID=? AND courseID=? AND examNum=? AND executionCode=?;";

	public final static String REMOVE_WaitingActiveExam = "DELETE FROM WaitingActiveExam WHERE subjectID=? AND courseID=? AND examNum=? AND executionCode=?;";

	public final static String INSERT_StudentAnswerInQuestion = "INSERT INTO StudentAnswerInQuestion VALUES(?, ?, ?, ?, ?, ?, ?, ?);";

	public final static String INSERT_StudentInActiveExam = "INSERT INTO StudentInActiveExam VALUES(?, ?, ?, ?, ?, ?, ?);";

	public final static String CHECK_ExecutionCodeExist = "SELECT * FROM ActiveExam WHERE executionCode=?;";

	public final static String SELECT_CheckedExams_By_Activator = "SELECT * FROM CheckedExam, ActiveExam WHERE CheckedExam.executionCode=ActiveExam.executionCode AND ActiveExam.activatorsID=? AND grade>-1;";

	public final static String SELECT_User_ApprovedExamForStudent = "SELECT examNum, executionCode, grade, comments FROM ApprovedExamForStudent WHERE subjectID=? AND courseID=? AND studentID=?;";

	public final static String INSERT_ApprovedExamForStudent = "INSERT INTO ApprovedExamForStudent VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

	public final static String REMOVE_checkedExam = "DELETE FROM CheckedExam WHERE subjectID=? AND courseID=? AND examNum=? AND executionCode=? AND studentID=?;";

	public final static String CHANGE_GradeByTeacher = "UPDATE CheckedExam SET grade=?, commentsOfChangeGrade=? WHERE subjectID=? AND courseID=? AND examNum=? AND executionCode=? AND studentID=?;";

	public final static String ADD_CommentsInCheckedExam = "UPDATE CheckedExam SET generalComments=? WHERE subjectID=? AND courseID=? AND examNum=? AND executionCode=? AND studentID=?;";

	public final static String SELECT_Unlocked_Activated_Exams_By_ActivatorsID = "SELECT subjectID, courseID, examNum, executionCode, duration, type FROM ActiveExam WHERE activatorsID=? AND courseID=? AND locked=0;";

	public final static String SELECT_All_Students = "SELECT idUsers, firstName, lastName FROM Users WHERE type='Student'";

	public final static String Check_Question_Answer = "select correctAnswer from Questions where subjectID = ? AND questionNum = ?;";

	public final static String Get_Points_of_Question = "select Points from QuestionInExam where subjectID = ? and questionNum = ? and courseID = ? and examNum = ?;";

	public final static String INSERT_CheckedExam = "insert into CheckedExam values (?, ?, ?, ?, ?, ?, ?, ?);";

	public final static String CALCULATE_StudentAVG = "SELECT AVG(grade) FROM ApprovedExamForStudent WHERE studentID=?";

	public final static String SELECT_All_Courses = "SELECT subjectID, courseID, courseName FROM Course";

	public final static String SELECT_All_Teachers = "SELECT idUsers, firstName, lastName FROM Users WHERE type='Teacher'";

	public final static String INSERT_SubmittedExam = "insert into SubmittedExam values (?, ?, ?, ?, ?, ?, ?);";

	public final static String DELETE_Exam_from_Exam_Table = "DELETE FROM Exam WHERE subjectID=? AND courseID=? AND examNum=?;";

	public final static String DELETE_Exam_from_Active_Exam_Table = "DELETE FROM ActiveExam WHERE subjectID=? AND courseID=? AND examNum=?;";

	public final static String getActivatorsID = "SELECT activatorsID FROM ActiveExam WHERE subjectID=? AND courseID=? AND examNum=?;";

	public final static String ALL_Grades_of_Student = "SELECT grade, subjectID, courseID, examNum FROM ApprovedExamForStudent WHERE studentID=?";

	public final static String ALL_Grades_of_Course = "SELECT grade FROM ApprovedExamForStudent WHERE courseID=?";

	public final static String ALL_Grades_of_TeacherAsActivator = "SELECT grade FROM ApprovedExamForStudent WHERE idUsers=?";

	public final static String SELECT_Comments_from_Checked_Exams = "SELECT generalComments, commentsOfChangeGrade FROM CheckedExam WHERE executionCode=? AND studentID=?;";

	public final static String SELECT_ActiveExamsByCourse = "SELECT subjectID, courseID, examNum, executionCode, activator, duration, locked, type FROM ActiveExam WHERE courseID=?;";

	public final static String SELECT_Exams_By_Author_and_Course = "SELECT examNum FROM Exam WHERE courseID=? AND author=?;";

	public final static String SELECT_Exams_By_Author = "SELECT subjectID, courseID, examNum FROM Exam WHERE author=?;";

	public final static String getStudentAnswers = "SELECT questionNum, questionOrderInExam, answer FROM StudentAnswerInQuestion WHERE studentID=? AND subjectID=? AND courseID=? AND examNum=? AND executionCode=?;";

	public final static String GET_QUESTIONS_OF_EXAM = "SELECT questionText, firstAnswer, secondAnswer, thirdAnswer, fourthAnswer, correctAnswer, Questions.questionNum "
			+ "FROM Questions, QuestionInExam, ActiveExam " + "WHERE executionCode = ? "
			+ "AND Questions.subjectID = QuestionInExam.subjectID "
			+ "AND QuestionInExam.subjectID = ActiveExam.subjectID "
			+ "AND QuestionInExam.courseID = ActiveExam.courseID "
			+ "AND Questions.questionNum = QuestionInExam.questionNum "
			+ "AND QuestionInExam.examNum = ActiveExam.examNum;";

	public final static String CALCULATE_ExamAVG = "SELECT AVG(grade) FROM ApprovedExamForStudent WHERE subjectID=? AND courseID=? AND examNum=?;";

	public final static String ALL_Grades_of_Exam = "SELECT grade FROM ApprovedExamForStudent WHERE subjectID=? AND courseID=? AND examNum=?;";

	public final static String COUNT_StudentWhoStarted = "SELECT COUNT(studentID) FROM SubmittedExam WHERE subjectID=? AND courseID=? AND examNum=?;";

	public final static String COUNT_StudentWhoFinished = "SELECT COUNT(studentID) FROM SubmittedExam WHERE subjectID=? AND courseID=? AND examNum=? AND submitted=1;";

	public final static String getQuestionsFromSpecificExam = "SELECT Questions.subjectID, Questions.questionNum, Questions.author, Questions.questionText, Questions.firstAnswer, Questions.secondAnswer, Questions.thirdAnswer, Questions.fourthAnswer, Questions.correctAnswer FROM Questions, QuestionInExam, Exam WHERE Exam.subjectID=? AND Exam.courseID=? AND Exam.examNum=? AND Exam.subjectID=QuestionInExam.subjectID AND Exam.courseID=QuestionInExam.courseID AND Exam.examNum=QuestionInExam.examNum AND QuestionInExam.questionNum=Questions.questionNum AND Exam.subjectID=Questions.subjectID;";

	public final static String GetStudentAnswerInQuestionByExecutionCode = "select * from StudentAnswerInQuestion where executionCode = ?;";

	public final static String SELECT_ApprovedExamByStudent = "SELECT subjectID, courseID, examNum, executionCode, grade, comments FROM ApprovedExamForStudent WHERE studentID=?;";

	public final static String GetNumberOfExamineesByExecutionCode = "select count(distinct ?) from StudentAnswerInQuestion where executionCode = ?;";

	public final static String GetNumberOfExamineesThatSubmitOrNot = "select count(*) from SubmittedExam where executionCode = ? and submitted = ?;";

	/******************** Methods ********************/

	/**
	 * this method creates connection to the db using driver with userName and
	 * passWord.
	 * 
	 * @param userName
	 *            User's Name
	 * @param passWord
	 *            Password
	 * @return The connection to the data base
	 */
	@SuppressWarnings("deprecation")
	public static Connection connection(String userName, String passWord) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			/* handle the error */
		}
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://group7project.c2ntivjwkagb.eu-central-1.rds.amazonaws.com:3306/group7db", userName,
					passWord);
			System.out.println("SQL connection succeed");
			return conn;
		} catch (SQLException ex) {/* handle any errors */
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return null;
	}

	/**
	 * This method return activeExam using executionCode.
	 * 
	 * @param executionCode
	 *            Execution Code
	 * @param connection
	 *            Connection to the data base
	 * @return The active exam and what to do with it
	 * @throws SQLException
	 *             Something went wrong with the query
	 */
	public static ActiveExamHandle getActiveExam(String executionCode, Connection connection) throws SQLException {
		ActiveExam activeExam = null;
		PreparedStatement statement = connection.prepareStatement(SELECT_ActiveExam);
		statement.setString(1, executionCode);
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			statement = connection.prepareStatement(SELECT_Exam_BY_Subject_CourseID_ExamID);
			statement.setString(1, resultSet.getString(1));
			statement.setString(2, resultSet.getString(2));
			statement.setString(3, resultSet.getString(3));
			int locked = resultSet.getInt(7);
			String type = resultSet.getString(8);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Exam exam = new Exam(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6), resultSet.getString(7));
				statement = connection.prepareStatement(SELECT_QUESTION_IN_EXAM);
				statement.setString(1, resultSet.getString(1));
				statement.setString(2, resultSet.getString(2));
				statement.setString(3, resultSet.getString(3));
				resultSet = statement.executeQuery();
				while (resultSet.next()) {
					ArrayList<String> possibleAnswers = new ArrayList<String>(4);
					statement = connection.prepareStatement(GetQuestionBySubjectIDAndQuestionNum);
					statement.setString(1, resultSet.getString(1));
					statement.setString(2, resultSet.getString(2));
					ResultSet rs = statement.executeQuery();
					int index = 0;
					if (rs.next()) {
						while (index < 4) { // add the possible answers to the array list
							possibleAnswers.add(index, rs.getString(index + 5));
							index++;
						}
						Question question = new Question(rs.getString(1), rs.getString(2), rs.getString(3),
								rs.getString(4), possibleAnswers, rs.getString(9));
						exam.getQuestions().add(new QuestionInExam(exam, question));
					}
					rs.close();
				}
				closeResultSetAndStatement(resultSet, null, statement);
				activeExam = new ActiveExam(exam, executionCode);
				activeExam.setLocked(locked);
				activeExam.setType(type);
			}
		}
		return (new ActiveExamHandle("ActiveExam", activeExam));
	}

	/**
	 * This method inserts student answer
	 * 
	 * @param submittedExam
	 *            Submitted Exam
	 * @param connection
	 *            Connection to the data base
	 * @throws SQLException
	 *             Something went wrong with the query
	 */
	public static void insert_StudentAnswerInQuestion(SubmittedExam submittedExam, Connection connection)
			throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_StudentAnswerInQuestion);
		preparedStatement.setString(1, submittedExam.getStudentInActiveExam().getStudent().getId());
		preparedStatement.setString(2, submittedExam.getStudentInActiveExam().getActiveExam().getExam().getSubjectID());
		preparedStatement.setString(3, submittedExam.getStudentInActiveExam().getActiveExam().getExam().getCourseID());
		preparedStatement.setString(4, submittedExam.getStudentInActiveExam().getActiveExam().getExam().getExamNum());
		preparedStatement.setString(5, submittedExam.getStudentInActiveExam().getActiveExam().getExecutionCode());
		if (!submittedExam.getAnswers().isEmpty()) {
			for (StudentAnswerInQuestion studentAnswerInQuestion : submittedExam.getAnswers()) {
				preparedStatement.setString(6, studentAnswerInQuestion.getQuestionNum());
				preparedStatement.setString(7, studentAnswerInQuestion.getQuestionOrderInExam());
				preparedStatement.setString(8, studentAnswerInQuestion.getStudentAnswer());
				preparedStatement.executeUpdate();
			}
		} else {
			preparedStatement.setString(6, "Man");
			preparedStatement.setString(7, "0");
			preparedStatement.setString(8, "0");
			preparedStatement.executeUpdate();
		}
		closeResultSetAndStatement(null, null, preparedStatement);
	}

	/**
	 * Returns the questions in the specific exam with execution code.
	 * 
	 * @param executionCode
	 *            Execution Code
	 * @param connection
	 *            Connection to the data base
	 * @return Question Handle that contains exam's questions.
	 * @throws SQLException
	 *             Something went wrong with the query
	 */
	public static QuestionHandle getQuestionInExam(String executionCode, Connection connection) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(GET_QUESTIONS_OF_EXAM);
		ArrayList<Question> questionArray = new ArrayList<Question>();
		preparedStatement.setString(1, executionCode);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			questionArray.add(new Question(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
					resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7)));
		}
		closeResultSetAndStatement(null, null, preparedStatement);
		return (new QuestionHandle("QuestionsInExam", questionArray));
	}

	/**
	 * Returns the questions of exam with no need of active exam
	 * 
	 * @param subjectID
	 *            The number of the subject
	 * @param courseID
	 *            The number of the Course
	 * @param examNumber
	 *            Exam Number
	 * @param connection
	 *            Connection to the data base
	 * @return The questions and what to do with it
	 * @throws SQLException
	 *             Something went wrong with the query
	 */
	public static QuestionHandle getQuestionsInGeneralExam(String subjectID, String courseID, String examNumber,
			Connection connection) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(getQuestionsFromSpecificExam);
		ArrayList<Question> questions = new ArrayList<>();
		ArrayList<String> possibleAnswers = new ArrayList<>();
		preparedStatement.setString(1, subjectID);
		preparedStatement.setString(2, courseID);
		preparedStatement.setString(3, examNumber);
		ResultSet rs = preparedStatement.executeQuery();
		int index = 0;
		while (rs.next()) {
			while (index < 4) { // add the possible answers to the array list
				possibleAnswers.add(index, rs.getString(index + 5));
				index++;
			}
			questions.add(new Question(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					possibleAnswers, rs.getString(9)));
			index = 0;
		}
		closeResultSetAndStatement(rs, null, preparedStatement);
		return (new QuestionHandle("All", questions));
	}

	/**
	 * Inserts a new record to StudentInActiveExam table in database.
	 * 
	 * @param studentInActiveExam
	 *            Student in active exam
	 * @param connection
	 *            Connection to the data base
	 * @throws SQLException
	 *             Something went wrong with the query
	 */
	public static void insert_StudentInActiveExam(StudentInActiveExam studentInActiveExam, Connection connection)
			throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_StudentInActiveExam);
		preparedStatement.setString(1, studentInActiveExam.getStudent().getId());
		preparedStatement.setString(2, studentInActiveExam.getActiveExam().getExam().getSubjectID());
		preparedStatement.setString(3, studentInActiveExam.getActiveExam().getExam().getCourseID());
		preparedStatement.setString(4, studentInActiveExam.getActiveExam().getExam().getExamNum());
		preparedStatement.setString(5, studentInActiveExam.getActiveExam().getExecutionCode());
		preparedStatement.setString(6, studentInActiveExam.getDate());
		preparedStatement.setString(7, studentInActiveExam.getStartedTime());
		preparedStatement.executeUpdate();
		closeResultSetAndStatement(null, null, preparedStatement);
	}

	/**
	 * Returns a reference of the Answers of student in specific active exam.
	 * 
	 * @param studentID
	 *            Student's ID
	 * @param subjectID
	 *            The number of the subject
	 * @param courseID
	 *            The number of the Course
	 * @param examNum
	 *            Exam Number
	 * @param executionCode
	 *            Execution Code
	 * @param student
	 *            Student
	 * @param connection
	 *            Connection to the data base
	 * @return Student Answer In Question Handle that contains student's answers.
	 * @throws SQLException
	 *             Something went wrong with the query
	 */
	public static StudentAnswerInQuestionHandle getAnswers(String studentID, String subjectID, String courseID,
			String examNum, String executionCode, String student, Connection connection) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(getStudentAnswers);
		ArrayList<StudentAnswerInQuestion> studentAnswersArray = new ArrayList<StudentAnswerInQuestion>();
		preparedStatement.setString(1, studentID);
		preparedStatement.setString(2,
				((student.equals("true")) ? (getSubjectID(subjectID, connection)) : (subjectID)));
		preparedStatement.setString(3, ((student.equals("true")) ? (getCourseID(courseID, connection)) : (courseID)));
		preparedStatement.setString(4, examNum);
		preparedStatement.setString(5, executionCode);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			studentAnswersArray.add(new StudentAnswerInQuestion(subjectID, resultSet.getString(1),
					resultSet.getString(2), resultSet.getString(3)));
		}
		closeResultSetAndStatement(null, null, preparedStatement);
		return (new StudentAnswerInQuestionHandle("Answers", studentAnswersArray));
	}

	/**
	 * returns the whole table of questions of the specific author (the user) for
	 * the table view when edit\remove is pressed
	 * 
	 * @param author
	 *            Author's Name
	 * @param subject
	 *            Subject Name
	 * @param connection
	 *            Connection to the data base
	 * @return The questions and what to do with it
	 * @throws SQLException
	 *             Something went wrong with the query
	 */
	public static QuestionHandle getQuestions(String author, String subject, Connection connection)
			throws SQLException {
		ArrayList<Question> questions = new ArrayList<Question>();
		ArrayList<String> possibleAnswers = new ArrayList<String>(4);
		PreparedStatement statement = connection.prepareStatement(SELECT_All_FROM_Questions_by_author);
		statement.setString(1, author);
		try {
			statement.setString(2, getSubjectID(subject, connection));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		ResultSet rs = statement.executeQuery();
		int index = 0;
		while (rs.next()) {
			while (index < 4) { // add the possible answers to the array list
				possibleAnswers.add(index, rs.getString(index + 5));
				index++;
			}
			questions.add(new Question(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					possibleAnswers, rs.getString(9)));
			index = 0;
		}
		closeResultSetAndStatement(rs, null, statement);
		return (new QuestionHandle("All", questions));
	}

	/**
	 * Gets all the users that are student and return a list that contains them.
	 * 
	 * @param connection
	 *            Connection to the data base
	 * @return Student Handle with list that contains all the students.
	 * @throws SQLException
	 *             Something went wrong with the query
	 */
	public static StudentHandle getAllStudents(Connection connection) throws SQLException {
		ArrayList<Student> students = new ArrayList<Student>();
		PreparedStatement statement = connection.prepareStatement(SELECT_All_Students);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			students.add(new Student(rs.getString(1), rs.getString(2), rs.getString(3)));
		}
		closeResultSetAndStatement(rs, null, statement);
		return (new StudentHandle("Students", students));
	}

	/**
	 * Returns a report of specific course
	 * 
	 * @param connection
	 *            Connection to the data base
	 * @return Report About Course
	 * @throws SQLException
	 *             Something went wrong with the query
	 */
	public static ReportAboutCourse getAllCourses(Connection connection) throws SQLException {
		ArrayList<Course> courses = new ArrayList<Course>();
		PreparedStatement statement = connection.prepareStatement(SELECT_All_Courses);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			courses.add(new Course(rs.getString(1), rs.getString(2), rs.getString(3)));
		}
		closeResultSetAndStatement(rs, null, statement);
		return new ReportAboutCourse("AllCourses", courses);
	}

	/**
	 * Returns a report of specific teacher
	 * 
	 * @param connection
	 *            Connection to the data base
	 * @return Report About Teacher
	 * @throws SQLException
	 *             Something went wrong with the query
	 */
	public static ReportAboutTeacher getAllTeachers(Connection connection) throws SQLException {
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		PreparedStatement statement = connection.prepareStatement(SELECT_All_Teachers);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			teachers.add(new Teacher(rs.getString(1), rs.getString(2), rs.getString(3)));
		}
		closeResultSetAndStatement(rs, null, statement);
		return new ReportAboutTeacher(teachers, "AllTeachers");
	}

	/**
	 * Returns the questions of specific subject by creating list of questions.
	 * 
	 * @param connection
	 *            Connection to the data base
	 * @param subject
	 *            Subject Name
	 * @return Question Handle that contains list of questions
	 * @throws SQLException
	 *             Something went wrong with the query
	 */
	public static QuestionHandle getQuestionsBySubject(Connection connection, String subject) throws SQLException {
		ArrayList<Question> questionsBySubject = new ArrayList<Question>();
		ArrayList<String> possibleAnswers = new ArrayList<String>(4);
		PreparedStatement statement = connection.prepareStatement(SqlUtilities.GetQuestionBySubject);
		try {
			statement.setString(1, getSubjectID(subject, connection));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		ResultSet rs = statement.executeQuery();
		int index = 0;
		while (rs.next()) {
			while (index < 4) { // add the possible answers to the array list
				possibleAnswers.add(index, rs.getString(index + 5));
				index++;
			}
			questionsBySubject.add(new Question(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					possibleAnswers, rs.getString(9)));
			index = 0;
		}
		closeResultSetAndStatement(rs, null, statement);
		return (new QuestionHandle("Subject", questionsBySubject));
	}

	/**
	 * Returns the exams of specific course by creating list of exams.
	 * 
	 * @param connection
	 *            Connection to the data base
	 * @param course
	 *            Course Name
	 * @return Exam Handle that contains list of exams
	 * @throws SQLException
	 *             Something went wrong with the query
	 */
	public static ExamHandle getExamsByCourse(Connection connection, String course) throws SQLException {
		ArrayList<Exam> examsByCourse = new ArrayList<Exam>();
		PreparedStatement statement = connection.prepareStatement(SqlUtilities.SELECT_Exam_BY_CourseID);
		try {
			statement.setString(1, getCourseID(course, connection));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			examsByCourse.add(new Exam(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5),
					rs.getString(6), rs.getString(7)));
		}
		closeResultSetAndStatement(rs, null, statement);
		return (new ExamHandle("Courses", examsByCourse));
	}

	/**
	 * Returns all the waiting exams.
	 * 
	 * @param connection
	 *            Connection to the data base
	 * @return Waiting Active Exam Handle that contains list of waiting exams
	 * @throws SQLException
	 *             Something went wrong with the query
	 */
	public static WaitingActiveExamHandle getWaitingActiveExam(Connection connection) throws SQLException {
		ArrayList<WaitingActiveExam> waitingActiveExams = new ArrayList<WaitingActiveExam>();
		PreparedStatement statement = connection.prepareStatement(SELECT_All_WaitingActiveExam);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			ActiveExam activeExam = new ActiveExam(new Exam(rs.getString(1), rs.getString(2), rs.getString(3)),
					rs.getString(4), rs.getInt(5));
			waitingActiveExams.add(new WaitingActiveExam(activeExam, rs.getInt(6), rs.getString(7)));
		}
		closeResultSetAndStatement(rs, null, statement);
		return (new WaitingActiveExamHandle("AllWaiting", waitingActiveExams));
	}

	/**
	 * Returns all the checked exams.
	 * 
	 * @param activatorsID
	 *            Activator's ID
	 * @param connection
	 *            Connection to the data base
	 * @return Checked Exam Handle that contains list of checked exams
	 * @throws SQLException
	 *             Something went wrong with the query
	 */
	public static CheckedExamHandle getCheckedExam(String activatorsID, Connection connection) throws SQLException {
		ArrayList<CheckedExam> checkedExams = new ArrayList<CheckedExam>();
		PreparedStatement statement = connection.prepareStatement(SELECT_CheckedExams_By_Activator);
		statement.setString(1, activatorsID);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			Exam exam = new Exam(rs.getString(1), rs.getString(2), rs.getString(3));
			ActiveExam activeExam = new ActiveExam(exam, rs.getString(4));
			StudentInActiveExam studentInActiveExam = new StudentInActiveExam(
					new Student(rs.getString(5), "firstName", "lastName"), activeExam);
			SubmittedExam submittedExam = new SubmittedExam(studentInActiveExam);
			checkedExams.add(new CheckedExam(submittedExam, rs.getInt(6)));
		}
		return (new CheckedExamHandle("AllCheckedExams", checkedExams));
	}

	/**
	 * Returns all the solved exams of specific student by creating list of approve
	 * exams.
	 * 
	 * @param studentHandle
	 *            Student Handle that contains the information about the student and
	 *            what to do with it
	 * @param connection
	 *            Connection to the data base
	 * @return Approved Exam For Student Handle that contains list of solved exams
	 * @throws SQLException
	 *             Something went wrong with the query
	 */
	public static ApprovedExamForStudentHandle getSolvedExamsByStudent(StudentHandle studentHandle,
			Connection connection) throws SQLException {
		ArrayList<ApprovedExamForStudent> approved = new ArrayList<ApprovedExamForStudent>();
		PreparedStatement statement = connection.prepareStatement(SELECT_ApprovedExamByStudent);
		statement.setString(1, studentHandle.getStudent().getId());
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			Exam exam = new Exam(rs.getString(1), rs.getString(2), rs.getString(3));
			ActiveExam activeExam = new ActiveExam(exam, rs.getString(4));
			StudentInActiveExam studentInActiveExam = new StudentInActiveExam(studentHandle.getStudent(), activeExam);
			SubmittedExam submittedExam = new SubmittedExam(studentInActiveExam);
			CheckedExam checkedExam = new CheckedExam(submittedExam);
			approved.add(new ApprovedExamForStudent(checkedExam, rs.getInt(5), rs.getString(6)));
		}
		return (new ApprovedExamForStudentHandle("SolvedExamsByStudent", approved));
	}

	/**
	 * Returns all the approved exams of specific student by creating list of
	 * approve exams.
	 * 
	 * @param studentID
	 *            Student ID
	 * @param subject
	 *            Subject Name
	 * @param course
	 *            Course Name
	 * @param connection
	 *            Connection to the data base
	 * @return Approved Exam For Student Handle that contains list of approved exams
	 * @throws SQLException
	 *             Something went wrong with the query
	 */
	public static ApprovedExamForStudentHandle getApprovedExamForStudent(String studentID, String subject,
			String course, Connection connection) throws SQLException {
		ArrayList<ApprovedExamForStudent> approvedExamForStudents = new ArrayList<ApprovedExamForStudent>();
		PreparedStatement preparedStatement = connection
				.prepareStatement(SqlUtilities.SELECT_User_ApprovedExamForStudent);
		preparedStatement.setString(1, getSubjectID(subject, connection));
		preparedStatement.setString(2, getCourseID(course, connection));
		preparedStatement.setString(3, studentID);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			Exam exam = new Exam(rs.getString(1));
			ActiveExam activeExam = new ActiveExam(exam, rs.getString(2));
			StudentInActiveExam studentInActiveExam = new StudentInActiveExam(activeExam);
			SubmittedExam submittedExam = new SubmittedExam(studentInActiveExam);
			CheckedExam checkedExam = new CheckedExam(submittedExam, rs.getString(4));
			approvedExamForStudents.add(new ApprovedExamForStudent(checkedExam, Integer.parseInt(rs.getString(3))));
		}
		closeResultSetAndStatement(null, null, preparedStatement);
		return (new ApprovedExamForStudentHandle("ApprovedExamForStudent", approvedExamForStudents));
	}

	/**
	 * updates the questions table in the data base
	 * 
	 * @param newQuestions
	 *            List of questions to update
	 * @param connection
	 *            Connection to the data base
	 * @throws SQLException
	 *             Something went wrong with the query
	 */
	public static void editQuestionsTable(ArrayList<Question> newQuestions, Connection connection) throws SQLException {
		PreparedStatement update = connection.prepareStatement(SqlUtilities.UPDATE_Questions_Table);
		for (Question question : newQuestions) {
			update.setString(1, question.getQuestionText());
			update.setString(2, question.getFirstPossibleAnswer());
			update.setString(3, question.getSecondPossibleAnswer());
			update.setString(4, question.getThirdPossibleAnswer());
			update.setString(5, question.getFourthPossibleAnswer());
			update.setString(6, question.getCorrectAnswer());
			update.setString(7, question.getSubjectID());
			update.setString(8, question.getQuestionNum());
			update.executeUpdate();
		}
		closeResultSetAndStatement(null, null, update);
	}

	/**
	 * adds new question to the DB
	 * 
	 * @param question
	 *            The new question to add
	 * @param connection
	 *            Connection to the data base
	 * @throws SQLException
	 *             Something went wrong with the query
	 */
	public static void insertNewQuestion(Question question, Connection connection) throws SQLException {
		PreparedStatement insert = connection.prepareStatement(SqlUtilities.INSERT_Question);
		String subjectID = getSubjectID(question.getSubjectID(), connection);
		int questionsCount = getQuestionCount(subjectID, connection);
		String questionNumber = questionsCount < 100
				? (questionsCount < 10 ? "00" + questionsCount : "0" + questionsCount)
				: Integer.toString(questionsCount);
		insert.setString(1, subjectID);
		insert.setString(2, questionNumber);
		insert.setString(3, question.getAuthor());
		insert.setString(4, question.getQuestionText());
		insert.setString(5, question.getFirstPossibleAnswer());
		insert.setString(6, question.getSecondPossibleAnswer());
		insert.setString(7, question.getThirdPossibleAnswer());
		insert.setString(8, question.getFourthPossibleAnswer());
		insert.setString(9, question.getCorrectAnswer());
		insert.executeUpdate();
		closeResultSetAndStatement(null, null, insert);
	}

	/**
	 * Inserts new exam into the DB
	 * 
	 * @param exam
	 *            Exam
	 * @param connection
	 *            A connection (session) with a specific database.
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors.
	 */
	public static void insertNewExam(Exam exam, Connection connection) throws SQLException {
		PreparedStatement insert = connection.prepareStatement(SqlUtilities.INSERET_EXAM);
		int examNum = SqlUtilities.getExamCount(getCourseID(exam.getCourseID(), connection), connection);
		String examNumber = examNum < 10 ? "0" + examNum : Integer.toString(examNum);
		insert.setString(1, getSubjectID(exam.getSubjectID(), connection));
		insert.setString(2, getCourseID(exam.getCourseID(), connection));
		insert.setString(3, examNumber);
		insert.setString(4, exam.getTeacherName());
		insert.setInt(5, exam.getExamDuration());
		insert.setString(6, exam.getFreeTextForExaminees());
		insert.setString(7, exam.getFreeTextForTeacherOnly());
		insert.executeUpdate();
		closeResultSetAndStatement(null, null, insert);
		insertQuestionInExam(exam, examNumber, connection); // insert all the questions to the QuestionInExam table
	}

	/**
	 * Inserts a new record to ActiveExam table in database
	 * 
	 * @param activeExam
	 *            Active exam.
	 * @param connection
	 *            A connection (session) with a specific database.
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors.
	 */
	public static void insertActiveExam(ActiveExam activeExam, Connection connection) throws SQLException {
		PreparedStatement insert = connection.prepareStatement(SqlUtilities.INSERT_ActiveExam);
		insert.setString(1, activeExam.getExam().getSubjectID());
		insert.setString(2, activeExam.getExam().getCourseID());
		insert.setString(3, activeExam.getExam().getExamNum());
		insert.setString(4, activeExam.getExecutionCode());
		insert.setString(5, activeExam.getActivator());
		insert.setInt(6, activeExam.getExam().getExamDuration());
		insert.setInt(7, activeExam.getLocked());
		if (activeExam.getType().equals("Computerized"))
			insert.setString(8, "c");
		else
			insert.setString(8, "m");
		insert.setString(9, activeExam.getActivatorsID());
		insert.executeUpdate();
		insert.close();
	}

	/**
	 * Locks specific active exam by updating it's locked to 1.
	 * 
	 * @param activeExam
	 *            Active exam.
	 * @param connection
	 *            A connection (session) with a specific database.
	 * @return Exam's execution code.
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors.
	 */
	public static String lockActiveExam(ActiveExam activeExam, Connection connection) throws SQLException {
		PreparedStatement insert = connection.prepareStatement(SqlUtilities.LOCK_Exam);
		insert.setString(1, activeExam.getExam().getSubjectID());
		insert.setString(2, activeExam.getExam().getCourseID());
		insert.setString(3, activeExam.getExam().getExamNum());
		insert.setString(4, activeExam.getExecutionCode());
		insert.executeUpdate();
		SqlUtilities.closeResultSetAndStatement(null, null, insert);
		return activeExam.getExecutionCode();
	}

	/**
	 * Inserts a new record to WaitingActiveExam table in database.
	 * 
	 * @param waitingActiveExam
	 *            Waiting active exam.
	 * @param connection
	 *            A connection (session) with a specific database.
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors.
	 */
	public static void insertWaitingActiveExam(WaitingActiveExam waitingActiveExam, Connection connection)
			throws SQLException {
		PreparedStatement insert = connection.prepareStatement(SqlUtilities.INSERT_WaitingActiveExam);
		insert.setString(1, waitingActiveExam.getActiveExam().getExam().getSubjectID());
		insert.setString(2, waitingActiveExam.getActiveExam().getExam().getCourseID());
		insert.setString(3, waitingActiveExam.getActiveExam().getExam().getExamNum());
		insert.setString(4, waitingActiveExam.getActiveExam().getExecutionCode());
		insert.setInt(5, waitingActiveExam.getActiveExam().getDuration());
		insert.setInt(6, waitingActiveExam.getNewDuration());
		insert.setString(7, waitingActiveExam.getReason());
		insert.executeUpdate();
		insert.close();
	}

	/**
	 * Changes the current time of an active exam.
	 * 
	 * @param waitingActiveExam
	 *            Waiting active exam.
	 * @param connection
	 *            A connection (session) with a specific database.
	 * @return The new time of the exam and the execution code of the exam as one
	 *         string
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors.
	 */
	public static String changeTimeActiveExam(WaitingActiveExam waitingActiveExam, Connection connection)
			throws SQLException {
		PreparedStatement insert = connection.prepareStatement(SqlUtilities.CHANGE_ActiveExamDuration);
		insert.setInt(1, waitingActiveExam.getNewDuration());
		insert.setString(2, waitingActiveExam.getActiveExam().getExam().getSubjectID());
		insert.setString(3, waitingActiveExam.getActiveExam().getExam().getCourseID());
		insert.setString(4, waitingActiveExam.getActiveExam().getExam().getExamNum());
		insert.setString(5, waitingActiveExam.getActiveExam().getExecutionCode());
		insert.executeUpdate();
		insert.close();
		return waitingActiveExam.getActiveExam().getExecutionCode() + " " + waitingActiveExam.getNewDuration();
	}

	/**
	 * Removes WaitingActiveExam because principal approved it's new time
	 * 
	 * @param waitingActiveExam
	 *            Waiting active exam.
	 * @param connection
	 *            A connection (session) with a specific database.
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors.
	 */
	public static void removeWaitingActiveExam(WaitingActiveExam waitingActiveExam, Connection connection)
			throws SQLException {
		PreparedStatement remove = connection.prepareStatement(SqlUtilities.REMOVE_WaitingActiveExam);
		remove.setString(1, waitingActiveExam.getActiveExam().getExam().getSubjectID());
		remove.setString(2, waitingActiveExam.getActiveExam().getExam().getCourseID());
		remove.setString(3, waitingActiveExam.getActiveExam().getExam().getExamNum());
		remove.setString(4, waitingActiveExam.getActiveExam().getExecutionCode());
		remove.executeUpdate();
		closeResultSetAndStatement(null, null, remove);
	}

	/**
	 * removes questions from DB
	 * 
	 * @param questions
	 *            List of questions.
	 * @param connection
	 *            A connection (session) with a specific database.
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors.
	 */
	public static void removeQuestions(ArrayList<Question> questions, Connection connection) throws SQLException {
		PreparedStatement remove = connection.prepareStatement(SqlUtilities.REMOVE_Questions);
		for (Question question : questions) {
			remove.setString(1, question.getSubjectID());
			remove.setString(2, question.getQuestionNum());
			remove.executeUpdate();
		}
		closeResultSetAndStatement(null, null, remove);
	}

	/**
	 * Inserts a new record to ApprovedExamForStudent table in database and keep the
	 * specific comments.
	 * 
	 * @param checkedExam
	 *            The specific exam.
	 * @param connection
	 *            A connection (session) with a specific database.
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors.
	 */
	public static void approveCheckedExam(CheckedExam checkedExam, Connection connection) throws SQLException {
		PreparedStatement insert = connection.prepareStatement(SqlUtilities.INSERT_ApprovedExamForStudent);
		PreparedStatement getComments = connection.prepareStatement(SqlUtilities.SELECT_Comments_from_Checked_Exams);
		getComments.setString(1, checkedExam.getExecutionCode());
		getComments.setString(2, checkedExam.getSubmittedExam().getStudentInActiveExam().getStudent().getId());
		ResultSet rs = getComments.executeQuery();
		rs.next();
		String comments = rs.getString(1) + " " + rs.getString(2);
		closeResultSetAndStatement(rs, null, getComments);
		insert.setString(1,
				checkedExam.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().getSubjectID());
		insert.setString(2,
				checkedExam.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().getCourseID());
		insert.setString(3,
				checkedExam.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().getExamNum());
		insert.setString(4, checkedExam.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExecutionCode());
		insert.setString(5, checkedExam.getSubmittedExam().getStudentInActiveExam().getStudent().getId());
		insert.setInt(6, checkedExam.getGrade());
		insert.setString(7, comments);
		insert.setString(8, checkedExam.getIdApprover());
		insert.executeUpdate();
		insert.close();
	}

	/**
	 * Deletes checked exam.
	 * 
	 * @param checkedExam
	 *            The specific exam.
	 * @param connection
	 *            A connection (session) with a specific database.
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors.
	 */
	public static void removeCheckedExam(CheckedExam checkedExam, Connection connection) throws SQLException {
		PreparedStatement remove = connection.prepareStatement(SqlUtilities.REMOVE_checkedExam);
		remove.setString(1,
				checkedExam.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().getSubjectID());
		remove.setString(2,
				checkedExam.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().getCourseID());
		remove.setString(3,
				checkedExam.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().getExamNum());
		remove.setString(4, checkedExam.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExecutionCode());
		remove.setString(5, checkedExam.getSubmittedExam().getStudentInActiveExam().getStudent().getId());
		remove.executeUpdate();
		closeResultSetAndStatement(null, null, remove);
	}

	/**
	 * Changes the grade by the teacher that reviews student's exam.
	 * 
	 * @param checkedExam
	 *            The specific exam.
	 * @param connection
	 *            A connection (session) with a specific database.
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors.
	 */
	public static void changeGradeByTeacher(CheckedExam checkedExam, Connection connection) throws SQLException {
		PreparedStatement update = connection.prepareStatement(SqlUtilities.CHANGE_GradeByTeacher);
		update.setInt(1, checkedExam.getGrade());
		update.setString(2, checkedExam.getCommentsOfChangeGrade());
		update.setString(3,
				checkedExam.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().getSubjectID());
		update.setString(4,
				checkedExam.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().getCourseID());
		update.setString(5,
				checkedExam.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().getExamNum());
		update.setString(6, checkedExam.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExecutionCode());
		update.setString(7, checkedExam.getSubmittedExam().getStudentInActiveExam().getStudent().getId());
		update.executeUpdate();
		update.close();
	}

	/**
	 * Add additional comments after reviewing the exam.
	 * 
	 * @param checkedExam
	 *            The specific exam.
	 * @param connection
	 *            A connection (session) with a specific database.
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors.
	 */
	public static void addCommentsByTeacher(CheckedExam checkedExam, Connection connection) throws SQLException {
		PreparedStatement update = connection.prepareStatement(SqlUtilities.ADD_CommentsInCheckedExam);
		update.setString(1, checkedExam.getGeneralComments());
		update.setString(2,
				checkedExam.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().getSubjectID());
		update.setString(3,
				checkedExam.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().getCourseID());
		update.setString(4,
				checkedExam.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().getExamNum());
		update.setString(5, checkedExam.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExecutionCode());
		update.setString(6, checkedExam.getSubmittedExam().getStudentInActiveExam().getStudent().getId());
		update.executeUpdate();
		update.close();
	}

	/**
	 * Returns a report of the specific exam.
	 * 
	 * @param examReportHandle
	 *            Exam's specific report
	 * @param connection
	 *            A connection (session) with a specific database.
	 * @return ReportAboutExam that contains average, median and distribution of the
	 *         specific exam
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors.
	 */
	public static ReportAboutExam calculateExamStatistic(ExamReportHandle examReportHandle, Connection connection)
			throws SQLException {
		/* AVG */
		PreparedStatement calculate1 = connection.prepareStatement(SqlUtilities.CALCULATE_ExamAVG);
		calculate1.setString(1, getSubjectID(examReportHandle.getSubject(), connection));
		calculate1.setString(2, getCourseID(examReportHandle.getCourse(), connection));
		calculate1.setString(3, examReportHandle.getExamNum());
		ResultSet rs1 = calculate1.executeQuery();
		rs1.next();
		/* MEDIAN */
		int med;
		PreparedStatement calculate2 = connection.prepareStatement(SqlUtilities.ALL_Grades_of_Exam);
		ArrayList<Integer> grades = new ArrayList<Integer>();
		calculate2.setString(1, getSubjectID(examReportHandle.getSubject(), connection));
		calculate2.setString(2, getCourseID(examReportHandle.getCourse(), connection));
		calculate2.setString(3, examReportHandle.getExamNum());
		ResultSet rs2 = calculate2.executeQuery();
		while (rs2.next()) {
			grades.add(rs2.getInt(1));
		}
		Collections.sort(grades);
		int mid = grades.size() / 2;
		if (!grades.isEmpty()) {
			med = grades.get(mid);
		} else
			med = 0;
		/* Total students who started exam */
		PreparedStatement calculate3 = connection.prepareStatement(SqlUtilities.COUNT_StudentWhoStarted);
		calculate3.setString(1, getSubjectID(examReportHandle.getSubject(), connection));
		calculate3.setString(2, getCourseID(examReportHandle.getCourse(), connection));
		calculate3.setString(3, examReportHandle.getExamNum());
		ResultSet rs3 = calculate3.executeQuery();
		rs3.next();
		/* Total students who finished exam */
		PreparedStatement calculate4 = connection.prepareStatement(SqlUtilities.COUNT_StudentWhoFinished);
		calculate4.setString(1, getSubjectID(examReportHandle.getSubject(), connection));
		calculate4.setString(2, getCourseID(examReportHandle.getCourse(), connection));
		calculate4.setString(3, examReportHandle.getExamNum());
		ResultSet rs4 = calculate4.executeQuery();
		rs4.next();
		/* Total students who forced to finish exam */
		int forced = rs3.getInt(1) - rs4.getInt(1);
		return new ReportAboutExam(rs1.getDouble(1), med, rs3.getInt(1), rs4.getInt(1), forced, grades);
	}

	/**
	 * Returns the average of all the grades of specific exam.
	 * 
	 * @param subjectID
	 *            Exam's subject number
	 * @param courseID
	 *            Exam's course number
	 * @param examNum
	 *            Exam's number
	 * @param connection
	 *            A connection (session) with a specific database.
	 * @return the average
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors.
	 */
	public static int calculateExamAVG(String subjectID, String courseID, String examNum, Connection connection)
			throws SQLException {
		/* AVG */
		PreparedStatement calculate1 = connection.prepareStatement(SqlUtilities.CALCULATE_ExamAVG);
		calculate1.setString(1, subjectID);
		calculate1.setString(2, courseID);
		calculate1.setString(3, examNum);
		ResultSet rs1 = calculate1.executeQuery();
		rs1.next();
		return (int) rs1.getDouble(1);
	}

	/**
	 * Calculates average, median and distribution of specific student.
	 * 
	 * @param reportHandle
	 *            The specific report
	 * @param connection
	 *            A connection (session) with a specific database.
	 * @return ReportAboutStudent that contains average, median and distribution of
	 *         specific student
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors.
	 */
	public static ReportAboutStudent calculateStudentStatistic(ReportHandle reportHandle, Connection connection)
			throws SQLException {
		PreparedStatement calculate1 = connection.prepareStatement(SqlUtilities.CALCULATE_StudentAVG);
		calculate1.setString(1, reportHandle.getStudent().getId());
		ResultSet rs1 = calculate1.executeQuery();
		rs1.next();
		int med;
		PreparedStatement calculate2 = connection.prepareStatement(SqlUtilities.ALL_Grades_of_Student);
		HashMap<String, Integer> gradesWithExam = new HashMap<String, Integer>();
		ArrayList<Integer> grades = new ArrayList<Integer>();
		calculate2.setString(1, reportHandle.getStudent().getId());
		ResultSet rs2 = calculate2.executeQuery();
		while (rs2.next()) {
			String str = rs2.getString(2) + rs2.getString(3) + rs2.getString(4);
			Integer grade = rs2.getInt(1);
			grades.add(grade);
			gradesWithExam.put(str, grade);
		}
		Collections.sort(grades);
		int mid = grades.size() / 2;
		if (!grades.isEmpty()) {
			med = grades.get(mid);
		} else
			med = 0;
		return new ReportAboutStudent("StudentStatistic", rs1.getDouble(1), med, reportHandle.getStudent(),
				gradesWithExam);
	}

	/**
	 * Calculates average, median and distribution of specific course.
	 * 
	 * @param reportHandle
	 *            The specific report
	 * @param connection
	 *            A connection (session) with a specific database.
	 * @return ReportAboutCourse that contains average, median and distribution of
	 *         specific course
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors.
	 */
	public static ReportAboutCourse calculateCourseStatistic(ReportHandle reportHandle, Connection connection)
			throws SQLException {
		double sum = 0;
		int count = 0;
		int med = 0;
		double realAVG = 0;
		PreparedStatement calculate3 = connection.prepareStatement(SqlUtilities.SELECT_Exam_BY_CourseID);
		ArrayList<Integer> avgs = new ArrayList<Integer>();
		calculate3.setString(1, reportHandle.getCourse().getCourseID());
		ResultSet rs3 = calculate3.executeQuery();
		while (rs3.next()) {
			Integer avg = calculateExamAVG(rs3.getString(1), rs3.getString(2), rs3.getString(3), connection);
			avgs.add(avg);
			sum += avg;
			count++;
		}
		if (avgs.size() != 0) {
			count = count / 2;
			Collections.sort(avgs);
			med = avgs.get(count);
			realAVG = sum / avgs.size();
		}
		return new ReportAboutCourse("CourseStatistic", realAVG, med, reportHandle.getCourse(), avgs);
	}

	/**
	 * Calculates average, median and distribution of specific teacher.
	 * 
	 * @param reportHandle
	 *            The specific report
	 * @param connection
	 *            A connection (session) with a specific database.
	 * @return ReportAboutTeacher that contains average, median and distribution of
	 *         specific teacher
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors.
	 */
	public static ReportAboutTeacher calculateTeacherStatistic(ReportHandle reportHandle, Connection connection)
			throws SQLException {
		double sum = 0;
		int count = 0;
		int med = 0;
		double realAVG = 0;
		PreparedStatement calculate3 = connection.prepareStatement(SqlUtilities.SELECT_Exams_By_Author);
		ArrayList<Integer> avgs = new ArrayList<Integer>();
		String fullName = reportHandle.getTeacher().getFirstName() + " " + reportHandle.getTeacher().getLastName();
		calculate3.setString(1, fullName);
		ResultSet rs3 = calculate3.executeQuery();
		while (rs3.next()) {
			Integer avg = calculateExamAVG(rs3.getString(1), rs3.getString(2), rs3.getString(3), connection);
			avgs.add(avg);
			sum += avg;
			count++;
		}
		if (avgs.size() != 0) {
			count = count / 2;
			Collections.sort(avgs);
			med = avgs.get(count);
			realAVG = sum / avgs.size();
		}
		return new ReportAboutTeacher("TeacherStatistic", realAVG, med, reportHandle.getTeacher(), avgs);
	}

	/**
	 * Returns the Subjects\Courses filtered by subject
	 * 
	 * @param query
	 *            Statement
	 * @param insertIntoQuery
	 *            Statement
	 * @param type
	 *            Subject or Course
	 * @param connection
	 *            A connection (session) with a specific database.
	 * @return the list of subjects filtered by user
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors.
	 */
	public static TypeHandle getTypeFromDB(String query, String insertIntoQuery, String type, Connection connection)
			throws SQLException {
		PreparedStatement typeOfSet = connection.prepareStatement(query);
		if (insertIntoQuery != null) { // if the method need to return the courses\subjects by user\exam numbers
			String[] attributes = insertIntoQuery.split(" ");
			if (attributes.length > 1) { // exam numbers
				typeOfSet.setString(1, getCourseID(attributes[0], connection));
				typeOfSet.setString(2, attributes[1] + " " + attributes[2]);
			} else {
				if (insertIntoQuery.matches("[0-9]*")) // subjects by user
					typeOfSet.setString(1, insertIntoQuery);
				else // courses
					typeOfSet.setString(1, getSubjectID(insertIntoQuery, connection));
			}
		}
		ArrayList<String> typeOfSetFromDB = new ArrayList<>();
		ResultSet rs = typeOfSet.executeQuery();
		if (type.equals("Students"))
			while (rs.next())
				typeOfSetFromDB.add(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
		else
			while (rs.next())
				typeOfSetFromDB.add(rs.getString(1));
		closeResultSetAndStatement(rs, null, typeOfSet);
		return new TypeHandle(type, typeOfSetFromDB);
	}

	/**
	 * Checks if the specific execution code is exist.
	 * 
	 * @param code
	 *            Specific exam execution code.
	 * @param connection
	 *            A connection (session) with a specific database.
	 * @return True if the execution code exists in the data base
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors.
	 */
	public static Boolean checkCode(ExecutionCodeHandle code, Connection connection) throws SQLException {
		PreparedStatement check = connection.prepareStatement(SqlUtilities.CHECK_ExecutionCodeExist);
		check.setString(1, code.getCode());
		ResultSet rs = check.executeQuery();
		return rs.next();
	}

	/**
	 * Returns list of exams by the teacher that activates them.
	 * 
	 * @param activatorsID
	 *            Teacher's id
	 * @param course
	 *            Specific course
	 * @param connection
	 *            A connection (session) with a specific database.
	 * @return Active Exam Handle that contains the list of exams
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors.
	 */
	public static ActiveExamHandle getActiveExamsByActivatorsID(String activatorsID, String course,
			Connection connection) throws SQLException {
		String courseNumber = getCourseID(course, connection);
		PreparedStatement statement = connection
				.prepareStatement(SqlUtilities.SELECT_Unlocked_Activated_Exams_By_ActivatorsID);
		statement.setString(1, activatorsID);
		statement.setString(2, courseNumber);
		ArrayList<ActiveExam> activeExams = new ArrayList<>();
		ResultSet rs = statement.executeQuery();
		while (rs.next())
			activeExams.add(new ActiveExam(new Exam(rs.getString(1), rs.getString(2), rs.getString(3)), rs.getInt(5),
					rs.getString(4), rs.getString(6).equals("c") ? "Computerized" : "Manual"));
		closeResultSetAndStatement(rs, null, statement);
		return new ActiveExamHandle("All", activeExams);
	}

	/**
	 * Returns list of exams by the subject.
	 * 
	 * @param courseID
	 *            Exam's course number.
	 * @param connection
	 *            A connection (session) with a specific database.
	 * @return The exam
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors.
	 */
	public static ActiveExamHandle getActiveExamsBySubject(String courseID, Connection connection) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(SqlUtilities.SELECT_ActiveExamsByCourse);
		statement.setString(1, getCourseID(courseID, connection));
		ArrayList<ActiveExam> activeExams = new ArrayList<>();
		ResultSet rs = statement.executeQuery();
		while (rs.next())
			activeExams.add(new ActiveExam(new Exam(rs.getString(1), rs.getString(2), rs.getString(3)), rs.getString(4),
					rs.getString(5), rs.getInt(6), rs.getInt(7),
					rs.getString(8).equals("c") ? "Computerized" : "Manual"));
		closeResultSetAndStatement(rs, null, statement);
		return new ActiveExamHandle("ActiveExamsBySubject", activeExams);
	}

	/**
	 * Returns the correct answer of specific question.
	 * 
	 * @param subjectID
	 *            The specific question's subject number.
	 * @param questionNum
	 *            The specific question number.
	 * @param connection
	 *            A connection (session) with a specific database.
	 * @return String that represent the answer of the specified Question
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors.
	 */
	public static String getCorrectAnswer(String subjectID, String questionNum, Connection connection)
			throws SQLException {
		PreparedStatement checkAnswer = connection.prepareStatement(SqlUtilities.Check_Question_Answer);
		checkAnswer.setString(1, subjectID);
		checkAnswer.setString(2, questionNum);
		ResultSet rs = checkAnswer.executeQuery();
		if (rs.next())
			return rs.getString(1);
		else {
			System.out.println("problem in correct answer");
			return null;
		}

	}

	/**
	 * Returns the teacher who activated the exam by execution code.
	 * 
	 * @param subjectID
	 *            The specific exam subject number.
	 * @param courseID
	 *            The specific exam course number.
	 * @param examNumber
	 *            The specific exam number.
	 * @param connection
	 *            A connection (session) with a specific database.
	 * @return Name that represent the activator
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors.
	 */
	public static String getActivatorsID(String subjectID, String courseID, String examNumber, Connection connection)
			throws SQLException {
		PreparedStatement statement = connection.prepareStatement(SqlUtilities.getActivatorsID);
		statement.setString(1, subjectID);
		statement.setString(2, courseID);
		statement.setString(3, examNumber);
		ResultSet rs = statement.executeQuery();
		rs.next();
		String activatorsID = rs.getString(1);
		closeResultSetAndStatement(rs, null, statement);
		return activatorsID;
	}

	/**
	 * Returns the points of specific question.
	 * 
	 * @param subjectID
	 *            The specific exam subject number.
	 * @param questionNum
	 *            The specific question in the exam.
	 * @param courseID
	 *            The specific exam course number.
	 * @param examNum
	 *            The specific exam number.
	 * @param connection
	 *            A connection (session) with a specific database.
	 * @return The points of at the specified Question
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors.
	 */
	public static int getPointsOfQuestion(String subjectID, String questionNum, String courseID, String examNum,
			Connection connection) throws SQLException {
		PreparedStatement getPoints = connection.prepareStatement(SqlUtilities.Get_Points_of_Question);
		getPoints.setString(1, subjectID);
		getPoints.setString(2, questionNum);
		getPoints.setString(3, courseID);
		getPoints.setString(4, examNum);
		ResultSet rs = getPoints.executeQuery();
		if (rs.next())
			return rs.getInt(1);
		else {
			System.out.println("problem in points");
			return 0;
		}
	}

	/**
	 * Inserts a new record to CheckedExam table in database, so first calculate
	 * 
	 * @param submittedExam
	 *            The specific submitted exam
	 * @param connection
	 *            A connection (session) with a specific database.
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors.
	 */
	public static void insertCheckedExam(SubmittedExam submittedExam, Connection connection) throws SQLException {
		PreparedStatement insert = connection.prepareStatement(SqlUtilities.INSERT_CheckedExam);
		insert.setString(1, submittedExam.getStudentInActiveExam().getActiveExam().getExam().getSubjectID());
		insert.setString(2, submittedExam.getStudentInActiveExam().getActiveExam().getExam().getCourseID());
		insert.setString(3, submittedExam.getStudentInActiveExam().getActiveExam().getExam().getExamNum());
		insert.setString(4, submittedExam.getStudentInActiveExam().getActiveExam().getExecutionCode());
		insert.setString(5, submittedExam.getStudentInActiveExam().getStudent().getId());
		if (submittedExam.getStudentInActiveExam().getActiveExam().getType().equals("c")) {
			insert.setInt(6, Utilities_Server.getCalculateExamGrade(submittedExam, connection));
		} else {
			insert.setInt(6, -1);
		}
		insert.setString(7, "");
		insert.setString(8, "");
		insert.executeUpdate();

		closeResultSetAndStatement(null, null, insert);
	}

	/**
	 * Inserts a new record to SubmittedExam table in database
	 * 
	 * @param submittedExam
	 *            The specific submitted exam
	 * @param connection
	 *            A connection (session) with a specific database.
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors.
	 */
	public static void insertSubmittedExam(SubmittedExam submittedExam, Connection connection) throws SQLException {
		PreparedStatement insert = connection.prepareStatement(SqlUtilities.INSERT_SubmittedExam);
		insert.setString(1, submittedExam.getStudentInActiveExam().getActiveExam().getExam().getSubjectID());
		insert.setString(2, submittedExam.getStudentInActiveExam().getActiveExam().getExam().getCourseID());
		insert.setString(3, submittedExam.getStudentInActiveExam().getActiveExam().getExam().getExamNum());
		insert.setString(4, submittedExam.getStudentInActiveExam().getActiveExam().getExecutionCode());
		insert.setString(5, submittedExam.getStudentInActiveExam().getStudent().getId());
		insert.setInt(6, submittedExam.getTimeToSolve());
		insert.setInt(7, submittedExam.getSubmitted());
		insert.executeUpdate();
		closeResultSetAndStatement(null, null, insert);
	}

	/**
	 * Deletes the selected exam from the data base
	 * 
	 * @param exam
	 *            The specific exam
	 * @param connection
	 *            A connection (session) with a specific database
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors.
	 */
	public static void deleteExam(Exam exam, Connection connection) throws SQLException {
		PreparedStatement delete = connection.prepareStatement(DELETE_Exam_from_Exam_Table);
		delete.setString(1, exam.getSubjectID());
		delete.setString(2, exam.getCourseID());
		delete.setString(3, exam.getExamNum());
		delete.executeUpdate();
		delete = connection.prepareStatement(DELETE_Exam_from_Active_Exam_Table);
		delete.setString(1, exam.getSubjectID());
		delete.setString(2, exam.getCourseID());
		delete.setString(3, exam.getExamNum());
		delete.executeUpdate();
		closeResultSetAndStatement(null, null, delete);
	}

	/**
	 * Returns a HashMap a of students that had copied in the specified exam.
	 * 
	 * @param examH
	 *            An active exam
	 * @param connection
	 *            A connection (session) with a specific database
	 * @return StudentHandle that contains a HashMap of copied students
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors
	 */
	@SuppressWarnings("deprecation")
	public static StudentHandle findExamHaveExamineesThatCopy(ActiveExamHandle examH, Connection connection)
			throws SQLException {
		HashMap<Student, ArrayList<Student>> copied = new HashMap<>();
		HashMap<String, ArrayList<String>> idOfCopied = new HashMap<String, ArrayList<String>>();
		HashMap<String, Vector<Integer>> exams = new HashMap<String, Vector<Integer>>();
		PreparedStatement examQuestionsByStudent = connection
				.prepareStatement(GetStudentAnswerInQuestionByExecutionCode);
		examQuestionsByStudent.setString(1, examH.getActiveExam().getExecutionCode());
		ResultSet rs1 = examQuestionsByStudent.executeQuery();
		/**
		 * while loop that puts by associating the specified studentID with the
		 * specified answers in this map.
		 */
		while (rs1.next()) {
			if (!exams.containsKey(rs1.getString(1))) {
				exams.put(rs1.getString(1), new Vector<>());
				if (!SqlUtilities.getCorrectAnswer(rs1.getString(2), rs1.getString(6), connection)
						.equals(Integer.toString(rs1.getInt(8))))
					exams.get(rs1.getString(1)).add(new Integer(rs1.getInt(8)));
				else
					exams.get(rs1.getString(1)).add(new Integer(0));
			} else {
				if (!SqlUtilities.getCorrectAnswer(rs1.getString(2), rs1.getString(6), connection)
						.equals(Integer.toString(rs1.getInt(8))))
					exams.get(rs1.getString(1)).add(new Integer(rs1.getInt(8)));
				else
					exams.get(rs1.getString(1)).add(new Integer(0));
			}
		}
		/**
		 * for loop that checks for every student if he had copied from another student
		 * by reviewing their common mistakes.
		 */
		for (String studentIDi : exams.keySet()) {
			for (String studentIDj : exams.keySet()) {
				if (!studentIDi.equals(studentIDj) && !idOfCopied.containsKey(studentIDj)) {
					int count = 0;
					for (int i = 0; i < exams.get(studentIDi).size(); i++) {
						if (exams.get(studentIDi).elementAt(i).compareTo(new Integer(0)) != 0
								&& exams.get(studentIDj).elementAt(i).compareTo(new Integer(0)) != 0
								&& exams.get(studentIDi).elementAt(i)
										.compareTo(exams.get(studentIDj).elementAt(i)) == 0) {
							count++;
						}
					}
					if (count == 3) {
						if (!idOfCopied.containsKey(studentIDi)) {
							idOfCopied.put(studentIDi, new ArrayList<>());
							idOfCopied.get(studentIDi).add(studentIDj);

						} else {
							idOfCopied.get(studentIDi).add(studentIDj);
						}
						count = 0;
					}
				}
			}
		}
		/**
		 * for loop that puts by associating the specified Student with the specified
		 * Students that copied in this map.
		 */
		for (String studentID : idOfCopied.keySet()) {
			PreparedStatement userNameAndLastNameOfCopier = connection.prepareStatement(GetTypeAndUserNameAndLastName);
			userNameAndLastNameOfCopier.setString(1, studentID);
			ResultSet rsCopier = userNameAndLastNameOfCopier.executeQuery();
			if (rsCopier.next()) {
				ArrayList<Student> list = new ArrayList<>();
				for (String studentThatCopiedID : idOfCopied.get(studentID)) {
					PreparedStatement userNameAndLastNameOfCopiers = connection
							.prepareStatement(GetTypeAndUserNameAndLastName);
					userNameAndLastNameOfCopiers.setString(1, studentThatCopiedID);
					ResultSet rsCopiers = userNameAndLastNameOfCopiers.executeQuery();
					if (rsCopiers.next()) {
						list.add(new Student(studentThatCopiedID, rsCopiers.getString(2), rsCopiers.getString(3)));
					}
				}
				copied.put(new Student(studentID, rsCopier.getString(2), rsCopier.getString(3)), list);
			}
		}
		return new StudentHandle("Copiers", copied);
	}

	/**
	 * Closes the objects that are a table of data representing our database.
	 * 
	 * @param resultSet
	 *            Object maintains a cursor pointing to its current row of data
	 * @param statement
	 *            Object used for executing a static SQL statement and returning the
	 *            results it produces.
	 * @param preparedStatement
	 *            Object that represents a precompiled SQL statement.
	 */
	private static void closeResultSetAndStatement(ResultSet resultSet, Statement statement,
			PreparedStatement preparedStatement) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get subject as name and return as id
	 * 
	 * @param subject
	 *            Subject Name
	 * @param connection
	 *            A connection (session) with a specific database
	 * @return The subject ID
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors
	 */
	private static String getSubjectID(String subject, Connection connection) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(SqlUtilities.SELECT_subjectID_FROM_Subject);
		statement.setString(1, subject);
		ResultSet rs = statement.executeQuery();
		rs.next();
		String subjectID = rs.getString(1);
		closeResultSetAndStatement(rs, null, statement);
		return subjectID;
	}

	/**
	 * Get course as name and return as id
	 * 
	 * @param course
	 *            Course Name
	 * @param connection
	 *            A connection (session) with a specific database
	 * @return The Course ID
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors
	 */
	private static String getCourseID(String course, Connection connection) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(SqlUtilities.SELECT_courseID_FROM_Course);
		statement.setString(1, course);
		ResultSet rs = statement.executeQuery();
		rs.next();
		String courseID = rs.getString(1);
		closeResultSetAndStatement(rs, null, statement);
		return courseID;
	}

	/**
	 * Adds the questions into the QuestionInExam table
	 * 
	 * @param exam
	 *            The specific exam
	 * @param examNumber
	 *            The id number of the exam
	 * @param connection
	 *            A connection (session) with a specific database
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors
	 */
	private static void insertQuestionInExam(Exam exam, String examNumber, Connection connection) throws SQLException {
		PreparedStatement insert = connection.prepareStatement(SqlUtilities.INSERET_QUESTION_IN_EXAM);
		for (QuestionInExam questionInExam : exam.getQuestions()) {
			insert.setString(1, getSubjectID(exam.getSubjectID(), connection));
			insert.setString(2, questionInExam.getQuestionNum());
			insert.setString(3, getCourseID(exam.getCourseID(), connection));
			insert.setString(4, examNumber);
			insert.setInt(5, questionInExam.getPoints());
			insert.executeUpdate();
		}
		closeResultSetAndStatement(null, null, insert);
	}

	/**
	 * returns the current amount of exams of a specific course and updates the new
	 * amount in the DB
	 * 
	 * @param courseID
	 *            The course of the exams
	 * @param connection
	 *            A connection (session) with a specific database
	 * @return The amount of exams
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors
	 */
	private static Integer getExamCount(String courseID, Connection connection) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(SqlUtilities.SELECT_FROM_Course);
		statement.setString(1, courseID);
		ResultSet rs = statement.executeQuery();
		rs.next();
		int examsCount = rs.getInt(1) + 1;
		statement = connection.prepareStatement(SqlUtilities.UPDATE_Course);
		statement.setInt(1, examsCount); // update the new count in the DB
		statement.setString(2, courseID);
		statement.executeUpdate();
		closeResultSetAndStatement(rs, null, statement);
		return examsCount;
	}

	/**
	 * Returns the current amount of questions of a specific subject and updates the
	 * new amount in the DB.
	 * 
	 * @param subjectID
	 *            The subject of the questions
	 * @param connection
	 *            A connection (session) with a specific database
	 * @return The amount of questions
	 * @throws SQLException
	 *             An exception that provides information on a database access error
	 *             or other errors
	 */
	private static Integer getQuestionCount(String subjectID, Connection connection) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(SqlUtilities.SELECT_FROM_Subject);
		statement.setString(1, subjectID);
		ResultSet rs = statement.executeQuery();
		rs.next();
		int questionsCount = rs.getInt(1) + 1;
		statement = connection.prepareStatement(SqlUtilities.UPDATE_Subject);
		statement.setInt(1, questionsCount); // update the new count in the DB
		statement.setString(2, subjectID);
		statement.executeUpdate();
		closeResultSetAndStatement(rs, null, statement);
		return questionsCount;
	}

} /* end of class */