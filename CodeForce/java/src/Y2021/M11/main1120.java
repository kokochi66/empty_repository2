package src.Y2021.M11;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class main1120 {
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

/*        String ch = "10 5 2";
        String[] mon = {"Knight 20 20 1 3","Wizard 5 5 1 3","Beginner 10 10 1 3"};

        System.out.println(solution(ch, mon));*/

/*        int time1 = 100;
        int time2 = 49;
        int time3 = 11;
        int time4 = 100;

        int gold1 = 200;
        int gold2 = 200;
        int gold3 = 1000;
        int gold4 = 10000;

*//*[[0, 5], [1500, 3], [3000, 1]]*//*
        int[][] up1 = {{0,5},{1500,3},{3000,1}};    // 4100
        int[][] up2 = {{0,5},{1500,3},{1501,1}};       // 1800
        int[][] up3 = {{0,5},{100,4},{200,3}};      // 2700
        int[][] up4 = {{0,100},{1,2},{1,1}};
        System.out.println(solution(time4, gold4, up4));*/

        //



        String[] ca = {"AAAACX", "AAAACX", "AAAACX", "ZZZZZX", "ATTTTX", "XUUUUU"};
        int[] cr = {1,2,4};
        int[] cc = {2,3};
//        String[] ca = {"KKKK", "KKKK", "KKKK", "FGHI"};
//        int[] cr = {2,3};
//        int[] cc = {2};
        System.out.println(solution(ca,cr,cc));
    }

    public static int solution(String[] cakes, int[] cut_rows, int[] cut_columns) {
        int answer = 0;
        int x = cakes[0].length();
        int y = cakes.length;
        char[][] cake = new char[y][x];
        boolean[][] check = new boolean[y][x];
        for(int i=0;i<cakes.length;i++) cake[i] = cakes[i].toCharArray();

        ArrayList<Kak> list = new ArrayList<>();
        int cut_row = cut_rows[0];
        int cut_row_idx = 1;
        for(int i=0;i<y;i++) {
            if(i >= cut_row) {
                cut_row = cut_row_idx < cut_rows.length ? cut_rows[cut_row_idx] : y+1;
                cut_row_idx++;
            }
            int cut_col = cut_columns[0];
            int cut_col_idx = 1;
            for(int j=0;j<x;j++) {
                if(j >= cut_col) {
                    cut_col = cut_col_idx < cut_columns.length ? cut_columns[cut_col_idx] : x+1;
                    cut_col_idx++;
                }
                if(!check[i][j]) {

//                    for(int k=0;k<y;k++) System.out.println(Arrays.toString(check[k]));

                    Kak k = new Kak(i,j);
                    BFS(k, i, j, check, cut_row ,cut_col, cake);
//                    System.out.println(i+" "+j+" "+k.set.size()+" "+cut_row+" "+cut_col);
                    list.add(k);
                }
            }
        }

        Collections.sort(list,(a,b) -> {
            return b.set.size() - a.set.size();
        });
//        System.out.println(list.get(0).set.size());
        return list.get(0).set.size();
    }

    public static void BFS(Kak k, int y, int x, boolean[][] check, int cut_row , int cut_col, char[][] cake) {
        // 다음 col, row값을 지정해줘야함
        if(y >= cake.length || x >= cake[0].length || check[y][x] || y >= cut_row || x >= cut_col) return;
        if(!k.set.contains(cake[y][x])) k.set.add(cake[y][x]);
        check[y][x] = true;
        BFS(k,y+1,x,check,cut_row, cut_col, cake);
        BFS(k,y,x+1,check,cut_row, cut_col, cake);
    }

    static class Kak {
        HashSet<Character> set = new HashSet<>();
        int x, y;
        public Kak(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int solution(int time, int gold, int[][] upgrade) {
        ArrayList<Integer> list = new ArrayList<>();
        // 아예 못캐는 경우도 고려해야함.

        int n = upgrade.length;
        int[] arr = new int[n];
        int nextLevel = 0;  // 다음레벨 캐는데까지 걸리는 시간
        int nextMoney = 0;  // 다음레벨 업그레이드 하고 남은 돈
        int max = 0;        // 결과
        for(int i=0;i<n;i++) {
//            System.out.println(upgrade[i][1]+" "+time+" "+nextLevel);
            if(upgrade[i][1] >= (time - nextLevel)) {
//                System.out.println(upgrade[i][1]+" "+time+" "+nextLevel);
                arr[i] = nextMoney;
                if(max < arr[i]) max = arr[i];
                break;
            }
            arr[i] = (((time - nextLevel)/upgrade[i][1])*gold) + nextMoney; // 남은시간동안 벌수 있는 돈
            if(max < arr[i]) max = arr[i];
            if(i < n-1) {
                if(nextMoney < upgrade[i+1][0]) {
                    int needMoney = upgrade[i+1][0] - nextMoney; // 다음 업글까지 필요한 돈
                    int needTimes = needMoney % gold == 0 ? needMoney/gold : (needMoney/gold) + 1;  // 캐야되는 횟수
                    nextLevel += (needTimes * upgrade[i][1]);       // 다음 레벨까지 필요한 시간
                    nextMoney = (needTimes * gold) - upgrade[i+1][0];   // 업글하고 남은 돈
                } else {
                    nextMoney -= upgrade[i+1][0];
                }
            }
        }
        System.out.println(Arrays.toString(arr));
        return max;
    }


    public static String solution2(String character, String[] monsters) {
        tok = new StringTokenizer(character);
        Peo player = new Peo("player::", Integer.parseInt(tok.nextToken()), Integer.parseInt(tok.nextToken()) , Integer.parseInt(tok.nextToken()));
        // 플레이어 선언
//        System.out.println(player);

        int n = monsters.length;
        ArrayList<Peo> list = new ArrayList<>();
        for(int i=0;i<n;i++) {
            tok = new StringTokenizer(monsters[i]);
            String name = tok.nextToken();
            int exp = Integer.parseInt(tok.nextToken());
            int hp = Integer.parseInt(tok.nextToken());
            int ad = Integer.parseInt(tok.nextToken());
            int sh = Integer.parseInt(tok.nextToken());
            Peo mon = new Peo(name,hp,ad,sh);
            mon.exp = exp;
            // 각 몬스터에게 들어가는 데미지를 계산
            mon.damage = player.ad - mon.sh <= 0 ? 0 : player.ad - mon.sh;

            if(mon.damage == 0) continue;
            mon.poTime = mon.hp%mon.damage == 0 ? hp/mon.damage : (hp/mon.damage) + 1;
            mon.expPerTime = (double)mon.exp/(double)mon.poTime;
            mon.canTarget = mon.poTime > 1 && (mon.ad - player.sh) >= player.hp;
            if(!mon.canTarget) list.add(mon);
            System.out.println(mon);
        }
        Collections.sort(list,(a,b) -> {
            return b.expPerTime - a.expPerTime < 0 ? -1 :
                    b.expPerTime == a.expPerTime ? b.exp - a.exp : 1;
        });
//        System.out.println(list.get(0));
        return list.get(0).name;
    }

    static class Peo {
        String name;
        int exp;
        int hp;
        int ad;
        int sh;
        int damage;
        int poTime;
        double expPerTime;
        boolean canTarget;

        public Peo(String name, int hp, int ad, int sh) {
            this.name = name;
            this.hp = hp;
            this.ad = ad;
            this.sh = sh;
        }

        @Override
        public String toString() {
            return "Peo{" +
                    "name='" + name + '\'' +
                    ", exp=" + exp +
                    ", hp=" + hp +
                    ", ad=" + ad +
                    ", sh=" + sh +
                    ", damage=" + damage +
                    ", poTime=" + poTime +
                    ", expPerTime=" + expPerTime +
                    ", canTarget=" + canTarget +
                    '}';
        }
    }
}




















































