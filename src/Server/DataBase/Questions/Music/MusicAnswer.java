package Server.DataBase.Questions.Music;

import Server.DataBase.Questions.Answer;

import java.io.Serializable;

public class MusicAnswer extends Answer implements Serializable {

    public MusicAnswer(String answerText, boolean isCorrect) {
        super(answerText, isCorrect);
    }
}
