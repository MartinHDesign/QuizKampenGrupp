package SinglePplayer;

import java.io.IOException;

public class NewGameHandler extends Thread {
    Player player1;
    Player player2;
    public NewGameHandler(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
    }
    public void run(){
        try {
            System.out.println("från newGameHandler");
            while (true){

            Object fromPlayer1 = player1.in.readObject();
            player2.out.writeObject(fromPlayer1);
            System.out.println(fromPlayer1.toString());
            Object fromPlayer2 = player2.in.readObject();
            System.out.println(fromPlayer2);
            player1.out.writeObject(fromPlayer2);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
