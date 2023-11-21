package Server.DataBase.MusicQuestions;

import java.io.Serializable;

public class MusicAnswer implements Serializable {
    private String answerText;
    private boolean isCorrect;

    public MusicAnswer(String answerText, boolean isCorrect) {
        this.answerText = answerText;
        this.isCorrect = isCorrect;

    }
    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}

