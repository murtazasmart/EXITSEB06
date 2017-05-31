package MaheshiMekala;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/**
 * Created by asus i3 on 5/28/2017.
 */
public class Card {
    public String pack []={"sA", "s2", "s3","s4","s5","s6","s7","s8", "s9","s10", "sJ", "sQ", "sK",/**/"hA", "h2", "h3","h4","h5","h6","h7","h8", "h9","h10", "hJ", "hQ", "hK",/**/"dA", "d2", "d3","d4","d5","d6","d7","d8", "d9","d10", "dJ", "dQ", "dK",/**/"cA", "c2", "c3","c4","c5","c6","c7","c8", "c9","c10", "cJ", "cQ", "cK",};



    public void xx(){
        Queue cardq= new Queue() {
            @Override
            public boolean add(Object o) {
                return false;
            }

            @Override
            public boolean offer(Object o) {
                return false;
            }

            @Override
            public Object remove() {
                return null;
            }

            @Override
            public Object poll() {
                return null;
            }

            @Override
            public Object element() {
                return null;
            }

            @Override
            public Object peek() {
                return null;
            }

            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public Object[] toArray(Object[] objects) {
                return new Object[0];
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection collection) {
                return false;
            }

            @Override
            public boolean addAll(Collection collection) {
                return false;
            }

            @Override
            public boolean removeAll(Collection collection) {
                return false;
            }

            @Override
            public boolean retainAll(Collection collection) {
                return false;
            }

            @Override
            public void clear() {

            }
        }
    }

    public String[] shuffel()
    {
        int cardnumber[]= new int[52];
        String shfpack[]= new String[52];


        for(int x=0;x<52;x++){
            System.out.println("ROUND="+x);
            int cardid=0;
            boolean randno=false;
            while(randno==false){
                cardid= (int) (Math.random()*100);
                System.out.println("random number="+cardid);
                if (cardid<52) {
                    if (pack[cardid] != "done") {
                        randno = true;
                        System.out.println("Number ok");
                    }
                }
            }

            boolean isDone=false;
            int y=0;
            System.out.println("Start cheking complet level");
            while (y<52){

                if (pack[y]=="done"){
                    isDone=true;
                }else{
                    isDone=false;
                    System.out.println("Breaked");
                    break;
                }
                y++;
            }
            System.out.println("end cheking");

            if (isDone==false) {
                if (pack[cardid]!="done"){
                    System.out.println("is done NOT COMPLEATED");
                    shfpack[x] = pack[cardid];
                    pack[cardid] = "done";
                }

            }
            System.out.println("END ROUND="+x);
            System.out.println("");
        }

        System.out.println("mekala");
        System.out.print("real pack= ");
        for (int xx=0; xx<52;xx++){

            System.out.print(pack[xx]+",");
        }
        System.out.println("");
        System.out.print("shufl pack=");
        for (int yy=0; yy<52;yy++){

            System.out.print(shfpack[yy]+",");
        }
        System.out.println("");
        return shfpack;
    }
}
