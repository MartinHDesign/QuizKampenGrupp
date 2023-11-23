package SinglePplayer.Panel;

import javax.swing.*;


public class QuestionButton extends JButton {

    private boolean isCorrect;

    public QuestionButton (String text,boolean isCorrect ) {
        super(text);
        this.isCorrect = isCorrect;

    }


    public QuestionButton() {
        this.isCorrect = false;

    }

    public void setIsCorrect(boolean input) {
        this.isCorrect = input;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}


