package Client.Panel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CategoryScreen extends JPanel {
        private JButton History = new JButton("Kategori 1");
        private JButton Category2 = new JButton("Kategori 2");
        private JButton Category3 = new JButton("Kategori 3");
        private JButton Category4 = new JButton("Kategori 4");
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
            // return int 0
            History.addActionListener(e -> {
                ObjectOutputStream out = masterFrame.getSendToServer();
                try {
                    out.writeObject("0");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            add(Category2);
            add(Category3);
            add(Category4);
            add(exit);
        }
            }