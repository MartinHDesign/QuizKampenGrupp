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
    LOSE losePanel;
    DRAW drawPanel;
    public CardLayoutContainer(CardLayout layout, MasterFrame masterFrame){
        setLayout(layout);

        loginPanel = new LOGIN(masterFrame);
        add(loginPanel, FinalStrings.LOGIN.toString());

//        menuPanel = new MENU(masterFrame);
//        add(menuPanel, FinalStrings.MENU.toString());

        waitPanel = new WAIT(masterFrame);
        add(waitPanel, FinalStrings.WAIT.toString());

        questionPanel = new QUESTIONS(masterFrame);
        add(questionPanel, FinalStrings.QUESTIONS.toString());

        HighScorePanel highScorePanel = new HighScorePanel(masterFrame);
        add(highScorePanel, "HIGHSCORE");

        categoryPanel = new CATEGORY(masterFrame);
        add(categoryPanel, FinalStrings.CATEGORY.toString());

        scorePanel = new SCORE(masterFrame);
        add(scorePanel, FinalStrings.SCORE.toString());

        winPanel = new WIN();
        add(winPanel,"WIN");
//
//        losePanel = new LOSE();
//        add(loginPanel, "LOSE");

//        drawPanel = new DRAW();
//        add(drawPanel, "DRAW");


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
