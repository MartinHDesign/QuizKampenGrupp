package SinglePplayer;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewGameHandler extends Thread {
    ExecutorService readPlayersInput = Executors.newFixedThreadPool(2);
    Player player1;
    Player player2;
    public NewGameHandler(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
    }
    public void run(){
        System.out.println("från newGameHandler");
        readPlayersInput.execute(() -> {
            while (true) {
                try {
                    Object fromPlayers1 = player1.in.readObject();
                    if (fromPlayers1 != null) {
                        player2.out.writeObject(fromPlayers1);
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    break; // Break the loop on exception
                }
            }
        });
        readPlayersInput.execute(() -> {
            while (true) {
                try {
                    Object fromPlayers2 = player2.in.readObject();
                    if (fromPlayers2 != null) {
                        player1.out.writeObject(fromPlayers2);
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
        });

//            Object fromPlayers1;
//            Object fromPlayers2;
//            while ((fromPlayers1 = player1.in.readObject()) != null && (fromPlayers2 = player2.in.readObject()) != null ) {
//                if (fromPlayers2 != null){
//                    player1.out.writeObject(fromPlayers2);
//                }
//                if (fromPlayers1 != null){
//                    player2.out.writeObject(fromPlayers1);
//                }
//
//

//                Object fromPlayer1 = player1.in.readObject();
//                player2.out.writeObject(fromPlayer1);
//                System.out.println("player 1: " + fromPlayer1);
//
//                Object fromPlayer2 = player2.in.readObject();
//                System.out.println("player 2: " + fromPlayer2);
//                player1.out.writeObject(fromPlayer2);
//            }
    }
}
