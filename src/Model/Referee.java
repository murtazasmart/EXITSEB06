package Model;

/**
 *
 * @author asus i3
 */
public class Referee {

    Card c;
    String [] cardpackc;
    String[][] playerCarray;
    int playerscount;

    public Player[] startGame(Player[] players){
        playerscount = players.length;
        //Player[] players = new Player[5];
        System.out.println("Game Started (refereee)");
        c = new Card();
        cardpackc = c.Shuffel();
        System.out.println("cards shuffled (refereee)");
        System.out.println("cards=");
        /*for (int i = 0; i < cardpackc.length; i++) {
            System.out.print(cardpackc[i]+",");
        }*/
        c.pushCard(cardpackc);
        System.out.println("pushed");


        //int playesrscout=4;// nede to send the players



        playerCarray = DistributeCard(playerscount);

        String[] usernames = storeUsernames(players);

        for(int i =0; i<playerscount;i++){
            players[i].getObjectReady(playerscount);
            players[i].setNumberofplayers(playerscount);
            players[i].setCardHand(playerCarray);
            players[i].setAllUsernames(usernames);
        }

        playerCarray = null;
        //FROM THIS POINT ON playerCarray will not be used, use the player object GetHand cards

        players = calculateScoreRoundA(players);

        return players;
    }

    public String[] storeUsernames(Player[] players){
        String[] usernames = new String[players.length] ;
        for(int i =0; i< playerscount;i++){
            usernames[i] = players[i].getUsername();
        }
        return usernames;
    }



    public String[][] DistributeCard(int playerscount){
        //System.out.println("then"+c.pullcard());
        playerCarray = new String [playerscount][5];

        if (playerscount*5<=52) {

            for (int i=0;i<5;i++) {

                for(int j=0;j<playerscount;j++){
                    playerCarray[j][i]=c.pullcard();
                }
            }

        }

        //----------------------------------------
        //-------- chk card distribution ---------
        //----------------------------------------

//        System.out.println("");
//        System.out.println("show own cards");
//        for(int j=0;j<playerscount;j++){
//            System.out.print("player "+j+" owned cards:");
//            for (int i=0;i<5;i++) {
//                System.out.print(""+playerCarray[j][i]+",");
//            }
//            System.out.println("");
//        }

        return playerCarray;
    }

   /* public void CardExchange(String[] changecardlist, int playearno){

        for (int i = 0; i < 3; i++) {
            if (changecardlist[i]!="no"||changecardlist[i]!=null) {
//                c.pushCard(changecardlist[i]);
//                changecardlist[i]=c.pullcard();
                for (int j = 0; j < 5; j++) {
                    if (changecardlist[i]== playerCarray[playearno][j]) {
                        c.pushCard(playerCarray[playearno][j]);
                        playerCarray[playearno][j]=c.pullcard();
                    }

                }

            }

        }

        System.out.println(""+changecardlist[1]);

    }*/

    public String swapPlayerCards(String currentCard){
        c.pushCard(currentCard);
        return c.pullcard();
    }

    public Player[] CardExchange(Player[] players){



        for(int i = 0; i< playerscount;i++){
            for(int j =0;j<5;j++){
                if(players[i].getSwapCards()[j] ==true){
                    String currentCard = players[i].getIndividualCardHand(i,j);
                    currentCard = swapPlayerCards(currentCard);
                    players[i].setIndividualCardHand(i, j, currentCard);
                }


            }
        }
        //NOTE INDIVDUAL GET HAND ON EACH PLAYER ISN'T ACCURATE
        return players;
    }

    //declaring point array
//   public  int[][] marks = { {20,19,18,17},
//                          {19,18,17,16},
//                          {18,17,16,15},
//                          {17,16,15,14},
//                          {16,15,14,13},
//                          {15,14,13,12},
//                          {14,13,12,11},
//                          {12,11,10,9},
//                          {11,10,9,8},
//                          {10,9,8,7},
//                          {9,8,7,6},
//                          {8,7,6,5} };
//
//
//
//   int point=0;
//   public int SetMarks(String card_type){
//
//       Player p=new Player();
//      card_type=this.toString();
//    switch(card_type){
//
//           case "s":
//               point=5;
//           break;
//
//           case "d":
//               point=4;
//           break;
//
//           case "c":
//               point=3;
//           break;
//
//           case "h":
//               point=2;
//           break;
//
//       }
//
//           System.out.println("point= "+point);
//           return point;
//
//   }

