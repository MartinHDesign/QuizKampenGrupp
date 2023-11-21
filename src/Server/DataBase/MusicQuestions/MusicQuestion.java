package Server.DataBase.MusicQuestions;


import java.io.Serializable;
import java.util.List;

public class MusicQuestion implements Serializable {

    private String question;
    private List<MusicAnswer> answers;

    public MusicQuestion(String question, List<MusicAnswer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<MusicAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<MusicAnswer> answers) {
        this.answers = answers;
    }
}
