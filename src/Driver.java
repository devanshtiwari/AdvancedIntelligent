import java.io.IOException;
import java.util.*;

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

        String ag1Choice, ag2Chocie;
        for (int i=0;i<payOffs.size();i++) {
            Agent ag1 = new Agent();
            Agent ag2 =new Agent();
            ag1.setPayoff(payOffs.get(i).player1);
            ag2.setPayoff(payOffs.get(i).player2);
            int iter = 0;
            DataWriter dataWriter = new DataWriter("Problem"+(i+1)+".csv", "Iteration,Qc, Qd, Qc, Qd\n");
            while (iter != 15000) {
                ag1.decideNextAction();
                ag2.decideNextAction();
                //Revision of Q Values after Selection
                ag1.reviseQ(ag2.selectedAction);
                ag2.reviseQ(ag1.selectedAction);
                //Writing into Files
                dataWriter.append(Integer.toString((iter+1)));
                dataWriter.append(Double.toString(ag1.getQc()));
                dataWriter.append(Double.toString(ag1.getQd()));
                dataWriter.append(Double.toString(ag2.getQc()));
                dataWriter.endOfLine(Double.toString(ag2.getQd()));
                iter++;
            }
            dataWriter.close();
//            ag1.printcheck();
//            ag2.printcheck();
            System.out.println("Game: " + (i + 1));
            System.out.println("Agent 1 Qc and Qd " + ag1.Qc + " " + ag1.Qd);
            System.out.println("Agent 2 Qc and Qd " + ag2.Qc + " " + ag2.Qd);
            ag1Choice = (ag1.Qc >= ag1.Qd)?"c":"d";
            ag2Chocie = (ag2.Qc >= ag2.Qd)?"c":"d";
            System.out.println("Strategy: (" + ag1Choice + "," + ag2Chocie + ")");
        }
        //For 20-20 players of each agent

        for(int k = 0; k< payOffs.size();k++) {
            Agent[] ag1 = new Agent[20];
            Agent[] ag2 = new Agent[20];
            for (int i = 0; i < 20; i++) {
                ag1[i] = new Agent();
                ag2[i] = new Agent();
            }
            List<String> strategyList = new ArrayList<String>();
            for (int i = 0; i < 20; i++) {
                ag1[i].setPayoff(payOffs.get(k).player1);
                for (int j = 0; j < 20; j++) {
                    ag2[j].setPayoff(payOffs.get(k).player2);
                    int iter = 0;
                    while (iter != 10000) {
                        ag1[i].decideNextAction();
                        ag2[j].decideNextAction();
                        //Revision of Q Values after Selection
                        ag1[i].reviseQ(ag2[j].selectedAction);
                        ag2[j].reviseQ(ag1[i].selectedAction);
                        //Writing into Files
//                    dataWriter.append(Integer.toString((iter + 1)));
//                    dataWriter.append(Double.toString(ag1.getQc()));
//                    dataWriter.append(Double.toString(ag1.getQd()));
//                    dataWriter.append(Double.toString(ag2.getQc()));
//                    dataWriter.endOfLine(Double.toString(ag2.getQd()));
                        iter++;
                    }
//                    System.out.println("Game: " + (i * 20 + j + 1));
//                    System.out.println("Agent 1 Qc and Qd " + ag1[i].Qc + " " + ag1[i].Qd);
//                    System.out.println("Agent 2 Qc and Qd " + ag2[j].Qc + " " + ag2[j].Qd);
                    ag1Choice = (ag1[i].Qc >= ag1[i].Qd) ? "c" : "d";
                    ag2Chocie = (ag2[j].Qc >= ag2[j].Qd) ? "c" : "d";
                    String strat = "(" + ag1Choice + "," + ag2Chocie + ")";
//                    System.out.println("Strategy: " + strat);
                    strategyList.add(strat);
                }
            }
            Set<String> unique = new HashSet<String>(strategyList);
            DataWriter dataWriter = new DataWriter("ManyPlayers" + (k+1) + ".csv","Strategy, Frequency\n");
            for (String key : unique) {
//                System.out.println(key + ": " + Collections.frequency(strategyList, key));
                dataWriter.append(key,";");
                dataWriter.endOfLine(Integer.toString(Collections.frequency(strategyList, key)));
            }
            dataWriter.close();
        }
        System.out.println("Finished. CSV Files Generated");
    }
}
