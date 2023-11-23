package SinglePplayer.Panel;



import javax.swing.*;
import java.awt.*;

public class CATEGORY extends JPanel {
        private JButton history = new JButton("HISTORIA");
        private JButton sport = new JButton("SPORT");
        private JButton music = new JButton("MUSIK");


    public CATEGORY(MasterFrame masterFrame){
            setLayout(new GridLayout(5,1));

            /*
            1. skicka till servern vilken kategori man valt
            2. vänta på att servern skickar fråga + svars alternativ
            3. byt till question screen som uppdaterats med frågor å svar
             */

            history.addActionListener(l -> {
                    masterFrame.sendToServer(0);
            });
            sport.addActionListener(l -> masterFrame.sendToServer(1));

            music.addActionListener(l -> {
                    masterFrame.sendToServer(2);
            });
            add(history);
            add(sport);
            add(music);

        }
            }