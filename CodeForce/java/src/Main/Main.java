package src.Main;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tok;
    public static void main(String[] args) throws Exception {
        solution();
    }

    public static void solution() throws Exception {
        int TT = Integer.parseInt(rd.readLine());
        for(int TS=0;TS<TT;TS++) {
            tok = new StringTokenizer(rd.readLine());
            int n = Integer.parseInt(tok.nextToken());
            int m = Integer.parseInt(tok.nextToken());

            tok = new StringTokenizer(rd.readLine());
            Node[] arr = new Node[n+1];
            long[] orinTime = new long[n+1];
            for(int i=0;i<=n;i++) arr[i] = new Node(i);
            for(int i=1;i<=n;i++) {
                arr[i].time = Long.parseLong(tok.nextToken());
                orinTime[i] = arr[i].time;
            }

            for(int i=0;i<m;i++) {
                tok = new StringTokenizer(rd.readLine());
                int idx = Integer.parseInt(tok.nextToken());
                int next = Integer.parseInt(tok.nextToken());
                arr[idx].next.add(arr[next]);
                arr[next].need.add(arr[idx]);
            }

            int target = Integer.parseInt(rd.readLine());
            boolean[] setCheck = new boolean[n+1];

            HashSet<Integer> indexSet = new HashSet<>();
            Queue<Node> nq = new LinkedList<>();
            nq.add(arr[target]);
            while(!nq.isEmpty()) {
                Node node = nq.poll();
                if(setCheck[node.index]) continue;
                setCheck[node.index] = true;
                indexSet.add(node.index);
                for(int i=0;i<node.need.size();i++) {
                    if(!setCheck[node.need.get(i).index]) {
                        nq.add(node.need.get(i));
                    }
                }
            }

            long max = 0;
            Queue<Node> q = new LinkedList<>();
            for(int i=1;i<=n;i++) {
                if(arr[i].need.size() == 0 && indexSet.contains(arr[i].index)) {
                    Node node = new Node(arr[i].index);
                    node.time = arr[i].time;
                    q.add(node);
                }
            }
            while(!q.isEmpty()) {
                Node node = q.poll();
                List<Node> next = arr[node.index].next;
                for(int i=0;i<next.size();i++) {
                    Node cn = next.get(i);
                    if(indexSet.contains(cn.index)) {
                        List<Node> need = cn.need;
                        boolean check = false;
                        for(int j=0;j<need.size();j++) {
                            Node ncn = need.get(j);
                            if(!ncn.cons) {
                                check = true;
                                break;
                            }
                        }
                        if(!check) {
                            // 조건이 모두 갖췄다면 큐에 추가
                            Node nn = new Node(cn.index);
                            nn.time = node.time + arr[cn.index].time;
                        }
                    }
                }
                wr.write(node.toString());
                wr.newLine();
            }


            wr.write(arr[target].time+"");
            wr.newLine();
        }
        wr.flush();
    }

    static class Node {
        int index;
        long time;
        boolean cons;
        List<Node> need;
        List<Node> next;
        public Node(int index) {
            this.index = index;
            this.cons = false;
            need = new ArrayList<>();
            next = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "Node{" +
                    "index=" + index +
                    ", time=" + time +
                    ", cons=" + cons +
                    '}';
        }
    }
}

