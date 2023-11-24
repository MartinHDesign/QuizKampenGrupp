package SinglePplayer.Panel;

import javax.swing.*;
import java.awt.*;

public class SCORE extends JPanel {
    String player1Username = "player1";
    String player2Username = "player2";
    int scorePlayer1 = 0;
    int scorePlayer2 = 0;
    private JLabel player1 = new JLabel("player score: 6");
    private JLabel player2 = new JLabel("player score: 3");

    String roundString = "Poäng för runda ";
    private JLabel round1player1 = new JLabel("Poäng för runda 1 = 0 poäng");
    private JLabel round1player2 = new JLabel(roundString);
    private JLabel round2player1 = new JLabel(roundString);
    private JLabel round2player2 = new JLabel(roundString);
    private JLabel round3player1 = new JLabel(roundString);
    private JLabel round3player2 = new JLabel(roundString);
    private JLabel round4player1 = new JLabel(roundString);
    private JLabel round4player2 = new JLabel(roundString);


    private JPanel topPanel = new JPanel();
    private JPanel centerPanel = new JPanel();

    private JPanel northPanel = new JPanel();
    private JButton exit = new JButton("ge upp");

    public SCORE(MasterFrame masterFrame){
        exit.addActionListener(e -> {
            setRoundPanelText(11,3);
            revalidate();
            repaint();
        });
        /*
        1. uppdatera score screen med senaste svaren
        2. server skickar svar från motståndare
        3. uppdatera och visa nya resultaten
        4. om servern skickar kategori visa kategori screen
            om servern skickar frågor visa question screen
            om servern skickar klar visa winner screen/looser screen
         */
        setLayout(new BorderLayout());
        player1.setHorizontalAlignment(JLabel.CENTER);
        player2.setHorizontalAlignment(JLabel.CENTER);
        topPanel.setLayout(new GridLayout(1,2));
        centerPanel.setLayout(new GridLayout(4,2));
        centerPanel.setSize(new Dimension(500,460));

        northPanel.add(exit);

        northPanel.setSize(new Dimension(500, 10));
        topPanel.setSize(new Dimension(500, 40));

        topPanel.add(player1);
        topPanel.add(player2);

        round1player1.setVisible(false);
        hideRoundScorePanels();

        centerPanel.add(round1player1);
        centerPanel.add(round1player2);
        centerPanel.add(round2player1);
        centerPanel.add(round2player2);
        centerPanel.add(round3player1);
        centerPanel.add(round3player2);
        centerPanel.add(round4player1);
        centerPanel.add(round4player2);

        exit.setSize(new Dimension(500,10));


        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.SOUTH);
    }

    private void hideRoundScorePanels() {
        round1player1.setVisible(false);
        round1player2.setVisible(false);
        round2player1.setVisible(false);
        round2player2.setVisible(false);
        round3player1.setVisible(false);
        round3player2.setVisible(false);
        round4player1.setVisible(false);
        round4player2.setVisible(false);
    }

    //metoder måste styras från cardLayout

    public void setRoundPanelText(int labelID, int score){
        switch (labelID){
            case 11 -> {
                round1player1.setText(roundString + "1" + " = " + score + " poäng");
                scorePlayer1 += score;
                displayPlayer1TotalScore();
                round1player1.setVisible(true);
            }
            case 12 -> {
                round1player2.setText(roundString + "1" + " = " + score + " poäng");
                scorePlayer2 += score;
                displayPlayer2TotalScore();
                setVisible(true);
            }
            case 21 -> round2player1.setText(roundString + "2" + " = " +  score + " poäng");
            case 22 -> round2player2.setText(roundString + "2" + " = " +  score + " poäng");
            case 31 -> round3player1.setText(roundString + "3" + " = " +  score + " poäng");
            case 32 -> round3player2.setText(roundString + "3" + " = " +  score + " poäng");
            case 41 -> round4player1.setText(roundString + "4" + " = " +  score + " poäng");
            case 42 -> round4player2.setText(roundString + "4" + " = " +  score + " poäng");
        }

    }

    private void displayPlayer1TotalScore() {
        player1.setText(player1Username + " totala poäng: " + scorePlayer1);
    }
    private void displayPlayer2TotalScore() {
        player1.setText(player2Username + " totala poäng: " + scorePlayer2);
    }

}
