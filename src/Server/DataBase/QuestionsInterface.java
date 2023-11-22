package Server.DataBase;



import java.util.List;

public interface QuestionsInterface<T> {

    List<T> getQuestions();

    void shuffleQuestions();
}
