package MaheshiMekala;

/**
 * Created by MA_Laptop on 5/28/2017.
 */
public class Trial {

    public static void main(String[] args) {
        //only for testing
        System.out.println("Im Maheshi");
        Card c = new Card();
        String x[]=c.shuffel();
        System.out.print("shufl pack in Main=");
        for (int yy=0; yy<52;yy++){

            System.out.print(x[yy]+",");
        }
    }
}
