package Client.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuestionScreen extends JPanel implements ActionListener {
    private static QuestionScreen instance;
    JLabel questionFromServer = new JLabel("Vad börjar på m och slutar på artin");
    JPanel buttonPanel = new JPanel();
    JPanel questionsAndButtons = new JPanel();
    QuestionButton answer1 = new QuestionButton();
    QuestionButton answer2 = new QuestionButton();
    QuestionButton answer3 = new QuestionButton();
    QuestionButton answer4 = new QuestionButton();
//    JProgressBar timeToAnswer = new JProgressBar();
    JLabel timeToAnswer = new JLabel("Här kommer en progressbar på 10 sec");
    JButton exit = new JButton("Ge upp");
    MasterFrame masterFrame;
    public QuestionScreen(MasterFrame masterFrame){
        this.masterFrame = masterFrame;
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
        buttonPanel.add(answer1); buttonPanel.add(answer2); buttonPanel.add(answer3); buttonPanel.add(answer4);


        questionsAndButtons.setLayout(new GridLayout(2,1));
        questionsAndButtons.add(questionFromServer);
        questionsAndButtons.add(buttonPanel);

        exit.setSize(new Dimension(500,10));
        exit.addActionListener(e -> masterFrame.showPage("2"));

        add(exit, BorderLayout.NORTH);
        add(questionsAndButtons, BorderLayout.CENTER);
        add(timeToAnswer, BorderLayout.SOUTH);
    }


    public static QuestionScreen getInstance() {
        return instance;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
