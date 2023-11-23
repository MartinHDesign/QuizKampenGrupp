package Server.DataBase;

import java.io.Serializable;

public class Player implements Serializable {
    private String name;

    private int score;

    public Player() {
    }

    public Player(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void gainOnePoint() {
        this.score++;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
