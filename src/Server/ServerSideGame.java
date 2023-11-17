package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerSideGame extends Thread{

    ServerSideGame opponentPlayer;

    public ServerSideGame(Socket player, GameStateWriter gameStateWriter) {

    }

    public void setOpponentPlayer(ServerSideGame opponentPlayer) {
        this.opponentPlayer = opponentPlayer;
    }

    public void run() {
        int port = 6666;
        String IP = "127.0.0.1";

        try(Socket socketToClient = new Socket(IP,port);
            ObjectInputStream objectsFromClient = new ObjectInputStream(socketToClient.getInputStream()))
         {


        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
