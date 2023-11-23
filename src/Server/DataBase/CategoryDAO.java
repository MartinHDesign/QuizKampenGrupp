package Server.DataBase;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO<T> implements QuestionsInterface<T> {

    private  List<T> questions = new ArrayList<>();

    public CategoryDAO(List<T> questions) {
        this.questions = questions;
    }

    public CategoryDAO() {

    }

    @Override
    public List<T> getQuestions() {
        return questions;
    }
}
