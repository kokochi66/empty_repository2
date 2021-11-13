package src.Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
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
        solution(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15});
    }

    public static int solution(int[] money) {
        int[] res = new int[money.length];
        int n = money.length;
        for(int i=0;i<money.length;i++) {
//            System.out.println("TEST :: i :: " + i);
            if(i == 0) res[i] = money[i];
            else if(i == 1) {
                res[i] = Math.max(money[0], money[1]);
                res[n-i] = Math.max(money[0], money[n-1]);
            }
            else if(i == 2) {
                res[i] = Math.max(res[0] + money[2] , money[1]);
            }
            else {
                if(i == n-i) {
                    // 하나로 만났을 경우 (짝수)
                    res[i] = Math.max(i-3 >= 0 ? res[i-3] : res[n+(i-3)], i-2 >= 0 ? res[i-2] : res[n+(i-2)]) +
                            Math.max(n-(i-3) < n ? res[n-(i-3)] : res[i+3], n-(i-2) < n ?  res[n-(i-2)] : res[i+2]) +
                            money[i];
                    break;
                } else if(Math.abs(i - (n-i)) == 1) {
                    // 두개로 만났을 경우 (홀수)
                    res[i] = Math.max(i-3 >= 0 ? res[i-3] : res[n+(i-3)], i-2 >= 0 ? res[i-2] : res[n+(i-2)]) +
                            Math.max(n-(i-3) < n ? res[n-(i-3)] : res[i+3], n-(i-2) < n ?  res[n-(i-2)] : res[i+2]) +
                            money[i];
                    res[i+1] = Math.max((i+1)-3 >= 0 ? res[(i+1)-3] : res[n+((i+1)-3)], (i+1)-2 >= 0 ? res[(i+1)-2] : res[n+((i+1)-2)]) +
                            Math.max(n-((i+1)-3) < n ? res[n-((i+1)-3)] : res[(i+1)+3], n-((i+1)-2) < n ?  res[n-((i+1)-2)] : res[(i+1)+2]) +
                            money[(i+1)];
                    break;
                } else {
                    // 만나지 않았을 경우
                    res[i] = Math.max(i-3 >= 0 ? res[i-3] : res[n+(i-3)], i-2 >= 0 ? res[i-2] : res[n+(i-2)]) + money[i];
                    res[n-i] = Math.max(n-(i-3) < n ? res[n-(i-3)] : res[i+3], n-(i-2) < n ?  res[n-(i-2)] : res[i+2]) + money[n-i];
                }
            }
            System.out.println(Arrays.toString(res));
        }
        int answer = 0;
        System.out.println(Arrays.stream(res).max().getAsInt());

        return Arrays.stream(res).max().getAsInt();
    }
}
