package SinglePplayer.Panel;

import Server.DataBase.Questions.Answer;
import Server.ServerResponse;

import javax.swing.*;
import java.awt.*;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class MasterFrame extends JFrame {
    private final CardLayout layout = new CardLayout();
    private final CardLayoutContainer allPanels = new CardLayoutContainer(layout, this);
    String pageNumber = FinalStrings.LOGIN.name();

    private Socket socketToServer;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String ip = "127.0.0.1";
    private int port = 5555;
    private ReadFromServer readFromServer = new ReadFromServer();

    private int currentCategory;

    protected final int HISTORY = 0;
    protected final int SPORT = 1;

    protected final int MUSIC = 2;


    public MasterFrame() {
        add(allPanels);

        showPage(pageNumber);
        setTitle("Jesus Quiztus ");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(500, 520));
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void setTitleNameToUserName(String userName) {
        setTitle(userName);
    }

    public String showPage(String page) {
        layout.show(allPanels, page);
        return page;
    }

    public void startConnection(Object userName) {
        try {
            socketToServer = new Socket(ip, port);
            out = new ObjectOutputStream(socketToServer.getOutputStream());
            in = new ObjectInputStream(socketToServer.getInputStream());
            out.writeObject(userName);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Object sendMessage(Object objectToServer) {
        try {
            out.writeObject(objectToServer);
            Object resp = in.readObject();
            return resp;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendToServer(Object fromClient) {
        try {
            out.writeObject(fromClient);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ObjectOutputStream getOut() {
        return out;
    }


    // lyssnar efter vad servern skickar.
    public class ReadFromServer implements Runnable {
        @Override
        public void run() {

            try {
                Object objectFromServer;

                while ((objectFromServer = in.readObject()) != null) {
                    ServerResponse serverResponse = getServerResponse(objectFromServer);

                    // poäng från servern
                    if (serverResponse.getScore() > 99){
                        if (serverResponse.getScore() < 200){
                            setPlayer1Score(serverResponse.getScore()-100);
                        } else
                            setPlayer2Score(serverResponse.getScore()-200);
                    }

                    // få questions
                    if (serverResponse.getQuestion() != null){
                        setQuestions(serverResponse);
                    }

                    // få byta GUI
                    if (serverResponse.getShowGUIPanel() != null){
                        if (serverResponse.getShowGUIPanel().equals("WIN")){
                            winGUIpage(serverResponse);
                        } else if (serverResponse.getShowGUIPanel().equals("QUESTIONS")) {
                            setGUIPage(serverResponse);
                            allPanels.questionPanel.iterateProgressbar();
                        } else
                            setGUIPage(serverResponse);
                    }

                    // få motståndares namn
                    if (serverResponse.getOpponentName() != null){
                        setOpponentName(serverResponse.getOpponentName());
                    }

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ReadFromServer getReadFromServer() {
        return readFromServer;
    }
    public ServerResponse getServerResponse(Object object) {
        return (ServerResponse) object;
    }

    public void setQuestions(ServerResponse serverResponse) {

        Answer answer1 = (Answer) serverResponse.getQuestion().getAnswers().get(0);
        Answer answer2 = (Answer) serverResponse.getQuestion().getAnswers().get(1);
        Answer answer3 = (Answer) serverResponse.getQuestion().getAnswers().get(2);
        Answer answer4 = (Answer) serverResponse.getQuestion().getAnswers().get(3);

        allPanels.questionPanel.questionFromServer.setText(serverResponse.getQuestion().getQuestion());
        allPanels.questionPanel.answer1.setText(answer1.getAnswerText());
        allPanels.questionPanel.answer2.setText(answer2.getAnswerText());
        allPanels.questionPanel.answer3.setText(answer3.getAnswerText());
        allPanels.questionPanel.answer4.setText(answer4.getAnswerText());
        allPanels.questionPanel.answer1.setCorrect(answer1.getIsCorrect());
        allPanels.questionPanel.answer2.setCorrect(answer2.getIsCorrect());
        allPanels.questionPanel.answer3.setCorrect(answer3.getIsCorrect());
        allPanels.questionPanel.answer4.setCorrect(answer4.getIsCorrect());


        revalidate();
        repaint();

    }
    public void setPlayer1Score(int score){
        allPanels.scorePanel.player1ScoreThisRound(score);
    }
    public void setPlayer2Score(int score){
        allPanels.scorePanel.player2ScoreThisRound(score);
    }
    public void setOpponentName(String playerName){
        allPanels.scorePanel.setPlayer2Username(playerName);
    }
    public void setPlayerName(String playerName){
        allPanels.scorePanel.setPlayer1Username(playerName);
    }

    public int getCurrentCategory() {
        return currentCategory;
    }

    private void setGUIPage(ServerResponse serverResponse) {
        showPage(serverResponse.getShowGUIPanel());
        revalidate();
        repaint();
    }
    private void winGUIpage(ServerResponse serverResponse){
        String player1name = allPanels.getScorePanel().getPlayer1Username();
        String player2name = allPanels.getScorePanel().getPlayer2Username();
        int scorePlayer1 = allPanels.getScorePanel().getScorePlayer1();
        int scorePlayer2 = allPanels.getScorePanel().getScorePlayer2();

        String text1 = player1name + " totala poäng: " + scorePlayer1;
        String text2 = player2name + " totala poäng: " + scorePlayer2;

        allPanels.winPanel.setFinalResultText(text1,text2,scorePlayer1,scorePlayer2);
        showPage(serverResponse.getShowGUIPanel());
    }
    public static void main(String[] args) {new MasterFrame();}
}


