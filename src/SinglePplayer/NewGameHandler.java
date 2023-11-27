package SinglePplayer;

import Server.ServerResponse;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewGameHandler extends Thread {
    ExecutorService readPlayersInput = Executors.newFixedThreadPool(2);
    Player player1;
    Player player2;

    public NewGameHandler(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void run() {
        System.out.println("från newGameHandler");
        ServerProtocol protocol = new ServerProtocol(player1, player2);
        GameProcess gameProcess = new GameProcess(player1,player2,protocol);


        try {
            player1.out.writeObject(new ServerResponse("CATEGORY"));
            player2.out.writeObject(new ServerResponse("WAIT"));


            while (true) {

                gameProcess.play();

            }






        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //startListningForInputFromBothClient();

    }

    // kod som eventuellt inte kommer användas sen eller måste skrivas om ->
    private void startListningForInputFromBothClient() {
        pairPlayers(player1, player2);
        pairPlayers(player2, player1);
    }

    private void pairPlayers(Player user1, Player user2) {
        readPlayersInput.execute(() -> {
            while (true) {
                try {
                    Object fromPlayers1 = user1.in.readObject();

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    break;
                }
            }
        });
    }
    // <-

}
