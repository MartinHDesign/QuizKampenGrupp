package Server.DataBase.Questions.Music;

import Server.DataBase.Questions.Question;
import java.util.List;

public class MusicQuestion extends Question<MusicAnswer> {

    public MusicQuestion(String question, List<MusicAnswer> answers) {
        super(question, answers);
    }
}
