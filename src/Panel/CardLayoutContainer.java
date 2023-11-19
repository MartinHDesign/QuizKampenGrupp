package Panel;

import javax.swing.*;
import java.awt.*;

public class CardLayoutContainer extends JPanel {
    public CardLayoutContainer(CardLayout layout, MasterFrame masterFrame){
        setLayout(layout);

        LoginPanel loginScreen = new LoginPanel(masterFrame);
        add(loginScreen, "1");

        GameMenu gameMenu = new GameMenu(masterFrame);
        add(gameMenu, "2");

        WaitingForOtherPlayerScreen waitingForOtherPlayerPanel = new WaitingForOtherPlayerScreen(masterFrame);
        add(waitingForOtherPlayerPanel, "3");

        QuestionScreen questionScreen = new QuestionScreen(masterFrame);
        add(questionScreen, "4");

    }


}
