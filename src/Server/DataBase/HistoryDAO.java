package Server.DataBase;

import Server.DataBase.Questions.History.MusicAnswer;
import Server.DataBase.Questions.History.HistoryQuestion;


import java.util.ArrayList;
import java.util.List;

public class HistoryDAO extends CategoryDAO <HistoryQuestion>{

    // Lista för att lagra historiska frågor
    private final List<HistoryQuestion> historyQuestions = new ArrayList<>();

    // Konstruktor för att skapa historiska frågor och svarsalternativ
    public HistoryDAO(List<HistoryQuestion> historyQuestions) {
        super(historyQuestions);
        // Fråga 1
        List<MusicAnswer> answer0 = new ArrayList<>();
        answer0.add(new MusicAnswer("1492", true));
        answer0.add(new MusicAnswer("1500", false));
        answer0.add(new MusicAnswer("1485", false));
        answer0.add(new MusicAnswer("1512", false));
        // Skapa en historisk fråga och lägg till den i listan
        HistoryQuestion question0 = new HistoryQuestion("Vilket år upptäcktes Amerika av Christopher Columbus?", answer0);
        historyQuestions.add(question0);

        // Fråga 2
        List<MusicAnswer> answer1 = new ArrayList<>();
        answer1.add(new MusicAnswer("1776", false));
        answer1.add(new MusicAnswer("1789", false));
        answer1.add(new MusicAnswer("1492", true));
        answer1.add(new MusicAnswer("1607", false));
        // Skapa en historisk fråga och lägg till den i listan
        HistoryQuestion question1 = new HistoryQuestion("Vilket år grundades USA?", answer1);
        historyQuestions.add(question1);

        // Fråga 3
        List<MusicAnswer> answer2 = new ArrayList<>();
        answer2.add(new MusicAnswer("1861", false));
        answer2.add(new MusicAnswer("1812", false));
        answer2.add(new MusicAnswer("1914", false));
        answer2.add(new MusicAnswer("1865", true));
        // Skapa en historisk fråga och lägg till den i listan
        HistoryQuestion question2 = new HistoryQuestion("Vilket år slutade amerikanska inbördeskriget?", answer2);
        historyQuestions.add(question2);

        // Fråga 4
        List<MusicAnswer> answer3 = new ArrayList<>();
        answer3.add(new MusicAnswer("1917", false));
        answer3.add(new MusicAnswer("1945", true));
        answer3.add(new MusicAnswer("1939", false));
        answer3.add(new MusicAnswer("1914", false));
        // Skapa en historisk fråga och lägg till den i listan
        HistoryQuestion question3 = new HistoryQuestion("Vilket år slutade andra världskriget?", answer3);
        historyQuestions.add(question3);

    }

    // Metod för att hämta listan av historiska frågor
    public List<HistoryQuestion> getHistoryQuestions() {
        return historyQuestions;
    }
}
