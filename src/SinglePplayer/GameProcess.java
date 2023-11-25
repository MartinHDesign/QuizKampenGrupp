package SinglePplayer;

import Server.ServerResponse;

import java.io.IOException;

public class GameProcess {

    private Player player1;
    private Player player2;

    Player currentPlayer;

    private final int numberOfQuestions = 2;
    private final int numberOfRounds = 2;
    private int roundsPlayed;

    private boolean playerSwapped;

    ProtocolREPLACEWithNewCode protocol;

    public GameProcess(Player player1, Player player2, ProtocolREPLACEWithNewCode protocol) {

        this.player1 = player1;
        this.player2 = player2;
        this.protocol = protocol;
        this.currentPlayer = player1;

    }

    public void play() throws IOException, ClassNotFoundException, InterruptedException {

        Object objectFromClient = currentPlayer.in.readObject();
        Object objectToReturn = protocol.processInput(objectFromClient, currentPlayer);
        currentPlayer.out.writeObject(objectToReturn);

        roundsPlayed++;
        System.out.println("playerSwapped = " + playerSwapped);

        if (roundsPlayed == numberOfQuestions + 1) {
            if (playerSwapped) {
                if (currentPlayer.equals(player1)) {
                    player2.out.writeObject(objectToReturn);
                    playerSwapped = false;
                    roundsPlayed = 0;
                    return;

                } else if (currentPlayer.equals(player2)) {
                    player1.out.writeObject(objectToReturn);
                    playerSwapped = false;
                    roundsPlayed = 0;
                    return;

                }

                }
            if (currentPlayer.equals(player1)) {
                System.out.println("changing to player 2");
                playerSwapped = true;
                currentPlayer = player2;
                roundsPlayed = 0;

            }
            else if (currentPlayer.equals(player2)) {
                System.out.println("changing to player 1");
                playerSwapped = true;
                currentPlayer = player1;
                roundsPlayed = 0;
            }



        }
    }
}
