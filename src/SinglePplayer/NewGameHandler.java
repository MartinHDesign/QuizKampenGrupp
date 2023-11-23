package SinglePplayer;

import Server.DataBase.HistoryQuestions.HistoryQuestion;
import Server.ServerResponse;
import SinglePplayer.Panel.SCORE;

import java.io.IOException;
import java.io.ObjectOutputStream;
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
        ProtocolREPLACEWithNewCode protocol = new ProtocolREPLACEWithNewCode(player1, player2);


        try {
            player2.out.writeObject(new ServerResponse("WAIT"));

            int rounds = 0;
            Player currentPlayer = player1;
            while(true) {
                    switch (rounds) {
                        case 3 -> currentPlayer = player2;

                        case 5 ->{
                            Object objectFromPlayer = currentPlayer.in.readObject();
                            Object objectToReturnToPlayer = protocol.processInput(objectFromPlayer, currentPlayer);
                            currentPlayer.out.writeObject(objectToReturnToPlayer);
                            currentPlayer = player1;
                            currentPlayer.out.writeObject(objectToReturnToPlayer);
                            rounds = 0;
                        }
                    }


                Object objectFromPlayer = currentPlayer.in.readObject();
                Object categoryChosen = objectFromPlayer;
                System.out.println(objectFromPlayer);

                Object objectToReturnToPlayer = protocol.processInput(objectFromPlayer, currentPlayer);
                currentPlayer.out.writeObject(objectToReturnToPlayer);
                rounds++;

            }
/*
            Object objectFromPlayer = player1.in.readObject();
            System.out.println(objectFromPlayer);
            Object categoryChosen = objectFromPlayer;


            Object objectToReturnToPlayer = protocol.processInput(objectFromPlayer, player1);
            player1.out.writeObject(objectToReturnToPlayer);
            ServerResponse test = (ServerResponse) objectToReturnToPlayer;
            objectFromPlayer = player1.in.readObject();
            System.out.println(objectFromPlayer);

            objectToReturnToPlayer = protocol.processInput(objectFromPlayer, player1);
            player1.out.writeObject(objectToReturnToPlayer);

            objectFromPlayer = player1.in.readObject();
            objectToReturnToPlayer = protocol.processInput(objectFromPlayer, player1);
            player1.out.writeObject(objectToReturnToPlayer);

            objectToReturnToPlayer = protocol.processInput(categoryChosen, player2);
            test = (ServerResponse) objectToReturnToPlayer;

            System.out.println(test.getQuestion());
            player2.out.writeObject(objectToReturnToPlayer);

            objectFromPlayer = player2.in.readObject();

            objectToReturnToPlayer = protocol.processInput(objectFromPlayer, player2);
            test = (ServerResponse) objectToReturnToPlayer;

            System.out.println(test.getQuestion());
            player2.out.writeObject(objectToReturnToPlayer);

            objectFromPlayer = player2.in.readObject();

            objectToReturnToPlayer = protocol.processInput(objectFromPlayer, player2);

            objectFromPlayer = player2.in.readObject();

            objectToReturnToPlayer = protocol.processInput(objectFromPlayer, player2);

            test = (ServerResponse) objectToReturnToPlayer;

            System.out.println(test.getShowGUIPanel());

            player2.out.writeObject(new ServerResponse("SCORE"));
            player1.out.writeObject(new ServerResponse("SCORE"));

*/



        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
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
