public class PayOff {
    int[][] player1 = new int[2][2];
    int[][] player2 = new int[2][2];

    void setPlayer1(int[][] mat){
        player1 = mat;
    }
    void setPlayer2(int[][] mat){
        //Player 2's perspective
        player2 = transposeMatrix(mat);
    }
    PayOff(int[][] p1, int[][] p2){
        setPlayer1(p1);
        setPlayer2(p2);
    }
    void printPayOff(){
        System.out.println("PayOff Matrix");
        System.out.println(player1[0][0] + "," + player2[0][0] + " " + player1[0][1] + "," + player2[0][1]);
        System.out.println(player1[1][0] + "," + player2[1][0] + " " + player1[1][1] + "," + player2[1][1]);
    }
    //To create the payoff matrix from PLayer2's perspective
    int[][] transposeMatrix(int [][] m){
        int[][] temp = new int[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++)
                temp[j][i] = m[i][j];
        return temp;
    }

}
