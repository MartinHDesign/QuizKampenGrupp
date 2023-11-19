package Panel;

import javax.swing.*;
import java.awt.*;

public class CardLayoutContainer extends JPanel {
    MasterFrame masterFrame;
    private GameMenu gameMenu = new GameMenu();
    public CardLayoutContainer(CardLayout layout, MasterFrame masterFrame){
        this.masterFrame = masterFrame;
        setLayout(layout);

        LoginPanel loginScreen = new LoginPanel(masterFrame);
        add(loginScreen, "1");

        add(gameMenu, "2");

    }


}
