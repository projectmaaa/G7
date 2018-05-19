package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import resources.Question;

/**
 * 
 * @author Alex
 *
 */
public class SqlUtilities {

	// region Constants

	public final static String SELECT_All_FROM_Questions = "SELECT * FROM Questions;";

	public final static String Login_SELECT_User_From_Users = "SELECT * FROM Users WHERE idUsers=? AND passWord=?;";

	public final static String UPDATE_Questions_Table = "UPDATE Questions SET teacherName=?, questionText=?, possibleAnswers=?, correctAnswer=? WHERE questionID=?";

	// region Public Methods

	// end region -> Constants

	@SuppressWarnings("deprecation")
	public static Connection connection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			/* handle the error */
		}
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://group7project.c2ntivjwkagb.eu-central-1.rds.amazonaws.com:3306/group7db", "app",
					"project7");
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
	 * returns the whole table of questions for the table view
	 */
	public static ArrayList<Question> getQuestions() throws SQLException {
		ArrayList<Question> questions = new ArrayList<Question>();
		Statement statement = SqlUtilities.connection().createStatement();
		ResultSet rs = statement.executeQuery(SELECT_All_FROM_Questions);
		while (rs.next()) {
			questions.add(
					new Question(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
		}
		rs.close();
		return questions;
	}

	/*
	 * updates the table in the data base
	 */
	public static void editTable(ArrayList<Question> newQuestions) throws SQLException {
		PreparedStatement update = SqlUtilities.connection()
				.prepareStatement(SqlUtilities.UPDATE_Questions_Table);
		for (Question question : newQuestions) {
			update.setString(1, question.getAuthor());
			update.setString(2, question.getQuestionText());
			update.setString(3, question.getPossibleAnswers());
			update.setString(4, question.getCorrectAnswer());
			update.setString(5, question.getQuestionID());
		}
		update.executeUpdate();
	}

	// end region -> Public Methods

} /* end of class */