package SinglePplayer.Panel;

import javax.swing.*;
import java.awt.*;

public class SCORE extends JPanel {
    private int amountOfRounds = 10;
    private JPanel p1Score = new JPanel(new GridLayout(amountOfRounds,1));
    private JPanel p2Score = new JPanel(new GridLayout(amountOfRounds,1));
    private String player1Username = "player1";
    private String player2Username = "player2";
    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;
    private String roundString = "Poäng för omgång ";
    private int player1Rounds = 1;
    private int player2Rounds = 1;

    private JLabel player1 = new JLabel("Guest score: 0");
    private JLabel player2 = new JLabel("Guest score: 0");


    private JPanel topPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JPanel northPanel = new JPanel();

    private JButton exit = new JButton("ge upp");
    private JButton player2testbutton = new JButton("p2");

    public SCORE(MasterFrame masterFrame){


        setLayout(new BorderLayout());
        player1.setHorizontalAlignment(JLabel.CENTER);
        player2.setHorizontalAlignment(JLabel.CENTER);
        topPanel.setLayout(new GridLayout(1,2));

         // från property
        centerPanel.setLayout(new GridLayout(1,2));
        centerPanel.setSize(new Dimension(500,460));

        centerPanel.add(p1Score);
        centerPanel.add(p2Score);


        northPanel.setSize(new Dimension(500, 10));
        topPanel.setSize(new Dimension(500, 40));

        topPanel.add(player1);
        topPanel.add(player2);

        exit.setSize(new Dimension(500,10));



        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.SOUTH);
    }

    public void setPlayer1Username(String username){
        this.player1Username = username;
    }
    public void setPlayer2Username(String username){
        this.player2Username = username;
    }

    public void player1ScoreThisRound(int score){
        JLabel temp = new JLabel(roundString + player1Rounds + " = " + score);
        temp.setHorizontalAlignment(JLabel.CENTER);
        temp.setVisible(true);
        scorePlayer1 += score;
        displayPlayer1TotalScore();
        player1Rounds++;
        p1Score.add(temp);
    }
    public void player2ScoreThisRound(int score){
        JLabel temp = new JLabel(roundString + player2Rounds + " = " + score);
        temp.setHorizontalAlignment(JLabel.CENTER);
        temp.setVisible(true);
        scorePlayer2 += score;
        displayPlayer2TotalScore();
        player2Rounds++;
        p2Score.add(temp);
    }
    private void displayPlayer1TotalScore() {
        player1.setText(player1Username + " totala poäng: " + scorePlayer1);
    }
    private void displayPlayer2TotalScore() {
        player2.setText(player2Username + " totala poäng: " + scorePlayer2);
    }

}
