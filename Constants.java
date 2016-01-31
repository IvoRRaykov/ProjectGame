import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface Constants {

	HashMap<String, List<Answer>> defaultQuestions = GameUtil.insertDefaultQuestions();
	
	int PLAYER_MAX_QUESTIONS = 30;
	int DECK_MAX_SIZE = 20;
	
	HashMap<LevelEnum, ArrayList<Zone>> defaultZones = GameUtil.insertDefaultZones();

	int STARTING_POINTS = 1000; 
}
