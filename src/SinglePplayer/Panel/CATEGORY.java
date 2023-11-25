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
                    masterFrame.setCurrentCategory(0);
                    masterFrame.sendToServer(masterFrame.getCurrentCategory());


            });
            sport.addActionListener(l -> {
                    masterFrame.setCurrentCategory(1);
                    masterFrame.sendToServer(masterFrame.getCurrentCategory());
            });

            music.addActionListener(l -> {
                    masterFrame.setCurrentCategory(2);
                    masterFrame.sendToServer(masterFrame.getCurrentCategory());
            });
            add(history);
            add(sport);
            add(music);

        }
            }