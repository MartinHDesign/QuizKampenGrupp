package Client.Panel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MasterFrame extends JFrame {
    private final CardLayout layout = new CardLayout();
    private final CardLayoutContainer allPanels = new CardLayoutContainer(layout, this);
    String pageNumber = "login";
    ObjectOutputStream sendToServer;
    ObjectInputStream fromServer;
    Socket socketToServer;


    public MasterFrame(){
        add(allPanels);

        showPage(pageNumber);

        try {
            socketToServer = new Socket("127.0.0.1", 6666);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (ObjectOutputStream out = new ObjectOutputStream(socketToServer.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socketToServer.getInputStream())
        ){
            sendToServer = out;
            fromServer = in;
            out.writeObject("Player1");

            Object objectFromServer = in.readObject();
            System.out.println((String) objectFromServer);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(500,520));
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new MasterFrame();
    }
    public void showPage(String page){
        layout.show(allPanels,page);
        repaint();
    }

}
