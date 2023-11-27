package SinglePplayer.Panel;

import javax.swing.*;
import java.awt.*;

public class QUESTIONS extends JPanel {
    JLabel questionFromServer = new JLabel("Här bör frågan dyka upp");
    JPanel buttonPanel = new JPanel();
    JPanel questionsAndButtons = new JPanel();
    private boolean correctAnswer;
    protected QuestionButton answer1 = new QuestionButton();
    protected QuestionButton answer2 = new QuestionButton();
    protected QuestionButton answer3 = new QuestionButton();
    protected QuestionButton answer4 = new QuestionButton();

    //    JProgressBar timeToAnswer = new JProgressBar();
    JLabel timeToAnswer = new JLabel("Här kommer en progressbar på 10 sec");
    MasterFrame masterFrame;


    private JLayeredPane layeredPane;
    private JPanel popupPanel;

    public QUESTIONS(MasterFrame masterFrame) {

        this.masterFrame = masterFrame;
        setLayout(new BorderLayout());

        questionFromServer.setSize(new Dimension(500, 250));

        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));
        buttonPanel.setSize(new Dimension(500, 230));

        answer1.addActionListener(l -> {
            this.correctAnswer = answer1.getisCorrect();
            setColorAndShowPopup(answer1);


        });
        answer2.addActionListener(l -> {
            this.correctAnswer = answer2.getisCorrect();
            setColorAndShowPopup(answer2);


        });
        answer3.addActionListener(l -> {
            this.correctAnswer = answer3.getisCorrect();
            setColorAndShowPopup(answer3);

        });
        answer4.addActionListener(l -> {
            this.correctAnswer = answer4.getisCorrect();
            setColorAndShowPopup(answer4);

        });

        buttonPanel.add(answer1);
        buttonPanel.add(answer2);
        buttonPanel.add(answer3);
        buttonPanel.add(answer4);

        questionsAndButtons.setLayout(new GridLayout(2, 1));
        questionsAndButtons.add(questionFromServer);
        questionsAndButtons.add(buttonPanel);


        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(300, 300)); // Set this according to your needs
        initPopupPanel();

        questionsAndButtons.setBounds(0, 0, 500, 480);
        popupPanel.setBounds(100, 100, 300, 200);

        layeredPane.add(questionsAndButtons, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(popupPanel, JLayeredPane.PALETTE_LAYER);

        add(layeredPane, BorderLayout.CENTER);
        add(timeToAnswer, BorderLayout.SOUTH);
    }

    private void initPopupPanel() {
        popupPanel = new JPanel(new BorderLayout());
        int popupWidth = 100;
        int popupHeight = 100;
        popupPanel.setSize(popupWidth, popupHeight);

        JButton nextQuestion = new JButton("Nästa fråga");
        nextQuestion.setSize(popupWidth, popupHeight);
        nextQuestion.addActionListener(e -> {
            togglePopupVisibility(false);
            setButtonsToDefaultColor();
            masterFrame.sendToServer(correctAnswer);

        });

        popupPanel.add(nextQuestion, BorderLayout.CENTER);
        popupPanel.setVisible(false);

        int x = (layeredPane.getWidth() - popupWidth) / 2;
        int y = (layeredPane.getHeight() - popupHeight) / 2;
        popupPanel.setBounds(x, y, popupWidth, popupHeight);
    }

    private void togglePopupVisibility(boolean visible) {
        popupPanel.setVisible(visible);
        if (visible) {
            layeredPane.moveToFront(popupPanel);
        } else {
            layeredPane.moveToBack(popupPanel);
        }
    }

    public void setButtonsToDefaultColor() {
        answer1.setBackground(UIManager.getColor("Button.background"));
        answer2.setBackground(UIManager.getColor("Button.background"));
        answer3.setBackground(UIManager.getColor("Button.background"));
        answer4.setBackground(UIManager.getColor("Button.background"));

    }

    public void setColorBasedOnCorrectAnswer(QuestionButton questionButton, boolean isCorrectAnswer) {
        questionButton.setOpaque(true);

        if (!isCorrectAnswer) {
            questionButton.setBackground(Color.red);

            return;
        }
        questionButton.setBackground(Color.green);

    }

    public void setColorAndShowPopup(QuestionButton button) {
        setColorBasedOnCorrectAnswer(button, button.getisCorrect());
        togglePopupVisibility(true);
    }
}



