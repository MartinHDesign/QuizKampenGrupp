package SinglePplayer;

import Server.DataBase.HistoryDAO;
import Server.ServerResponse;

import java.io.IOException;
import java.net.Socket;

public class GameProcess {

    private Player player1;
    private Player player2;
    int player1score;
    int player2score;

    Player currentPlayer;

    private final int numberOfQuestions = 2; //properties
    private int questionsAnswered;

    private boolean playerSwapped;

    ServerProtocol protocol;

    public GameProcess(Player player1, Player player2, ServerProtocol protocol) {

        this.player1 = player1;
        this.player2 = player2;
        this.protocol = protocol;
        this.currentPlayer = player1;

    }

    public void play() throws IOException, ClassNotFoundException, InterruptedException {
        System.out.println("här 1");
        HistoryDAO hd = new HistoryDAO();
        Object objectFromClient;


        // loopar antal rundor
        for (int rundor = 0; rundor < 1; rundor++) {
            //p1 skickar vald kategori
            objectFromClient = currentPlayer.in.readObject();
            //loopar för antal frågor
            for (int questions = 0; questions < 2; questions++) {
                //serv skickar ny fråga till p1
                currentPlayer.out.writeObject(new ServerResponse(hd.getHistoryQuestions().get(questions)));
                currentPlayer.out.writeObject(new ServerResponse("QUESTIONS"));
                //får svaret från p1
                objectFromClient = currentPlayer.in.readObject();
                //+ poäng om rätt svar
                if (objectFromClient.equals(true)){
                    System.out.println(player1score);
                    player1score++;
                    System.out.println(player1score);
                }
            }
            System.out.println("p1 svarat på 2 frågor");
            //p1 till score sidan
//            currentPlayer.out.writeObject(player1score);
            System.out.println("byt p1 till score");
            currentPlayer.out.writeObject(new ServerResponse("SCORE"));
            // byt till p2
            swapCurrentPlayer(player1,player2);
            for (int questions = 0; questions < 2; questions++) {
                //serv skickar ny fråga till p2
                currentPlayer.out.writeObject(new ServerResponse(hd.getHistoryQuestions().get(questions)));
                currentPlayer.out.writeObject(new ServerResponse("QUESTIONS"));
                //får svaret från p2
                objectFromClient = currentPlayer.in.readObject();
                //+ poäng om rätt svar
            }
            System.out.println("p2 svarat på 2 frågor");

            //skicka category = byt till category sidan fär p2
            currentPlayer.out.writeObject(new ServerResponse("CATEGORY"));

            //får svar p2
            objectFromClient = currentPlayer.in.readObject();
            for (int questions = 0; questions < 2; questions++) {
                //serv skickar ny fråga till p2
                currentPlayer.out.writeObject(new ServerResponse(hd.getHistoryQuestions().get(questions)));
                currentPlayer.out.writeObject(new ServerResponse("QUESTIONS"));
                //får svaret från p2
                objectFromClient = currentPlayer.in.readObject();
                //+ poäng om rätt svar
            }
            System.out.println("p2 har valt kategori och svarat på 2 frågor");
            // p2 till score sidan med player1 poäng för runda
            currentPlayer.out.writeObject(new ServerResponse("SCORE"));
            //byt till player1
            swapCurrentPlayer(player1,player2);
            for (int questions = 0; questions < 2; questions++) {
                //serv skickar nya frågor till p1
                currentPlayer.out.writeObject(new ServerResponse(hd.getHistoryQuestions().get(questions)));
                currentPlayer.out.writeObject(new ServerResponse("QUESTIONS"));
                //får svaret från p1
                objectFromClient = currentPlayer.in.readObject();
                //+ poäng om rätt svar
            }
            System.out.println("p1 svarat på 2 frågor");
            currentPlayer.out.writeObject(new ServerResponse("CATEGORY"));
        }
        //om alla rundor är slut score screen för båda
        player1.out.writeObject(new ServerResponse("LOGIN"));
        player2.out.writeObject(new ServerResponse("LOGIN"));





//        if (objectFromClient instanceof String) {
//            //Tar en in en sträng från "Spela nästa runda"-knappen i SCORE
//            newRound();
//            return;
//        }
//        System.out.println("här 2");
//        Object objectToReturn = protocol.processInput(objectFromClient, currentPlayer);
//
//        currentPlayer.out.writeObject(objectToReturn);
//        questionsAnswered++;
//
//        if (questionsAnswered == numberOfQuestions + 1) {
//            System.out.println("här 3");
//            if (playerSwapped) {
//                System.out.println("här 4");
//                questionsAnswered = 0;
//                sendEndOfRoundResults(objectToReturn);
//                return;
//            }
//            swapCurrentPlayer(player1, player2);
//        }
    }

    private void swapCurrentPlayer(Player player1, Player player2) throws IOException {
        if (currentPlayer.equals(player1)) {
            System.out.println("changing to player 2");
            playerSwapped = true;
            currentPlayer = player2;
            questionsAnswered = 0;

        } else if (currentPlayer.equals(player2)) {
            System.out.println("changing to player 1");
            playerSwapped = true;
            currentPlayer = player1;
            questionsAnswered = 0;
        }
    }

    private void sendEndOfRoundResults(Object objectToReturn) throws IOException {
        if (currentPlayer.equals(player1)) {
            player2.out.writeObject(objectToReturn);
            playerSwapped = false;
            questionsAnswered = 0;

        } else if (currentPlayer.equals(player2)) {
            player1.out.writeObject(objectToReturn);
            playerSwapped = false;
            questionsAnswered = 0;
        }
    }

    private void newRound() throws IOException {
        if (currentPlayer.equals(player1)) {
            System.out.println("Sending new game instructions, Player1");
            player1.out.writeObject(new ServerResponse("CATEGORY"));
            player2.out.writeObject(new ServerResponse("WAIT"));

        } if (currentPlayer.equals(player2)) {
            System.out.println("Sending new game instructions, Player1");
            player1.out.writeObject(new ServerResponse("WAIT"));
            player2.out.writeObject(new ServerResponse("CATEGORY"));

        }
    }
}
