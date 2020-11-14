import java.util.*;
import java.io.*;

public class icot_1 {
    //https://www.youtube.com/watch?v=5Lu34WIx2Us

    static BufferedReader Read = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter Write = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tok;
    static StringBuilder output = new StringBuilder("");
    static int[][] darr, doper;
    static boolean[][] dused, dcheck;
    static int[] arr, oper;
    static boolean[] used, check;
    static int h, w, n, m;
    public static void main(String[] args) throws Exception {
        Solution();
    }
    public static void Solution() throws Exception {
        tok = new StringTokenizer(Read.readLine());
        n = Integer.parseInt(tok.nextToken());
    }
    public static void icot_Dynamic1() throws Exception {
        n = Integer.parseInt(Read.readLine());
        tok = new StringTokenizer(Read.readLine());
        arr = new int[n];
        for(int i=0;i<n;i++) arr[i] = Integer.parseInt(tok.nextToken());
        oper = new int[n];
        for(int i=n-1;i>=0;i--){
            int curr = 0;
            if(i+3<n) curr = Math.max(oper[i+2],oper[i+3]);
            else if(i+2<n) curr = oper[i+2];
            oper[i] = arr[i]+curr;
        }
        System.out.println(Math.max(oper[0],oper[1]));
    }
}