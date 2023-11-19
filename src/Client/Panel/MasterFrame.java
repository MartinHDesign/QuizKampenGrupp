package Client.Panel;

import javax.swing.*;
import java.awt.*;

public class MasterFrame extends JFrame {
    private final CardLayout layout = new CardLayout();
    private final CardLayoutContainer allPanels = new CardLayoutContainer(layout, this);
    String pageNumber = "4";

    public MasterFrame(){
        add(allPanels);

        showPage(pageNumber);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(500,520));
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new MasterFrame();
    }
    public void showPage(String page){
        layout.show(allPanels,page);
        repaint();
    }

}
