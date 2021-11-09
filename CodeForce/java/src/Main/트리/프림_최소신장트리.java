package src.Main.트리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 프림_최소신장트리 {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tok;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(rd.readLine());
        int m = Integer.parseInt(rd.readLine());
        int[][] arr = new int[n][n];
        for(int i=0;i<n;i++) Arrays.fill(arr[i], INF);
        for(int i=0;i<m;i++) {
            tok = new StringTokenizer(rd.readLine());
            int a = Integer.parseInt(tok.nextToken());
            int b = Integer.parseInt(tok.nextToken());
            int c = Integer.parseInt(tok.nextToken());
            arr[a-1][b-1] = c;
            arr[b-1][a-1] = c;
        }
        int res = 프림알고리즘(arr,0);
        System.out.println(res);
    }

    static int INF = 1000000000;
    public static int 프림알고리즘(int[][] arr, int root) throws Exception {
        PriorityQueue<트리노드> pq = new PriorityQueue<트리노드>();
        int n = arr.length;
        int sum = 0;
        boolean[] chk = new boolean[n];
        chk[root] = true;
        for(int i=0;i<n;i++) {
            if(arr[root][i] < INF) pq.add(new 트리노드(root, i, arr[root][i]));
        }
        while(!pq.isEmpty()) {
            트리노드 tc = pq.poll();
            if(chk[tc.end]) continue;
            sum += tc.weight;
            chk[tc.end] = true;
            for(int i=0;i<n;i++) {
                if(arr[tc.end][i] < INF && !chk[i]) pq.add(new 트리노드(tc.end, i, arr[tc.end][i]));
            }
        }
        return sum;
    }
    static class 트리노드 implements Comparable<트리노드> {
        int start, end, weight;
        public 트리노드(int s, int e, int w) {
            this.start = s;
            this.end = e;
            this.weight = w;
        }
        @Override
        public int compareTo(트리노드 o) {
            // TODO Auto-generated method stub
            return Integer.compare(this.weight, o.weight);
        }

    }
}
