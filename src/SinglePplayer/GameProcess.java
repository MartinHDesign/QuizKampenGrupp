package SinglePplayer;

import Server.DataBase.HistoryDAO;
import Server.DataBase.MusicDAO;
import Server.DataBase.SportDAO;
import Server.ServerResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

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
    private int questionsAnswered;

    private boolean playerSwapped;

    ServerProtocol protocol;

    public GameProcess(Player player1, Player player2, ServerProtocol protocol) {

        this.player1 = player1;
        this.player2 = player2;
        this.protocol = protocol;
        this.currentPlayer = player1;

    }

    public void play() throws IOException, ClassNotFoundException, InterruptedException {
        System.out.println("här 1");

        Object objectFromClient;

        getProperties();


        int rounds = numberOfRounds; // från property
        int questionsAmount = numberOfQuestions; // från property


        //skickar namn till andra spelaren
        player1.out.writeObject(new ServerResponse(player2.getName(), 0));
        player2.out.writeObject(new ServerResponse(player1.getName(), 0));

        // loopar antal rundor
        for (int rundor = 0; rundor < rounds; rundor++) {
            //p1 skickar vald kategori
            objectFromClient = player1.in.readObject();
            // nummer för att identifiera vilken kategori som valts
            int categoryNumber = categoryNumber(objectFromClient);

            // lista där frågor/svar från vald kategori sparas
            List<ServerResponse> currentCategoryQuestions = new ArrayList<>();

            //loopar för antal frågor
            for (int questions = 0; questions < questionsAmount; questions++) {
                // lägger till en fråga/svar i listan så samma fråga kan skickas till p2
                currentCategoryQuestions.add(getRandomQuestionFromCategory(categoryNumber));
                //serv skickar ny fråga till p1
                player1.out.writeObject(currentCategoryQuestions.get(questions));
                // server säger åt player1 att gå till question screen
                player1.out.writeObject(new ServerResponse("QUESTIONS"));
                //får svaret från p1
                objectFromClient = player1.in.readObject();
                //+ poäng om rätt svar
                if (objectFromClient.equals(true)){
                    player1score++;
                    totalScorePlayer1++;
                }

            }
            System.out.println("p1 svarat på 2 frågor");
            //skickar poängen till player1
            player1.out.writeObject(new ServerResponse(player1score+100));
            System.out.println("byt p1 till score");
            //p1 till score sidan
            player1.out.writeObject(new ServerResponse("SCORE"));
            // player 2 får samma frågor som player1
            for (int questions = 0; questions < questionsAmount; questions++) {
                //serv skickar ny fråga till p2
                player2.out.writeObject(currentCategoryQuestions.get(questions));
                //säger åt player 2 att visa question screen
                player2.out.writeObject(new ServerResponse("QUESTIONS"));
                //får svaret från p2
                objectFromClient = player2.in.readObject();
                // om rätt ger p2 poäng
                if (objectFromClient.equals(true)){
                    player2score++;
                    totalScorePlayer2++;
                }
            }
            // skickar player2 poäng till player1
            player1.out.writeObject(new ServerResponse(player2score+200));
            //säger åt player1 att visa score screen
            player1.out.writeObject(new ServerResponse("SCORE"));
            //skickar player2s poäng till player2
            player2.out.writeObject(new ServerResponse(player2score+100));
            // skickar player1 poäng till player2
            player2.out.writeObject(new ServerResponse(player1score+200));
            // återställer poängen för nästa fråga
            player1score = 0;
            player2score = 0;

            System.out.println("p2 svarat på 2 frågor");

            // rensar listan med frågor
            currentCategoryQuestions.clear();
            //skicka category = byt till category sidan fär p2
            player2.out.writeObject(new ServerResponse("CATEGORY"));

            //får svar p2 vilken kategori
            objectFromClient = player2.in.readObject();
            // nummer för att identifiera vilken kategori som valts
            categoryNumber = categoryNumber(objectFromClient);

            for (int questions = 0; questions < questionsAmount; questions++) {
                // lägger till en fråga/svar i listan så samma fråga kan skickas till p1
                currentCategoryQuestions.add(getRandomQuestionFromCategory(categoryNumber));
                //serv skickar ny fråga till p2
                player2.out.writeObject(currentCategoryQuestions.get(questions));
                player2.out.writeObject(new ServerResponse("QUESTIONS"));
                //får svaret från p2
                objectFromClient = player2.in.readObject();
                // om rätt ger poäng
                if (objectFromClient.equals(true)){
                    player2score++;
                    totalScorePlayer2++;
                }
            }
            System.out.println("p2 har valt kategori och svarat på 2 frågor");

            // skickar player2 score till player2
            player2.out.writeObject(new ServerResponse(player2score+100));
            //skickar player2 score till player1
            player1.out.writeObject(new ServerResponse(player2score+200));
            //nollställer player2 score
            player2score = 0;

            //skickar player 2 till score screen
            player2.out.writeObject(new ServerResponse("SCORE"));
            //byt till player1

            for (int questions = 0; questions < questionsAmount; questions++) {
                //serv skickar nya frågor till p1
                player1.out.writeObject(currentCategoryQuestions.get(questions));
                player1.out.writeObject(new ServerResponse("QUESTIONS"));
                //får svaret från p1
                objectFromClient = player1.in.readObject();
                if (objectFromClient.equals(true)){
                    player1score++;
                    totalScorePlayer1++;
                }
            }
            player1.out.writeObject(new ServerResponse(player1score+100));
            player2.out.writeObject(new ServerResponse(player1score+200));
            player1score = 0;

            player2.out.writeObject(new ServerResponse("SCORE"));
            System.out.println("p1 svarat på 2 frågor");
            player1.out.writeObject(new ServerResponse("CATEGORY"));
            System.out.println(player1score);
            System.out.println(player2score);
        }
        //om alla rundor är slut score screen för båda
        player1.out.writeObject(new ServerResponse("WIN"));
        player2.out.writeObject(new ServerResponse("WIN"));

//        if (player1score > player2score){
//            player1.out.writeObject(new ServerResponse("WIN"));
//            player2.out.writeObject(new ServerResponse("LOSE"));
//        } else if (player2score > player1score){
//            player1.out.writeObject(new ServerResponse("LOSE"));
//            player2.out.writeObject(new ServerResponse("WIN"));
//        } else {
//            player1.out.writeObject(new ServerResponse("DRAW"));
//            player2.out.writeObject(new ServerResponse("DRAW"));
//        }





//        if (objectFromClient instanceof String) {
//            //Tar en in en sträng från "Spela nästa runda"-knappen i SCORE
//            newRound();
//            return;
//        }
//        System.out.println("här 2");
//        Object objectToReturn = protocol.processInput(objectFromClient, currentPlayer);
//
//        currentPlayer.out.writeObject(objectToReturn);
//        questionsAnswered++;
//
//        if (questionsAnswered == numberOfQuestions + 1) {
//            System.out.println("här 3");
//            if (playerSwapped) {
//                System.out.println("här 4");
//                questionsAnswered = 0;
//                sendEndOfRoundResults(objectToReturn);
//                return;
//            }
//            swapCurrentPlayer(player1, player2);
//        }
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

    private void swapCurrentPlayer(Player player1, Player player2) throws IOException {
        if (currentPlayer.equals(player1)) {
            System.out.println("changing to player 2");
            playerSwapped = true;
            currentPlayer = player2;
            questionsAnswered = 0;

        } else if (currentPlayer.equals(player2)) {
            System.out.println("changing to player 1");
            playerSwapped = true;
            currentPlayer = player1;
            questionsAnswered = 0;
        }
    }

    private void sendEndOfRoundResults(Object objectToReturn) throws IOException {
        if (currentPlayer.equals(player1)) {
            player2.out.writeObject(objectToReturn);
            playerSwapped = false;
            questionsAnswered = 0;

        } else if (currentPlayer.equals(player2)) {
            player1.out.writeObject(objectToReturn);
            playerSwapped = false;
            questionsAnswered = 0;
        }
    }

    private void newRound() throws IOException {
        if (currentPlayer.equals(player1)) {
            System.out.println("Sending new game instructions, Player1");
            player1.out.writeObject(new ServerResponse("CATEGORY"));
            player2.out.writeObject(new ServerResponse("WAIT"));

        } if (currentPlayer.equals(player2)) {
            System.out.println("Sending new game instructions, Player1");
            player1.out.writeObject(new ServerResponse("WAIT"));
            player2.out.writeObject(new ServerResponse("CATEGORY"));

        }
    }

    public void getProperties(){
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
