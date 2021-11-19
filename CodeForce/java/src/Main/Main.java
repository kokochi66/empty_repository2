package src.Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
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
//        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
//        String[] referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
//        String[] seller = {"young", "john", "tod", "emily", "mary"};
//        int[] amount = {12, 4, 2, 5, 10};
//        solution(enroll, referral, seller, amount);
        int[][] key = {
                {0,0,0},
                {1,0,0},
                {0,1,1}
        };

        int[][] lock = {
                {1,1,1},
                {1,1,0},
                {1,0,1}
        };

        System.out.println(solution(key, lock));
    }


    public static boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;
        int n = key.length;
        int m = lock.length;

        int minX=m, minY=m, maxX=0, maxY=0;
        for(int i=0;i<m;i++) {
            for(int j=0;j<m;j++) {
                if(lock[i][j] == 0) {
                    if(i < minY) minY = i;
                    if(j < minX) minX = j;
                    if(i > maxY) maxY = i;
                    if(j > maxX) maxX = j;
                }
            }
        }

        int[][] pan = new int[maxY-minY+1][maxX-minX+1];
        for(int i=minY;i<=maxY;i++) {
            for(int j=minX;j<=maxX;j++) {
                pan[i-minY][j-minX] = lock[i][j];
            }
        }
        for(int i=0;i<pan.length;i++) System.out.println(Arrays.toString(pan[i]));

        // 4가지 회전하여 일부가 맞는 부분이 있으면 통과 (패널을 돌면서)

        int[][] rot90 = new int[n][n];
        int[][] rot180 = new int[n][n];
        int[][] rot270 = new int[n][n];

        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                rot90[i][j] = key[n-j-1][i];
                rot180[i][j] = key[n-i-1][n-j-1];
                rot270[i][j] = key[j][n-i-1];
            }
        }
        // 90도 회전 -> n-j-1, i
        // 180도 회전 -> n-i-1, n-j-1
        // 270도 회전 -> j, n-i-1

        System.out.println("==== key ===");
        for(int i=0;i<n;i++) System.out.println(Arrays.toString(key[i]));
        System.out.println("==== //key ===");
        System.out.println("==== key ===");
        for(int i=0;i<n;i++) System.out.println(Arrays.toString(rot90[i]));
        System.out.println("==== //key ===");
        System.out.println("==== key ===");
        for(int i=0;i<n;i++) System.out.println(Arrays.toString(rot180[i]));
        System.out.println("==== //key ===");
        System.out.println("==== key ===");
        for(int i=0;i<n;i++) System.out.println(Arrays.toString(rot270[i]));
        System.out.println("==== //key ===");

        int panX = pan[0].length;
        int panY = pan.length;
        for(int i=0;i<n - panY;i++) {
            for(int j=0;j<n - panX;j++) {
                boolean check0 = false;
                boolean check90 = false;
                boolean check180 = false;
                boolean check270 = false;

                for(int k=0;k<panY;k++) {
                    for(int l=0;l<panX;l++) {
                        if(pan[k][l] == key[i+k][j+l]) check0 = true;
                        if(pan[k][l] == rot90[i+k][j+l]) check90 = true;
                        if(pan[k][l] == rot180[i+k][j+l]) check180 = true;
                        if(pan[k][l] == rot270[i+k][j+l]) check270 = true;
                        if(check0 && check90 && check180 && check270) break;
                    }
                    if(check0 && check90 && check180 && check270) break;
                }
                if(!(check0 && check90 && check180 && check270)) return true;
            }
        }
        return false;
    }
    public static int[] solution2(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int n = enroll.length;

        HashMap<String, Member> memMap = new HashMap<>();
        memMap.put("-", new Member("-", null, 0));
        for(int i=0;i<n;i++) {
            Member mem = new Member(enroll[i], memMap.get(referral[i]), 0);
            memMap.put(enroll[i], mem);
        }
        // 초기화

        int m = seller.length;
        for(int i=0;i<m;i++) {
            Member mem = memMap.get(seller[i]);
            mem.perMoney = amount[i] * 100;
            while(mem.parent != null) {
                int mon = mem.perMoney;
                int pmon = mon/10;
                mon -= pmon;
//                if(mem.name.equals("mary")) System.out.println("MARY :: " + mon +" " + pmon);
                mem.money += mon;
                mem.parent.perMoney = pmon;
                mem = mem.parent;
            }
        }
        int[] answer = new int[n];
        for(int i=0;i<n;i++) {
            Member mem = memMap.get(enroll[i]);
            answer[i] = mem.money;
 //            System.out.println(mem  +" " + (mem.parent != null ? mem.parent.name : ""));
        }


        return answer;
    }

    static class Member {
        String name;
        Member parent;
        int money;
        int perMoney;

        public Member(String name, Member parent, int money) {
            this.name = name;
            this.parent = parent;
            this.money = money;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Member getParent() {
            return parent;
        }

        public void setParent(Member parent) {
            this.parent = parent;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        @Override
        public String toString() {
            return "Member{" +
                    "name='" + name + '\'' +
                    ", money=" + money +
                    '}';
        }
    }
}
