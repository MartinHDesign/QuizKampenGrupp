package SinglePplayer.Panel;

import Server.ServerResponse;
import javax.swing.*;
import java.awt.*;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class MasterFrame extends JFrame {
    private final CardLayout layout = new CardLayout();
    private final CardLayoutContainer allPanels = new CardLayoutContainer(layout, this);
    String pageNumber = FinalStrings.LOGIN.toString();

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

                    if (serverResponse.getPlayerNames() != null) {
                        setNamesAndPointsOnScoreBoard();
                    }
                    if (serverResponse.getShowGUIPanel() != null) {
                        setGUIPage(serverResponse);
                    }

                    if (serverResponse.getQuestion() != null) {
                        setQuestions(serverResponse);
                        revalidate();
                        repaint();

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
        allPanels.questionPanel.questionFromServer.setText(serverResponse.getQuestion().getQuestion());
        allPanels.questionPanel.answer1.setText(serverResponse.getQuestion().getAnswers().get(0).getAnswerText);
        allPanels.questionPanel.answer2.setText(serverResponse.getQuestion().getAnswers().get(1).getAnswerText());
        allPanels.questionPanel.answer3.setText(serverResponse.getQuestion().getAnswers().get(2).getAnswerText());
        allPanels.questionPanel.answer4.setText(serverResponse.getQuestion().getAnswers().get(3).getAnswerText());
        allPanels.questionPanel.answer1.setCorrect(serverResponse.getQuestion().getAnswers().get(0).getIsCorrect());
        allPanels.questionPanel.answer2.setCorrect(serverResponse.getQuestion().getAnswers().get(1).getIsCorrect());
        allPanels.questionPanel.answer3.setCorrect(serverResponse.getQuestion().getAnswers().get(2).getIsCorrect());
        allPanels.questionPanel.answer4.setCorrect(serverResponse.getQuestion().getAnswers().get(3).getIsCorrect());
        revalidate();
        repaint();
    }

    public void setCurrentCategory(int currentCategory) {
        this.currentCategory = currentCategory;
    }

    public int getCurrentCategory() {
        return currentCategory;
    }

    public static void main(String[] args) {
        new MasterFrame();
    }

    private void setNamesAndPointsOnScoreBoard() {

    }
    private void setGUIPage(ServerResponse serverResponse) {
        showPage(serverResponse.getShowGUIPanel());
        revalidate();
        repaint();
    }
}


