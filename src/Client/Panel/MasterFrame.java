package Client.Panel;

import javax.swing.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MasterFrame extends JFrame {
    private final CardLayout layout = new CardLayout();
    private final CardLayoutContainer allPanels = new CardLayoutContainer(layout, this);
    String pageNumber = "login";
    ObjectOutputStream sendToServer;
    ObjectInputStream fromServer;


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
