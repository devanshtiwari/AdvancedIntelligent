import javafx.scene.transform.MatrixType;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.Random;

/**
 * Created by devan on 21-01-2018.
 */
public class Driver {

    public static void main(String[] args) throws IOException {

        ArrayList<PayOff> payOffs = new ArrayList<>();
        //For all the PayOff Matrices given
        //Total 5
        payOffs.add(new PayOff(new int[][]{{1, 5}, {5, 1}},new int[][]{{1, 5}, {5, 1}}));
        payOffs.add(new PayOff(new int[][]{{1, 5}, {3, 1}},new int[][]{{1, 5}, {3, 1}}));
        payOffs.add(new PayOff(new int[][]{{1, 3}, {5, 1}},new int[][]{{1, 5}, {3, 1}}));
        payOffs.add(new PayOff(new int[][]{{3, 0}, {5, 1}},new int[][]{{3, 5}, {0, 1}}));
        payOffs.add(new PayOff(new int[][]{{1, 0}, {5, 2}},new int[][]{{2, 5}, {0, 1}}));

//        Random rand = new Random();
//        int randomNum = rand.nextInt(2) +1;
//        //First Game
//        if(randomNum == 2)
//            ag1.selectedAcation = 'c';
//        else
//            ag1.selectedAcation = 'd';
//        //Similarly
//        randomNum = rand.nextInt(2) +1;
//        if(randomNum == 1)
//            ag2.selectedAcation = 'c';
//        else
//            ag2.selectedAcation = 'd';
//
//        ag1.reviseQ(ag2.selectedAcation);
//        ag2.reviseQ(ag1.selectedAcation);
        String ag1Choice, ag2Chocie;
        for (int i=0;i<payOffs.size();i++) {
            Agent ag1 = new Agent();
            Agent ag2 =new Agent();
//            System.out.println(payOffs.get(i).player2[1][0]);
//            payOffs.get(i).printPayOff();
            ag1.setPayoff(payOffs.get(i).player1);
            ag2.setPayoff(payOffs.get(i).player2);
            int iter = 0;
            DataWriter dataWriter = new DataWriter("Problem"+(i+1)+".csv");
            while (iter != 15000) {
                ag1.decideNextAction();
                ag2.decideNextAction();
//                System.out.println("Action: " + ag1.selectedAction + "," + ag2.selectedAction);
//                System.out.print("Agent 1: ");
                ag1.reviseQ(ag2.selectedAction);
//                System.out.print("Agent 2: ");
                ag2.reviseQ(ag1.selectedAction);
                dataWriter.append(Integer.toString((iter+1)));
                dataWriter.append(Double.toString(ag1.getQc()));
                dataWriter.append(Double.toString(ag1.getQd()));
                dataWriter.append(Double.toString(ag2.getQc()));
                dataWriter.endOfLine(Double.toString(ag2.getQd()));
                iter++;
            }
            dataWriter.close();
            ag1.printcheck();
            ag2.printcheck();
            System.out.println("Game: " + (i + 1));
            System.out.println("Agent 1 Qc and Qd " + ag1.Qc + " " + ag1.Qd);
            System.out.println("Agent 2 Qc and Qd " + ag2.Qc + " " + ag2.Qd);
            ag1Choice = (ag1.Qc >= ag1.Qd)?"c":"d";
            ag2Chocie = (ag2.Qc >= ag2.Qd)?"c":"d";
            System.out.println("Strategy: (" + ag1Choice + "," + ag2Chocie + ")");
        }

    }
}
