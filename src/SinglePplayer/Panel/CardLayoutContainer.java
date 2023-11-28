package SinglePplayer.Panel;



import javax.swing.*;
import java.awt.*;

public class CardLayoutContainer extends JPanel {
    LOGIN loginPanel;
    MENU menuPanel;
    WAIT waitPanel;
    QUESTIONS questionPanel;
    CATEGORY categoryPanel;
    SCORE scorePanel;
    WIN winPanel;

    public CardLayoutContainer(CardLayout layout, MasterFrame masterFrame){
        setLayout(layout);

        loginPanel = new LOGIN(masterFrame);
        add(loginPanel, FinalStrings.LOGIN.name());

//        menuPanel = new MENU(masterFrame);
//        add(menuPanel, FinalStrings.MENU.name());

        waitPanel = new WAIT(masterFrame);
        add(waitPanel, FinalStrings.WAIT.name());

        questionPanel = new QUESTIONS(masterFrame);
        add(questionPanel, FinalStrings.QUESTIONS.name());

        HighScorePanel highScorePanel = new HighScorePanel(masterFrame);
        add(highScorePanel, FinalStrings.HIGHSCORE.name());

        categoryPanel = new CATEGORY(masterFrame);
        add(categoryPanel, FinalStrings.CATEGORY.name());

        scorePanel = new SCORE(masterFrame);
        add(scorePanel, FinalStrings.SCORE.name());

        winPanel = new WIN();
        add(winPanel,FinalStrings.WIN.name());
    }

    public LOGIN getLoginPanel() {
        return loginPanel;
    }

    public void setLoginPanel(LOGIN loginPanel) {
        this.loginPanel = loginPanel;
    }

    public MENU getMenuPanel() {
        return menuPanel;
    }

    public void setMenuPanel(MENU menuPanel) {
        this.menuPanel = menuPanel;
    }

    public WAIT getWaitPanel() {
        return waitPanel;
    }

    public void setWaitPanel(WAIT waitPanel) {
        this.waitPanel = waitPanel;
    }

    public QUESTIONS getQuestionPanel() {
        return questionPanel;
    }

    public void setQuestionPanel(QUESTIONS questionPanel) {
        this.questionPanel = questionPanel;
    }

    public CATEGORY getCategoryPanel() {
        return categoryPanel;
    }

    public void setCategoryPanel(CATEGORY categoryPanel) {
        this.categoryPanel = categoryPanel;
    }

    public SCORE getScorePanel() {
        return scorePanel;
    }

    public void setScorePanel(SCORE scorePanel) {
        this.scorePanel = scorePanel;
    }
}
