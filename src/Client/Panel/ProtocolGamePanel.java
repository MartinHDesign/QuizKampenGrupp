package Client.Panel;

import Server.DataBase.HistoryQuestions.HistoryAnswer;
import Server.DataBase.HistoryQuestions.HistoryQuestion;
import Server.ServerResponse;

import java.io.IOException;

public class ProtocolGamePanel {

    private final int NEXTROUND = 0;
    private final int WAITING = 1;
    private int numberOfQuestionsAnswered;
    private final int SHOWENDOFROUND = 3;
    private final int SHOWENDOFGAME = 4;

    private MasterFrame masterFrame;
    private final QuestionScreen questionButtons = QuestionScreen.getInstance();

    private int numberOfRounds = 2;
    private int numberOfRoundsPlayed;
    private int numberOfQuestionsPerRound = 2;

    private int numberOfPlayerAnswers;

    private int STATE = NEXTROUND;

    public ProtocolGamePanel() {
        this.masterFrame = MasterFrame.getInstance();
    }

    public void panelProcess() {

        switch (STATE) {
            case 0 -> {
                System.out.println("Trying to set frame to category frame");
                masterFrame.showPage("category");
                masterFrame.revalidate();
                masterFrame.repaint();
                System.out.println("category frame set");
                STATE = WAITING;
            }
        }
    }

    public void gameProcess(Object fromServer) {
        if (fromServer instanceof ServerResponse response) {
            int score = response.getScore();
            HistoryQuestion question = response.getQuestion();
            int endOfGame = response.getEndOfGame();
            String categories = response.getShowGUIPanel();

            if (question != null) {
                questionButtons.buttonPanel.removeAll();
                for (HistoryAnswer answer : question.getAnswers()) {
                    QuestionButton button = new QuestionButton(answer.getAnswerText(),answer.getIsCorrect());
                    button.addActionListener(l -> {
                        try {
                            masterFrame.sendToServer.writeObject(answer.getIsCorrect());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    });
                    questionButtons.buttonPanel.add(button);

                }
            }
        }




    }


}
