package Client.Panel;

import Server.ServerResponse;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class GameMenu extends JPanel {
    private JButton newGame = new JButton("Nytt spel");
    private JButton vsGame = new JButton("Spela mot");
    private JButton highScore = new JButton("High score");
    private JButton settings = new JButton("Inställningar");
    private JButton exit = new JButton("avsluta");

    public GameMenu(MasterFrame masterFrame) {
        setLayout(new GridLayout(5, 1));

        add(newGame);
        newGame.addActionListener(e -> {
            Socket socketToServer;
            ObjectOutputStream toServer;
            ObjectInputStream fromServer;
            Object temp;

//            masterFrame.showPage("wait");
//            masterFrame.revalidate();
//            masterFrame.repaint();

            try {
                socketToServer = new Socket("127.0.0.1", 6666);
                System.out.println("Socket to server");
            } catch (UnknownHostException ew) {
                throw new RuntimeException(ew);
            } catch (IOException ew) {
                throw new RuntimeException(ew);
            }

            // try {

            //     ObjectOutputStream out = new ObjectOutputStream(socketToServer.getOutputStream());
            //     ObjectInputStream in = new ObjectInputStream(socketToServer.getInputStream());

            //     System.out.println("för out");

            //  masterFrame.setSendToServer(out);
            //   masterFrame.setFromServer(in);
//                System.out.println("efter out");
//                masterFrame.sendToServer.writeObject("Player1");
//                System.out.println("skickad");

            System.out.println("hallå?");


            //               masterFrame.runTheGoddamnProgram(masterFrame);
//                masterFrame.showPage("category");
//                masterFrame.repaint();} catch (IOException ea) {

            // skicka till server att man vill spela nytt spel
            // få tillbaka om man är spelare 1 eller 2
            // om spelare 1 visa kategori screen
            // om spelare två visa wait screen eller score screen?

            //  });

            //    add(vsGame);
            //   add(highScore);
            //    add(settings);
            //     add(exit);
        });
        //   }
    }
}



