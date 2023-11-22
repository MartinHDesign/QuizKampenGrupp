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

    public static void main(String[] args) {
        GameServer gameServer = new GameServer();
        int port = 55555;
        gameServer.start(port);
    }
}
