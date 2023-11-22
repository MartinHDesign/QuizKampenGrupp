package SinglePplayer;

import java.io.*;
import java.net.Socket;

public class GameServerHandler extends Thread{
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    GameServer gameServer;

    public GameServerHandler(GameServer gameServer,Socket socket) {
        this.gameServer = gameServer;
        this.clientSocket = socket;
    }

    public void run() {
        try {
            Protocol protocol = new Protocol();
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());

            Object objectFromClient;
            objectFromClient = in.readObject();

            Player temp = createNewPlayer(objectFromClient);

            System.out.println("Player " + temp.getName() + " connected");

            // skapa ny player med username som kommer från client
            // kolla om hen finns i playerDAO
            // lägg till antingen ny spelare i online player


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
    public Player createNewPlayer(Object objectFromClient){
        if (objectFromClient.toString().isEmpty()){
            int guestNumber = gameServer.getGuestNumber();
            gameServer.guestNumberCountUp();
            return new Player("Guest"+guestNumber,clientSocket,out,in);
        } else {
            return new Player(objectFromClient.toString(), clientSocket, out, in);
        }
    }
}
