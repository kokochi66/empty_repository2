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
        solution();
    }

    public static void solution() throws Exception {
        int TT = Integer.parseInt(rd.readLine());
        for(int TS=0;TS<TT;TS++) {
            StringBuilder s = new StringBuilder(rd.readLine());
            int idx = 0;
            boolean check = false;

            while(idx < s.length()) {
                System.out.println("TEST :: " + idx + "  "+s.charAt(idx));
                if(s.charAt(idx) == '1') {
                    // 패턴 1
                    idx++;
                    if(idx >= s.length() || s.charAt(idx) == '1') {
                        check=true;
                        break;
                    }    // 0 체크
                    idx++;
                    if(idx >= s.length() || s.charAt(idx) == '1') {
                        check=true;
                        break;
                    }    // 0 체크
                    while(idx < s.length() && s.charAt(idx) == '0') idx++;     // 연속된 0 체크
                    if(idx >= s.length()) {
                        check=true;
                        break;
                    }    // 범위체크
                    int idxC = idx;
                    while(idx < s.length() && s.charAt(idx) == '1') idx++;     // 연속된 1 체크
                    if(idx - idxC > 0 && idx + 1 < s.length() && s.charAt(idx+1) == '0') idx--;
                } else {
                    // 패턴 2
                    idx++;
                    if(idx >= s.length() || s.charAt(idx) == '0') {
                        check=true;
                        break;
                    }    // 1 체크
                    idx++;
                }

            }
            if(check) wr.write("NO");
            else wr.write("YES");
            wr.newLine();
        }
        wr.flush();
    }
}
