package Server;


import Server.DataBase.Questions.Question;
import SinglePplayer.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ServerResponse implements Serializable {

    private int score;
    private Question question;
    private String showGUIPanel;
    private String opponentName;
    private String category;
    private int opponentScore;

    private List<String> playerNames;

    private List<Integer> playerScores;


    private boolean endOfGame;
    public ServerResponse(String opponentName, int opponentScore){
        this.opponentName = opponentName;
    }



    public ServerResponse(int score) {
        this.score = score;
    }

    public ServerResponse() {
    }

    public ServerResponse(String showGUIPanel, boolean endOfGame) {
        this.showGUIPanel = showGUIPanel;
        this.endOfGame = endOfGame;
    }

    public ServerResponse(List<String> playerNames, List<Integer> playerScores, boolean endOfGame) {
        this.playerNames = playerNames;
        this.playerScores = playerScores;
        this.endOfGame = endOfGame;
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


    public ServerResponse(Question question) {
        this.question = question;
    }



    public ServerResponse(String categories) {
        this.showGUIPanel = categories;

    }

    public ServerResponse(Question question, String showGUIPanel) {
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

    public Question getQuestion() {
        return question;
    }

    public String getShowGUIPanel() {
        return showGUIPanel;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public String getCategory() {
        return category;
    }

    public int getOpponentScore() {
        return opponentScore;
    }

}

