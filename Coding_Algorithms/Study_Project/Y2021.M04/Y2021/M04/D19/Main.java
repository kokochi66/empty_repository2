package Y2021.M04.D19;
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
		int n = Integer.parseInt(rd.readLine());
		int[][] dp = new int[n+1][n+1];
		int INF = 1000000000;
		int max = 0;
		for(int i=0;i<n-1;i++) {
			tok = new StringTokenizer(rd.readLine());
			int a = Integer.parseInt(tok.nextToken());
			int b = Integer.parseInt(tok.nextToken());
			int c = Integer.parseInt(tok.nextToken());
			dp[a][b] = c;
			dp[b][a] = c;
			if(max < c) max = c;
		}
		// ÀÔ·ÂºÎ
		
		Queue<Integer> q = new LinkedList<>();
		boolean[] chk = new boolean[n+1];
		q.add(1);
		int Leaf =0;
		while(!q.isEmpty()) {
			Leaf = q.poll();
			chk[Leaf] = true;
			for(int i=1;i<=n;i++) {
				if(!chk[i] && dp[Leaf][i] > 0) {
					q.add(i);
					break;
				}
			}
		}
		
		if(n > 1) {
			Arrays.fill(chk, false);
			ArrayList<Integer> ts = new ArrayList<>();
			int root  = 0;
			for(int i=1;i<=n;i++) {
				if(!chk[i] && dp[Leaf][i] > 0) {
					root = i;
					break;
				}
			}
			chk[Leaf] = true;
			chk[root] = true;
			ts.add(Leaf);
			ts.add(root);
			
			Queue<Integer[]> qs = new LinkedList<>();
			for(int i=1;i<=n;i++) {
				if(!chk[i] && dp[root][i] > 0) {
					qs.add(new Integer[] {i, root});
				}
			}
			
			while(!qs.isEmpty()) {
				int cn = qs.peek()[0];
				int cr = qs.poll()[1];
				chk[cn] = true;
				for(int i=0;i<ts.size();i++) {
					int cw = ts.get(i);
					int dist = dp[cn][cr] + dp[cr][cw];
					if(dist > max) max = dist;
					dp[cn][cw] = dist;
					dp[cw][cn] = dist;
				}
				for(int i=1;i<=n;i++) {
					if(!chk[i] && dp[cn][i] > 0) {
						qs.add(new Integer[] {i, cn});
					}
				}
				ts.add(cn);
			}
		}
		
//		for(int i=0;i<=n;i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
		System.out.println(max);
		
	}
}