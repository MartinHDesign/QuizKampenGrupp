package Server;

import Server.DataBase.Player;

import java.io.IOException;

public class ServerProtocol {
    private final GameStateWriter gameStateWriter;
    private final int NEWGAME = 0;
    private final int WAITING = 1;
    private int numberOfQuestionsAnswered;
    private final int SHOWENDOFROUND = 3;

    private int numberOfPlayerAnswers;

    private int STATE = NEWGAME;

    public ServerProtocol(GameStateWriter gameStateWriter) {
        this.gameStateWriter = gameStateWriter;
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



    public void gameProcess(boolean correctAnswerFromClient, Player player) throws IOException {
        if (numberOfQuestionsAnswered == 2) {
            STATE = SHOWENDOFROUND;
        }
        switch (STATE) {
            case 0 -> {
                {
                    gameStateWriter.sendQuestions();
                    gameStateWriter.chooseCategory(1);
                    STATE = WAITING;
                }
            }
            case 1 -> {
                gameStateWriter.sendQuestions();
            }

            case 2 -> {
                if (correctAnswerFromClient) {
                    player.gainOnePoint();
                }
                this.numberOfQuestionsAnswered++;
                gameStateWriter.sendOpponentAnswer();
                STATE = WAITING;
            }

            case 3 -> {
                gameStateWriter.sendEndOfRoundScore();
                STATE = WAITING;
            }
        }


    }



}
