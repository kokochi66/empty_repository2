import java.io.*;
import java.util.*;

public class CF1109 {
    // 1. https://codeforces.com/problemsets/acmsguru/problem/99999/101

    static BufferedReader Read = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter Write = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tok;
    static Stack<Integer[]> st;
    static boolean[] used;
    static int[] arr;
    static int[][] darr;
    static int h, w, n;

    public static void main(String[] args) throws Exception {
        Solution();
    }

    public static void Solution() throws Exception {
        n = Integer.parseInt(Read.readLine());
        darr = new int[n][2];
        used = new boolean[n];
        st = new Stack<>();
        boolean pass = false;
        for(int i=0;i<n;i++){
            tok = new StringTokenizer(Read.readLine());
            darr[i][0] = Integer.parseInt(tok.nextToken());
            darr[i][1] = Integer.parseInt(tok.nextToken());
        }

        for(int i=0;i<n;i++){
            st.push(new Integer[]{darr[i][0], darr[i][1], (i+1)});
            used[i] = true;
            if(CF1109(0)) {
                pass = true;
                break;
            }
            st.clear();
            used[i] = false;

            st.push(new Integer[]{darr[i][1], darr[i][0], (i+1)*(-1)});
            used[i] = true;
            if(CF1109(0)) {
                pass = true;
                break;
            }
            st.clear();
            used[i] = false;
        }
        
        
        if(!pass) Write.write("No solution");
        Write.flush();
    }
    public static boolean CF1109(int k) throws Exception {
        if(k==(n-1)){
            while(!st.isEmpty()){
                Integer[] Curr = st.pop();
                char out = '+';
                if(Curr[2]>0) out = '-';
                else Curr[2] *= -1;
                Write.write((Curr[2])+" "+out);
                Write.newLine();
            }
            return true;
        } else {
            Integer[] Curr = st.peek();
            for(int i=0;i<n;i++){
                if(!used[i] && Curr[1] == darr[i][0]) {
                    used[i] = true;
                    st.push(new Integer[]{darr[i][0], darr[i][1], (i+1)});
                    if(CF1109(k+1)) return true;
                    st.pop();
                    used[i] = false;
                }
                else if(!used[i] && Curr[1] == darr[i][1]) {
                    used[i] = true;
                    st.push(new Integer[]{darr[i][1], darr[i][0], (i+1)*(-1)});
                    if(CF1109(k+1)) return true;
                    st.pop();
                    used[i] = false;
                }
            }

        }

        return false;
    }
}