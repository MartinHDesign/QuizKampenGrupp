package SinglePplayer.Panel;

import javax.swing.*;
import java.awt.*;

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
//            String temp = masterFrame.sendMessage("score");
//            System.out.println("menu");
//            masterFrame.showPage(temp);
        });

        add(vsGame);
        add(highScore);
        add(settings);
        add(exit);
    }

}
