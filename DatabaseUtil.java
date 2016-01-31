import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseUtil {
	private static final String DATABASE_URL = "jdbc:mysql://localhost/naivobazata";
	private static final String DATABASE_USERNAME = "root";
	private static final String DATABASE_PASSWORD = "12345";

	private static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static HashMap<LevelEnum, ArrayList<Zone>> selectZones() {
		HashMap<LevelEnum, ArrayList<Zone>> zones = new HashMap<LevelEnum, ArrayList<Zone>>();
		ArrayList<Zone> zoness = new ArrayList<Zone>();
		try {
			Statement statement = getConnection().createStatement();
			ResultSet set = statement.executeQuery("SELECT * FROM `na_ivo_vuprosite`");
			while (set.next()) {
				String name = set.getString("name");
				int level = set.getInt("level");
				LevelEnum levelEnum = LevelEnum.getEnumByLevel(level);
				Zone zone = new Zone(name, levelEnum);
				if (zones.containsKey(levelEnum)) {
					ArrayList<Zone> enumZones = zones.get(levelEnum);
					enumZones.add(zone);
					zones.put(levelEnum, enumZones);
				} else {
					ArrayList<Zone> enumZones = new ArrayList<Zone>();
					enumZones.add(zone);
					zones.put(levelEnum, enumZones);
				}
				zoness.add(zone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return zones;
	}

	public static HashMap<String, List<Answer>> selectQuestions() {
		HashMap<String, List<Answer>> questions = new HashMap<String, List<Answer>>();
		try {
			Statement statement = getConnection().createStatement();
			ResultSet set = statement.executeQuery("SELECT * FROM `naivovuprosite`");
			while (set.next()) {
				String text = set.getString("text");
				String rightAnswer = set.getString("right_answer");
				String wrongAnswer1 = set.getString("wrong_answer1");
				String wrongAnswer2 = set.getString("wrong_answer2");
				String wrongAnswer3 = set.getString("wrong_answer3");
				ArrayList<Answer> answers = new ArrayList<>();
				answers.add(new Answer(rightAnswer, true));
				answers.add(new Answer(wrongAnswer1, false));
				answers.add(new Answer(wrongAnswer2, false));
				answers.add(new Answer(wrongAnswer3, false));
				questions.put(text, answers);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return questions;
	}
}
