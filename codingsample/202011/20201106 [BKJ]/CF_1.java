import java.io.*;
import java.util.*;

public class CF_1 {

    static BufferedReader Read = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter Write = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception{
        StringTokenizer tok = new StringTokenizer(Read.readLine());
        int n = Integer.parseInt(tok.nextToken());
        int m = Integer.parseInt(tok.nextToken());
        System.out.println(n+m);
    }
}
