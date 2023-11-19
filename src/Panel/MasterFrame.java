package Panel;

import javax.swing.*;
import java.awt.*;

public class MasterFrame extends JFrame {
    CardLayout layout = new CardLayout();
    CardLayoutContainer allPanels = new CardLayoutContainer(layout);

    public MasterFrame(){
        add(allPanels);

        layout.show(allPanels,"1");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(500,520));
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new MasterFrame();
    }
}
