package Panel;

import javax.swing.*;
import java.awt.*;

public class GameMenu extends JPanel {
    private JButton newGame = new JButton("Nytt spel");
    private JButton vsGame = new JButton("Spela mot");
    private JButton highScore = new JButton("Poäng tabell");
    private JButton exit = new JButton("avsluta");
    public GameMenu(){
        setLayout(new GridLayout(4,1));
        add(newGame);
        add(vsGame);
        add(highScore);
        add(exit);
    }

}
