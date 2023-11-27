package Server.DataBase.Questions;

import java.io.Serializable;
import java.util.List;

public abstract class Question<T extends Answer> implements Serializable {

    private String question;
    private List<T> answers;

    public Question(String question, List<T> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<T> getAnswers() {
        return answers;
    }

    public void setAnswers(List<T> answers) {
        this.answers = answers;
    }


}
