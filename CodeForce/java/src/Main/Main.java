package src.Main;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

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
//        System.out.println(Arrays.toString(solution(new String[]{"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"})));
        System.out.println(Arrays.toString(solution(6, 6, new int[][]{{2,2,5,4},{3,3,6,6},{5,1,6,3}})));
    }


    public static int[] solution(int rows, int columns, int[][] queries) {
        int[][] pan = new int[rows][columns];
        for(int i=0;i<rows;i++) {
            for(int j=0;j<columns;j++) {
                pan[i][j] = (i*columns) + (j+1);
            }
        }
//        for(int i=0;i<rows;i++) System.out.println(Arrays.toString(pan[i]));
        // 선언부

        for(int q=0;q<queries.length;q++) {
            int x1 = queries[q][0]-1;
            int y1 = queries[q][1]-1;
            int x2 = queries[q][2]-1;
            int y2 = queries[q][3]-1;
            int min = 1000000000;

            int next = pan[x1+1][y1];
            int cx = x1;
            int cy = y1+1;
            while(!(cx == x1 && cy == y1)) {
                
            }
        }
        int[] answer = {};
        return answer;
    }

    public static String[] solution2(String[] record) {
        String[] answer = {};
        ArrayList<String[]> msgList = new ArrayList<>();
        int n =record.length;
        HashMap<String, String> map = new HashMap<>();
        for(int i=0;i<n;i++) {
            System.out.println("TEST :: " + record[i]);
            tok = new StringTokenizer(record[i]);
            String op = tok.nextToken();
            String id = tok.nextToken();

            String[] cArr = new String[2];
            cArr[1] = id;
            if(op.equals("Enter")) {
                String nick = tok.nextToken();
                cArr[0] = "E";
                msgList.add(cArr);
                if(!map.containsKey(id)) {
                    map.put(id, nick);
                } else if(map.containsKey(id)) {
                    map.replace(id, nick);
                }
            } else if(op.equals("Leave")) {
                cArr[0] = "L";
                msgList.add(cArr);
            } else if(op.equals("Change")) {
                String nick = tok.nextToken();
                map.replace(id, nick);
            }
        }

        String[] res = new String[msgList.size()];
        for(int i=0;i< msgList.size();i++){
            String[] c = msgList.get(i);
            if(c[0].equals("E")) {
                res[i] = map.get(c[1])+"님이 들어왔습니다.";
            } else {
                res[i] = map.get(c[1])+"님이 나갔습니다.";
            }
        }
        return res;
    }
}
