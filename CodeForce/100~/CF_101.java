import java.util.*;
import java.io.*;

public class CF_101 {
    static BufferedReader Read = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter Write = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tok;
    static StringBuilder output = new StringBuilder("");
    static int[][] darr, doper, counter;
    static boolean[][] dused, dcheck;
    static int[] arr, oper, CS;
    static boolean[] used, check;
    static int h, w, n, m;
    static boolean finish;
    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(Read.readLine());
        darr = new int[n][2];
        doper = new int[n][2];
        used = new boolean[n];
        counter = new int[7][n];
        CS = new int[7];

        for(int i=0;i<n;i++){
            tok = new StringTokenizer(Read.readLine());
            darr[i][0] = Integer.parseInt(tok.nextToken());
            darr[i][1] = Integer.parseInt(tok.nextToken());
            counter[darr[i][0]][CS[darr[i][0]]] = i+1;
            CS[darr[i][0]]++;
            counter[darr[i][1]][CS[darr[i][1]]] = -1*(i+1);
            CS[darr[i][1]]++;
        }
        finish = false;

        for(int i=0;i<n;i++){
            used[i] = true;
            doper[0][0] = i;
            doper[0][1] = 1;
            stk(1,darr[i][1]);
            doper[0][1] = -1;
            stk(1,darr[i][0]);
            used[i] = false;
        }
        if(!finish) Write.write("No solution");
        Write.flush();
    }

}
