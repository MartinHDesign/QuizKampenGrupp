package SinglePplayer;

import Server.DataBase.HistoryDAO;
import Server.ServerResponse;

import java.io.IOException;

public class ProtocolREPLACEWithNewCode {

    private Player player1;
    private Player player2;

    private Player currentPlayer = player1;

    private final HistoryDAO historyQuestions = new HistoryDAO();

    private int currentCategory;
    private final int HISTORY = 0;
    private final int NEWGAME = 0;
    private final int SENDQUESTION = 1;
    private int numberOfQuestions = 2;
    private int questionsAnswered = 0;
    private int questionToSend = 0;
    private int rounds = 2;
    private int roundsPlayed = 0;

    public ProtocolREPLACEWithNewCode(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Object processInput(Object objectFromClient, Player player) throws IOException {
        System.out.println("Numer of questions answered: " + questionsAnswered);

        if (objectFromClient instanceof Integer) {
            questionsAnswered++;
            System.out.println("Sending questions to " + player.getName());
            return new ServerResponse(historyQuestions.getHistoryQuestions().get(0), "QUESTIONS");
        }

            if (objectFromClient instanceof Boolean) {

                if ((Boolean) objectFromClient) {
                    player.gainOnePoint();
                    System.out.println(player.getName() + " gained one point");
                }
                if (questionsAnswered == numberOfQuestions) {
                    System.out.println("telling player" + player.getName() + " to wait");
                    questionToSend = 0;
                    return new ServerResponse("WAIT");
                }
                if (questionsAnswered == numberOfQuestions*2) {
                    System.out.println("sending end of round score");
                    return new ServerResponse("SCORE");
                }

                System.out.println("Sending questions to " + player.getName());
                questionToSend++;
                questionsAnswered++;
                return new ServerResponse(historyQuestions.getHistoryQuestions().get(questionToSend));
            }

        return new ServerResponse("Null");
    }
}
