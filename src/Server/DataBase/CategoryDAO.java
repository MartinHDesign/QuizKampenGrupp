package Server.DataBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CategoryDAO<T> implements QuestionsInterface<T> {

    private final List<T> allQuestions;
    private List<T> availableQuestions;
    public CategoryDAO(List<T> questions) {
        this.allQuestions = new ArrayList<>(questions);
        this.availableQuestions = new ArrayList<>(questions);
        shuffleQuestions();
    }

    @Override
    public List<T> getQuestions() {
        return availableQuestions;
    }

    @Override
    public void shuffleQuestions() {
        Collections.shuffle(availableQuestions);
    }

    public T chooseRandomQuestion() {
        if (availableQuestions.isEmpty()) {
            // Återställ listan om alla frågor har använts
            availableQuestions = new ArrayList<>(allQuestions);
            shuffleQuestions();
        }

        // Välj en slumpmässig fråga och ta bort den från tillgängliga frågor
        Random random = new Random();
        int index = random.nextInt(availableQuestions.size());
        T chosenQuestion = availableQuestions.remove(index);

        return chosenQuestion;
    }
}
