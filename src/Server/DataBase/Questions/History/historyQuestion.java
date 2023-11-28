package Server.DataBase.Questions.History;

import Server.DataBase.Questions.Question;
import java.util.List;

public class historyQuestion extends Question<HistoryAnswer> {

    public historyQuestion(String question, List<HistoryAnswer> answers) {
        super(question, answers);
    }


}


