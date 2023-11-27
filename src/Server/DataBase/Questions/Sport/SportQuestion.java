package Server.DataBase.Questions.Sport;

import Server.DataBase.Questions.Question;
import java.util.List;

public class SportQuestion extends Question<SportAnswer> {

    public SportQuestion(String question, List<SportAnswer> answers) {
        super(question, answers);
    }
}