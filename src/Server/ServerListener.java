package Server;

import Server.DataBase.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {
    //lyssnar efter spelare
    public ServerListener() {
        int port = 6666;

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
           while (true) {

               Socket player1Socket = serverSocket.accept();
               System.out.println("Player 1 connected");
               Socket player2Socket = serverSocket.accept();
               System.out.println("Player 2 connected");

               Player player1 = new Player();
               Player player2 = new Player();

               GameStateWriter gameStateWriter = new GameStateWriter(player1Socket,player2Socket);

               ServerProtocol player1Protocol = new ServerProtocol(gameStateWriter);
               ServerProtocol player2Protocol = new ServerProtocol(gameStateWriter);

               ServerSideGame gamePlayer1 = new ServerSideGame(player1, gameStateWriter,player1Protocol);
               ServerSideGame gamePlayer2 = new ServerSideGame(player2, gameStateWriter, player2Protocol);

               gamePlayer1.setOpponentPlayer(player2);
               gamePlayer2.setOpponentPlayer(player1);

           }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public static void main(String[] args) {
        ServerListener listener = new ServerListener();
    }
}
