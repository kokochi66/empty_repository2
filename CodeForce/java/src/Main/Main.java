package src.Main;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tok;
    public static void main(String[] args) throws Exception {
//        solution();

        String str = "abcdasdasdasd.exe";

        System.out.println(str.substring(0,str.lastIndexOf(".")));
    }


}
