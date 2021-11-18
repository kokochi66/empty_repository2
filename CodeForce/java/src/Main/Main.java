package src.Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
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
//        solution(25, new int[]{2,14,11,21,17}, 2);
//        solution(25, new int[]{6, 11, 14, 17, 21}, 2);
//        solution(25, new int[]{10}, 1);
//        solution(25, new int[]{10,10}, 2);
        solution(16, new int[]{4,8,11}, 2);
    }

    public static int solution(int distance, int[] rocks, int n) {

        Arrays.sort(rocks);
        PriorityQueue<Rock> pq = new PriorityQueue<Rock>((a,b) -> {
            return (int)Math.min(b.left, b.right) != (int)Math.min(a.left, a.right) ?  (int)Math.min(a.left, a.right) - (int)Math.min(b.left, b.right) :  (int)Math.max(a.left, a.right) - (int)Math.max(b.left, b.right);
        });

        int m = rocks.length;
        Rock[] rockArr = new Rock[m];
        Rock start = new Rock(1000000000, rocks[0], true);
        Rock end = new Rock(distance - rocks[m-1], 1000000000, true);
        for(int i=0;i<m;i++) {
            rockArr[i] = new Rock(i == 0 ? rocks[0] : rocks[i] - rocks[i-1], m-1 == i ? distance - rocks[i] : rocks[i+1] - rocks[i], false);
            if(i == 0) {
                rockArr[i].setLeftRock(start);
                start.rightRock = rockArr[i];
            }
            else {
                rockArr[i].setLeftRock(rockArr[i-1]);
                rockArr[i-1].setRightRock(rockArr[i]);
            }
            if(i == m-1) {
                rockArr[i].setRightRock(end);
                end.leftRock = rockArr[i];
            }
            pq.add(rockArr[i]);
        }

        int cnt = 0;
        while(!pq.isEmpty() && cnt < n) {
            Rock r = pq.poll();
            System.out.println(r.left +" "+r.right+" "+r.check);
            if(!r.check) {
                cnt++;
                Rock leftR = r.leftRock;
                Rock rightR = r.rightRock;
                if(leftR != null && !leftR.check) {
                    leftR.check = true;
                    Rock nLeftR = new Rock(leftR.left, leftR.right + r.right, false );
                    if(leftR.leftRock != null) {
                        nLeftR.leftRock = leftR.leftRock;
                        leftR.leftRock.rightRock = nLeftR;
                    }
                    if(rightR == null) nLeftR.rightRock = null;
                    r.leftRock = nLeftR;
                } else if(leftR != null && leftR.check) {
                    leftR.right = leftR.right + r.right;
                }


                if(rightR != null && !rightR.check) {
                    rightR.check = true;
                    Rock nRightR = new Rock(rightR.left + r.left, rightR.right, false);
                    if(rightR.rightRock != null) {
                        nRightR.rightRock = rightR.rightRock;
                        rightR.rightRock.leftRock = nRightR;
                    }
                    if(leftR == null) nRightR.leftRock = null;
                    r.rightRock = nRightR;
                } else if (rightR != null && rightR.check) {
                    rightR.left = rightR.left + r.left;
                }

                if(r.leftRock != null && r.rightRock != null) {
                    r.leftRock.rightRock = r.rightRock;
                    r.rightRock.leftRock = r.leftRock;
                }
                if(r.leftRock != null && !r.leftRock.check) pq.add(r.leftRock);
                if(r.rightRock != null && !r.rightRock.check) pq.add(r.rightRock);
            }
        }
        Rock r = null;
        if(pq.isEmpty()) {
//            System.out.println("none :: ");
            r = new Rock(start.right, end.left, false);
        } else {
            r = pq.poll();
            while(r.check) r = pq.poll();
        }

        System.out.println(r.left+" "+r.right+" "+r.check);
        return (int) Math.min(r.left, r.right);
    }

    static class Rock {
        long left;
        long right;
        boolean check;
        Rock leftRock;
        Rock rightRock;

        public Rock(long l, long r, boolean c) {
            this.left = l;
            this.right = r;
            this.check = c;
        }

        public Rock getLeftRock() {
            return leftRock;
        }

        public void setLeftRock(Rock leftRock) {
            this.leftRock = leftRock;
        }

        public Rock getRightRock() {
            return rightRock;
        }

        public void setRightRock(Rock rightRock) {
            this.rightRock = rightRock;
        }

        public long getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public long getRight() {
            return right;
        }

        public void setRight(int right) {
            this.right = right;
        }

        public boolean isCheck() {
            return check;
        }

        public void setCheck(boolean check) {
            this.check = check;
        }

        @Override
        public String toString() {
            return "Rock{" +
                    "left=" + left +
                    ", right=" + right +
                    ", check=" + check +
                    '}';
        }
    }
}
