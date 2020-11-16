import java.util.*;
import java.io.*;

public class javaTraining {
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
        int TestCase = Integer.parseInt(Read.readLine());
        tok = new StringTokenizer(Read.readLine());
        n = Integer.parseInt(tok.nextToken());
    }
}
