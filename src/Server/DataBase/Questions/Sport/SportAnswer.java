package Server.DataBase.Questions.Sport;

import Server.DataBase.Questions.Answer;
import java.io.Serializable;

public class SportAnswer extends Answer implements Serializable {

    public SportAnswer(String answerText, boolean isCorrect) {
        super(answerText, isCorrect);
    }
}
