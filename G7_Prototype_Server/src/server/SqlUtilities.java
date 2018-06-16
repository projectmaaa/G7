package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import resources.*;

public class SqlUtilities {

	// region Constants

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

	public final static String SELECT_Exam_BY_SubjectID = "SELECT * FROM Exam WHERE subjectID=?;";

	public final static String SELECT_Exam_BY_Subject_CourseID_ExamID = "SELECT * FROM Exam WHERE subjectID=? AND courseID=? AND examNum=?;";

	public final static String SELECT_ActiveExam = "SELECT * FROM ActiveExam WHERE executionCode=?;";

	public final static String INSERT_ActiveExam = "INSERT INTO ActiveExam VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

	public final static String SELECT_Subjects = "SELECT subjectName FROM Subject";

	public final static String SELECT_Courses_BY_SubjectID = "SELECT courseName FROM Course WHERE subjectID=?;";

	public final static String LOCK_Exam = "UPDATE ActiveExam SET locked=1 WHERE subjectID=? AND courseID=? AND examNum=? AND executionCode=?;";

	public final static String INSERT_WaitingActiveExam = "INSERT INTO WaitingActiveExam VALUES (?, ?, ?, ?, ?, ?, ?);";

	public final static String SELECT_All_WaitingActiveExam = "SELECT * FROM WaitingActiveExam";

	public final static String CHANGE_ActiveExamDuration = "UPDATE ActiveExam SET duration=? WHERE subjectID=? AND courseID=? AND examNum=? AND executionCode=?;";

	public final static String REMOVE_WaitingActiveExam = "DELETE FROM WaitingActiveExam WHERE subjectID=? AND courseID=? AND examNum=? AND executionCode=?;";

	public final static String INSERT_StudentAnswerInQuestion = "INSERT INTO StudentAnswerInQuestion VALUES(?, ?, ?, ?, ?, ?, ?, ?);";

	public final static String INSERT_StudentInActiveExam = "INSERT INTO StudentInActiveExam VALUES(?, ?, ?, ?, ?, ?, ?);";

	public final static String CHECK_ExecutionCodeExist = "SELECT * FROM ActiveExam WHERE executionCode=?;";

	public final static String SELECT_All_CheckedExams = "SELECT * FROM CheckedExam";

	public final static String INSERT_ApprovedExamForStudent = "INSERT INTO ApprovedExamForStudent VALUES (?, ?, ?, ?, ?, ?, ?);";

	public final static String REMOVE_checkedExam = "DELETE FROM CheckedExam WHERE subjectID=? AND courseID=? AND examNum=? AND executionCode=? AND studentID=?;";

	public final static String CHANGE_GradeByTeacher = "UPDATE CheckedExam SET grade=?, commentsOfChangeGrade=? WHERE subjectID=? AND courseID=? AND examNum=? AND executionCode=? AND studentID=?;";

	public final static String ADD_CommentsInCheckedExam = "UPDATE CheckedExam SET comment=? WHERE subjectID=? AND courseID=? AND examNum=? AND executionCode=? AND studentID=?;";

	public final static String SELECT_Unlocked_Activated_Exams_By_Activator = "SELECT subjectID, courseID, examNum, executionCode, duration, type FROM ActiveExam WHERE activator=? AND subjectID=? AND locked=0;";

	public final static String SELECT_All_Students = "SELECT idUsers, firstName, lastName FROM Users WHERE type='Student'";

	public final static String Check_Question_Answer = "select correctAnswer from Questions where subjectID = ? AND questionNum = ?;";

	public final static String Get_Points_of_Question = "select Points from QuestionInExam where subjectID = ? and questionNum = ? and courseID = ? and examNum = ?;";

	public final static String INSERT_CheckedExam = "INSERT INTO CheckedExam VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
	// region Public Methods

