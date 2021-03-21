import java.io.*;
import java.util.*;
public class test extends PrintWriter {
	test() { super(System.out); }
	Scanner sc = new Scanner(System.in);
	public static void main(String[] $) {
		
		test o = new test(); o.main(); o.flush();
	}
 
	int[] qq;
	int find(int i) {
		return qq[i] < 0 ? i : (qq[i] = find(qq[i]));
	}
	void main() {
		int n = sc.nextInt();
		int[][] aa = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				aa[i][j] = sc.nextInt();
		int m = n * (n - 1) / 2;
		Integer[] hh = new Integer[m];
		m = 0;
		for (int i = 0; i < n; i++)
			for (int j = i + 1; j < n; j++)
				hh[m++] = i * n + j;
		Arrays.sort(hh, (u, v) -> {
			int au = aa[u / n][u % n];
			int av = aa[v / n][v % n];
			return au - av;
		});
		int k = n + n - 1;
		int[] cc = new int[k];
		for (int i = 0; i < n; i++)
			cc[i] = aa[i][i];
		int[] pp = new int[k]; Arrays.fill(pp, -1);
		qq = new int[k]; Arrays.fill(qq, -1);
		k = n;
		for (int h = 0; h < m; h++) {
			int u = hh[h], i = u / n, j = u % n, a = aa[i][j];
			i = find(i);
			j = find(j);
			if (i != j) {
				pp[i] = qq[i] = pp[j] = qq[j] = k;
				cc[k++] = a;
			}
		}
		System.out.println(Arrays.toString(pp));
		System.out.println(Arrays.toString(qq));
		System.out.println(Arrays.toString(cc));
		System.out.println(k);
		int k_ = 0;
		int[] ii = new int[k];
		for (int i = 0; i < k - 1; i++)
			if (cc[pp[i]] > cc[i])
				ii[i] = k_++;
		ii[k - 1] = k_++;
		println(k_);
		for (int i = 0; i < k - 1; i++)
			if (cc[pp[i]] > cc[i])
				print(cc[i] + " ");
		println(cc[k - 1]);
		println(k_);
		for (int i = 0; i < k - 1; i++)
			if (cc[pp[i]] > cc[i]) {
				int p = pp[i], p_;
				while ((p_ = pp[p]) != -1 && cc[p_] == cc[p])
					p = p_;
				println((ii[i] + 1) + " " + (ii[p] + 1));
			}
	}
}
