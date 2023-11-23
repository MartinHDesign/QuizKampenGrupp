package Server;

import Server.DataBase.HistoryQuestions.HistoryQuestion;
import SinglePplayer.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ServerResponse implements Serializable {

    private int score;
    private HistoryQuestion question;
    private int endOfGame;
    private String showGUIPanel;

    private List<String> playerNames;

    private List<Integer> playerScores;


    public ServerResponse(int score) {
        this.score = score;
    }

    public ServerResponse(int score, String showGUIPanel) {
        this.score = score;
        this.showGUIPanel = showGUIPanel;
    }

    public ServerResponse(String player1Name, int player1Score, String player2Name, int player2Score, String GUI) {
        this.playerNames = new ArrayList<>();
        this.playerScores = new ArrayList<>();

        this.playerNames.add(player1Name);
        this.playerNames.add(player2Name);

        this.playerScores.add(player1Score);
        this.playerScores.add(player2Score);

        this.showGUIPanel = GUI;

    }


    public ServerResponse(HistoryQuestion question) {
        this.question = question;
    }



    public ServerResponse(String categories) {
        this.showGUIPanel = categories;

    }

    public ServerResponse(HistoryQuestion question, String showGUIPanel) {
        this.question = question;
        this.showGUIPanel = showGUIPanel;
    }

    public List<String> getPlayerNames() {
        return playerNames;
    }

    public List<Integer> getPlayerScores() {
        return playerScores;
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
    public String getShowGUIPanel() {
        return showGUIPanel;
    }
}

