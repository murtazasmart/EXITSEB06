package Model;

import java.util.Date;

/**
 * Created by Bhagya Rathnayake on 6/25/2017.
 */
public class LeaderModel {

    private String userName;
    private String gameDate;
    private int Score;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }
}
