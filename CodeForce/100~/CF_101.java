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
        used = new boolean[n];
        oper = new int[n];

        for(int i=0;i<n;i++){
            tok = new StringTokenizer(Read.readLine());
            darr[i][0] = Integer.parseInt(tok.nextToken());
            darr[i][1] = Integer.parseInt(tok.nextToken());
        }
        

        if(!finish) Write.write("No solution");
        Write.flush();
    }

}
