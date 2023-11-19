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

    public void chooseCategory(int player) throws IOException {

        // send categories to client 2

    }

    public void sendQuestions() throws IOException {

    }

    public void sendEndOfRoundScore() {

    }

    public void sendOpponentAnswer() {

    }

}

