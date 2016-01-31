import java.util.Collections;
import java.util.List;

public class Question {

	private String text;

	private List<Answer> answers;

	public Question(String question) {

		this.text = question;
		answers = Constants.defaultQuestions.get(question);
	}

	public String getText() {
		return this.text;
	}

	public List<Answer> getAnswers() {
		return this.answers;
	}
	
	public void showAnswers(){
		Collections.shuffle(this.answers);
		System.out.println(answers);
	}

	public Answer getRightAnswer() {
		for(Answer answer: this.answers){
			if(answer.isRight()) {
				return answer;
			}
		}
		return null;
	}

}
