package SinglePplayer;

import Server.DataBase.HistoryDAO;
import Server.DataBase.MusicDAO;
import Server.DataBase.SportDAO;
import Server.ServerResponse;
import SinglePplayer.Panel.FinalStrings;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GameProcess {
    private Properties settings = new Properties();
    private int numberOfRounds;
    private int numberOfQuestions;

    private Player player1;
    private Player player2;
    int player1score;
    int player2score;
    int totalScorePlayer1;
    int totalScorePlayer2;

    Player currentPlayer;
    HistoryDAO history = new HistoryDAO();
    MusicDAO music = new MusicDAO();
    SportDAO sport = new SportDAO();

    ServerProtocol protocol;

    public GameProcess(Player player1, Player player2, ServerProtocol protocol) {

        this.player1 = player1;
        this.player2 = player2;
        this.protocol = protocol;
        this.currentPlayer = player1;

    }

    public void play() throws IOException, ClassNotFoundException, InterruptedException {
        Object objectFromClient;

        loadFromProperties();
        // skickar user name till motståndaren
        player1.out.writeObject(new ServerResponse(player2.getName(), 0));
        player2.out.writeObject(new ServerResponse(player1.getName(), 0));


        // loopar antal rundor
        for (int rundor = 0; rundor < numberOfRounds; rundor++) {
            //väljer vem som börjar välja kategori
            Player playerToChoseCategory = player1;
            Player playerToAnswerQuestions = player2;

            // växlar användare varannan runda
            if (rundor % 2 != 0){
                playerToChoseCategory = player2;
                playerToAnswerQuestions = player1;
            }

            // läser in vilken kategori som valts
            objectFromClient = playerToChoseCategory.in.readObject();
            int categoryNumber = categoryNumber(objectFromClient);

            // lista där frågorna sparas så båda spelare får samma frågor
            List<ServerResponse> currentCategoryQuestions = new ArrayList<>();

            for (int questions = 0; questions < numberOfQuestions; questions++) {
                //lägger till en fråga i lista
                currentCategoryQuestions.add(getRandomQuestionFromCategory(categoryNumber));
                //skickar frågor till spelare
                playerToChoseCategory.out.writeObject(currentCategoryQuestions.get(questions));
                playerToChoseCategory.out.writeObject(new ServerResponse(FinalStrings.QUESTIONS.name()));
                //läser svaret från spelaren
                objectFromClient = playerToChoseCategory.in.readObject();
                //om svaret är rätt får rätt spelare poäng
                if (objectFromClient.equals(true)){
                    if (playerToChoseCategory == player1){
                        player1score++;
                        totalScorePlayer1++;
                    } else {
                        player2score++;
                        totalScorePlayer2++;
                    }
                }
            }
            //skickar poängen till spelaren
            if (playerToChoseCategory == player1){
                playerToChoseCategory.out.writeObject(new ServerResponse(player1score+100));
            } else {
                playerToChoseCategory.out.writeObject(new ServerResponse(player2score+100));
            }
            //visar score sidan
            playerToChoseCategory.out.writeObject(new ServerResponse(FinalStrings.SCORE.name()));

            for (int questions = 0; questions < numberOfQuestions; questions++) {
                //Skickar frågor till andra spelaren
                playerToAnswerQuestions.out.writeObject(currentCategoryQuestions.get(questions));
                playerToAnswerQuestions.out.writeObject(new ServerResponse(FinalStrings.QUESTIONS.name()));
                //tar in svaret
                objectFromClient = playerToAnswerQuestions.in.readObject();
                //ger rätt spelare poäng
                if (objectFromClient.equals(true)){
                    if (playerToAnswerQuestions == player1){
                        player1score++;
                        totalScorePlayer1++;
                    } else {
                        player2score++;
                        totalScorePlayer2++;
                    }
                }
            }

            //skickar spelarens poäng till motståndaren
            if (playerToChoseCategory == player1){
                playerToChoseCategory.out.writeObject(new ServerResponse(player2score+200));
            } else {
                playerToChoseCategory.out.writeObject(new ServerResponse(player1score+200));
            }
            // visar spelaren score sidan
            playerToChoseCategory.out.writeObject(new ServerResponse(FinalStrings.SCORE.name()));

            //skickar poängen till spelaren
            if (playerToChoseCategory == player1){
                playerToAnswerQuestions.out.writeObject(new ServerResponse(player2score+100));
            } else{
                playerToAnswerQuestions.out.writeObject(new ServerResponse(player1score+100));
            }

            //skickar poängen till motståndaren
            if (playerToChoseCategory == player1){
                playerToAnswerQuestions.out.writeObject(new ServerResponse(player1score+200));
            } else{
                playerToAnswerQuestions.out.writeObject(new ServerResponse(player2score+200));
            }
            //nollställer poängen
            player1score = 0;
            player2score = 0;
            //tömmer listan med frågor
            currentCategoryQuestions.clear();
            //visar båda spelare score sidan
            playerToAnswerQuestions.out.writeObject(new ServerResponse(FinalStrings.SCORE.name()));
            playerToChoseCategory.out.writeObject(new ServerResponse(FinalStrings.SCORE.name()));
            // 2 sekunders fördröjning
            Thread.sleep(2000);
            // skickar spelare som ska välja kategori till category sidan
            playerToAnswerQuestions.out.writeObject(new ServerResponse(FinalStrings.CATEGORY.name()));
        }
        //om alla rundor är slut win sidan för båda
        player1.out.writeObject(new ServerResponse(FinalStrings.WIN.name()));
        player2.out.writeObject(new ServerResponse(FinalStrings.WIN.name()));
    }



    public int categoryNumber(Object objectFromClient){
        if (objectFromClient.toString().equals("history")){
            return 0;
        } else if (objectFromClient.toString().equals("sport")) {
            return 1;
        } else
            return 2;
    }
    public ServerResponse getRandomQuestionFromCategory(int categoryNumber){
        switch (categoryNumber){
            case 0 -> {return new ServerResponse(history.takeRandomQuestion());}
            case 1 -> {return new ServerResponse(sport.takeRandomQuestion());}
            case 2 -> {return new ServerResponse(music.takeRandomQuestion());}
        }
        return new ServerResponse(history.takeRandomQuestion());
    }


    public void loadFromProperties(){
        try {
            settings.load(new FileInputStream("src/SinglePplayer/setting.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String numberOfRoundsTemp = settings.getProperty("numberOfRoundsInGame", "2");
        int tempRounds = Integer.parseInt(numberOfRoundsTemp);
        setNumberOfRounds(tempRounds);

        String numberOfQuestionsTemp = settings.getProperty("numberOfQuestions", "2");
        int tempQuestions = Integer.parseInt(numberOfQuestionsTemp);
        setNumberOfQuestions(tempQuestions);

    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }
}
