package Murtaza;

import java.io.Serializable;
import java.net.Socket;

/**
 * Created by MA_Laptop on 6/3/2017.
 */
public class Player implements Serializable{
    String username;
    String score;

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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
