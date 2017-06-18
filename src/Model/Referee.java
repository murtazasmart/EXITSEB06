package Model;

/**
 *
 * @author asus i3
 */
public class Referee {

    Card c= new Card();
    String [] cardpackc;

    public int StartGame(){
        System.out.println("Game Started");
        cardpackc =c.Shuffel();
        System.out.println("cards=");
        for (int i = 0; i < cardpackc.length; i++) {
            System.out.print(cardpackc[i]+",");
        }
        c.pushCard(cardpackc);
        System.out.println("pushed");


        int playesrscout=5;// nede to send the players
        return playesrscout;
    }

    public String[][] DistributeCard(int playerscount){
        //System.out.println("then"+c.pullcard());
        String[][] playerCarray = new String [playerscount][5];

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

}
