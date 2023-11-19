package Server.DataBase;

import Server.DataBase.HistoryQuestions.HistoryAnswer;
import Server.DataBase.HistoryQuestions.HistoryQuestion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HistoryDAO extends CategoryDAO{


    /* Using a hash map with an integer key (to find the correct question) and a question as its value,
    the question class contains a list of answers
    the answer class contains a String of an answer and a boolean to indicate if it's the correct answer or not
    The gameStateWriter class sends out the same question from the hashmap to the clients*/

    private final List<HistoryAnswer> answer0 = new ArrayList<>();
    private final HistoryQuestion question0 = new HistoryQuestion("vilket år upptäcktes Amerika av Christopher Columbus?", answer0);

    private HashMap<Integer, HistoryQuestion> historyQuestion;

    public HistoryDAO() {
        answer0.add(new HistoryAnswer("1492",true));
        answer0.add(new HistoryAnswer("1500",false));
        answer0.add(new HistoryAnswer("1485",false));
        answer0.add(new HistoryAnswer("1512",false));
        historyQuestion = new HashMap<>();

        historyQuestion.put(1,question0);

    }

    public HashMap<Integer, HistoryQuestion> getHistoryQuestion() {
        return historyQuestion;
    }
}
