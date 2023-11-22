package SinglePplayer;

import java.io.*;
import java.net.Socket;
import java.util.List;

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
            // här läser servern userName från clienten
            objectFromClient = in.readObject();


            Player temp;
            // kolla om spelare finns i dao
            if (playerExists(objectFromClient)){
                temp = gameServer.getExistingPlayer(objectFromClient);
            } else {
                // skapa ny player med username som kommer från client
                temp = createNewPlayer(objectFromClient);
            }


            System.out.println("Player " + temp.getName() + " connected");

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

    public boolean playerExists(Object userName){
        List<Player> DAOPlayer = gameServer.getDAOPlayers();
        for (Player player: DAOPlayer){
            if (player.getName().equals(userName.toString()))
                return true;
        }
        return false;
    }
}
