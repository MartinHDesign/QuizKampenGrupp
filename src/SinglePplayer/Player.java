package SinglePplayer;

import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class  Player {
    String name = "Guest Player";
    ImageIcon avatar = new ImageIcon(); // kommer när vi har avatar bilder
    private int score = 0;
    private int highScore = 0;
    Socket socketToClient;
    ObjectOutputStream out;
    ObjectInputStream in;
    public Player(String userName, Socket socketToClient, ObjectOutputStream out, ObjectInputStream in){
        this.name = userName;
        this.socketToClient = socketToClient;
        this.out = out;
        this.in = in;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageIcon getAvatar() {
        return avatar;
    }

    public void setAvatar(ImageIcon avatar) {
        this.avatar = avatar;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void gainOnePoint() {
        this.score++;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public Socket getSocketToClient() {
        return socketToClient;
    }

    public void setSocketToClient(Socket socketToClient) {
        this.socketToClient = socketToClient;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }
}
