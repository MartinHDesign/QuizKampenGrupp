package Server;

import Server.DataBase.HistoryQuestions.HistoryQuestion;

import javax.swing.plaf.PanelUI;
import java.io.Serializable;

public class ServerResponse implements Serializable {

    private int score;
    private HistoryQuestion question;
    private int endOfGame;
    private String categories;

    public ServerResponse(int score) {
        this.score = score;
    }

    public ServerResponse(HistoryQuestion question) {
        this.question = question;
    }

    public ServerResponse(int score, int endOfGame) {
        this.score = score;
        this.endOfGame = endOfGame;
    }

    public ServerResponse(String categories) {
        this.categories = categories;

    }

    public int getScore() {
        return score;
    }

    public HistoryQuestion getQuestion() {
        return question;
    }

    public int getEndOfGame() {
        return endOfGame;
    }
    public String getCategories() {
        return categories;
    }
}

