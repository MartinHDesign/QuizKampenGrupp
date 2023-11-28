package Server.DataBase;

import Server.DataBase.Questions.History.HistoryAnswer;
import Server.DataBase.Questions.History.historyQuestion;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HistoryDAO extends CategoryDAO <historyQuestion>{

    // Lista för att lagra historiska frågor
    private List<historyQuestion> historyQuestions = new ArrayList<>();

    // Konstruktor för att skapa historiska frågor och svarsalternativ
    public HistoryDAO() {
        // Fråga 1
        List<HistoryAnswer> answer0 = new ArrayList<>();
        answer0.add(new HistoryAnswer("1492", true));
        answer0.add(new HistoryAnswer("1500", false));
        answer0.add(new HistoryAnswer("1485", false));
        answer0.add(new HistoryAnswer("1512", false));
        // Skapa en historisk fråga och lägg till den i listan
        historyQuestion question0 = new historyQuestion("Vilket år upptäcktes Amerika av Christopher Columbus?", answer0);
        historyQuestions.add(question0);

        // Fråga 2
        List<HistoryAnswer> answer1 = new ArrayList<>();
        answer1.add(new HistoryAnswer("1776", true));
        answer1.add(new HistoryAnswer("1789", false));
        answer1.add(new HistoryAnswer("1492", false));
        answer1.add(new HistoryAnswer("1607", false));
        // Skapa en historisk fråga och lägg till den i listan
        historyQuestion question1 = new historyQuestion("Vilket år grundades USA?", answer1);
        historyQuestions.add(question1);

        // Fråga 3
        List<HistoryAnswer> answer2 = new ArrayList<>();
        answer2.add(new HistoryAnswer("1861", false));
        answer2.add(new HistoryAnswer("1812", false));
        answer2.add(new HistoryAnswer("1914", false));
        answer2.add(new HistoryAnswer("1865", true));
        // Skapa en historisk fråga och lägg till den i listan
        historyQuestion question2 = new historyQuestion("Vilket år slutade amerikanska inbördeskriget?", answer2);
        historyQuestions.add(question2);

        // Fråga 4
        List<HistoryAnswer> answer3 = new ArrayList<>();
        answer3.add(new HistoryAnswer("1917", false));
        answer3.add(new HistoryAnswer("1945", true));
        answer3.add(new HistoryAnswer("1939", false));
        answer3.add(new HistoryAnswer("1914", false));
        // Skapa en historisk fråga och lägg till den i listan
        historyQuestion question3 = new historyQuestion("Vilket år slutade andra världskriget?", answer3);
        historyQuestions.add(question3);

    }

    // Metod för att hämta listan av historiska frågor
    public List<historyQuestion> getHistoryQuestions() {
        return historyQuestions;
    }
    public historyQuestion takeRandomQuestion() {
        if (historyQuestions.isEmpty()) {
            HistoryDAO newListOfQuestions = new HistoryDAO();
            historyQuestions = newListOfQuestions.getHistoryQuestions();
        }
        Random random = new Random();
        int questionToTake = random.nextInt(historyQuestions.size());

        historyQuestion toReturn = historyQuestions.get(questionToTake);
        historyQuestions.remove(0);
        return toReturn;
    }
}
