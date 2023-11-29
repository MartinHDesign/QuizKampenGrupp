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
    private int player1score;
    private int player2score;
    private int totalScorePlayer1;
    private int totalScorePlayer2;

    private Player playerToChoseCategory;
    private Player playerToAnswerQuestions;
    private int categoryNumber;
    private Object objectFromClient;
    private List<ServerResponse> currentCategoryQuestions = new ArrayList<>();


    HistoryDAO history = new HistoryDAO();
    MusicDAO music = new MusicDAO();
    SportDAO sport = new SportDAO();

    ServerProtocol protocol;

    public GameProcess(Player player1, Player player2, ServerProtocol protocol) {

        this.player1 = player1;
        this.player2 = player2;
        this.protocol = protocol;
        this.playerToChoseCategory = player1;
        this.playerToAnswerQuestions = player2;

    }

    public void play() throws IOException, ClassNotFoundException, InterruptedException {

        loadFromProperties();
        // skickar user name till motståndaren
        player1.out.writeObject(new ServerResponse(player2.getName(), 0));
        player2.out.writeObject(new ServerResponse(player1.getName(), 0));


        // loopar antal rundor
        for (int rundor = 0; rundor < numberOfRounds; rundor++) {
            // växlar användare varannan runda
            swapPlayerToChooseCategory(rundor);
            // läser in vilken kategori som valts
            readCategoryFromPlayer();
            // lista där frågorna sparas så båda spelare får samma frågor
            for (int questions = 0; questions < numberOfQuestions; questions++) {
                //lägger till en fråga i lista
                currentCategoryQuestions.add(getRandomQuestionFromCategory(categoryNumber));
                //skickar frågor tar emot svar
                sendQuestionsToPlayerWhoChooseCategory(questions);
                //om svaret är rätt får rätt spelare poäng
                updateCategoryScore();
                System.out.println("player 1 score " + player1score);
            }
            sendCategoryScoreToGui();
            for (int questions = 0; questions < numberOfQuestions; questions++) {
                //skickar frågor till spelare 2
                SendQuestionsToPlayerWhoAnswers(questions);
                //ger rätt spelare poäng
                updateAnswerScore();

            }
            //skickar spelarens poäng till motståndaren
            sendOpponentsScoreToCategoryPlayer();
            //skickar poängen till spelaren
            sendAnswerScoreToGui();
            //skickar poängen till motståndaren
            sendOpponentsScoreToAnswerPlayer();
            //nollställer poängen
            resetScoreAndQuestions();
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
    public void updateCategoryScore(){
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
    public void updateAnswerScore(){
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
    public void resetScoreAndQuestions(){
        player1score = 0;
        player2score = 0;
        //tömmer listan med frågor
        currentCategoryQuestions.clear();
    }
    public void sendOpponentsScoreToAnswerPlayer() throws IOException {
        if (playerToChoseCategory == player1){
            playerToAnswerQuestions.out.writeObject(new ServerResponse(player1score+200));
        } else{
            playerToAnswerQuestions.out.writeObject(new ServerResponse(player2score+200));
        }
    }
    public void sendOpponentsScoreToCategoryPlayer() throws IOException {
        if (playerToChoseCategory == player1){
            playerToChoseCategory.out.writeObject(new ServerResponse(player2score+200));
        } else {
            playerToChoseCategory.out.writeObject(new ServerResponse(player1score+200));
        }
        // visar spelaren score sidan
        playerToChoseCategory.out.writeObject(new ServerResponse(FinalStrings.SCORE.name()));
    }
    public void sendAnswerScoreToGui() throws IOException {
        if (playerToChoseCategory == player1){
            playerToAnswerQuestions.out.writeObject(new ServerResponse(player2score+100));
        } else{
            playerToAnswerQuestions.out.writeObject(new ServerResponse(player1score+100));
        }
    }
    public void sendCategoryScoreToGui() throws IOException {
        //skickar poängen till spelaren
        if (playerToChoseCategory == player1){
            playerToChoseCategory.out.writeObject(new ServerResponse(player1score+100));
        } else {
            playerToChoseCategory.out.writeObject(new ServerResponse(player2score+100));
        }
        //visar score sidan
        playerToChoseCategory.out.writeObject(new ServerResponse(FinalStrings.SCORE.name()));
    }
    public void SendQuestionsToPlayerWhoAnswers(int questions) throws IOException, ClassNotFoundException {
        //Skickar frågor till andra spelaren
        playerToAnswerQuestions.out.writeObject(currentCategoryQuestions.get(questions));
        playerToAnswerQuestions.out.writeObject(new ServerResponse(FinalStrings.QUESTIONS.name()));
        //tar in svaret
        objectFromClient = playerToAnswerQuestions.in.readObject();
    }
    public void sendQuestionsToPlayerWhoChooseCategory(int questions) throws IOException, ClassNotFoundException {
        //skickar frågor till spelare
        playerToChoseCategory.out.writeObject(currentCategoryQuestions.get(questions));
        playerToChoseCategory.out.writeObject(new ServerResponse(FinalStrings.QUESTIONS.name()));
        //läser svaret från spelaren
        objectFromClient = playerToChoseCategory.in.readObject();
    }
    public void readCategoryFromPlayer() throws IOException, ClassNotFoundException {
        objectFromClient = playerToChoseCategory.in.readObject();
        categoryNumber = categoryNumber(objectFromClient);
    }

    public void swapPlayerToChooseCategory(int rundor){
        if (rundor % 2 != 0){
            playerToChoseCategory = player2;
            playerToAnswerQuestions = player1;
        } else {
            playerToChoseCategory = player1;
            playerToAnswerQuestions = player2;
        }
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

    public void setPlayerToChoseCategory(Player playerToChoseCategory) {
        this.playerToChoseCategory = playerToChoseCategory;
    }

    public void setPlayerToAnswerQuestions(Player playerToAnswerQuestions) {
        this.playerToAnswerQuestions = playerToAnswerQuestions;
    }
}
