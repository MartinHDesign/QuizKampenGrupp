package Server;

import java.net.Socket;
import java.util.List;

public class GameStateWriter {

    List<Socket> listOfPLayers;

    public GameStateWriter(Socket player1, Socket player2) {
        this.listOfPLayers.add(player1);
        this.listOfPLayers.add(player2);
    }
}
