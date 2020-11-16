import java.util.*;
import java.io.*;

public class CONT_1115 {
    static final int INTMAX = Integer.MAX_VALUE;
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
        for(int tt=0;tt<TestCase;tt++){
            tok = new StringTokenizer(Read.readLine());
            h = Integer.parseInt(tok.nextToken());
            w = Integer.parseInt(tok.nextToken());
            arr = new int[h*w];
            int CM = 0, sum=0;
            for(int i=0;i<h;i++){
                tok = new StringTokenizer(Read.readLine());
                for(int j=0;j<w;j++) {
                    arr[(w*i)+j] = Integer.parseInt(tok.nextToken());
                    if(arr[(w*i)+j]<0) {
                        arr[(w*i)+j] = -arr[(w*i)+j];
                        CM++;
                    }
                }
            }
            CM%=2;
            Arrays.sort(arr);
            for(int i=0;i<CM;i++) arr[i] = -arr[i];
            for(int i=0;i<h*w;i++) {
                sum += arr[i];
            }
            Write.write(sum+"");
            Write.newLine();
        }
        Write.flush();
    }
    public static void CONT_1115_1() throws Exception {
        int TestCase = Integer.parseInt(Read.readLine());
        for(int tt=0;tt<TestCase;tt++){
            n = Integer.parseInt(Read.readLine());
            Write.write(n+"");
            Write.newLine();
            for(int i=1;i<=n;i++) Write.write(i+" ");
            Write.newLine();
        }
        Write.flush();
    }
    
}
