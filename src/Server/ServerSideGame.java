package Server;

import Server.DataBase.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerSideGame extends Thread{

    Player opponentPlayer;

    private final Player player;

    ServerProtocol protocol;

    public ServerSideGame(Player player, GameStateWriter gameStateWriter, ServerProtocol protocol) {
        this.protocol = protocol;
        this.player = player;

    }

    public void setOpponentPlayer(Player opponentPlayer) {
        this.opponentPlayer = opponentPlayer;
    }

    public void run() {
        int port = 6666;
        String IP = "127.0.0.1";

        try(Socket socketToClient = new Socket(IP,port);
            ObjectInputStream objectsFromClient = new ObjectInputStream(socketToClient.getInputStream()))
         {
             Object clientAnswer = objectsFromClient.readObject();

             if (clientAnswer instanceof Boolean) {

                 protocol.playerResponses((boolean)clientAnswer,this.player);
             }




        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
