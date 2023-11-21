package Client;

import java.io.IOException;
import java.io.ObjectInputStream;


public class ClientProtocol {

    ObjectInputStream objectInputStream;
    private final int NEWGAME = 0;
    private final int DISPLAYANSWERS = 1;
    private final int SHOWENDOFROUND = 2;
    private final int ENDOFGAME = 3;
    private int numberOfPlayerAnswers;



    private int STATE = NEWGAME;

    public ClientProtocol(ObjectInputStream objectInputStream) {
        this.objectInputStream = objectInputStream;
    }
    //Pseudocode below, sout to be replaced with appropriate methods.
    public void gameProcess () throws IOException {

        if (numberOfPlayerAnswers == 2) {
            STATE = SHOWENDOFROUND;
        }
        switch (STATE) {
            case 0 -> {
                System.out.println("Receive questions and display question and answers from server");
                STATE = DISPLAYANSWERS;
            }
            case 1 -> System.out.println("Display answers received from server");
            case 2 -> {
                System.out.println("Display points for both players");
                STATE = DISPLAYANSWERS;
            }

        }
    }
}
