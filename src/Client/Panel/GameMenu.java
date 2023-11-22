package Client.Panel;

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
    public GameMenu(MasterFrame masterFrame){
        setLayout(new GridLayout(5,1));

        add(newGame);
        newGame.addActionListener(e -> {

            // skicka till server att man vill spela nytt spel
            // få tillbaka om man är spelare 1 eller 2
            // om spelare 1 visa kategori screen
            // om spelare två visa wait screen eller score screen?
            masterFrame.showPage("wait");
            masterFrame.repaint();

//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException ex) {
//                throw new RuntimeException(ex);
//            }
//            Socket socketToServer = null;
//            try {
//                socketToServer = new Socket("127.0.0.1", 6666);
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//
//            // out och in måste spara någonstans för framtida användning
//            try (ObjectOutputStream out = new ObjectOutputStream(socketToServer.getOutputStream());
//                 ObjectInputStream in = new ObjectInputStream(socketToServer.getInputStream())
//            ) {
//                System.out.println("Trying to connect");
//                String test = "Connecting";
//                out.writeObject(test);
//                Object fromServer = in.readObject();
//                if (fromServer instanceof String s){
//                    System.out.println(s);
//                }
//
//
//            } catch (UnknownHostException ex) {
//                throw new RuntimeException(ex);
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            } catch (ClassNotFoundException ex) {
//                throw new RuntimeException(ex);
//            }
//            masterFrame.showPage("question");

        });

        add(vsGame);
        add(highScore);
        add(settings);
        add(exit);
    }

}
