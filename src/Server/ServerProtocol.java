package Server;

import Server.DataBase.Player;

import java.io.IOException;

public class ServerProtocol {
    private final GameStateWriter gameStateWriter;
    private final int NEXTROUND = 0;
    private final int WAITING = 1;
    private int numberOfQuestionsAnswered;
    private final int SHOWENDOFROUND = 3;

    private Player player1;
    private Player player2;

    private Player currentPlayersTurn;

    private int numberOfRounds = 2;
    private int numberOfRoundsPlayed;
    private int numberOfQuestionsPerRound = 2;

    private int numberOfPlayerAnswers;

    private int STATE = NEXTROUND;

    public ServerProtocol(GameStateWriter gameStateWriter, Player player1, Player player2) {
        this.gameStateWriter = gameStateWriter;
        this.player1 = player1;
        this.player2 = player2;
        currentPlayersTurn = player1;
    }


    //En liten minimetod som väntar med att köra den riktiga koden tills båda spelare har svarat
    public synchronized void playerResponses(boolean correctAnswerFromClient, Player player) throws IOException {
        if (correctAnswerFromClient) {
        player.gainOnePoint();
        }
        numberOfPlayerAnswers++;
        if (numberOfPlayerAnswers == 2) {
            gameProcess(correctAnswerFromClient, player);
            numberOfPlayerAnswers = 0;
        }
    }



    public void gameProcess(Object response, Player player) throws IOException {

        if (response instanceof Boolean) {
            if ((Boolean) response) {
                player.gainOnePoint();
            }
        }
        if (response instanceof Integer) {
            gameStateWriter.setCurrentCategory((Integer) response);
        }
        if (numberOfQuestionsAnswered == numberOfQuestionsPerRound*2) {
            STATE = SHOWENDOFROUND;
        }

        if (numberOfRounds == numberOfRoundsPlayed) {
            //end game
        }
        switch (STATE) {
            case 0 -> {
                {
                    numberOfQuestionsAnswered = 0;
                    gameStateWriter.chooseCategory(currentPlayersTurn);
                    STATE = WAITING;
                }
            }
            case 1 -> {
                setCurrentPlayersTurn();
                gameStateWriter.sendQuestions();
                numberOfQuestionsAnswered++;
            }

            case 2 -> {

            }

            case 3 -> {
                gameStateWriter.sendEndOfRoundScore();
                setCurrentPlayersTurn();
                STATE = NEXTROUND;
            }
        }


    }

    public void setCurrentPlayersTurn() {
        if (numberOfQuestionsAnswered == numberOfQuestionsPerRound || numberOfQuestionsAnswered == numberOfQuestionsPerRound*2) {
            if (currentPlayersTurn.equals(player1)) {
                currentPlayersTurn = player2;

            } else if (currentPlayersTurn.equals(player2) || numberOfQuestionsAnswered == numberOfQuestionsPerRound*2) {
                currentPlayersTurn = player1;

            }
        }
    }





}
