package Client.Panel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class MasterFrame extends JFrame {

    private static MasterFrame instance;
    private CardLayout layout;
    private CardLayoutContainer allPanels;
    String pageNumber = "gameMenu";
    ObjectOutputStream sendToServer;
    ObjectInputStream fromServer;
    Socket socketToServer;


    private MasterFrame() {

        layout = new CardLayout();

      //  allPanels = new CardLayoutContainer(layout);

        add(allPanels);

        showPage(pageNumber);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(500, 520));
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public ObjectOutputStream getSendToServer() {
        return sendToServer;
    }

    public ObjectInputStream getFromServer() {
        return fromServer;
    }


    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setSendToServer(ObjectOutputStream sendToServer) {
        this.sendToServer = sendToServer;
    }

    public void setFromServer(ObjectInputStream fromServer) {
        this.fromServer = fromServer;
    }

    public void setSocketToServer(Socket socketToServer) {
        this.socketToServer = socketToServer;
    }

    public String showPage(String page) {
        layout.show(allPanels, page);
        repaint();
        return page;
    }

    public static synchronized MasterFrame getInstance() {
        if (instance == null) {
            instance = new MasterFrame();
        }
        return instance;
    }


}

