package SinglePplayer.Panel;

import javax.swing.*;
import java.awt.*;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class MasterFrame extends JFrame {
    private final CardLayout layout = new CardLayout();
    private final CardLayoutContainer allPanels = new CardLayoutContainer(layout, this);
    String pageNumber = "LOGIN";

    private Socket socketToServer;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    String ip = "127.0.0.1";
    int port = 55555;


    public MasterFrame(){
        add(allPanels);

        showPage(pageNumber);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(500,520));
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public String showPage(String page){
        layout.show(allPanels,page);
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

    public static void main(String[] args) {
        new MasterFrame();
    }
}
