
import java.util.Random;

/**
 * Created by devan on 21-01-2018.
 */
public class Agent {
    char selectedAction;
    int[][] payoff = new int[2][2];

    public double getQc() {
        return Qc;
    }

    public double getQd() {
        return Qd;
    }

    double Qc = 1, Qd = 1;
    int check=0;

    double alpha = 0.05;
    double epsilon_greedy = 0.05;
    public void setPayoff(int[][] payoff) {
        this.payoff = payoff;
    }

    int getUtility(char x){
        if(x == 'c' && selectedAction == 'c')
            return payoff[0][0];
        else if (x =='c' && selectedAction =='d')
            return payoff[0][1];
        else if (x=='d' && selectedAction=='c')
            return payoff[1][0];
        else if (x=='d' && selectedAction=='d')
            return payoff[1][1];
        else
            return 0;
    }
    void reviseQ(char x){
        if(selectedAction == 'c'){
            Qc = alpha * getUtility(x) + (1-alpha)*Qc;
            //Qd += (epsilon_greedy) * (alpha * getUtility(x) + (1-alpha)*Qd);
//           System.out.println("Selected x and selectionAction: " + x +"," +selectedAction+"Utility: " + getUtility(x));
        }
        else if(selectedAction == 'd')
        {
            Qd = alpha * getUtility(x) + (1-alpha)*Qd;
            //Qc += (epsilon_greedy) * (alpha * getUtility(x) + (1-alpha)*Qc);
//            System.out.println("Selected x and selectionAction: " + x +"," +selectedAction+"Utility: " + getUtility(x));

        }
    }
    void printcheck(){
        System.out.println("check " + check);
    }
    void decideNextAction(){
        double temp = new Random().nextFloat();
        if(Qc >= Qd) {
            if(temp < epsilon_greedy) {
                selectedAction = 'd';
                check ++;
                return;
            }
            else
                selectedAction = 'c';
            return;
        }
        if(temp < epsilon_greedy) {
            selectedAction = 'c';
            check++;
            return;
        }
        selectedAction = 'd';
        return;
    }
}
