package Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class GameStateWriter {

    Socket player1Socket;
    Socket player2Socket;

    int player1Score;
    int player2Score;

    public GameStateWriter(Socket player1Socket, Socket player2Socket) {
        this.player1Socket = player1Socket;
        this.player2Socket = player2Socket;
    }

    public void chooseCategory(int player) throws IOException {


        if (player == 1) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(player1Socket.getOutputStream())){
                //send categories to client1
            }
        }
        // send categories to client 2

    }

    public void sendQuestions() throws IOException {
        try (ObjectOutputStream player1Stream = new ObjectOutputStream(player1Socket.getOutputStream());
        ObjectOutputStream player2Stream = new ObjectOutputStream(player1Socket.getOutputStream())){
            //send questions
        }
    }

    public void sendEndOfRoundScore(){

    }

    public void sendOpponentAnswer() {

    }

    //Här kan kanske man istället kan ha en statisk spelare som skickas in via ServerListener
    //Och sedan plussa på Poängen direkt på spelaren
    public void player1Scored() {
        player1Score++;
    }

    public void player2Scored() {
        player1Score++;
    }
}
