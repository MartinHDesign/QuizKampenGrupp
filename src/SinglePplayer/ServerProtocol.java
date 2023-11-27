package SinglePplayer;

import Server.DataBase.HistoryDAO;
import Server.DataBase.HistoryQuestions.HistoryQuestion;
import Server.ServerResponse;

import java.io.IOException;
import java.util.List;

public class ServerProtocol {

    private Player player1;
    private Player player2;

    private Player currentPlayer = player1;

    private final HistoryDAO historyQuestions = new HistoryDAO();

    private List<HistoryQuestion> currentCategory;

    private final int HISTORY = 0;
    private final int SPORT = 0;
    private final int MUSIC = 0;
    private int numberOfQuestions = 2; //från properties
    private int questionsAnswered = 0;
    private int questionToSend = 0;
    private int numberOfRounds = 2; //från properties
    private int roundsPlayed = 0;

    public ServerProtocol(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Object processInput(Object objectFromClient, Player player) throws IOException {
        System.out.println("Numer of questions answered: " + questionsAnswered);

        if (objectFromClient instanceof Integer) {
            return processCategoryChosen((Integer) objectFromClient);
        }
        if (objectFromClient instanceof Boolean) {

            return (processAnsweredQuestion((Boolean) objectFromClient, player));
        }

        return null;


    }

    public Object processAnsweredQuestion(boolean input, Player player) {

        if (input) {
            player.gainOnePoint();
            System.out.println(player.getName() + " gained one point");
        }
        if (questionsAnswered == numberOfQuestions) {
            System.out.println("telling player" + player.getName() + " to wait");
            questionToSend =0;

            return new ServerResponse("WAIT");

        }
        if (questionsAnswered == numberOfQuestions * 2) {
            System.out.println("sending end of round score");
            questionsAnswered = 0;
            if (roundsPlayed == numberOfRounds) {
                return new ServerResponse("SCORE",true);
            }
            //Här kan vi lägga till vad som behövs för att visa SCORE-Panel
            roundsPlayed++;
            return new ServerResponse("SCORE");
        }
        System.out.println("Sending questions to " + player.getName());
        questionToSend++;
        questionsAnswered++;
        return new ServerResponse(currentCategory.get(questionToSend), "QUESTION");

    }

    public Object processCategoryChosen(int input) {
        questionsAnswered++;
        switch (input) {
            case 0 -> currentCategory = historyQuestions.getHistoryQuestions();

        }
        return new ServerResponse(currentCategory.get(0), "QUESTIONS");
    }

    public int getQuestionsAnswered() {
        return questionsAnswered;
    }
}