	// end region -> Constants

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
				activeExam.setType(type);
			}
		}
		return (new ActiveExamHandle("ActiveExam", activeExam));
	}

	/**
	 * 
	 * @param submittedExam
	 * @param connection
	 * @throws SQLException
	 */
	public static void Insert_StudentAnswerInQuestion(SubmittedExam submittedExam, Connection connection)
			throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_StudentAnswerInQuestion);
		for (StudentAnswerInQuestion studentAnswerInQuestion : submittedExam.getAnswers()) {
			preparedStatement.setString(1, studentAnswerInQuestion.getStudent().getId());
			preparedStatement.setString(2, studentAnswerInQuestion.getSubjectID());
			preparedStatement.setString(3,
					submittedExam.getStudentInActiveExam().getActiveExam().getExam().getCourseID());
			preparedStatement.setString(4,
					submittedExam.getStudentInActiveExam().getActiveExam().getExam().getExamNum());
			preparedStatement.setString(5, submittedExam.getStudentInActiveExam().getActiveExam().getExecutionCode());
			preparedStatement.setString(6, studentAnswerInQuestion.getQuestionNum());
			preparedStatement.setString(7, studentAnswerInQuestion.getQuestionOrderInExam());
			preparedStatement.setString(8, studentAnswerInQuestion.getStudentAnswer());
			preparedStatement.executeUpdate();
		}
		closeResultSetAndStatement(null, null, preparedStatement);
	}

	/**
	 * 
	 * @param studentInActiveExam
	 * @param connection
	 * @throws SQLException
	 */
	public static void Insert_StudentInActiveExam(StudentInActiveExam studentInActiveExam, Connection connection)
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
	 * returns the whole table of questions of the specific author (the user) for
	 * the table view when edit\remove is pressed
	 * 
	 * @param author
	 * @param connection
	 * @return
	 * @throws SQLException
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

	public static ExamHandle getExamsBySubject(Connection connection, String subject) throws SQLException {
		ArrayList<Exam> examsBySubject = new ArrayList<Exam>();
		PreparedStatement statement = connection.prepareStatement(SqlUtilities.SELECT_Exam_BY_SubjectID);
		try {
			statement.setString(1, getSubjectID(subject, connection));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			examsBySubject.add(new Exam(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getInt(5), rs.getString(6), rs.getString(7)));
		}
		closeResultSetAndStatement(rs, null, statement);
		return (new ExamHandle("Subject", examsBySubject));
	}

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

	public static CheckedExamHandle getCheckedExam(Connection connection) throws SQLException {
		ArrayList<CheckedExam> checkedExams = new ArrayList<CheckedExam>();
		PreparedStatement statement = connection.prepareStatement(SELECT_All_CheckedExams);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			Exam exam = new Exam(rs.getString(1), rs.getString(2), rs.getString(3));
			ActiveExam activeExam = new ActiveExam(exam, rs.getString(4));
			StudentInActiveExam studentInActiveExam = new StudentInActiveExam(
					new Student(rs.getString(5), "firstName", "lastName"), activeExam);
			SubmittedExam submittedExam = new SubmittedExam(studentInActiveExam);
			checkedExams.add(new CheckedExam(submittedExam, rs.getInt(6)));
		}
		closeResultSetAndStatement(rs, null, statement);
		return (new CheckedExamHandle("AllCheckedExams", checkedExams));
	}

	/**
	 * updates the questions table in the data base
	 * 
	 * @param newQuestions
	 * @param connection
	 * @throws SQLException
	 */
	public static void editTable(ArrayList<Question> newQuestions, Connection connection) throws SQLException {
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
	 * inserts new exam into the DB
	 * 
	 * @param exam
	 * @param connection
	 * @throws SQLException
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
		insert.executeUpdate();
		insert.close();
	}

	public static void lockActiveExam(ActiveExam activeExam, Connection connection) throws SQLException {
		PreparedStatement insert = connection.prepareStatement(SqlUtilities.LOCK_Exam);
		insert.setString(1, activeExam.getExam().getSubjectID());
		insert.setString(2, activeExam.getExam().getCourseID());
		insert.setString(3, activeExam.getExam().getExamNum());
		insert.setString(4, activeExam.getExecutionCode());
		insert.executeUpdate();
		insert.close();
	}

	public static void insertWaitingActiveExam(WaitingActiveExam waitingActiveExam, Connection connection)
			throws SQLException {
		PreparedStatement insert = connection.prepareStatement(SqlUtilities.INSERT_WaitingActiveExam);
		insert.setString(1, waitingActiveExam.getActiveExam().getExam().getSubjectID());
		insert.setString(2, waitingActiveExam.getActiveExam().getExam().getCourseID());
		insert.setString(3, waitingActiveExam.getActiveExam().getExam().getExamNum());
		insert.setString(4, waitingActiveExam.getActiveExam().getExecutionCode());
		insert.setInt(5, waitingActiveExam.getActiveExam().getExam().getExamDuration());
		insert.setInt(6, waitingActiveExam.getNewDuration());
		insert.setString(7, waitingActiveExam.getReason());
		insert.executeUpdate();
		insert.close();
	}

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
	 * @param connection
	 * @throws SQLException
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

	public static void approveCheckedExam(CheckedExam checkedExam, Connection connection) throws SQLException {
		PreparedStatement insert = connection.prepareStatement(SqlUtilities.INSERT_ApprovedExamForStudent);
		insert.setString(1,
				checkedExam.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().getSubjectID());
		insert.setString(2,
				checkedExam.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().getCourseID());
		insert.setString(3,
				checkedExam.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().getExamNum());
		insert.setString(4, checkedExam.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExecutionCode());
		insert.setString(5, checkedExam.getSubmittedExam().getStudentInActiveExam().getStudent().getId());
		insert.setInt(6, checkedExam.getGrade());
		insert.setString(7, checkedExam.getComments() + checkedExam.getCommentsOfChangeGrade());
		insert.executeUpdate();
		insert.close();
	}

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

	public static void addCommentsByTeacher(CheckedExam checkedExam, Connection connection) throws SQLException {
		PreparedStatement update = connection.prepareStatement(SqlUtilities.ADD_CommentsInCheckedExam);
		update.setString(1, checkedExam.getComments());
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
	 * Returns the Subjects\Courses filtered by subject
	 * 
	 * @param query
	 * @param insertIntoQuery
	 * @param type
	 * @param connection
	 * @return
	 * @throws SQLException
	 */
	public static TypeHandle getTypeFromDB(String query, String insertIntoQuery, String type, Connection connection)
			throws SQLException {
		PreparedStatement typeOfSet = connection.prepareStatement(query);
		if (insertIntoQuery != null) // if the method need to return the subjects
			typeOfSet.setString(1, getSubjectID(insertIntoQuery, connection));
		ArrayList<String> typeOfSetFromDB = new ArrayList<>();
		ResultSet rs = typeOfSet.executeQuery();
		while (rs.next())
			typeOfSetFromDB.add(rs.getString(1));
		closeResultSetAndStatement(rs, null, typeOfSet);
		return new TypeHandle(type, typeOfSetFromDB);
	}

	public static Boolean checkCode(ExecutionCodeHandle code, Connection connection) throws SQLException {
		PreparedStatement check = connection.prepareStatement(SqlUtilities.CHECK_ExecutionCodeExist);
		check.setString(1, code.getCode());
		ResultSet rs = check.executeQuery();
		return rs.next();
	}

	public static ActiveExamHandle getActiveExamsByActivator(String activator, String subject, Connection connection)
			throws SQLException {
		String subjectNumber = getSubjectID(subject, connection);
		PreparedStatement statement = connection
				.prepareStatement(SqlUtilities.SELECT_Unlocked_Activated_Exams_By_Activator);
		statement.setString(1, activator);
		statement.setString(2, subjectNumber);
		ArrayList<ActiveExam> activeExams = new ArrayList<>();
		ResultSet rs = statement.executeQuery();
		while (rs.next())
			activeExams.add(new ActiveExam(new Exam(rs.getString(1), rs.getString(2), rs.getString(3)), rs.getInt(5),
					rs.getString(4), rs.getString(6).equals("c") ? "Computerized" : "Manual"));
		closeResultSetAndStatement(rs, null, statement);
		return new ActiveExamHandle("All", activeExams);
	}

	/**
	 * 
	 * @param subjectID
	 * @param questionNum
	 * @param connection
	 * @return the answer at the specified Question
	 * @throws SQLException
	 */
	public static String getCorrectAnswer(String subjectID, String questionNum, Connection connection)
			throws SQLException {
		PreparedStatement checkAnswer = connection.prepareStatement(SqlUtilities.Check_Question_Answer);
		checkAnswer.setString(1, subjectID);
		checkAnswer.setString(2, questionNum);
		ResultSet rs = checkAnswer.executeQuery();
		if(rs.next())
			return rs.getString(1);
		else
		{
			System.out.println("problem in correct answer");
			return null;
		}
		
	}

	/**
	 * 
	 * @param subjectID
	 * @param questionNum
	 * @param courseID
	 * @param examNum
	 * @param connection
	 * @return the points of at the specified Question
	 * @throws SQLException
	 */
	public static int getPointsOfQuestion(String subjectID, String questionNum, String courseID, String examNum,
			Connection connection) throws SQLException {
		PreparedStatement getPoints = connection.prepareStatement(SqlUtilities.Get_Points_of_Question);
		getPoints.setString(1, subjectID);
		getPoints.setString(2, questionNum);
		getPoints.setString(3, courseID);
		getPoints.setString(4, examNum);
		ResultSet rs = getPoints.executeQuery();
		if(rs.next())
			return rs.getInt(1);
		else
		{
			System.out.println("problem in points");
			return 0;
		}
	}
/**
 * Inserts to a new record to CheckedExam table in database, so first calculate the result and then insert the values
 * @param submittedExam
 * @param connection
 * @throws SQLException
 */
	public static void insertCheckedExam(SubmittedExam submittedExam, Connection connection) throws SQLException {
		
		PreparedStatement insert = connection.prepareStatement(SqlUtilities.INSERT_CheckedExam);
		
			insert.setString(1, submittedExam.getStudentInActiveExam().getActiveExam().getExam().getSubjectID());
			insert.setString(2, submittedExam.getStudentInActiveExam().getActiveExam().getExam().getCourseID());
			insert.setString(3, submittedExam.getStudentInActiveExam().getActiveExam().getExam().getExamNum());
			insert.setString(4, submittedExam.getStudentInActiveExam().getActiveExam().getExecutionCode());
			insert.setString(5, submittedExam.getStudentInActiveExam().getStudent().getId());
			insert.setInt(6, Utilities_Server.getCalculateExamGrade(submittedExam, connection));
			insert.setString(7, "");
			insert.setString(8, "");
			insert.executeUpdate();
		
		closeResultSetAndStatement(null, null, insert);
	}

	// end region -> Public Methods

	/**
	 * 
	 * @param resultSet
	 * @param statement
	 * @param preparedStatement
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
	 * get subject as name and return as id
	 * 
	 * @param subject
	 * @return
	 * @throws SQLException
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
	 * get course as name and return as id
	 * 
	 * @param course
	 * @return
	 * @throws SQLException
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
	 * adds the questions into the QuestionInExam table
	 * 
	 * @param exam
	 * @param examNumber
	 * @param connection
	 * @throws SQLException
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
	 * returns the current amount of exams of a specific course & updates the new
	 * amount in the DB
	 * 
	 * @param courseID
	 * @param connection
	 * @return
	 * @throws SQLException
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
	 * returns the current amount of questions of a specific subject & updates the
	 * new amount in the DB
	 * 
	 * @param subjectID
	 * @param connection
	 * @return
	 * @throws SQLException
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