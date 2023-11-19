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

               GameStateWriter gameStateWriter = new GameStateWriter(player1Socket,player2Socket, player1, player2);

               ServerProtocol protocol = new ServerProtocol(gameStateWriter);


               ServerSideGame gameServer1 = new ServerSideGame(player1,player1Socket, gameStateWriter,protocol);
               ServerSideGame gameServer2 = new ServerSideGame(player2,player2Socket, gameStateWriter, protocol);

               gameServer1.setOpponentPlayer(player2);
               gameServer2.setOpponentPlayer(player1);

               gameServer1.start();
               System.out.println("Thread 1 started");
               gameServer2.start();
               System.out.println("Thread 2 started");

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
