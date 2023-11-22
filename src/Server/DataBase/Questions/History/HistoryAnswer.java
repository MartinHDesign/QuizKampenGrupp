package Server.DataBase.Questions.History;


import Server.DataBase.Questions.Answer;

import java.io.Serializable;

public class HistoryAnswer extends Answer implements Serializable {

    public HistoryAnswer(String answerText, boolean isCorrect) {
        super(answerText, isCorrect);
    }
}
