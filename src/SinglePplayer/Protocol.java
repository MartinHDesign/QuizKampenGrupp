package SinglePplayer;

public class Protocol {
    private final String WAIT = "WAIT";
    private final String LOGIN = "LOGIN";
    private final String MENU = "MENU";
    private final String CATEGORY = "CATEGORY";
    private final String QUESTIONS = "QUESTIONS";
    private final String SCORE = "SCORE";
    private final String WINNER = "WINNER";
    private final String LOOSER = "LOOSER";
    private String STATE = LOGIN;
    private int questionQuantity = 2;
    private int answeredQuestions = 0;
    private int rounds = 2;
    private int roundsPlayed = 0;

    public Object processInput(Object objectFromClient)  {
        Object returnObject = null;
        if (STATE == LOGIN){
            STATE = MENU;
            return MENU;
        } else if (STATE == MENU) {
            STATE = CATEGORY;
            return CATEGORY;
        } else if (STATE == CATEGORY) {
            STATE = QUESTIONS;
            return QUESTIONS;
        } else if (STATE == QUESTIONS) {
            STATE = SCORE;
            return SCORE;
        } else if (STATE == SCORE) {
            STATE = WINNER;
            return WINNER;
        } else if (STATE == WINNER) {
            STATE = LOOSER;
            return LOOSER;
        } else if (STATE == LOOSER) {
            STATE = MENU;
            return MENU;
        } else if (STATE == WAIT) {
            STATE = CATEGORY;
            return CATEGORY;
        }
        return returnObject;
    }
}
