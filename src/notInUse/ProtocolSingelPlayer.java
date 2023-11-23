package notInUse;

import SinglePplayer.Player;

public class ProtocolSingelPlayer {
    private Player player1;
    private Player player2;
    private final String WAIT = "WAIT";
    private final String MENU = "MENU";
    private final String CATEGORY = "CATEGORY";
    private final String QUESTIONS = "QUESTIONS";
    private final String SCORE = "SCORE";
    private final String WINNER = "WINNER";
    private final String LOOSER = "LOOSER";
    private String STATE = MENU;
    private int questionQuantity = 2;
    private int answeredQuestions = 0;
    private int rounds = 2;
    private int roundsPlayed = 0;
    public ProtocolSingelPlayer(Player player1){
        this.player1 = player1;
    }

    public Object processInput(Object objectFromClient)  {
        System.out.println("String from client in protokoll: " + objectFromClient.toString());
        System.out.println("protkoll state: " + STATE);
        Object returnObject = objectFromClient;
        if (STATE == MENU) {
            STATE = CATEGORY;
            return CATEGORY;
        }else if (STATE == CATEGORY) {
            STATE = QUESTIONS;
            return QUESTIONS;
        } else if (STATE == QUESTIONS) {
            STATE = SCORE;
            return MENU;
        }

//        else if (STATE == SCORE) {
//            STATE = WINNER;
//            return WINNER;
//        } else if (STATE == WINNER) {
//            STATE = LOOSER;
//            return LOOSER;
//        } else if (STATE == LOOSER) {
//            STATE = MENU;
//            return MENU;
//        }
//        else if (Objects.equals(STATE, WAIT)) {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            STATE = CATEGORY;
//            return CATEGORY;
//        }
        return returnObject;
    }
}
