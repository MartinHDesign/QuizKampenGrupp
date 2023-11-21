package Server;

import Server.DataBase.HistoryQuestions.HistoryQuestion;

public class ServerResponse {

    private int score;
    private HistoryQuestion question;
    private int endOfGame;

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
}