    public Player[] playerKick(int playerKickedId, Player[] players){
        players[playerKickedId] = null;
        Player[] playersNew = new Player[playerscount-1];
        int count = 0;
        for(int i = 0; i<playerscount;i++){
            if(players[i]==null){
                continue;
            }
            playersNew[count] = players[i];
            count++;
        }

        return playersNew;

    }

    public Player[] calculateScoreRoundA(Player[] players){
        int valueScore=0, typeScore=0, totalScoreNew=0, totalScoreOld, risk=0, alpha;
        String [][] playerCardHand;
        int[] allScores = new int[playerscount];
        for(int i = 0;i<playerscount;i++){
            totalScoreOld = players[i].getScore();
            playerCardHand = players[i].getCardHand();
            typeScore = getTypeScore(i, playerCardHand);
            valueScore = getValueScore(i, playerCardHand);
            risk = getRiskValue(i, playerCardHand);
            alpha = valueScore + typeScore;
            totalScoreNew = typeScore + valueScore + totalScoreOld;
            players[i].setRisk(risk);
            players[i].setScore(totalScoreNew);
            players[i].setAlpha(alpha);
            allScores[i] = totalScoreNew;
        }
        for(int i = 0;i<playerscount;i++){
            players[i].setAllScores(allScores);
        }

        return players;
    }

    public Player[] calculateScoreRoundB(Player[] players){
        int valueScore=0, typeScore=0, beta=0, alpha=0, sigma=1, totalScore;
        String [][] playerCardHand;
        int[] allScores = new int[playerscount];
        for(int i = 0;i<playerscount;i++){
            totalScore = players[i].getScore();
            alpha = players[i].getAlpha();
            playerCardHand = players[i].getCardHand();
            typeScore = getTypeScore(i, playerCardHand);
            valueScore = getValueScore(i, playerCardHand);
            beta = valueScore + typeScore;
            if(alpha<beta){
                sigma = 1;
                totalScore = (alpha-beta)+sigma*players[i].getRisk();
            }else{
                sigma = -1;
                totalScore = (beta-alpha)+sigma*players[i].getRisk();
            }
            players[i].setScore(totalScore);
            allScores[i] = totalScore;
        }
        for(int i = 0;i<playerscount;i++){
            players[i].setAllScores(allScores);
        }

        return players;
    }
//
//    public Player[] calculateOverallScore(Player[] players){
//        int valueScore=0, typeScore=0, totalScoreNew=0, totalScoreOld;
//        String [][] playerCardHand;
//        int[] allScores = new int[playerscount];
//        for(int i = 0;i<playerscount;i++){
//            totalScoreOld = players[i].getScore();
//            playerCardHand = players[i].getCardHand();
//            typeScore = getTypeScore(i, playerCardHand);
//            valueScore = getValueScore(i, playerCardHand);
//            totalScoreNew = typeScore + valueScore + totalScoreOld;
//            players[i].setScore(totalScoreNew);
//            allScores[i] = totalScoreNew;
//        }
//        for(int i = 0;i<playerscount;i++){
//            players[i].setAllScores(allScores);
//        }
//
//        return players;
//    }

    public Player[] updatePlayerToBeKicked(Player[] players){
        int lowestScore = 999, lowestScorePlayerId = 0;
        for(int i = 0;i<players.length;i++){
            if(players[i].getScore()<lowestScore ){
                lowestScore = players[i].getScore();
                lowestScorePlayerId = i;
            }
        }
        players[lowestScorePlayerId].setKicked(true);
        return players;
    }

    public Player[] updatePointsDonation(Player[] players){
        for(int i = 0;i<playerscount;i++){
            if(players[i].isKicked() == true ){
                players[players[i].getPlayerIdToDonatePoints()].updateDonationScore((players[i].getScore()/4));
            }
        }
        return players;
    }

