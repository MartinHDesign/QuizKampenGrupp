package Server;

import Server.DataBase.Player;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerSideGame extends Thread {
//
//    Player opponentPlayer;
//
//    private final Player player;
//
//    ServerProtocol protocol;
//
//    Socket playerSocket;
//
//    public ServerSideGame(Player player, Socket playerSocket, GameStateWriter gameStateWriter, ServerProtocol protocol) {
//        this.protocol = protocol;
//        this.player = player;
//        this.playerSocket = playerSocket;
//
//
//    }
//
//    public void setOpponentPlayer(Player opponentPlayer) {
//        this.opponentPlayer = opponentPlayer;
//    }
//
//    public void run() {
//
//        try {
//            ObjectInputStream objectsFromClient = new ObjectInputStream(playerSocket.getInputStream());
//            System.out.println("Socket bound");
//
//
//
//
//            while (true) {
//                Object clientAnswer = objectsFromClient.readObject();
//                System.out.println("Object received");
//
//
//                    protocol.gameProcess(clientAnswer, player);
//
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
}


