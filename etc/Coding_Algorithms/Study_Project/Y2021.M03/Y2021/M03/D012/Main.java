package Y2021.M03.D012;
import java.util.*;
import java.io.*;
public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tok;
	public static void main(String[] args) throws Exception {
		solution2();
	}
	public static void solution2() throws Exception {
		tok = new StringTokenizer(rd.readLine());
		int n = Integer.parseInt(tok.nextToken());
		int k = Integer.parseInt(tok.nextToken());
		long[][] dp = new long[n+1][k+1];
		// n���� k���� ����� ����� ����
		// n-1�� k���� ����� �� + n�� k-1���� ����� �� �� �����ϰ� �ǹǷ� ���� ���α׷����� �̿��� �� �ִ�.
		for(int i=0;i<=n;i++) {
			for(int j=1;j<=k;j++) {
				if(i==0 || j == 1) dp[i][j] = 1;
				else {
					dp[i][j] = (dp[i-1][j] + dp[i][j-1])%1000000000;
				}
			}
		}
//		for(int i=0;i<=n;i++) System.out.println(Arrays.toString(dp[i]));
		System.out.println(dp[n][k]);
		
	}
	public static void solution() throws Exception {
		tok = new StringTokenizer(rd.readLine());
		int a = Integer.parseInt(tok.nextToken());
		int b = Integer.parseInt(tok.nextToken());
		int c = Integer.parseInt(tok.nextToken());
		int res = a;
		System.out.println(sol_pow(a,b,c)%c);
	}
	public static long sol_pow(long a, long b, long c) {
		if(b == 0) return 1;		// �������� 0�̶�� 1�� ��ȯ
		else if(b == 1) return a;		// �������� 1�̶�� a�� �״�� ��ȯ
		else if(b%2 != 0) return (sol_pow(a,b-1,c)*a)%c;
		// �������� Ȧ����� ������ ���� �� �����Ƿ� -1 ��, a�� ������ ������.
		long half = sol_pow(a,b/2,c)%c;
		// �������� Ǯ��, ������ �������� ������ ���� ��, �ش� ������ ���� �����ִ� ������ ��ħ.
		// O(n Log n)���� Ǯ�� �����ϸ�, �ش� ������ ���� c�� ���������� ���� 
		return (half * half) % c;
		// ���� ���ҵ� ������ ���ļ� �����.
	}
}