    public int getTypeScore(int playerId, String[][] playerCardHand){


        String type = null;
//
        //String[][] array=playerCarray;
//       // array=displayHand();
        int typeScore = 0;
        for(int i=0;i<5;i++){
//
            type = playerCardHand[playerId][i].substring(0,playerCardHand[playerId][i].indexOf("-"));
//
            System.out.println("user "+ playerId+" card type= "+type);

            switch(type){

                case "s":
                    typeScore+=5;
                    //sum+=point;
                    break;

                case "d":
                    typeScore+=4;
                    //sum+=point;
                    break;

                case "c":
                    typeScore+=3;
                    //sum+=point;
                    break;

                case "h":
                    typeScore+=2;
                    //sum+=point;
                    break;

            }

        }
        String firstcard = playerCardHand[playerId][0].substring(0, 1);
        String secondcard = playerCardHand[playerId][1].substring(0, 1);

        if ((firstcard.equals("d") & secondcard.equals("h"))) {
            typeScore += 5;

        } else if ((firstcard.equals("d") & secondcard.equals("s"))) {
            typeScore += 5;
        } else if ((firstcard.equals("h") & secondcard.equals("s"))) {
            typeScore += 5;
        } else if ((firstcard.equals("s") & secondcard.equals("h"))) {
            typeScore += 5;
        } else if ((firstcard.equals("s") & secondcard.equals("h"))) {
            typeScore += 5;
        } else if ((firstcard.equals("h") & secondcard.equals("d"))) {
            typeScore += 5;
        } else if ((firstcard.equals("s") & secondcard.equals("d"))) {
            typeScore += 5;
        } else if ((firstcard.equals("h") & secondcard.equals("c "))) {
            typeScore += 5;
        } else {
            typeScore += 0;
        }


        return typeScore;
    }

    public int getValueScore(int playerId, String[][] playerCardHand){


        String value=null;
        // String[][] array = playerCarray;
        int valueScore = 0;
        for(int i=0;i<5;i++){

            value=playerCardHand[playerId][i].substring(playerCardHand[playerId][i].indexOf("-")+1);

            System.out.println("user "+ playerId+ " card value= "+value);

            switch(value){

                case "A":
                    valueScore+=15;
                    //sum+=point;
                    break;
                case "K":
                    valueScore+=14;
                    //sum+=point;
                    break;
                case "Q":
                    valueScore+=13;
                    //sum+=point;
                    break;
                case "J":
                    valueScore+=12;
                    //sum+=point;
                    break;
                case "10":
                    valueScore+=11;
                    //sum+=point;
                    break;
                case "9":
                    valueScore+=10;
                    //sum+=point;
                    break;
                case "8":
                    valueScore+=9;
                    //sum+=point;
                    break;
                case "7":
                    valueScore+=8;
                    //sum+=point;
                    break;
                case "6":
                    valueScore+=7;
                    //sum+=point;
                    break;
                case "5":
                    valueScore+=6;
                    //sum+=point;
                    break;
                case "4":
                    valueScore+=5;
                    //sum+=point;
                    break;

                case "3":
                    valueScore+=4;
                    //sum+=point;
                    break;

                case "2":
                    valueScore+=3;
                    //sum+=point;
                    break;
            }

            if (value.equals("A") & value.equals("K") & value.equals("Q") & value.equals("J")) {
                valueScore += 8;
            } else if (value.equals("10") & value.equals("9") & value.equals("8") & value.equals("8")) {
                valueScore += 5;
            } else if (value.equals("10") & value.equals("9") & value.equals("8")) {
                valueScore += 6;

            } else if (value.equals("A") | value.equals("K") | value.equals("Q") | value.equals("J")) {
                valueScore += 4;
            } else if (value.equals("10") | value.equals("9") | value.equals("8")) {
                valueScore += 2;

            }

        }

        return valueScore;

    }

    //Risk calculation befire change hand
    public int getRiskValue(int playerId, String[][] playerCardHand) {
        String type = null;
        String value=null;
        int riskValue = 0;
            for (int i = 0; i < 5; i++) {
//

                type = playerCardHand[playerId][i].substring(0, playerCardHand[playerId][i].indexOf("-"));
                value=playerCardHand[playerId][i].substring(0, playerCardHand[playerId][i].indexOf("-")+1);
                //System.out.println("user " + j + " card type= " + type);

                switch (type) {

                    case "s":
                        riskValue += 4;
                        //sum+=point;
                        break;

                    case "d":
                        riskValue += 3;
                        //sum+=point;
                        break;

                    case "c":
                        riskValue += 2;
                        //sum+=point;
                        break;

                    case "h":
                        riskValue += 1;
                        //sum+=point;
                        break;

                }
            }


            System.out.println("The Risk of user" +playerId+ " for Hand: " + riskValue);



        return riskValue;
    }
}
