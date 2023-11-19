package Server;

import Server.DataBase.HistoryDAO;
import Server.DataBase.Player;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class GameStateWriter {

    Socket player1Socket;
    Socket player2Socket;

    Player player1;
    Player player2;

    private final HistoryDAO historyQuestions = new HistoryDAO();

    public GameStateWriter(Socket player1Socket, Socket player2Socket, Player player1, Player player2) {
        this.player1Socket = player1Socket;
        this.player2Socket = player2Socket;
        this.player1 = player1;
        this.player2 = player2;
    }

    public void chooseCategory(int player) throws IOException {


        if (player == 1) {

        }
        // send categories to client 2

    }

    public void sendQuestions() throws IOException {

    }

    public void sendEndOfRoundScore() {

    }

    public void sendOpponentAnswer() {

    }

}

