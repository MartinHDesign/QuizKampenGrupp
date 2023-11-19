package Panel;

import javax.swing.*;
import java.awt.*;

public class QuestionScreen extends JPanel {
    JLabel questionFromServer = new JLabel("Vad börjar på m och slutar på artin");
    JPanel buttonPanel = new JPanel();
    JPanel questionsAndButtons = new JPanel();
    JButton answer1 = new JButton("Svar1");
    JButton answer2 = new JButton("Svar2");
    JButton answer3 = new JButton("Svar3");
    JButton answer4 = new JButton("Svar4");
//    JProgressBar timeToAnswer = new JProgressBar();
    JLabel timeToAnswer = new JLabel("Här kommer en progressbar på 10 sec");
    JButton exit = new JButton("Ge upp");
    public QuestionScreen(MasterFrame masterFrame){
        setLayout(new BorderLayout());

        questionFromServer.setSize(new Dimension(500,250));

        buttonPanel.setLayout(new GridLayout(2,2));
        buttonPanel.setSize(new Dimension(500,230));
        buttonPanel.add(answer1); buttonPanel.add(answer2); buttonPanel.add(answer3); buttonPanel.add(answer4);

        timeToAnswer.setBackground(Color.blue);

        questionsAndButtons.setLayout(new GridLayout(2,1));
        questionsAndButtons.add(questionFromServer);
        questionsAndButtons.add(buttonPanel);

        exit.setSize(new Dimension(500,10));
        exit.addActionListener(e -> masterFrame.showPage("2"));

        add(exit, BorderLayout.NORTH);
        add(questionsAndButtons, BorderLayout.CENTER);
        add(timeToAnswer, BorderLayout.SOUTH);
    }
}
