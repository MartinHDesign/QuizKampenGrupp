package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {
    //lyssnar efter spelare
    public ServerListener() {
        int port = 6666;


        try {
           ServerSocket serverSocket = new ServerSocket(port);

           while (true) {

               Socket player1 = serverSocket.accept();
               System.out.println("Player 1 connected");
               Socket player2 = serverSocket.accept();
               System.out.println("Player 2 connected");

               GameStateWriter gameStateWriter = new GameStateWriter(player1,player2);

               ServerProtocol player1Protocol = new ServerProtocol(gameStateWriter);
               ServerProtocol player2Protocol = new ServerProtocol(gameStateWriter);

               ServerSideGame gamePlayer1 = new ServerSideGame(player1, gameStateWriter,player1Protocol);
               ServerSideGame gamePlayer2 = new ServerSideGame(player2, gameStateWriter, player2Protocol);

               gamePlayer1.setOpponentPlayer(gamePlayer2);
               gamePlayer2.setOpponentPlayer(gamePlayer1);

           }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    public static void main(String[] args) {

    }
}
