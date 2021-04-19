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
	
	static boolean[] chk;
	static ArrayList<Integer[]>[] nd;
	public static void init() {
		Arrays.fill(chk, false);
	}
	
	public static int dfs(int n, int len) {
		int max = len;
		chk[n] = true;
		for(int i=0;i<nd[n].size();i++) {
			Integer[] c = nd[n].get(i);
			if(!chk[c[0]]) max = Math.max(max, dfs(c[0], len + c[1]));
		}
		return max;
	}
	public static void solution() throws Exception {
		int n = Integer.parseInt(rd.readLine());
		nd = new ArrayList[n+1];
		chk = new boolean[n+1];
		for(int i=0;i<=n;i++) nd[i] = new ArrayList<Integer[]>();
		for(int i=0;i<n-1;i++) {
			tok = new StringTokenizer(rd.readLine());
			int a = Integer.parseInt(tok.nextToken());
			int b = Integer.parseInt(tok.nextToken());
			int c = Integer.parseInt(tok.nextToken());
			nd[a].add(new Integer[] {b,c});
			nd[b].add(new Integer[] {a,c});
		} // ÀÔ·ÂºÎ
		int[] max = new int[nd[1].size()];
		for(int i=0;i<nd[1].size();i++) {
			init();
			chk[1] = true;
			Integer[] c = nd[1].get(i);
			max[i] = dfs(c[0], c[1]);
		}
		System.out.println(Arrays.toString(max));
		Arrays.sort(max);
		System.out.println(max.length >=2 ? max[0]+max[1] : max[0]);
	}
}