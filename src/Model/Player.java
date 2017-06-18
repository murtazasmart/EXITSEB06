package Model;

import java.io.Serializable;

/**
 * Created by MA_Laptop on 6/18/2017.
 */
public class Player implements Serializable {
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

    Referee re=new Referee();
    int playersc=re.StartGame();
    String[][] playerHand=null;

    public String[][] displayHand(){
        playerHand=re.DistributeCard(5);
        for(int j=0;j<5;j++){
            System.out.print("player "+j+" owned cards:");
            for (int i=0;i<5;i++) {
                System.out.print(""+playerHand[j][i]+",");
            }
            System.out.println("");
        }
        //display user1 hand
        for (int i=0;i<5;i++){
            System.out.print("user 1 card "+playerHand[0][i]);
        }
        for (int i=0;i<5;i++){
            System.out.print(" user 2 card "+playerHand[1][i]);

        }
        for (int i=0;i<5;i++){
            System.out.print("user 3 card "+playerHand[2][i]);
        }
        for (int i=0;i<5;i++){
            System.out.print("user 4 card "+playerHand[3][i]);
        }
        for (int i=0;i<5;i++){
            System.out.print("user 5 card "+playerHand[4][i]);
        }

        //end of for loop
        return playerHand;
    }// end of display hand

    //declaring point array
    public  int[][] marks = { {20,19,18,17},
            {19,18,17,16},
            {18,17,16,15},
            {17,16,15,14},
            {16,15,14,13},
            {15,14,13,12},
            {14,13,12,11},
            {12,11,10,9},
            {11,10,9,8},
            {10,9,8,7},
            {9,8,7,6},
            {8,7,6,5} };
    Card cr=new Card();

    public String splitCard(String[][] array){

        //array=new String[]{"s-2","d-10","h-6","s-4","s-9"};
        String type=null;
        String value=null;
        array=displayHand();
        for(int i=0;i<5;i++){
            //String[] word=pack[i].split("-");
            type=array[0][i].substring(0,cr.pack[i].indexOf("-"));
            value=array[0][i].substring(cr.pack[i].indexOf("-")+1);
            //String type=word[i];
            System.out.println("type="+type);
            System.out.println("value="+value);
           /* for(String result:word){
            System.out.println(result);
            }*/
        }

        return type;

    }
}//end of player class