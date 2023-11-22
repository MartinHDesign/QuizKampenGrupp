package Server.DataBase.Questions.History;

import Server.DataBase.Questions.Question;
import java.util.List;

public class HistoryQuestion extends Question<HistoryAnswer> {

    public HistoryQuestion(String question, List<HistoryAnswer> answers) {
        super(question, answers);
    }
}
