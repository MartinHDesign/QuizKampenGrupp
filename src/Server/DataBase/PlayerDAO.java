package Server.DataBase;

import SinglePplayer.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    private List<Player> playerDAO = new ArrayList<>();


    public void writeNewPlayerToFile(List<Player>updatedPlayerDAO) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Server/DataBase/Playerlist.txt",true))) {

            for (Player player : updatedPlayerDAO) {
                writer.write(player.getName() + "," + player.getHighScore());
                writer.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readPlayerFromFile(){

        String playerList = "src/Server/DataBase/Playerlist.txt";

        try (BufferedReader readFromFile = new BufferedReader(new FileReader(playerList))) {
            String line;
            while ((line = readFromFile.readLine()) != null) {
                String[] playerListData = line.split(",");
                String userName = playerListData[0];
                int highScore = Integer.parseInt(playerListData[1]);

                Player temp = new Player(userName,highScore,null);
                playerDAO.add(temp);
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
