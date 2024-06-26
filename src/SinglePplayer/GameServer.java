package SinglePplayer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;


public class GameServer {
    private int guestNumber = 1;
    private ServerSocket serverSocket;

    //Lista med online players när spelaren startar nytt spel försvinner hen från listan tills hen spelat färdigt.
    private List<Player> onlinePlayer = new ArrayList<>();

    // När en spelare loggar in -> kolla om hen finns i dao annars lägg till i listan. Sök via Player.getName
    // Dao bör skrivas och läsas från fil så att den sparas när servern stängs av
    private List<Player> DAOPlayers = new ArrayList<>();
    // Lista där spelare läggs till om dom vill möta en random motståndare
    private List<Player> newRandomOponentGameConnect = new ArrayList<>();
    // Lista för vs mode
    private List<Player> newChallangeOponentGameConnect = new ArrayList<>();

    public void start(int port) {
        try {
        serverSocket = new ServerSocket(port);
        while (true) {
            new GameServerHandler(this,serverSocket.accept()).start();
        }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void stop() throws IOException {
        serverSocket.close();
    }
    public List<Player> getOnlinePlayer() {
        return onlinePlayer;
    }

    public void setOnlinePlayer(List<Player> onlinePlayer) {
        this.onlinePlayer = onlinePlayer;
    }
    public void connectPlayer(Player userName){
        onlinePlayer.add(userName);
    }

    public int getGuestNumber() {
        return guestNumber;
    }

    public void guestNumberCountUp() {
        this.guestNumber++;
    }
    public void playerOnline(Player temp) {
        if (!isPlayerOnline(temp))
            this.onlinePlayer.add(temp);
    }

    public boolean isPlayerOnline(Player temp){
        for (Player player : onlinePlayer){
            if (player.getName().equals(temp.getName())){
                return true;
            }
        }
        return false;
    }

    public List<Player> getDAOPlayers() {
        return DAOPlayers;
    }

    public void addPlayerDAO(Object player) {
        this.DAOPlayers.add((Player) player); ;
    }
    public Player getExistingPlayer(Object objectFromClient) {
        for (Player player : DAOPlayers){
            if (player.getName().equals(objectFromClient.toString())){
                System.out.println("Player found");
                return player;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        GameServer gameServer = new GameServer();
        int port = 5555;
        gameServer.start(port);
    }


}
