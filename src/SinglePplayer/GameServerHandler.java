package SinglePplayer;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class GameServerHandler extends Thread{
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    GameServer gameServer;
    Player player;

    public GameServerHandler(GameServer gameServer,Socket socket) {
        this.gameServer = gameServer;
        this.clientSocket = socket;
    }

    public void run() {
        try {

            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());

            Object objectFromClient;
            // här läser servern userName från clienten
            objectFromClient = in.readObject();


            Player connectingPlayer = validatePlayer(objectFromClient);

            this.player = connectingPlayer;
            gameServer.playerOnline(connectingPlayer);



//            ProtocolSingelPlayer protocol = new ProtocolSingelPlayer(connectingPlayer);
            System.out.println("innan läsning från client börjar");
//            while ((objectFromClient = in.readObject()) != null) {
//                System.out.println("server start listening");
//                out.writeObject(protocol.processInput(objectFromClient));
//
//            }
            while (true){
                if (gameServer.getOnlinePlayer().size() == 2) {
                    System.out.println("server start listening 2 player");
                    Player player1 = gameServer.getOnlinePlayer().get(0);
                    Player player2 = gameServer.getOnlinePlayer().get(1);
                    NewGameHandler newGame = new NewGameHandler(player1, player2);
                    newGame.start();
                    break;
                }
                //obs ful kod
//                if (objectFromClient.toString().equals("NEWGAME")){

//                } else if (objectFromClient.toString().equals("VSGAME")) {
//                    //Leta efter specifik spelare
//                }
            }

//            in.close();
//            out.close();
//            clientSocket.close();
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
    public Player validatePlayer(Object objectFromClient){
        Player temp;
        // kolla om spelare finns i dao
        if (playerExists(objectFromClient)){
            temp = gameServer.getExistingPlayer(objectFromClient);
            System.out.println("Welcome back " + temp.getName() + ". connected to server");
        } else {
            // skapa ny player med username som kommer från client
            temp = createNewPlayer(objectFromClient);
            // lägger till player i DAOPlayer
            gameServer.addPlayerDAO(temp);
            System.out.println("Player " + temp.getName() + " connected");
        }
        return temp;
    }
}
