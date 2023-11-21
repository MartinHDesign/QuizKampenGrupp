package Server;

import Server.DataBase.HistoryDAO;
import Server.DataBase.Player;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class GameStateWriter {

    ObjectOutputStream outputStreamToPlayer1;
    ObjectOutputStream outputStreamToPlayer2;
    Player player1;
    Player player2;
    private final HistoryDAO historyQuestions = new HistoryDAO();

    public GameStateWriter(Socket player1Socket, Socket player2Socket, Player player1, Player player2) throws IOException {
        this.outputStreamToPlayer1 = new ObjectOutputStream(player1Socket.getOutputStream());
        this.outputStreamToPlayer2 = new ObjectOutputStream(player2Socket.getOutputStream());
        this.player1 = player1;
        this.player2 = player2;
    }

    public void chooseCategory(Player player) throws IOException {

        if (player.equals(player1)) {
            outputStreamToPlayer1.writeObject(1); //Här kan vi skicka valfritt objekt så länge clienten vet hur den ska hanteras, vi kollar på det
        } else if (player.equals(player2)) {
            outputStreamToPlayer2.writeObject(1);
        }
    }

    public void sendQuestions() throws IOException {

    }

    public void sendEndOfRoundScore() {
        try {
            outputStreamToPlayer1.writeObject(player2.getScore());
            outputStreamToPlayer2.writeObject(player1.getScore());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendOpponentAnswer() {

    }

}

