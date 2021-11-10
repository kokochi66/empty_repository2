package src.Main;

import src.Main.탐색.다익스트라_최단경로;
import src.Main.트리.프림_최소신장트리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

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
        int[][] data1 = {
                {0,1,1},
                {0,2,2},
                {1,2,5},
                {1,3,1},
                {2,3,8}
        };
        System.out.println(solution(4, data1));

    }

    public static int solution(int n, int[][] costs) throws Exception {
        int answer = 0;
        int INF = 1000000000;

        int[][] grp = new int[n][n];
        for(int i=0;i<n;i++) Arrays.fill(grp[i], INF);
        for(int i=0;i< costs.length;i++) {
            grp[costs[i][0]][costs[i][1]] = costs[i][2];
            grp[costs[i][1]][costs[i][0]] = costs[i][2];
        }
//        for (int[] grps : grp) {
//            System.out.println(Arrays.toString(grps));
//        }

        return Prim(grp, 0);
    }

    static int INF = 1000000000;
    public static int Prim(int[][] arr, int root) throws Exception {
        PriorityQueue<TreeNode> pq = new PriorityQueue<TreeNode>();
        int n = arr.length;
        int sum = 0;
        boolean[] chk = new boolean[n];
        chk[root] = true;
        for(int i=0;i<n;i++) {
            if(arr[root][i] < INF) pq.add(new TreeNode(root, i, arr[root][i]));
        }
        while(!pq.isEmpty()) {
            TreeNode tc = pq.poll();
            if(chk[tc.end]) continue;
            sum += tc.weight;
            chk[tc.end] = true;
            for(int i=0;i<n;i++) {
                if(arr[tc.end][i] < INF && !chk[i]) pq.add(new TreeNode(tc.end, i, arr[tc.end][i]));
            }
        }
        return sum;
    }
    static class TreeNode implements Comparable<TreeNode> {
        int start, end, weight;
        public TreeNode(int s, int e, int w) {
            this.start = s;
            this.end = e;
            this.weight = w;
        }
        @Override
        public int compareTo(TreeNode o) {
            // TODO Auto-generated method stub
            return Integer.compare(this.weight, o.weight);
        }

    }

    public static int[] solution1(String[] operations) throws Exception {
        // 프로그래머스 https://programmers.co.kr/learn/courses/30/lessons/42628 이중우선순위 큐
        // I (숫자) :: 큐에 주어진 숫자를 삽입
        // D 1 :: 최댓값 삭제
        // D -1 :: 최솟값 삭제
        // 계속 정렬해주는 리스트로 구현

        ArrayList<Integer> list = new ArrayList<>();
        for (String operation : operations) {
            tok = new StringTokenizer(operation);
            String oper = tok.nextToken();
            int number = Integer.parseInt(tok.nextToken());
            if(oper.equals("I")) {
                list.add(number);
                Collections.sort(list);
            } else if(number == 1 && list.size() > 0) {
                list.remove(list.size()-1);
            } else if(number == -1 && list.size() > 0) {
                list.remove(0);
            }
        }
        int[] res = new int[2];
        res[0] = list.size() > 0 ? list.get(list.size()-1) : 0;
        res[1] = list.size() > 0 ? list.get(0) : 0;

        return res;
    }
}
