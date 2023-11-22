package SinglePplayer.Panel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MENU extends JPanel {
    private JButton newGame = new JButton("Nytt spel");
    private JButton vsGame = new JButton("Spela mot");
    private JButton highScore = new JButton("High score");
    private JButton settings = new JButton("Inställningar");
    private JButton exit = new JButton("avsluta");
    public MENU(MasterFrame masterFrame){
        setLayout(new GridLayout(5,1));

        add(newGame);
        newGame.addActionListener(e -> {
//            Object temp = masterFrame.sendMessage("score");
//            System.out.println("menu");
//            masterFrame.showPage(temp.toString());
            masterFrame.sendToServer("CATEGORY");
        });

        add(vsGame);
        add(highScore);
        add(settings);


        exit.addActionListener(e -> {
            masterFrame.sendToServer("");
        });
        add(exit);
    }

}
