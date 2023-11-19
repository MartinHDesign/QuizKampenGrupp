package Server.DataBase.HistoryQuestions;

import java.io.Serializable;
import java.util.List;

public class HistoryQuestion implements Serializable {

    private String question;
    private List<HistoryAnswer> answers;

    public HistoryQuestion(String question, List<HistoryAnswer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<HistoryAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<HistoryAnswer> answers) {
        this.answers = answers;
    }
}
