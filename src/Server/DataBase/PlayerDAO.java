package Server.DataBase;

import SinglePplayer.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    private List<Player> playerDAO = new ArrayList<>(); // kalle,0


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
                System.out.println(userName);
                int highScore = Integer.parseInt(playerListData[1]);
                System.out.println(highScore);

                //första linen som läses in = martin,8
                Player temp = new Player(userName,highScore,null); // username, highscore
                playerDAO.add(temp);
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        PlayerDAO playerDAO = new PlayerDAO();
        playerDAO.readPlayerFromFile();


        List<Player> updatedPlayerDAO = new ArrayList<>();
        Player newPlayer = new Player("username",10,null);
        Player newPlayer2 = new Player("username2",10,null);
        Player newPlayer3 = new Player("username3",10,null);
        Player newPlayer4 = new Player("username4",10,null);
        updatedPlayerDAO.add(newPlayer);
        updatedPlayerDAO.add(newPlayer2);
        updatedPlayerDAO.add(newPlayer3);
        updatedPlayerDAO.add(newPlayer4);

        playerDAO.writeNewPlayerToFile(updatedPlayerDAO);



        for(Player player : playerDAO.playerDAO) {
            System.out.println(player.getName() + player.getHighScore());
        }

    }
}
