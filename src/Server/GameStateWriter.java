package Server;

import Server.DataBase.CategoryDAO;
import Server.DataBase.HistoryDAO;
import Server.DataBase.Player;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class GameStateWriter {

    ObjectOutputStream outputStreamToPlayer1;
    ObjectOutputStream outputStreamToPlayer2;
    Player player1;
    Player player2;
    private final HistoryDAO historyQuestions = new HistoryDAO();

    private CategoryDAO currentCategory;

    public GameStateWriter(Socket player1Socket, Socket player2Socket, Player player1, Player player2) throws IOException {
        this.outputStreamToPlayer1 = new ObjectOutputStream(player1Socket.getOutputStream());
        this.outputStreamToPlayer2 = new ObjectOutputStream(player2Socket.getOutputStream());
        this.player1 = player1;
        this.player2 = player2;
    }

    public void chooseCategory(Player player) throws IOException {

        System.out.println("Trying to send category");

        if (player.getName().equalsIgnoreCase(player1.getName())) {
            outputStreamToPlayer1.writeObject(new ServerResponse("Historia"));
            System.out.println("Category sent to" + player1.getName());
            //Här kan vi skicka valfritt objekt så länge clienten vet hur den ska hanteras, vi kollar på det

        } else if (player.equals(player2)) {
            outputStreamToPlayer2.writeObject(new ServerResponse("Historia"));

        }
    }

    public void setCurrentCategory(String category) {
        System.out.println("trying to set Category");
        if (category.equals("0")) {
            currentCategory = historyQuestions;
        }
        //sätter current category baserat på svar från klienten
    }

    public void sendQuestions(Player player) throws IOException {

    }

    public void sendEndOfRoundScore() {
        try {
            outputStreamToPlayer1.writeObject(new ServerResponse(player2.getScore()));
            outputStreamToPlayer2.writeObject(new ServerResponse(player1.getScore()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendOpponentAnswer() {

    }

    public void endOfGame(){
        try {
            outputStreamToPlayer1.writeObject(new ServerResponse(player2.getScore(), 1));
            outputStreamToPlayer2.writeObject(new ServerResponse(player1.getScore(), 1));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}

