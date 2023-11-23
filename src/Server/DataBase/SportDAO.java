package Server.DataBase;

import Server.DataBase.SportQuestions.SportAnswer;
import Server.DataBase.SportQuestions.SportQuestion;

import java.util.ArrayList;
import java.util.List;

public class SportDAO extends CategoryDAO<SportQuestion> {

    // Lista för att lagra sportrelaterade frågor
    private final List<SportQuestion> sportQuestions = new ArrayList<>();

    // Konstruktor för att skapa sportrelaterade frågor och svarsalternativ
    public SportDAO() {


        // Fråga 1
        List<SportAnswer> answer0 = new ArrayList<>();
        answer0.add(new SportAnswer("Muhammad Ali", false));
        answer0.add(new SportAnswer("Mike Tyson", true));
        answer0.add(new SportAnswer("Evander Holyfield", false));
        answer0.add(new SportAnswer("Lennox Lewis", false));

        // Skapa en sportrelaterad fråga och lägg till den i listan
        SportQuestion question0 = new SportQuestion("Vem är känt som 'The Baddest Man on the Planet' inom boxning?", answer0);
        sportQuestions.add(question0);

        // Fråga 2
        List<SportAnswer> answer1 = new ArrayList<>();
        answer1.add(new SportAnswer("Usain Bolt", true));
        answer1.add(new SportAnswer("Michael Phelps", false));
        answer1.add(new SportAnswer("Serena Williams", false));
        answer1.add(new SportAnswer("Roger Federer", false));

        // Skapa en sportrelaterad fråga och lägg till den i listan
        SportQuestion question1 = new SportQuestion("Vem är känd som 'Världens snabbaste man'?", answer1);
        sportQuestions.add(question1);

        // Fråga 3
        List<SportAnswer> answer2 = new ArrayList<>();
        answer2.add(new SportAnswer("Super Bowl", false));
        answer2.add(new SportAnswer("World Series", false));
        answer2.add(new SportAnswer("FIFA World Cup", true));
        answer2.add(new SportAnswer("NBA Finals", false));

        // Skapa en sportrelaterad fråga och lägg till den i listan
        SportQuestion question2 = new SportQuestion("Vilken turnering är mest känd inom fotboll?", answer2);
        sportQuestions.add(question2);

        // Fråga 4
        List<SportAnswer> answer3 = new ArrayList<>();
        answer3.add(new SportAnswer("Roger Federer", false));
        answer3.add(new SportAnswer("Rafael Nadal", false));
        answer3.add(new SportAnswer("Novak Djokovic", true));
        answer3.add(new SportAnswer("Andy Murray", false));

        // Skapa en sportrelaterad fråga och lägg till den i listan
        SportQuestion question3 = new SportQuestion("Vilken tennisspelare har vunnit flest Grand Slam-titlar?", answer3);
        sportQuestions.add(question3);
    }

    // Metod för att hämta listan av sportrelaterade frågor
    public List<SportQuestion> getSportQuestions() {
        return sportQuestions;
    }
}
