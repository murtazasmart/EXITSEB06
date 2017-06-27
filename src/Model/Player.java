package Model;

import java.io.Serializable;

/**
 * Created by MA_Laptop on 6/18/2017.
 */
public class Player implements Serializable {


    private String username;
    private boolean isKicked = false;
    private int numberofplayers;
    private boolean swapCards[];
    private int risk, alpha;
    private int playerId;
    private String[] allUsernames;
    private  int[] allScores;
    private int playerIdToDonatePoints;
    private int Score;
    private String[][] cardHand = new String[numberofplayers][5];

    public Player() {
    }

    public Player(ClientThread clientThread){
        username = clientThread.getUsername();

    }

    public int getNumberofplayers() {
        return numberofplayers;
    }

    public void setNumberofplayers(int numberofplayers) {
        this.numberofplayers = numberofplayers;
    }

    public boolean[] getSwapCards() {
        return swapCards;
    }

    public void setSwapCards(boolean[] swapCards) {
        this.swapCards = swapCards;
    }

    public String[][] getCardHand(){
        return cardHand;
    }
    public void setCardHand(String[][] CardHand){
        this.cardHand =CardHand;
    }

    public void setIndividualCardHand(int user, int cardindx, String cardval){
        this.cardHand[user][cardindx]=cardval;
    }

    public String getIndividualCardHand(int user, int cardindx){
        return cardHand[user][cardindx];
    }

    public int getScore(){
        return Score;
    }

    public void setScore(int Score){
        this.Score=Score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void getObjectReady(int playerscount) {
        swapCards = new boolean[5];
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String[] getAllUsernames() {
        return allUsernames;
    }

    public void setAllUsernames(String[] allUsernames) {
        this.allUsernames = allUsernames;
    }

    public boolean isKicked() {
        return isKicked;
    }

    public void setKicked(boolean kicked) {
        isKicked = kicked;
    }

    public int getPlayerIdToDonatePoints() {
        return playerIdToDonatePoints;
    }

    public void setPlayerIdToDonatePoints(int playerIdToDonatePoints) {
        this.playerIdToDonatePoints = playerIdToDonatePoints;
    }

    public void updateDonationScore(int score){
        Score += score;
    }

    public int[] getAllScores() {
        return allScores;
    }

    public void setAllScores(int[] allScores) {
        this.allScores = allScores;
    }

    public int getRisk() {
        return risk;
    }

    public void setRisk(int risk) {
        this.risk = risk;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }
}//end of player class