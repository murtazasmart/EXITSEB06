package Model;

import java.io.Serializable;

/**
 * Created by MA_Laptop on 6/18/2017.
 */
public class Player implements Serializable {


    private String username;
    boolean isKicked;
    private int numberofplayers;
    private boolean swapCards[];
    int roundARisk;
    int roundBRisk;
    private int playerId;

    public Player() {
    }

    public Player(ClientThread clientThread){
        username = clientThread.username;

    }


    /**
     * @return the numberofplayers
     */
    public int getNumberofplayers() {
        return numberofplayers;
    }

    /**
     * @param numberofplayers the numberofplayers to set
     */
    public void setNumberofplayers(int numberofplayers) {
        this.numberofplayers = numberofplayers;
    }

    /**
     * @return the swapCards
     */
    public boolean[] getSwapCards() {
        return swapCards;
    }

    /**
     * @param swapCards the swapCards to set
     */
    public void setSwapCards(boolean[] swapCards) {
        this.swapCards = swapCards;
    }

//   Refree re=new Refree();
//   int playersc=re.StartGame();
//   String[][] playerHand=null;
//
//   public String[][] displayHand(){
//        playerHand=re.DistributeCard(5);
//        for(int j=0;j<5;j++){
//            System.out.print("player "+j+" owned cards:");
//            for (int i=0;i<5;i++) {
//                System.out.print(""+playerHand[j][i]+",");
//
//            }
//            System.out.println("");
//        }
//        //display user1 hand
//        for (int j=0;j<5;j++){
//            for (int i=0;i<5;i++){
//              System.out.print("\n user" +(j+1)+"  card "+playerHand[j][i]+"\n");
//        }
//        }
//
//        //end of for loop
//        return playerHand;
//   }// end of display hand
//
//
//   Card cr=new Card();
//   int point=0;
//   public int GetType(String[][] array){
//
//
//        String type=null;
////
//            array=playerHand;
////       // array=displayHand();
//        int sum=0;
//    for(int j=0;j<5;j++){
//        for(int i=0;i<5;i++){
////
//            type=array[j][i].substring(0,playerHand[j][i].indexOf("-"));
////
//           System.out.println("user "+ j+" card type= "+type);
//
//       switch(type){
//
//           case "s":
//               point=5;
//               //sum+=point;
//           break;
//
//           case "d":
//               point=4;
//               //sum+=point;
//           break;
//
//           case "c":
//               point=3;
//               //sum+=point;
//           break;
//
//           case "h":
//               point=2;
//               //sum+=point;
//           break;
//
//       }
//              sum+=point;
//
//       }
//
//           //sum+=point;
//           System.out.println("point"+point);
//           System.out.println("sum "+sum);
//            }
//
//     return sum;
//}
//
//
//
//
//   public String GetValue(String[][] array){
//
//
//        String value=null;
//            array=playerHand;
//
//    for(int j=0;j<5;j++){
//        for(int i=0;i<5;i++){
//
//            value=array[j][i].substring(playerHand[j][i].indexOf("-")+1);
//
//           System.out.println("user "+ j+ " card value= "+value);
//
//        }
//}
//
//            return value;
//
//  }
//
//
//   //get the username from userid
//   public String[][] user_info(){
//
//        String[][] userDetail=new String[1][4];//for 5 users
//
//        // i dont how u guys get those user is and stuff fill this and return it
//
//
//
//    return null;
//}
//
//
//   //2d array to keep the cards to change
//   public String[][] changeHand(){
//
//   String[][] change=new  String[4][3]; //maximum 3 cards to be change
//
//
//
//
//       return null;
//   }




    //initial card hand


    private String[][] CardHand=new String[numberofplayers][5];

    public String[][] getCardHand(){
        return CardHand;

    }
    public void setCardHand(String[][] CardHand){
        this.CardHand=CardHand;
    }

    public void setIndividualCardHand(int user, int cardindx, String cardval){
        this.CardHand[user][cardindx]=cardval;
    }
    public String getIndividualCardHand(int user, int cardindx){
        return CardHand[user][cardindx];
    }

    //score
    private int Score;
    public int getScore(){
        return Score;
    }

    public void setScore(int Score){
        this.Score=Score;
    }

    //to keep the username for eac user id
    private String[] user_info=new String[numberofplayers];

    public String[] getUser_info(){
        return user_info;
    }
    public void setUser_info(String[] user_info){
        this.user_info=user_info;
    }

    //change card hand
    private String[] ChangeHand=new String[5];//private String[][] ChangeHand=new String[numberofplayers][5];

    public String[] getChangeHand(){//public String[][] getChangeHand(){
        return ChangeHand;
    }

    public void setChangeHand(String[] ChangeHand){//public void setChangeHand(String[][] ChangeHand){
        this.ChangeHand=ChangeHand;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    void getObjectReady(int playerscount) {
        swapCards = new boolean[playerscount];
    }

    /**
     * @return the playerId
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * @param playerId the playerId to set
     */
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }





//    String username;
//    int score;
//    String[][] otherPlayerCards;
//
//    public Player() {
//    }
//
//    public Player(ClientThread clientThread){
//        username = clientThread.username;
//
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public int getScore() {
//        return score;
//    }
//
//    public void setScore(int score) {
//        this.score = score;
//    }
//
//    Referee re=new Referee();
//    //int playersc=re.StartGame();
//    String[][] playerHand=null;
//
//    public String[][] displayHand(){
//        playerHand=re.DistributeCard(5);
//        for(int j=0;j<5;j++){
//            System.out.print("player "+j+" owned cards:");
//            for (int i=0;i<5;i++) {
//                System.out.print(""+playerHand[j][i]+",");
//            }
//            System.out.println("");
//        }
//        //display user1 hand
//        for (int i=0;i<5;i++){
//            System.out.print("user 1 card "+playerHand[0][i]);
//        }
//        for (int i=0;i<5;i++){
//            System.out.print(" user 2 card "+playerHand[1][i]);
//
//        }
//        for (int i=0;i<5;i++){
//            System.out.print("user 3 card "+playerHand[2][i]);
//        }
//        for (int i=0;i<5;i++){
//            System.out.print("user 4 card "+playerHand[3][i]);
//        }
//        for (int i=0;i<5;i++){
//            System.out.print("user 5 card "+playerHand[4][i]);
//        }
//
//        //end of for loop
//        return playerHand;
//    }// end of display hand
//
//    //declaring point array
//    public  int[][] marks = { {20,19,18,17},
//            {19,18,17,16},
//            {18,17,16,15},
//            {17,16,15,14},
//            {16,15,14,13},
//            {15,14,13,12},
//            {14,13,12,11},
//            {12,11,10,9},
//            {11,10,9,8},
//            {10,9,8,7},
//            {9,8,7,6},
//            {8,7,6,5} };
//    Card cr=new Card();
//
//    public String splitCard(String[][] array){
//
//        //array=new String[]{"s-2","d-10","h-6","s-4","s-9"};
//        String type=null;
//        String value=null;
//        array=displayHand();
//        for(int i=0;i<5;i++){
//            //String[] word=pack[i].split("-");
//            type=array[0][i].substring(0,cr.pack[i].indexOf("-"));
//            value=array[0][i].substring(cr.pack[i].indexOf("-")+1);
//            //String type=word[i];
//            System.out.println("type="+type);
//            System.out.println("value="+value);
//           /* for(String result:word){
//            System.out.println(result);
//            }*/
//        }
//
//        return type;
//
//    }
}//end of player class