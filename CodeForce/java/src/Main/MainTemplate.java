package src.Main;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class MainTemplate {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tok;

    public static void main(String[] args) throws Exception {
//        solution();
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add(5, "b");
        list.set(1,"abcde");
        for (String s : list) {
            System.out.println(s);
        }

        // a b c d e f g h j
    }

    public static void solution() throws Exception {
        int TT = Integer.parseInt(rd.readLine());
        for (int TS = 0; TS < TT; TS++) {
            tok = new StringTokenizer(rd.readLine());

        }
    }

}
