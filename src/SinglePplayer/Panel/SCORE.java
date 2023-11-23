package SinglePplayer.Panel;

import javax.swing.*;
import java.awt.*;

public class SCORE extends JPanel {
    private JLabel player1 = new JLabel("player score: 6");
    private JLabel player2 = new JLabel("player score: 3");
    private JPanel topPanel = new JPanel();
    private JPanel centerPanel = new JPanel();

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
        player1.setHorizontalAlignment(JLabel.CENTER);
        player2.setHorizontalAlignment(JLabel.CENTER);
        topPanel.setLayout(new GridLayout(1,2));
        centerPanel.setLayout(new GridLayout(8,2));
        centerPanel.setSize(new Dimension(500,460));

        northPanel.add(exit);

        northPanel.setSize(new Dimension(500, 10));
        topPanel.setSize(new Dimension(500, 40));

        topPanel.add(player1);
        topPanel.add(player2);

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
}
