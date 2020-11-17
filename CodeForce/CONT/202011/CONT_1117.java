import java.util.*;
import java.io.*;

public class CONT_1117 {
    static BufferedReader Read = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter Write = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tok;
    static StringBuilder output = new StringBuilder("");
    static int[][] darr, doper;
    static char[][] dcarr;
    static boolean[][] dused, dcheck;
    static int[] arr, oper;
    static boolean[] used, check;
    static int h, w, n, m;
    public static void main(String[] args) throws Exception {
        Solution();
    }
    public static void Solution() throws Exception {
        int TestCase = Integer.parseInt(Read.readLine());
        for(int TT=0;TT<TestCase;TT++){
            tok = new StringTokenizer(Read.readLine());
            h = Integer.parseInt(tok.nextToken());
            w = Integer.parseInt(tok.nextToken());
            dcarr = new char[h][w];
            for(int i=0;i<h;i++){
                dcarr[i] = Read.readLine().toCharArray();
            }
            // 입력부 O
            int count  = 0;
            for(int i=0;i<h;i++){
                for(int j=0;j<w;j++){
                    int oc = 0 ;
                    if(i<h-1&&j<w-1){
                        for(int k=i;k<i+2;k++){
                            for(int z=j;z<j+2;z++){
                                if(dcarr[k][z]=='1') oc++;
                            }
                        }
                    }
                    if(oc == 4){
                        // System.out.println("oc4으로 들어옴");
                        CONT_1117_3_dfs(i,j, 1);
                        count++;
                    } else if(oc == 3){
                        // System.out.println("oc3으로 들어옴");
                        CONT_1117_3_dfs(i,j, 2);
                        count++;
                    }
                    else if(dcarr[i][j]=='1'){
                        CONT_1117_3_dfs(i,j, 0);
                        // 잔반처리
                        count+=3;
                    }
                }
            }
            System.out.println(count);
            Write.flush();
        }
    }
    public static void CONT_1117_3_dfs(int hi, int wi, int dir) throws Exception{
        if(dir==0) {
            // 잔반처리입력
            if(wi<w-1&&hi<h-1) {
                dcarr[hi][wi] = dcarr[hi][wi] == '0' ?  '1' : '0';
                dcarr[hi+1][wi] = dcarr[hi+1][wi] == '0' ?  '1' : '0';
                dcarr[hi][wi+1] = dcarr[hi][wi+1] == '0' ?  '1' : '0';
                Write.write((hi+1)+" "+(wi+1)+" "+(hi+2)+" "+(wi+1)+" "+(hi+1)+" "+(wi+2)+" ");
                Write.newLine();
                dcarr[hi][wi] = dcarr[hi][wi] == '0' ?  '1' : '0';
                dcarr[hi][wi-1] = dcarr[hi][wi-1] == '0' ?  '1' : '0';
                dcarr[hi+1][wi] = dcarr[hi+1][wi] == '0' ?  '1' : '0';
                Write.write((hi+1)+" "+(wi+1)+" "+(hi+1)+" "+(wi)+" "+(hi+2)+" "+(wi+1)+" ");
                Write.newLine();
                dcarr[hi][wi] = dcarr[hi][wi] == '0' ?  '1' : '0';
                dcarr[hi-1][wi] = dcarr[hi-1][wi] == '0' ?  '1' : '0';
                dcarr[hi][wi+1] = dcarr[hi][wi+1] == '0' ?  '1' : '0';
                Write.write((hi+1)+" "+(wi+1)+" "+(hi)+" "+(wi+1)+" "+(hi+1)+" "+(wi+2)+" ");
                Write.newLine();
            } else if(wi>0 &&hi<h-1) {
                dcarr[hi][wi] = dcarr[hi][wi] == '0' ?  '1' : '0';
                dcarr[hi][wi-1] = dcarr[hi][wi-1] == '0' ?  '1' : '0';
                dcarr[hi+1][wi] = dcarr[hi+1][wi] == '0' ?  '1' : '0';
                Write.write((hi+1)+" "+(wi+1)+" "+(hi+1)+" "+(wi)+" "+(hi+2)+" "+(wi+1)+" ");
                Write.newLine();
                dcarr[hi][wi] = dcarr[hi][wi] == '0' ?  '1' : '0';
                dcarr[hi+1][wi] = dcarr[hi+1][wi] == '0' ?  '1' : '0';
                dcarr[hi][wi+1] = dcarr[hi][wi+1] == '0' ?  '1' : '0';
                Write.write((hi+1)+" "+(wi+1)+" "+(hi+2)+" "+(wi+1)+" "+(hi+1)+" "+(wi+2)+" ");
                Write.newLine();
                dcarr[hi][wi] = dcarr[hi][wi] == '0' ?  '1' : '0';
                dcarr[hi-1][wi] = dcarr[hi-1][wi] == '0' ?  '1' : '0';
                dcarr[hi][wi-1] = dcarr[hi][wi-1] == '0' ?  '1' : '0';
                Write.write((hi+1)+" "+(wi+1)+" "+(hi)+" "+(wi+1)+" "+(hi+1)+" "+(wi)+" ");
                Write.newLine();
            } else if(wi<w-1 &&hi>0) {
                dcarr[hi][wi] = dcarr[hi][wi] == '0' ?  '1' : '0';
                dcarr[hi-1][wi] = dcarr[hi-1][wi] == '0' ?  '1' : '0';
                dcarr[hi][wi+1] = dcarr[hi][wi+1] == '0' ?  '1' : '0';
                Write.write((hi+1)+" "+(wi+1)+" "+(hi)+" "+(wi+1)+" "+(hi+1)+" "+(wi+2)+" ");
                Write.newLine();
                dcarr[hi][wi] = dcarr[hi][wi] == '0' ?  '1' : '0';
                dcarr[hi-1][wi] = dcarr[hi-1][wi] == '0' ?  '1' : '0';
                dcarr[hi][wi-1] = dcarr[hi][wi-1] == '0' ?  '1' : '0';
                Write.write((hi+1)+" "+(wi+1)+" "+(hi)+" "+(wi+1)+" "+(hi+1)+" "+(wi)+" ");
                Write.newLine();
                dcarr[hi][wi] = dcarr[hi][wi] == '0' ?  '1' : '0';
                dcarr[hi+1][wi] = dcarr[hi+1][wi] == '0' ?  '1' : '0';
                dcarr[hi][wi+1] = dcarr[hi][wi+1] == '0' ?  '1' : '0';
                Write.write((hi+1)+" "+(wi+1)+" "+(hi+2)+" "+(wi+1)+" "+(hi+1)+" "+(wi+2)+" ");
                Write.newLine();
            } else if(wi>0 &&hi>0) {
                dcarr[hi][wi] = dcarr[hi][wi] == '0' ?  '1' : '0';
                dcarr[hi-1][wi] = dcarr[hi-1][wi] == '0' ?  '1' : '0';
                dcarr[hi][wi-1] = dcarr[hi][wi-1] == '0' ?  '1' : '0';
                Write.write((hi+1)+" "+(wi+1)+" "+(hi)+" "+(wi+1)+" "+(hi+1)+" "+(wi)+" ");
                Write.newLine();
                dcarr[hi][wi] = dcarr[hi][wi] == '0' ?  '1' : '0';
                dcarr[hi-1][wi] = dcarr[hi-1][wi] == '0' ?  '1' : '0';
                dcarr[hi][wi+1] = dcarr[hi][wi+1] == '0' ?  '1' : '0';
                Write.write((hi+1)+" "+(wi+1)+" "+(hi)+" "+(wi+1)+" "+(hi+1)+" "+(wi+2)+" ");
                Write.newLine();
                dcarr[hi][wi] = dcarr[hi][wi] == '0' ?  '1' : '0';
                dcarr[hi][wi-1] = dcarr[hi][wi-1] == '0' ?  '1' : '0';
                dcarr[hi+1][wi] = dcarr[hi+1][wi] == '0' ?  '1' : '0';
                Write.write((hi+1)+" "+(wi+1)+" "+(hi+1)+" "+(wi)+" "+(hi+2)+" "+(wi+1)+" ");
                Write.newLine();
            } 
        }
        else if(dir==1){
            // 메인입력 (4)
            dcarr[hi][wi] = '0';
            dcarr[hi+1][wi] = '0';
            dcarr[hi][wi+1] = '0';
            Write.write((hi+1)+" "+(wi+1)+" "+(hi+2)+" "+(wi+1)+" "+(hi+1)+" "+(wi+2)+" ");
            Write.newLine();
        }
        else if(dir==2){
            // 차선입력 (3)
            if(dcarr[hi][wi]=='0') {
                dcarr[hi][wi+1] = '0';
                dcarr[hi+1][wi] = '0';
                dcarr[hi+1][wi+1] = '0';
                Write.write((hi+1)+" "+(wi+2)+" "+(hi+2)+" "+(wi+1)+" "+(hi+2)+" "+(wi+2)+" ");
                Write.newLine();
            } else if(dcarr[hi+1][wi]=='0') {
                dcarr[hi][wi] = '0';
                dcarr[hi][wi+1] = '0';
                dcarr[hi+1][wi+1] = '0';
                Write.write((hi+1)+" "+(wi+1)+" "+(hi+1)+" "+(wi+2)+" "+(hi+2)+" "+(wi+2)+" ");
                Write.newLine();
            } else if(dcarr[hi][wi+1]=='0') {
                dcarr[hi][wi] = '0';
                dcarr[hi+1][wi] = '0';
                dcarr[hi+1][wi+1] = '0';
                Write.write((hi+1)+" "+(wi+1)+" "+(hi+2)+" "+(wi+1)+" "+(hi+2)+" "+(wi+2)+" ");
                Write.newLine();
            } else if(dcarr[hi+1][wi+1]=='0') {
                dcarr[hi][wi] = '0';
                dcarr[hi+1][wi] = '0';
                dcarr[hi][wi+1] = '0';
                Write.write((hi+1)+" "+(wi+1)+" "+(hi+2)+" "+(wi+1)+" "+(hi+1)+" "+(wi+2)+" ");
                Write.newLine();
            }

        }
    }
    public static void CONT_1117_2() throws Exception {
        int TestCase = Integer.parseInt(Read.readLine());
        for(int TT=0;TT<TestCase;TT++){
            tok = new StringTokenizer(Read.readLine());
            n = Integer.parseInt(tok.nextToken());
            m = Integer.parseInt(tok.nextToken());
            tok = new StringTokenizer(Read.readLine());
            arr = new int[n*m];
            for(int i=0;i<arr.length;i++) arr[i] = Integer.parseInt(tok.nextToken());
            // 입력부 O
            int tonn = (n/2)+1;
            int index = arr.length;
            long sum = 0;
            for(int i=0;i<m;i++){
                index -= tonn;
                sum += arr[index];
            }
            Write.write(sum+"");
            Write.newLine();
        }
        Write.flush();
    }
    public static void CONT_1117_1() throws Exception {
        int TestCase = Integer.parseInt(Read.readLine());
        int[] c = new int[2];
        for(int TT=0;TT<TestCase;TT++){
            tok = new StringTokenizer(Read.readLine());
            n = Integer.parseInt(tok.nextToken());
            c[0] = Integer.parseInt(tok.nextToken());
            c[1] = Integer.parseInt(tok.nextToken());
            h = Integer.parseInt(tok.nextToken());
            StringBuilder str = new StringBuilder(Read.readLine());
            int[] nc = new int[2];
            // 입력부 O
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)=='0') nc[0]++;
                else nc[1]++;
            }

            int xcoin = Math.max(c[0], c[1]);
            int ncoin = Math.min(c[0],c[1]);
            if(h+ncoin >= xcoin) {
                int res = (c[0]*nc[0]) + (c[1]*nc[1]);
                Write.write(res+"");
            } else {
                int res = 0;
                if(ncoin == c[0]) {
                    res += (ncoin*nc[0]) + ((ncoin+h)*nc[1]);
                } else {
                    res += (ncoin*nc[1]) + ((ncoin+h)*nc[0]);
                }
                Write.write(res+"");
            }
            Write.newLine();
        }
        Write.flush();
    }
}