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
    public QUESTIONS(MasterFrame masterFrame){
        /*
        1. svara på fråga, spara svar
        2. om det finns fler frågor svara, spara svar
        3. skicka svaren till servern
        4. visa score screen
         */
        setLayout(new BorderLayout());

        questionFromServer.setSize(new Dimension(500,250));

        buttonPanel.setLayout(new GridLayout(2,2,10, 10));
        buttonPanel.setSize(new Dimension(500,230));

        answer1.addActionListener(l -> {

                masterFrame.sendToServer(answer1.isCorrect());

        });
        answer2.addActionListener(l -> {

                masterFrame.sendToServer(answer2.isCorrect());

        });
        answer3.addActionListener(l -> {

                masterFrame.sendToServer(answer3.isCorrect());

        });
        answer4.addActionListener(l -> {
                masterFrame.sendToServer(answer4.isCorrect());

        });

        buttonPanel.add(answer1); buttonPanel.add(answer2); buttonPanel.add(answer3); buttonPanel.add(answer4);

        questionsAndButtons.setLayout(new GridLayout(2,1));
        questionsAndButtons.add(questionFromServer);
        questionsAndButtons.add(buttonPanel);

        exit.setSize(new Dimension(500,10));

        add(exit, BorderLayout.NORTH);
        add(questionsAndButtons, BorderLayout.CENTER);
        add(timeToAnswer, BorderLayout.SOUTH);
    }


}
