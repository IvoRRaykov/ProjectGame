import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Player {

	Scanner sc = new Scanner(System.in);

	private Color color;

	private String username;
	private String password;

	private TreeMap<Integer, Question> allQuestions;
	private TreeMap<Integer, Question> deck;
	private ArrayList<Zone> zones;

	private int points = 0;

	Player(Color color) {
		this.color = color;
		giveZonesToPlayer();
		for (int i = 0; i < zones.size(); i++) {
			this.points += zones.get(i).getPoints();
		}

		this.fillAllQuestions();

	}

	void giveZonesToPlayer() {
		
	}

	private void fillAllQuestions() {
		Set<String> questions = Constants.defaultQuestions.keySet();
		for (int i = 1; i <= Constants.PLAYER_MAX_QUESTIONS; i++) {
			ArrayList<String> questionsArray = new ArrayList<String>(questions);
			Collections.shuffle(questionsArray);
			if (questionsArray.get(0) != null) {
				allQuestions.put(i, new Question(questionsArray.get(0)));
				questionsArray.remove(i);
			} else {
				i--;
				continue;
			}
		}

	}

	private void addQuestionToDeck() {
		showAllQuestions();
		Integer questionID = sc.nextInt();

		for (Map.Entry<Integer, Question> pair : allQuestions.entrySet()) {
			if (questionID.equals(pair.getKey()) && !this.deck.containsKey(pair.getKey())) {
				this.deck.put(pair.getKey(), pair.getValue());
				break;
			} else {
				System.out.println("Already added!");
				addQuestionToDeck();
			}
		}
	}

	private Map.Entry<Integer, Question> choseQuestionFromDeck() {
		showDeck();
		Integer questionID = sc.nextInt();
		Map.Entry<Integer, Question> toReturn = null;

		for (Map.Entry<Integer, Question> pair : deck.entrySet()) {
			if (questionID.equals(pair.getKey())) {
				toReturn = pair;
				this.deck.remove(pair);
			}
		}
		return toReturn;
	}

	public void fillDeck() {
		for (int i = 0; i < Constants.DECK_MAX_SIZE; i++) {
			this.addQuestionToDeck();
		}
	}

	public void clearDeck() {
		this.deck.clear();
	}

	void showDeck() {
		for (Map.Entry<Integer, Question> pair : deck.entrySet()) {
			System.out.println("" + pair.getKey() + " : " + pair.getValue());
		}
	}

	void showAllQuestions() {
		for (Map.Entry<Integer, Question> pair : allQuestions.entrySet()) {
			System.out.println("" + pair.getKey() + " : " + pair.getValue());
		}
	}

	Boolean answer(Question q) {
		q.showAnswers();
		System.out.println("Chose answer");
		// na android poveche
		return sc.nextLine().equalsIgnoreCase(q.getRightAnswer().getText().trim());
	}

	void choseZone(Zone zone) {
		// izbira zona ?? :D
	}

	void attack(Player defender, Zone zone) {
		boolean shouldBreak = false;
		while (!shouldBreak) {
			this.choseZone(zone);
			Map.Entry<Integer, Question> questionPair11 = this.choseQuestionFromDeck();
			if (defender.answer(questionPair11.getValue())) {
				defender.allQuestions.put(questionPair11.getKey(), questionPair11.getValue());
				shouldBreak = true;
			} else {
				zone.setLifes(zone.getLifes() - 1);
				if (zone.getLifes() == 0) {
					this.aquire(defender, zone);
					shouldBreak = true;
				}
			}
		}
	}

	void aquire(Player defender, Zone zone) {

		zone.setColor(this.color);
		this.zones.add(zone);
		this.points += zone.getPoints();
		defender.zones.remove(zone);
		defender.points -= zone.getPoints();

	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public int getPoints() {
		return points;
	}

}
