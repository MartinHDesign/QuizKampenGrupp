package Client;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Client.Panel.QuestionButton;
import Server.DataBase.HistoryDAO;
import Server.DataBase.HistoryQuestions.HistoryAnswer;


public class Client {

    public static void main(String[] args) throws IOException {
        int port = 6666;
        String IP = "127.0.0.1";
        HistoryDAO historyQuestions = new HistoryDAO();
        Socket socketToServer = new Socket(IP, port);

        JFrame testFrame = new JFrame(); //all elements of frame strictly for testing, to be removed later.
        JPanel testPanel = new JPanel(new GridLayout(2, 2));
        int indexOfQuestion = 0;


        testFrame.add(testPanel);
        testFrame.setVisible(true);
        testFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        System.out.println("This executes");
        for (HistoryAnswer answerToQuestion : historyQuestions.getHistoryQuestion().get(1).getAnswers()) {
            String question = answerToQuestion.getAnswerText();
            boolean isCorrect = answerToQuestion.getIsCorrect();
//            QuestionButton questionButton = new QuestionButton(question, isCorrect, indexOfQuestion);
//            questionButton.addActionListener(l -> {
//
//
//                try  {
//                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socketToServer.getOutputStream());
//                    objectOutputStream.writeObject(isCorrect);
//                    objectOutputStream.flush();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//
//            });
//            testPanel.add(questionButton);
            System.out.println("Button added");
        }

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socketToServer.getInputStream());
            System.out.println("Socket bound");


            while (true) {

                Object objectFromServer = objectInputStream.readObject();
            }




        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        }
    }


