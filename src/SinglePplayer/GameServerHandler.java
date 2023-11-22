package SinglePplayer;

import java.io.*;
import java.net.Socket;

public class GameServerHandler extends Thread{
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public GameServerHandler(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            Protocol protocol = new Protocol();
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());

            Object objectFromClient;
            objectFromClient = in.readObject();

            // skapa ny player med username som kommer från client
            // kolla om hen finns i playerDAO
            // lägg till antingen ny spelare i online player

            System.out.println(objectFromClient.toString()+" Connected");

            while ((objectFromClient = in.readObject()) != null) {
                out.writeObject(protocol.processInput(objectFromClient));
            }

            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
