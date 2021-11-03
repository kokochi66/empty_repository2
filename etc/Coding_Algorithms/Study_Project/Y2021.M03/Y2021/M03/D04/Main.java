package Y2021.M03.D04;
import java.util.*;

import java.io.*;
public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tok;
	public static void main(String[] args) throws Exception {
		solution();
	}
	public static void solution() throws Exception {
		 int v = Integer.parseInt(rd.readLine());
		 int e = Integer.parseInt(rd.readLine());
		 Dijkstra_pq dik = new Dijkstra_pq(v);
		 for(int i=0;i<e;i++) {
			 tok = new StringTokenizer(rd.readLine());
			 int a = Integer.parseInt(tok.nextToken()) -1;
			 int b = Integer.parseInt(tok.nextToken()) -1;
			 int w = Integer.parseInt(tok.nextToken());
			 dik.putSingleEdge(a, b, w);
		 }
		
		 tok = new StringTokenizer(rd.readLine());
		 int start = Integer.parseInt(tok.nextToken()) -1;
		 int end = Integer.parseInt(tok.nextToken()) -1;
		 dik.rot(start);
//		 System.out.println(Arrays.toString(dik.res));
		 System.out.println(dik.res[end]);
	}
	static class Dijkstra_pq {	// ����Ʈ�� �켱���� ť�� �̿��� ������ ���ͽ�Ʈ�� �˰���
		final int INF = 1000000000;
		List<Edge>[] grp;
		int[] res;
		boolean[] visited;
		static class Edge implements Comparable<Edge> { // ���� Ŭ������ ��������
			int v, weight;
			public Edge(int v, int w) {
				this.v = v;
				this.weight = w;
			}
			@Override
			public int compareTo(Edge o) {
				return Integer.compare(this.weight, o.weight); // �켱���� ť�� ���� �񱳰� ������
			}
		}
		
		public Dijkstra_pq(int v) {
			grp = new ArrayList[v];
			res = new int[v];
			visited = new boolean[v];
			for(int i=0;i<v;i++) grp[i] = new ArrayList<>();
		} 	// ���ͽ�Ʈ�� �ʱ� ���� v�� ������ ����
		
		public void putEdge(int a, int b, int w) {
			grp[a].add(new Edge(b, w));
			grp[b].add(new Edge(a,w));
		} // ����� ���� ����
		public void putSingleEdge(int a, int b, int w) {
			grp[a].add(new Edge(b,w));
		} // �ܹ��� ���� ����
		
		public void rot(int start) {
			PriorityQueue<Edge> pq = new PriorityQueue<>(); 	// �켱���� ť �ʱ�ȭ
			Arrays.fill(visited, false);
			Arrays.fill(res, INF);
			res[start] = 0;
			pq.add(new Edge(start,0));

			while(!pq.isEmpty()) {
				Edge curr = pq.poll();
				if(!visited[curr.v]) {
					visited[curr.v] = true;		// �켱���� ť���� ���� ������ �湮ó����
					for(Edge next : grp[curr.v]) { // for���� ���� ���� ������ ������ ������ �ϳ��� ����
						if(!visited[next.v] && res[curr.v] + next.weight < res[next.v]) {
							res[next.v] = res[curr.v] + next.weight;
							pq.add(new Edge(next.v, res[next.v]));
						}	// �� ��ġ���� ��ü����
					}
				}
				
			}
		} // �˰����� �̿��Ͽ� �ִܰŸ��� ã�Ƴ�
	}
}