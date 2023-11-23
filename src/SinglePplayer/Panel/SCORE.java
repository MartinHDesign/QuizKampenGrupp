package SinglePplayer.Panel;

import javax.swing.*;
import java.awt.*;

public class SCORE extends JPanel {
    private JLabel player1name = new JLabel();
    private JLabel player2name = new JLabel();
    private JPanel topPanel = new JPanel();
    private JPanel centerPanel = new JPanel();

    private JButton playNextRound = new JButton("Spela nästa runda");

    private JPanel northPanel = new JPanel();
    private JButton exit = new JButton("ge upp");

    public SCORE(MasterFrame masterFrame){
        exit.addActionListener(e -> {
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
        player1name.setHorizontalAlignment(JLabel.CENTER);
        player2name.setHorizontalAlignment(JLabel.CENTER);
        topPanel.setLayout(new GridLayout(1,2));
        centerPanel.setLayout(new GridLayout(8,2));
        centerPanel.setSize(new Dimension(500,460));

        northPanel.add(exit);

        playNextRound.addActionListener(l -> {
            masterFrame.showPage("CATEGORY");
        });
        northPanel.add(playNextRound);

        northPanel.setSize(new Dimension(500, 10));
        topPanel.setSize(new Dimension(500, 40));

        topPanel.add(player1name);
        topPanel.add(player2name);

        for (int i = 0; i < 16; i++) {
            ScoreLabel temp = new ScoreLabel();
            temp.setHorizontalAlignment(JLabel.CENTER);
            centerPanel.add(temp);
        }

        exit.setSize(new Dimension(500,10));


        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(northPanel, BorderLayout.SOUTH);
    }
    private class ScoreLabel extends JLabel{
        public ScoreLabel(){
            setText("0 0 0");

        }

    }

    public void setPlayer1Name(String name) {
        this.player1name.setText(name);
    }
    public void setPlayer2Name(String name) {
        this.player2name.setText(name);
    }

}
