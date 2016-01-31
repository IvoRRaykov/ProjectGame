import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameUtil {

	public static HashMap<String, List<Answer>> insertDefaultQuestions() {
		
		return DatabaseUtil.selectQuestions();

	}

	public static HashMap<LevelEnum, ArrayList<Zone>> insertDefaultZones() {
		HashMap<LevelEnum, ArrayList<Zone>> zones = DatabaseUtil.selectZones();
		return zones;
	}

	public static void giveZonesToPlayers(Player player, Player otherPlayer) {
		for (LevelEnum enumLevel : Constants.defaultZones.keySet()) {
			ArrayList<Zone> zones = Constants.defaultZones.get(enumLevel);
			ArrayList<Zone> playerOneZones = player.getZones();
			ArrayList<Zone> playerTwoZones = otherPlayer.getZones();
			if (enumLevel.getLevel() == 3) {
				playerOneZones.add(zones.get(0));
				playerTwoZones.add(zones.get(1));
				player.setZones(playerOneZones);
				otherPlayer.setZones(playerTwoZones);
			} else {
				playerOneZones.add(zones.get(0));
				playerOneZones.add(zones.get(1));
				playerTwoZones.add(zones.get(2));
				playerTwoZones.add(zones.get(3));
				player.setZones(playerOneZones);
				otherPlayer.setZones(playerTwoZones);
			}
		}
	}
	public static void giveQuestionsToPlayers(Player player, Player otherPlayer){
		for(int i=0;i < 30; i++){
			
			
			
		}
		
		
	}

}
