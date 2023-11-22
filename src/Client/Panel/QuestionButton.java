package Client.Panel;

import javax.swing.*;


public class QuestionButton extends JButton {

    private boolean isCorrect;
    private int index;

    public QuestionButton(String text, boolean isCorrect, int index) {
        super(text);
        this.isCorrect = isCorrect;
        this.index = index;
    }

}


