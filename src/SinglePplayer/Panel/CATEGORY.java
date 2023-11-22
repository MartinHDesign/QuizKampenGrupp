package SinglePplayer.Panel;



import javax.swing.*;
import java.awt.*;

public class CATEGORY extends JPanel {
        private JButton History = new JButton("history");
        private JButton Category2 = new JButton("Kategori 2");
        private JButton Category3 = new JButton("Kategori 3");
        private JButton Category4 = new JButton("Kategori 4");
        private JButton exit = new JButton("avsluta");

    public CATEGORY(MasterFrame masterFrame){
            setLayout(new GridLayout(5,1));

            /*
            1. skicka till servern vilken kategori man valt
            2. vänta på att servern skickar fråga + svars alternativ
            3. byt till question screen som uppdaterats med frågor å svar
             */

            History.addActionListener(e -> {
                    masterFrame.sendToServer("QUESTIONS");
//                String temp = masterFrame.sendMessage("score");
//                System.out.println("LOGIN");
//                masterFrame.showPage(temp);
            });
            add(History);
            add(Category2);
            add(Category3);
            add(Category4);
            add(exit);
        }
            }