package src.Main;


import java.io.*;
import java.math.*;
import java.util.*;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tok;
    static StringBuilder output = new StringBuilder("");
    public static void main(String[] args) throws Exception {
//        String s = "abzcdefddghijbklmynopqrsaatuvxxxwcxyz";
//        StringBuilder str = new StringBuilder(s);
//        str.reverse();
//        System.out.println(solution(str.toString()));
//        solution(3,2);
//        int[][] d1 = {
//                {2, 1, 2},
//                {3, 4, 5},
//                {3, 4, 0}
//        };
//        int[][] d2 = {
//                {100, 100, 100},
//                {100, 100, 100},
//                {2, 1, 0}
//        };
//        System.out.println(solution(d1,d2));
        int[][] d = {{0, 0, 1, 1, 1}, {1, 0, 0, 1, 1}, {0, 0, 0, 0, 1}, {1, 1, 1, 1, 1}, {0, 0, 1, 0, 1}};
        int[][] d2 = {{0, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {0, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}};
        int[][] d3 = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {0, 1, 1, 1, 1}};
        int[][] d4 = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        System.out.println(solution(d4));
    }

    public static int solution(int[][] grid) {
        // n*n 크기 격자안에 1*1*1 정육면체 블록을 쌓음
        // 쌓을수 있는칸(1), 쌓을 수 없는칸(2)
        // 가장 높이 쌓을 수 있는 블록 탑의 높이를 구해야됨
        // 0 -> 해당칸에 한칸 쌓음
        // 0이 없음 -> 주변 최솟값보다 1칸 높게
        // 주변이 격자밖이면 조건에 영향을 주지 않음

        // 2중 포문으로 탐색
        int n = grid.length;
        int[][] dist = new int[n][n];
        int max = -1;
        for(int k=0;k<1;k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {

                    if(grid[i][j] == 1) {
                        if (i - 1 >= 0 && grid[i - 1][j] == 0) dist[i][j] = 1;
                        if (j - 1 >= 0 && grid[i][j - 1] == 0) dist[i][j] = 1;
                        if (i + 1 < n && grid[i + 1][j] == 0) dist[i][j] = 1;
                        if (j + 1 < n && grid[i][j + 1] == 0) dist[i][j] = 1;
                        // 주변에 0이 있다면 해당값은 1

                        if (dist[i][j] != 1) {
                            int u = 1000000000, l = 1000000000, r = 1000000000, d = 1000000000;
                            if (i - 1 >= 0 && dist[i - 1][j] != 0) u = dist[i - 1][j];
                            if (j - 1 >= 0 && dist[i][j - 1] != 0) l = dist[i][j - 1];
                            if (i + 1 < n && dist[i + 1][j] != 0) d = dist[i + 1][j];
                            if (j + 1 < n && dist[i][j + 1] != 0) r = dist[i][j + 1];
                            int min = Math.min(Math.min(u, l), Math.min(r, d));
                            dist[i][j] = min + 1;
                        }
                        // 주변에 0이 없으면 주변 값의 최솟값+1 (0이 주변에 없으므로 적어도 한개는 결정된 값이 있음을 보장)

                        int ci = i;
                        while(ci - 1 >= 0 && dist[ci - 1][j] > dist[ci][j] + 1) {
                            dist[ci - 1][j] = dist[ci][j] + 1;
                            ci--;
                        }
                        if (j - 1 >= 0 && dist[i][j - 1] > dist[i][j] + 1) dist[i][j - 1] = dist[i][j] + 1;
                        if (i + 1 < n && dist[i + 1][j] > dist[i][j] + 1) dist[i + 1][j] = dist[i][j] + 1;
                        if (j + 1 < n && dist[i][j + 1] > dist[i][j] + 1) dist[i][j + 1] = dist[i][j] + 1;
                        // 결정 후에 주변에 자신의+1 보다 큰값이 있다면 자신의+1로 조정


                    }
                    for (int q=0;q<n;q++) System.out.println(Arrays.toString(dist[q]));
                    System.out.println();
                }
            }
        }

        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                if(dist[i][j] > max) max = dist[i][j];
            }
        }

        return max;
    }

    public static int solution4(int[][] board, int[][] durability) {
        // 1x1 크기 정사각 격자로 이루어진 n*n 크기 게임맵 주어짐
        // 맵 밖으로 나가기 전 움직인 거리 구하기
        // 맨 왼쪽위 에서 오른쪽으로 처음에 이동함
        int x = 0, y = 0, z = 0;
        // 오 위 왼 아
        int[][] move = {{0,1},{-1,0},{0,-1},{1,0}};
        int h = board.length;
        int w = board[0].length;
        int cnt = 1;

        if(board[0][0] == 5 || board[0][0] == 1 || board[0][0] == 3) return 0;  // 왼쪽 위가 막혀있다면 진입 불가
        // 각 방향전환
        int[] d1 = {2,0,3,1}; // 1번 = (2,0,3,1)
        int[] d2 = {3,2,0,1}; // 2번 = (3,2,0,1)
        int[] d3 = {2,3,1,0}; // 3번 = (2,3,1,0)
        int[] d4 = {1,3,0,2}; // 4번 = (1,3,0,2)
        int[] d5 = {2,3,0,1}; // 5번 = (2,3,0,1)

        if(board[x][y] == 1) z = d1[z];
        if(board[x][y] == 2) z = d2[z];
        if(board[x][y] == 3) z = d3[z];
        if(board[x][y] == 4) z = d4[z];
        if(board[x][y] == 5) z = d5[z];
        if(board[x][y] != 0) {
            durability[x][y]--;
            if(durability[x][y] == 0) board[x][y] = 0;
        }
        x += move[z][0];
        y += move[z][1];

        while(x < h && y < w && x >= 0 && y >= 0) {
            // 맵 안에 있는 동안 반복
            cnt++;
            int rz = z;
            if(board[x][y] == 1) z = d1[z];
            if(board[x][y] == 2) z = d2[z];
            if(board[x][y] == 3) z = d3[z];
            if(board[x][y] == 4) z = d4[z];
            if(board[x][y] == 5) z = d5[z];
            if(board[x][y] != 0) {
                durability[x][y]--;
                if(durability[x][y] == 0) board[x][y] = 0;
                if(Math.abs(rz-z) == 2) cnt-=2;
            }
            for(int i=0;i<h;i++) {
                System.out.println(Arrays.toString(board[i])+" "+Arrays.toString(durability[i])+"  // "+ x+" "+y+" "+z+" "+cnt);
            }
            System.out.println();
            x += move[z][0];
            y += move[z][1];
        }
        cnt++;
        return cnt;
    }

    public static int[][] solution3(int r, int c) {
        // r행 c열의 격자, C모양으로 순회하면서 격자를 채움
        // 우측상단부터 채움, 이미 방문하거나 끝일때까지 왼쪽 -> 아래 -> 오른쪽 -> 위한칸 -> 왼쪽 -> 위 -> 오른쪽 -> 아래 한칸
        // 격자를 다 채우면 반복을 멈추고 종료
        int x = 0, y = c-1, z = 0;
        int checkCount = 1;
        int[][] move = {{0, -1}, {1,0}, {0,1}, {-1,0},{0,-1},{-1,0},{0,1},{1,0}};       // 8가지 3, 7 에서는 한번씩
        int[][] pan = new int[r][c];
        boolean[][] check = new boolean[r][c];

        pan[x][y] = checkCount++;
        check[x][y] = true;
        int nx = x + move[z][0];
        int ny = y + move[z][1];
        if(nx < 0 || nx >= r || ny < 0 || ny >= c || check[nx][ny]) z = (z+1)%8;
        while(checkCount <= r*c) {
            x += move[z][0];
            y += move[z][1];
            pan[x][y] = checkCount++;
            check[x][y] = true;

            nx = x + move[z][0];
            ny = y + move[z][1];
            if(nx < 0 || nx >= r || ny < 0 || ny >= c || check[nx][ny] || z == 3 || z == 7) z = (z+1)%8;
            // 다음이 이미 방문한 지점이나, 배열의 끝일경우 방향 전환
        }

        return pan;
    }

    public static String solution2(String source) {
        // source 가 주어짐 -> 새로운 문자열을 만들러 리턴하라

        // 새로운 빈 문자열 dest를 만들기
        // 빈 문자열일경우, dest를 retrun하고 종료
        // 모든 알파벳을 종류별로 1개씩 제거하고, 제거한 알파벳을 dest의 맨뒤에 순서대로 이어붙임
        // 동일한것이 여러개 있다면, 그중 어떤것을 골라도 상관없음.
        String answer = "";
        char[] s = source.toCharArray();
        Arrays.sort(s);
//        System.out.println(Arrays.toString(s));
        boolean[] check = new boolean[s.length];
        int checkCount = 0;
        StringBuilder res = new StringBuilder("");
        while(s.length > checkCount) {
            char c = 'a';
            while(c <= 'z') {
                for(int i=0;i<s.length;i++) {
                    if(!check[i] && s[i] == c) {
                        check[i] = true;
                        checkCount++;
                        res.append(s[i]);
                        break;
                    }
                }
                c++;
            }
        }
//        System.out.println("res : " + res);

        return res.toString();
    }
}
