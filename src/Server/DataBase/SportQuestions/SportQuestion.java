package Server.DataBase.SportQuestions;

import java.io.Serializable;
import java.util.List;

public class SportQuestion implements Serializable {

    private String question;
    private List<SportAnswer> answers;

    public SportQuestion(String question, List<SportAnswer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<SportAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<SportAnswer> answers) {
        this.answers = answers;
    }
}
