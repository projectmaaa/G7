package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import resources.Exam;
import resources.ExamHandle;
import resources.Question;
import resources.QuestionInExam;
import resources.QuestionsHandle;

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

	public final static String SELECT_FROM_Subject = "SELECT questionsCount FROM Subject WHERE subjectID=?;";

	public final static String UPDATE_Subject = "UPDATE Subject SET questionsCount=? WHERE subjectID=?;";

	public final static String GetTypeAndUserNameAndLastName = "SELECT type, firstName, lastName FROM Users WHERE idUsers=?;";

	public final static String GetQuestionBySubject = "SELECT * FROM Questions WHERE subjectID=?;";

	public final static String SELECT_FROM_Course = "SELECT examsCount FROM Course Where courseID=?;";

	public final static String UPDATE_Course = "UPDATE Course SET examsCount=? WHERE courseID=?;";

	public final static String SELECT_subjectID_FROM_Subject = "SELECT subjectID FROM Subject WHERE subjectName=?;";

	public final static String SELECT_courseID_FROM_Course = "SELECT courseID FROM Course WHERE courseName=?;";
	
	public final static String SELECT_Exams_BY_SubjectID = "SELECT * FROM Exam WHERE subjectID=?;";

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

	/**
	 * returns the whole table of questions of the specific author (the user) for
	 * the table view when edit\remove is pressed
	 * 
	 * @param author
	 * @param connection
	 * @return
	 * @throws SQLException
	 */
	public static QuestionsHandle getQuestions(String author, String subject, Connection connection)
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
		statement.close();
		rs.close();
		return (new QuestionsHandle("All", questions));
	}

	public static QuestionsHandle getQuestionsBySubject(Connection connection, String subject) throws SQLException {
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
		statement.close();
		rs.close();
		return (new QuestionsHandle("Subject", questionsBySubject));
	}
	
	public static ExamHandle getExamsBySubject(Connection connection, String subject) throws SQLException {
		ArrayList<Exam> examsBySubject = new ArrayList<Exam>();
		PreparedStatement statement = connection.prepareStatement(SqlUtilities.SELECT_Exams_BY_SubjectID);
		try {
			statement.setString(1, getSubjectID(subject, connection));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			examsBySubject.add(new Exam(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
			 rs.getInt(5),  rs.getString(6),  rs.getString(7)));
		}
		statement.close();
		rs.close();
		return (new ExamHandle("Subject", examsBySubject));
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
			update.close();
		}
	}

	/**
	 * adds new question to the DB
	 */
	public static void insertNewQuestion(Question question, Connection connection) throws SQLException {
		PreparedStatement insert = connection.prepareStatement(SqlUtilities.INSERT_Question);
		insert.setString(1, question.getSubjectID());
		insert.setString(2, question.getQuestionNum());
		insert.setString(3, question.getAuthor());
		insert.setString(4, question.getQuestionText());
		insert.setString(5, question.getFirstPossibleAnswer());
		insert.setString(6, question.getSecondPossibleAnswer());
		insert.setString(7, question.getThirdPossibleAnswer());
		insert.setString(8, question.getFourthPossibleAnswer());
		insert.setString(9, question.getCorrectAnswer());
		insert.executeUpdate();
		insert.close();
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
		insert.close();
		insertQuestionInExam(exam, examNumber, connection); // insert all the questions to the QuestionInExam table
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
		remove.close();
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
	public static Integer getQuestionCount(String subjectID, Connection connection) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(SqlUtilities.SELECT_FROM_Subject);
		statement.setString(1, subjectID);
		ResultSet rs = statement.executeQuery();
		rs.next();
		int questionsCount = rs.getInt(1) + 1;
		statement = connection.prepareStatement(SqlUtilities.UPDATE_Subject);
		statement.setInt(1, questionsCount); // update the new count in the DB
		statement.setString(2, subjectID);
		statement.executeUpdate();
		statement.close();
		rs.close();
		return questionsCount;
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
	public static Integer getExamCount(String courseID, Connection connection) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(SqlUtilities.SELECT_FROM_Course);
		statement.setString(1, courseID);
		ResultSet rs = statement.executeQuery();
		rs.next();
		int examsCount = rs.getInt(1) + 1;
		statement = connection.prepareStatement(SqlUtilities.UPDATE_Course);
		statement.setInt(1, examsCount); // update the new count in the DB
		statement.setString(2, courseID);
		statement.executeUpdate();
		statement.close();
		rs.close();
		return examsCount;
	}

	// end region -> Public Methods

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
		rs.close();
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
		rs.close();
		return courseID;
	}

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
		insert.close();
	}

} /* end of class */