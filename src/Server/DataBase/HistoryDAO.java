package Server.DataBase;

import Server.DataBase.HistoryQuestions.HistoryAnswer;
import Server.DataBase.HistoryQuestions.HistoryQuestion;

import java.util.ArrayList;
import java.util.List;

public class HistoryDAO extends CategoryDAO <HistoryQuestion>{

    // Lista för att lagra historiska frågor
    private final List<HistoryQuestion> historyQuestions = new ArrayList<>();

    // Konstruktor för att skapa historiska frågor och svarsalternativ
    public HistoryDAO(List<HistoryQuestion> historyQuestions) {
        super(historyQuestions);
        // Fråga 1
        List<HistoryAnswer> answer0 = new ArrayList<>();
        answer0.add(new HistoryAnswer("1492", true));
        answer0.add(new HistoryAnswer("1500", false));
        answer0.add(new HistoryAnswer("1485", false));
        answer0.add(new HistoryAnswer("1512", false));
        // Skapa en historisk fråga och lägg till den i listan
        HistoryQuestion question0 = new HistoryQuestion("Vilket år upptäcktes Amerika av Christopher Columbus?", answer0);
        historyQuestions.add(question0);

        // Fråga 2
        List<HistoryAnswer> answer1 = new ArrayList<>();
        answer1.add(new HistoryAnswer("1776", false));
        answer1.add(new HistoryAnswer("1789", false));
        answer1.add(new HistoryAnswer("1492", true));
        answer1.add(new HistoryAnswer("1607", false));
        // Skapa en historisk fråga och lägg till den i listan
        HistoryQuestion question1 = new HistoryQuestion("Vilket år grundades USA?", answer1);
        historyQuestions.add(question1);

        // Fråga 3
        List<HistoryAnswer> answer2 = new ArrayList<>();
        answer2.add(new HistoryAnswer("1861", false));
        answer2.add(new HistoryAnswer("1812", false));
        answer2.add(new HistoryAnswer("1914", false));
        answer2.add(new HistoryAnswer("1865", true));
        // Skapa en historisk fråga och lägg till den i listan
        HistoryQuestion question2 = new HistoryQuestion("Vilket år slutade amerikanska inbördeskriget?", answer2);
        historyQuestions.add(question2);

        // Fråga 4
        List<HistoryAnswer> answer3 = new ArrayList<>();
        answer3.add(new HistoryAnswer("1917", false));
        answer3.add(new HistoryAnswer("1945", true));
        answer3.add(new HistoryAnswer("1939", false));
        answer3.add(new HistoryAnswer("1914", false));
        // Skapa en historisk fråga och lägg till den i listan
        HistoryQuestion question3 = new HistoryQuestion("Vilket år slutade andra världskriget?", answer3);
        historyQuestions.add(question3);

    }

    // Metod för att hämta listan av historiska frågor
    public List<HistoryQuestion> getHistoryQuestions() {
        return historyQuestions;
    }
}
