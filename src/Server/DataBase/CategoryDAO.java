package Server.DataBase;

import java.util.List;

public class CategoryDAO<T> implements QuestionsInterface<T> {

    private final List<T> questions;

    public CategoryDAO(List<T> questions) {
        this.questions = questions;
    }

    @Override
    public List<T> getQuestions() {
        return questions;
    }
}
