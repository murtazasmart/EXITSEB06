package Murtaza;

import java.io.Serializable;
import java.net.Socket;

/**
 * Created by MA_Laptop on 6/3/2017.
 */
public class Player implements Serializable{
    String username;
    int score;
    String[][] otherPlayerCards;

    public Player() {
    }

    public Player(ClientThread clientThread){
        username = clientThread.username;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
