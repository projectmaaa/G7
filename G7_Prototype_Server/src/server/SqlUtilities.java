package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import resources.Question;
import resources.QuestionsHandle;

public class SqlUtilities {

	// region Constants

	public final static String SELECT_All_FROM_Questions = "SELECT * FROM Questions;";

	public final static String Login_SELECT_UserID_From_Users = "SELECT idUsers FROM Users WHERE idUsers=? AND passWord=?;";

	public final static String Login_getlog_Status = "SELECT logged FROM Users WHERE idUsers=? AND passWord=?;";

	public final static String Login_UpdateUser_logStatus_Connected = "UPDATE Users SET logged=1 WHERE idUsers=? AND passWord=?;";

	public final static String Login_UpdateUser_logStatus_DisConnected = "UPDATE Users SET logged=0 WHERE idUsers=? AND passWord=?;";

	public final static String UPDATE_Questions_Table = "UPDATE Questions SET questionText=?, firstAnswer=?, secondAnswer=?, thirdAnswer=?, fourthAnswer=?, correctAnswer=? WHERE questoinSubject=? AND questionNum=?;";

	public final static String INSERT_Question = "INSERT INTO Questions VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

	public final static String REMOVE_Question = "DELETE FROM Questions WHERE questoinSubject=? AND questionNum=?;";

	public final static String SELECT_FROM_Questions_Count = "SELECT questionsCount FROM Questions_Count WHERE subjectID=?;";

	public final static String UPDATE_Questions_Count = "UPDATE Questions_Count SET questionsCount=? WHERE subjectID=?;";

	public final static String GetUserNameAndLastName = "SELECT firstName, lastName FROM Users WHERE idUsers=?;";

	public final static String GetQuestionBySubject = "SELECT * FROM Questions WHERE questoinSubject=?;";

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
	 * returns the whole table of questions for the table view when edit\remove is
	 * pressed
	 */
	public static QuestionsHandle getQuestions(Connection connection) throws SQLException {
		ArrayList<Question> questions = new ArrayList<Question>();
		ArrayList<String> possibleAnswers = new ArrayList<String>(4);
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(SELECT_All_FROM_Questions);
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
		switch (subject) {
		case "Software":
			statement.setString(1, "01");
			break;
		case "Math":
			statement.setString(1, "02");
			break;
		case "Physics":
			statement.setString(1, "03");
			break;
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

	/**
	 * updates the table in the data base
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
			update.setString(7, question.getQuestionSubject());
			update.setString(8, question.getQuestionNum());
			update.executeUpdate();
		}
	}

	/**
	 * adds new question to the DB
	 */
	public static void insertNewQuestion(Question question, Connection connection) throws SQLException {
		PreparedStatement insert = connection.prepareStatement(SqlUtilities.INSERT_Question);
		insert.setString(1, question.getQuestionSubject());
		insert.setString(2, question.getQuestionNum());
		insert.setString(3, question.getAuthor());
		insert.setString(4, question.getQuestionText());
		insert.setString(5, question.getFirstPossibleAnswer());
		insert.setString(6, question.getSecondPossibleAnswer());
		insert.setString(7, question.getThirdPossibleAnswer());
		insert.setString(8, question.getFourthPossibleAnswer());
		insert.setString(9, question.getCorrectAnswer());
		insert.executeUpdate();
	}

	/**
	 * removes question from DB
	 */
	public static void removeQuestion(Question question, Connection connection) throws SQLException {
		PreparedStatement remove = connection.prepareStatement(SqlUtilities.REMOVE_Question);
		remove.setString(1, question.getQuestionSubject());
		remove.setString(2, question.getQuestionNum());
		remove.executeUpdate();
	}

	/**
	 * 
	 * @param subjectID
	 * @param connection
	 * @return
	 * @throws SQLException
	 * 
	 *             returns the current amount of questions of a specific subject &
	 *             updates the new amount in the DB
	 */
	public static Integer getQuestionCount(String subjectID, Connection connection) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(SqlUtilities.SELECT_FROM_Questions_Count);
		statement.setString(1, subjectID);
		ResultSet rs = statement.executeQuery();
		rs.next();
		int questionsCount = rs.getInt(1) + 1;
		statement = connection.prepareStatement(SqlUtilities.UPDATE_Questions_Count);
		statement.setInt(1, questionsCount); // update the new count in the DB
		statement.setString(2, subjectID);
		statement.executeUpdate();
		return questionsCount;
	}

	// end region -> Public Methods

} /* end of class */