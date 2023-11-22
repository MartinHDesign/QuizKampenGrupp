package Client.Panel;

import javax.swing.*;
import java.awt.*;

public class CardLayoutContainer extends JPanel {
    public CardLayoutContainer(CardLayout layout, MasterFrame masterFrame){
        setLayout(layout);

        LoginPanel loginScreen = new LoginPanel(masterFrame);
        add(loginScreen, "login");

        GameMenu gameMenu = new GameMenu(masterFrame);
        add(gameMenu, "menu");

        WaitingForOtherPlayerScreen waitingForOtherPlayerPanel = new WaitingForOtherPlayerScreen(masterFrame);
        add(waitingForOtherPlayerPanel, "wait");

        QuestionScreen questionScreen = new QuestionScreen(masterFrame);
        add(questionScreen, "question");

        HighScorePanel highScorePanel = new HighScorePanel(masterFrame);
        add(highScorePanel, "highScore");

        CategoryScreen categoryScreen = new CategoryScreen(masterFrame);
        add(categoryScreen, "category");

        ScoreScreen scoreScreen = new ScoreScreen();
        add(scoreScreen, "ScoreScreen");

    }


}
