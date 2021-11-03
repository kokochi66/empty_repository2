package Algorithms.Graph;
import java.util.*;
public class AL_Dijkstra {
	public static void main(String[] args) {
		final int INF = 1000000000;
		int[][] graph = {
				{0, 2, 5, 1, INF, INF},
				{2, 0, 3, 2, INF, INF},
				{5, 3, 0, 3, 1, 5},
				{1, 2, 3, 0, 1, INF},
				{INF, INF, 1, 1, 0, 2},
				{INF, INF, 5, INF, 2, 0}
		};
		
		
		
//		Dijkstra_Array djk = new Dijkstra_Array(6,graph);
//		djk.rot(5);
//		for(int i=0;i<6;i++) {
//			System.out.println(Arrays.toString(djk.graph[i]));
//		}
		Dijkstra_pq djk = new Dijkstra_pq(6);
		djk.putEdge(0, 1, 2);
		djk.putEdge(0, 2, 5);
		djk.putEdge(0, 3, 1);
		djk.putEdge(1, 2, 3);
		djk.putEdge(1, 3, 2);
		djk.putEdge(2, 3, 3);
		djk.putEdge(2, 4, 1);
		djk.putEdge(2, 5, 5);
		djk.putEdge(3, 4, 1);
		djk.putEdge(4, 5, 2);
		djk.rot(0);
		System.out.println(Arrays.toString(djk.res));
	}

	static class Dijkstra_Array {	// �迭�� �̿��Ͽ� ������ ���ͽ�Ʈ�� �˰��� (������ ���� �� ��ȿ������)
		final int INF = 1000000000;	// ��尡 ������� ���� �κ��� ġȯ�ϱ� ���� ���Ѵ밪
		boolean[] visited;							// �湮���θ� ���� �� �ִ� �Ҹ��� �迭
		int[] res;											// ���ϴ� ��ġ�κ����� �ִܰŸ��� ����� res�迭
		int[][] graph;									// �Է¹��� �׷����� ������ 2���� �迭
		
		public Dijkstra_Array(int n, int[][] grp) {
			visited = new boolean[n];
			res = new int[n];
			graph = new int[n][n];
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					this.graph[i][j] = grp[i][j];
				}
			}
		}	// Ŭ���� �ʱ�ȭ
		
		private int getSmallIndex() {
			int min = INF;
			int index = 0;
			for(int i=0;i<res.length;i++) {
				if(res[i] < min && !visited[i]) {	// �ּڰ��̸鼭, ���� �湮���� ���� ���� ã�Ƴ�
					min = res[i];
					index = i;
				}
			}
			return index;
		}	// ���� ����� �ּ� ����ġ�� �ε������� ����ϴ� �Լ�
		
		public void rot(int start) {
			for(int i=0;i<res.length;i++) res[i] = graph[start][i];		// �ִܰŸ� �迭�� �ʱ� ���۰��� �Ÿ��� �ʱ�ȭ
			visited[start] = true;
			 for(int i=0;i<res.length-1;i++) {
				 int c = getSmallIndex();		// ���� ���� ª�� �Ÿ��� ���� �ε����� ã����(�켱���� ť ����)
				 visited[c] = true;
				 for(int j=0; j< res.length;j++) {
					 if(!visited[j]) {
						 res[j] = res[c] + graph[c][j] < res[j] ? res[c] + graph[c][j] : res[j];	// �ٸ� ������ �湮�� �Ÿ��� �� ª���� �ִܰŸ� �迭�� ��������
					 }
				 }
			 }
		}	// �����̼��� ���� ���� �ִܰŸ� �迭�� ���ϴ� �����κ����� �ִܰŸ��� ������
		
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
