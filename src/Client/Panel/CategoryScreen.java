package Client.Panel;

import javax.swing.*;
import java.awt.*;

public class CategoryScreen extends JPanel {

        private JButton History = new JButton("Nytt spel");
        private JButton Category2 = new JButton("Spela mot");
        private JButton Category3 = new JButton("High score");
        private JButton Category4 = new JButton("Inställningar");
        private JButton exit = new JButton("avsluta");

    public CategoryScreen(MasterFrame masterFrame){
            setLayout(new GridLayout(5,1));

            /*
            1. skicka till servern vilken kategori man valt
            2. vänta på att servern skickar fråga + svars alternativ
            3. byt till question screen som uppdaterats med frågor å svar
             */

            History.addActionListener(e -> {masterFrame.showPage("question");});
            add(History);
            add(Category2);
            add(Category3);
            add(Category4);
            add(exit);
        }
    }