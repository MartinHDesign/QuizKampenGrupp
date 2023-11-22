package SinglePplayer.Panel;

import javax.swing.*;
import java.awt.*;

public class QUESTIONS extends JPanel {
    JLabel questionFromServer = new JLabel("Vad börjar på m och slutar på artin");
    JPanel buttonPanel = new JPanel();
    JPanel questionsAndButtons = new JPanel();
    JButton answer1 = new JButton("spelare 2 till MENU");
    JButton answer2 = new JButton("spelare 2 till CATEGORY");
    JButton answer3 = new JButton("answer1");
    JButton answer4 = new JButton("answer1");

//    JProgressBar timeToAnswer = new JProgressBar();
    JLabel timeToAnswer = new JLabel("Här kommer en progressbar på 10 sec");
    JButton exit = new JButton("Spelare 2 till CATEGORY");
    Client.Panel.MasterFrame masterFrame;
    public QUESTIONS(MasterFrame masterFrame){
        /*
        1. svara på fråga, spara svar
        2. om det finns fler frågor svara, spara svar
        3. skicka svaren till servern
        4. visa score screen
         */
        setLayout(new BorderLayout());

        questionFromServer.setSize(new Dimension(500,250));

        buttonPanel.setLayout(new GridLayout(2,2));
        buttonPanel.setSize(new Dimension(500,230));
        answer1.addActionListener(e -> {
            masterFrame.sendToServer("MENU");
//            String temp = masterFrame.sendMessage("score");
//            System.out.println("LOGIN");
//            masterFrame.showPage(temp);
        });
        answer2.addActionListener(e -> {
            masterFrame.sendToServer("CATEGORY");
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
