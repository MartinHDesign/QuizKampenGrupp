package SinglePplayer.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QUESTIONS extends JPanel {
    JLabel questionFromServer = new JLabel("Här bör frågan dyka upp");
    JPanel buttonPanel = new JPanel();
    JPanel questionsAndButtons = new JPanel();
    protected QuestionButton answer1 = new QuestionButton();
    protected QuestionButton answer2 = new QuestionButton();
    protected QuestionButton answer3 = new QuestionButton();
    protected QuestionButton answer4 = new QuestionButton();

    //    JProgressBar timeToAnswer = new JProgressBar();
    JLabel timeToAnswer = new JLabel("Här kommer en progressbar på 10 sec");
    JButton exit = new JButton("Spelare 2 till CATEGORY");
    MasterFrame masterFrame;

    public QUESTIONS(MasterFrame masterFrame) {
        /*
        1. svara på fråga, spara svar
        2. om det finns fler frågor svara, spara svar
        3. skicka svaren till servern
        4. visa score screen
         */
        setLayout(new BorderLayout());

        questionFromServer.setSize(new Dimension(500, 250));

        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));
        buttonPanel.setSize(new Dimension(500, 230));

        answer1.addActionListener(l -> {
            setColorBasedOnCorrectAnswer(answer1, answer1.isCorrect());
            showPopup();
            masterFrame.sendToServer(answer1.isCorrect());

        });
        answer2.addActionListener(l -> {
            setColorBasedOnCorrectAnswer(answer2, answer2.isCorrect());
            showPopup();
            masterFrame.sendToServer(answer2.isCorrect());

        });
        answer3.addActionListener(l -> {
            setColorBasedOnCorrectAnswer(answer3, answer3.isCorrect());
            showPopup();
            masterFrame.sendToServer(answer3.isCorrect());

        });
        answer4.addActionListener(l -> {
            setColorBasedOnCorrectAnswer(answer4, answer4.isCorrect());
            showPopup();
            masterFrame.sendToServer(answer4.isCorrect());

        });

        buttonPanel.add(answer1);
        buttonPanel.add(answer2);
        buttonPanel.add(answer3);
        buttonPanel.add(answer4);

        questionsAndButtons.setLayout(new GridLayout(2, 1));
        questionsAndButtons.add(questionFromServer);
        questionsAndButtons.add(buttonPanel);

        exit.setSize(new Dimension(500, 10));

        add(exit, BorderLayout.NORTH);
        add(questionsAndButtons, BorderLayout.CENTER);
        add(timeToAnswer, BorderLayout.SOUTH);
    }

    private void showPopup() {
        // Create a modal JDialog
        JDialog popupDialog = new JDialog(masterFrame, true);
        popupDialog.setLayout(new FlowLayout());
        popupDialog.setModal(true);

        JButton newButton = new JButton("Nästa fråga");
        newButton.addActionListener(e -> {

            popupDialog.dispose();
            setButtonsToDefaultColor();

        });

        popupDialog.add(newButton);
        popupDialog.setSize(300, 200);
        popupDialog.setLocationRelativeTo(masterFrame);
        popupDialog.setVisible(true);
    }

    public void setButtonsToDefaultColor() {
        answer1.setBackground(null);
        answer2.setBackground(null);
        answer3.setBackground(null);
        answer4.setBackground(null);

    }

    public void setColorBasedOnCorrectAnswer(QuestionButton questionButton, boolean isCorrectAnswer) {

        if (!isCorrectAnswer) {
           questionButton.setBackground(Color.red);
           return;
        }
        questionButton.setBackground(Color.green);


    }
}



