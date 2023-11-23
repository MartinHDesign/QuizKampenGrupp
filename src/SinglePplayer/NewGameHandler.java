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
        //Här kommer ett protokoll för spelets gång
        startListningForInputFromBothClient();
    }
    private void startListningForInputFromBothClient(){
        pairPlayers(player1,player2);
        pairPlayers(player2,player1);
    }
    private void pairPlayers(Player user1, Player user2){
        readPlayersInput.execute(() -> {
            while (true) {
                try {
                    Object fromPlayers1 = user1.in.readObject();
                    if (fromPlayers1 != null) {
                        user2.out.writeObject(fromPlayers1);
                    } else {
                        break;
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    break;
                }
            }
        });
    }

}
