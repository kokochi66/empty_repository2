package Y2021.M03.D015;
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
		int m = Integer.parseInt(rd.readLine());
		boolean[] broken = new boolean[10];
		if(m>0) {
			tok = new StringTokenizer(rd.readLine());
			for(int i=0;i<m;i++) {
				int c = Integer.parseInt(tok.nextToken());
				broken[c] = true;
			}
		}
		
		
		int min = Math.abs(100-n);
		int number = 100;
		// �ʱⰪ 100���� ����, �ʱⰪ���� �ش� ��ȣ�� +,-�� �̵��ϴ� ���� ���� �ּҰ����� ������.
		
		for(int i=0;i<=1000000;i++) {
			if(sol_check(i,broken)) {
				int sub = sol_sub(i,n);
				if(sub < min) {
					min = sub;
					number = i;
				}
			}
		}	// 50������ ���� ���� �˾ƾ� �ϱ� ������ 2����� 100������ ��� �� �߿� ���� ª������ ã�Ƴ�.
		// ������ 100�� * üũ(10) * ���̰�����(1) �� �� 1000�������̵�.
		System.out.println(min);
	}
	public static boolean sol_check(int n, boolean[] brk) {
		if(n == 0) return !brk[0];
		while(n>0) {
			if(brk[n%10]) return false;
			n/=10;
		}
		return true;
	}
	public static int sol_sub(int a, int n) {
		return Math.abs(n-a) + String.valueOf(a).length();			// �Է°����� ��ǥ ��ȣ�� ���� ���հ��� �����
	}
}