package SinglePplayer.Panel;

import javax.swing.*;
import java.awt.*;

public class WIN extends JPanel {
    private JLabel result = new JLabel(new ImageIcon("src/SinglePplayer/Panel/GifAndImages/draw.gif"));
    private JPanel scoreBothPlayers = new JPanel();
    private JLabel player1 = new JLabel("player1", JLabel.CENTER);
    private JLabel player2 = new JLabel("player2", JLabel.CENTER);

    public WIN(){


        result.setPreferredSize(new Dimension(500,480));


        scoreBothPlayers.setLayout(new GridLayout(1,2));
        scoreBothPlayers.add(player1);
        scoreBothPlayers.add(player2);

        setLayout(new BorderLayout());
        add(result, BorderLayout.CENTER);
        add(scoreBothPlayers, BorderLayout.SOUTH);

        setVisible(true);
    }
    public void setFinalResultText(String player1name, String player2name, int scorePlayer1, int scorePlayer2){
        player1.setText(player1name);
        player2.setText(player2name);
        if (scorePlayer1 > scorePlayer2){
            result.setIcon(new ImageIcon("src/SinglePplayer/Panel/GifAndImages/win.gif"));
        } else if (scorePlayer2 > scorePlayer1) {
            result.setIcon(new ImageIcon("src/SinglePplayer/Panel/GifAndImages/lose.gif"));
        } else {
            result.setIcon(new ImageIcon("src/SinglePplayer/Panel/GifAndImages/draw.gif"));
        }
    }
}
