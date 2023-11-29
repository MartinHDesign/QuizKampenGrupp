package Server.DataBase;

import Server.DataBase.Questions.History.HistoryAnswer;
import Server.DataBase.Questions.History.historyQuestion;
import Server.DataBase.Questions.Music.MusicAnswer;
import Server.DataBase.Questions.Music.MusicQuestion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MusicDAO extends CategoryDAO <MusicQuestion>{

    // Lista för att lagra musikrelaterade frågor
    private List<MusicQuestion> MusicQuestions = new ArrayList<>();

    // Konstruktor för att skapa musikrelaterade frågor och svarsalternativ
    public MusicDAO() {

        // Fråga 1
        List<MusicAnswer> answer0 = new ArrayList<>();
        answer0.add(new MusicAnswer("Beatles", true));
        answer0.add(new MusicAnswer("Rolling Stones", false));
        answer0.add(new MusicAnswer("Led Zeppelin", false));
        answer0.add(new MusicAnswer("Queen", false));

        // Skapa en musikrelaterad fråga och lägg till den i listan
        MusicQuestion question0 = new MusicQuestion("Vilket band släppte albumet 'Abbey Road'?", answer0);
        MusicQuestions.add(question0);

        // Fråga 2
        List<MusicAnswer> answer1 = new ArrayList<>();
        answer1.add(new MusicAnswer("Michael Jackson", false));
        answer1.add(new MusicAnswer("Madonna", false));
        answer1.add(new MusicAnswer("Prince", true));
        answer1.add(new MusicAnswer("Whitney Houston", false));

        // Skapa en musikrelaterad fråga och lägg till den i listan
        MusicQuestion question1 = new MusicQuestion("Vem kallas 'The Purple One' inom musikvärlden?", answer1);
        MusicQuestions.add(question1);

        // Fråga 3
        List<MusicAnswer> answer2 = new ArrayList<>();
        answer2.add(new MusicAnswer("Jazz", false));
        answer2.add(new MusicAnswer("Rock", true));
        answer2.add(new MusicAnswer("Country", false));
        answer2.add(new MusicAnswer("R&B", false));

        // Skapa en musikrelaterad fråga och lägg till den i listan
        MusicQuestion question2 = new MusicQuestion("Vilken musikgenre är kännetecknande för bandet Queen?", answer2);
        MusicQuestions.add(question2);

    }
    public MusicQuestion takeRandomQuestion(){
        if (MusicQuestions.isEmpty()) {
            MusicDAO newListOfQuestions = new MusicDAO();
            MusicQuestions = newListOfQuestions.getMusicQuestions();
        }
        Random random = new Random();
        int questionToTake = random.nextInt(MusicQuestions.size());

        MusicQuestion toReturn = MusicQuestions.get(questionToTake);
        MusicQuestions.remove(questionToTake);

        return toReturn;
    }

    // Metod för att hämta listan av musikrelaterade frågor
    public List<MusicQuestion> getMusicQuestions() {
        return MusicQuestions;
    }
}
