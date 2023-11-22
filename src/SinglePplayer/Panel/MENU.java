package SinglePplayer.Panel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MENU extends JPanel {
    private JButton newGame = new JButton("player 2 to CATEGORY");
    private JButton vsGame = new JButton("player 2 to MENU");
    private JButton highScore = new JButton("High score");
    private JButton settings = new JButton("Inställningar");
    private JButton exit = new JButton("exit");
    public MENU(MasterFrame masterFrame){
        setLayout(new GridLayout(5,1));

        add(newGame);
        newGame.addActionListener(e -> {
            masterFrame.sendToServer("CATEGORY");
        });
        vsGame.addActionListener(e -> {
            masterFrame.sendToServer("MENU");
        });

        add(vsGame);
        add(highScore);
        add(settings);



        add(exit);
    }

}
