package src.Main;


import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tok;
    static StringBuilder output = new StringBuilder("");
    static int[][] darr, doper;
    static boolean[][] dused, dcheck;
    static int[] arr, oper;
    static boolean[] used, check;
    static int h, w, n, m;
    public static void main(String[] args) throws Exception {
        solution();
    }

    public static void solution() throws Exception {
        int TT = Integer.parseInt(rd.readLine());
        for(int TS=0;TS<TT;TS++) {
            int n = Integer.parseInt(rd.readLine());
            int[][] arr = new int[n][2];
            for(int i=0;i<n;i++) {
                tok = new StringTokenizer(rd.readLine());
                arr[i][0] = Integer.parseInt(tok.nextToken());
                arr[i][1] = Integer.parseInt(tok.nextToken());
            }

            boolean[] check = new boolean[n];
            int currPeo = 0;
            int minLeft = 1000000000;
            int minleftIdx = -1;
            //right first
            for(int i=0;i<n;i++) {
                if(arr[i][1] >= currPeo) {
                    currPeo++;
                    check[i] = true;
                    if(minLeft > arr[i][0]) {
                        minLeft = arr[i][0];
                        minleftIdx = i;
                    }
                } else if(arr[i][1] == currPeo-1 ) {
                    if(minLeft < arr[i][0]) {
                        check[minleftIdx] = false;
                        check[i] = true;

                        minLeft = 1000000000;
                        minleftIdx = -1;
                        for(int j=0;j<i;j++) {
                            if(check[j] && minLeft > arr[j][0]) {
                                minLeft = arr[j][0];
                                minleftIdx = j;
                            }
                        }
                    }
                }
            }
            System.out.println(Arrays.toString(check));
            System.out.println(currPeo);

            int rotPeo = 0;
            for(int i=n-1;i>=0;i--) {
                if(check[i] && arr[i][0] >= rotPeo) {
                    rotPeo++;
                }
            }
//            System.out.println(rotPeo);
            wr.write(rotPeo+"");
            wr.newLine();
        }
        wr.flush();
    }

    public static void solution3() throws Exception {
        int TT = Integer.parseInt(rd.readLine());
        for(int TS=0;TS<TT;TS++) {
            int n = Integer.parseInt(rd.readLine());
            tok = new StringTokenizer(rd.readLine());
            int[] arr = new int[n];
            for(int i=0;i<n;i++) {
                arr[i] = Integer.parseInt(tok.nextToken());
            }

            int left = 0, right = n-1;
            boolean check = true;
            int errP = -1;
            // left first
            while(left < right) {
                if(arr[left] == errP) {
                    left++;
                    continue;
                }
                if(arr[right] == errP) {
                    right--;
                    continue;
                }

                if(arr[left] != arr[right]) {
                    if(errP == -1) errP = arr[right];
                    else {
                        check = false;
                        break;
                    }
                    right--;
                } else {
                    left++;
                    right--;
                }
            }
            if(check) {
                wr.write("YES");
                wr.newLine();
                continue;
            }

            left = 0;
            right = n-1;
            errP = -1;
            check = true;
            while(left < right) {
                if(arr[left] == errP) {
                    left++;
                    continue;
                }
                if(arr[right] == errP) {
                    right--;
                    continue;
                }

                if(arr[left] != arr[right]) {
                    if(errP == -1) errP = arr[left];
                    else {
                        check = false;
                        break;
                    }
                    left++;
                } else {
                    left++;
                    right--;
                }
            }
            if(check) {
                wr.write("YES");
                wr.newLine();
            } else {
                wr.write("NO");
                wr.newLine();
            }
        }
        wr.flush();
    }

    public static void solution2() throws Exception {
        int TT = Integer.parseInt(rd.readLine());
        for(int TS=0;TS<TT;TS++) {
            tok = new StringTokenizer(rd.readLine());
            long n = Integer.parseInt(tok.nextToken());
            long m = Integer.parseInt(tok.nextToken());
            if(n == 1 && m == 1) wr.write(0+"");
            else if(Math.min(n,m) == 1) wr.write(1+"");
            else wr.write(2+"");
            wr.newLine();
        }
        wr.flush();
    }
}
