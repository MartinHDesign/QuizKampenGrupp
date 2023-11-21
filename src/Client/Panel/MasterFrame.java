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
    String pageNumber = "gameMenu";
    ObjectOutputStream sendToServer;
    ObjectInputStream fromServer;
    Socket socketToServer;


    public MasterFrame(){
        add(allPanels);

        showPage(pageNumber);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(500,520));
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

    public static void main(String[] args) {
        new MasterFrame();
    }
    public String showPage(String page){
        layout.show(allPanels,page);
        repaint();
        return page;
    }

    public void runTheGoddamnProgram(MasterFrame master) throws IOException, ClassNotFoundException {
        ProtocolGamePanel protocol = new ProtocolGamePanel(master);

        while (true) {
            System.out.println("Loop starting");
            protocol.panelProcess();
            Object objectFromServer = master.fromServer.readObject();
            protocol.gameProcess(objectFromServer);
            System.out.println("End of loop");
        }


    }
}
