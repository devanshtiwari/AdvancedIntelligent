import java.util.Random;

/**
 * Created by devan on 21-01-2018.
 */
public class Driver {

    public static void main(String[] args) {
        Agent ag1 = new Agent();
        Agent ag2 =new Agent();
        int[][] mat = {{1, 5}, {5, 1}};
        ag1.setPayoff(mat);
        ag2.setPayoff(mat);
        Random rand = new Random();
        int randomNum = rand.nextInt(2) +1;
        //First Game
        if(randomNum == 2)
            ag1.selectedAcation = 'c';
        else
            ag1.selectedAcation = 'd';
        //Similarly
        randomNum = rand.nextInt(2) +1;
        if(randomNum == 1)
            ag2.selectedAcation = 'c';
        else
            ag2.selectedAcation = 'd';

        ag1.reviseQ(ag2.selectedAcation);
        ag2.reviseQ(ag1.selectedAcation);
        int iter =0;
        while(iter != 100000)
        {
            ag1.decideNextAction();
            ag2.decideNextAction();
            ag1.reviseQ(ag2.selectedAcation);
            ag2.reviseQ(ag1.selectedAcation);
            System.out.println("Agent 1 Qc and Qd " + ag1.Qc + " " + ag1.Qd);
            System.out.println("Agent 2 Qc and Qd " + ag2.Qc + " " + ag2.Qd);
            iter++;
        }
        ag1.printcheck();
        ag2.printcheck();
    }
}
