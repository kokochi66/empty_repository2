package src.Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Objects;
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
        solution(25, new int[]{6, 11, 14, 17, 21}, 2);
//        solution(25, new int[]{10}, 1);
//        solution(25, new int[]{10,10}, 2);
//        solution(16, new int[]{4,8,11}, 2);
//        solution(28, new int[]{2,6,10,14,17,22,27}, 4);
    }

    public static int solution(int distance, int[] rocks, int n) {

        Arrays.sort(rocks);
        PriorityQueue<Rock> pq = new PriorityQueue<Rock>((a,b) -> {
            return (int)Math.min(b.left, b.right) != (int)Math.min(a.left, a.right) ?  (int)Math.min(a.left, a.right) - (int)Math.min(b.left, b.right) :  (int)Math.max(a.left, a.right) - (int)Math.max(b.left, b.right);
        });

        int m = rocks.length;
        Rock[] rockArr = new Rock[m];
        Rock start = new Rock(1000000000, rocks[0], true, -1);
        Rock end = new Rock(distance - rocks[m-1], 1000000000, true, -1);
        for(int i=0;i<m;i++) {
            rockArr[i] = new Rock(i == 0 ? rocks[0] : rocks[i] - rocks[i-1], m-1 == i ? distance - rocks[i] : rocks[i+1] - rocks[i], false, i);
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
            System.out.println("loop R :: " +r);
            if(!r.check) {
                Rock leftR = r.leftRock;
                Rock rightR = r.rightRock;
                if((leftR != null && leftR.check) || (rightR != null && rightR.check)) {
                    // 왼쪽R이 끝값이면, 제거
                    leftR.right += r.right;
                    rightR.left += r.left;
                } else {
                    // 그냥 중간값인 경우,
                    // pq가 2개이상 남아있을경우, 2개 꺼내서 왼쪽 오른쪽 비교 => 같으면 왼쪽 오른쪽을 지움, 아니면 자신만 지움
                    Rock pqNext1 = null;
                    Rock pqNext2 = null;
                    boolean loopCheck = false;
                    while(!pq.isEmpty()) {
                        Rock pqN = pq.poll();
                        if(!pqN.check) {
//                            System.out.println("루프체크 :: " + pqN+" "+leftR+" "+rightR);
                            if(pqNext1 == null) pqNext1 = pqN;
                            else if(pqNext2 == null) {
                                pqNext2 = pqN;
                                if((pqNext1.equals(leftR) || pqNext1.equals(rightR)) && (pqNext2.equals(leftR) || pqNext2.equals(rightR))) loopCheck = true;
                                break;
                            }
                        }
                    }
                    if(loopCheck) {
                        // 왼쪽오른쪽 제거
                        cnt+=2;
                        System.out.println("왼쪽 오른쪽 들어옴 :: " + pqNext1 +" "+pqNext2+" "+r.leftRock+" "+r.rightRock);
                        removeRock(r.leftRock, pq, rockArr);
                        removeRock(r.rightRock, pq, rockArr);
                    }
                    if(!loopCheck) {
                        // 그냥 본인 제거
                        cnt++;
                        System.out.println("그냥 본인제거");
                        if(pqNext1 != null) pq.add(pqNext1);
                        if(pqNext2 != null) pq.add(pqNext2);
                        removeRock(r, pq, rockArr);
                    }
                }
            }
            for(int i=0;i< rockArr.length;i++) {
                System.out.println(rockArr[i]);
            }
            System.out.println("===============================");
        }
        Rock r = null;
        if(pq.isEmpty()) {
//            System.out.println("none :: ");
            r = new Rock(start.right, end.left, false, -1);
        } else {
            r = pq.poll();
            while(r.check) r = pq.poll();
        }

        System.out.println(r.left+" "+r.right+" "+r.check);
        return (int) Math.min(r.left, r.right);
    }

    public static void removeRock(Rock r, PriorityQueue<Rock> pq, Rock[] arr) {
        Rock leftR = r.leftRock;
        Rock rightR = r.rightRock;
        r.check = true;
        if(leftR != null && !leftR.check) {
            leftR.check = true;
            Rock nLeftR = new Rock(leftR.left, leftR.right + r.right, false , leftR.index);
            if(leftR.leftRock != null) {
                nLeftR.leftRock = leftR.leftRock;
                leftR.leftRock.rightRock = nLeftR;
            }
            if(rightR == null) nLeftR.rightRock = null;
            System.out.println("LEFT :: " + r+" "+leftR+" "+nLeftR);
            r.leftRock = nLeftR;
            arr[leftR.index] = nLeftR;
        } else if(leftR != null && leftR.check) {
            leftR.right = leftR.right + r.right;
        }   // 최소 Rock이 끝값이라면, 그냥 제거


        if(rightR != null && !rightR.check) {
            rightR.check = true;
            Rock nRightR = new Rock(rightR.left + r.left, rightR.right, false, rightR.index);
            if(rightR.rightRock != null) {
                nRightR.rightRock = rightR.rightRock;
                rightR.rightRock.leftRock = nRightR;
            }
            if(leftR == null) nRightR.leftRock = null;
            r.rightRock = nRightR;
            arr[rightR.index] = nRightR;
        } else if (rightR != null && rightR.check) {
            rightR.left = rightR.left + r.left;
        }   // 최소 Rock이 끝값이라면, 그냥 제거
        if(r.leftRock != null && r.rightRock != null) {
            r.leftRock.rightRock = r.rightRock;
            r.rightRock.leftRock = r.leftRock;
        }
        if(r.leftRock != null && !r.leftRock.check) pq.add(r.leftRock);
        if(r.rightRock != null && !r.rightRock.check) pq.add(r.rightRock);
    }

    static class Rock {
        long left;
        long right;
        int index;
        boolean check;
        Rock leftRock;
        Rock rightRock;

        public Rock(long l, long r, boolean c, int i) {
            this.left = l;
            this.right = r;
            this.check = c;
            this.index = i;
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
                    ", index=" + index +
                    ", check=" + check +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Rock rock = (Rock) o;
            return getLeft() == rock.getLeft() && getRight() == rock.getRight();
        }
    }
}
