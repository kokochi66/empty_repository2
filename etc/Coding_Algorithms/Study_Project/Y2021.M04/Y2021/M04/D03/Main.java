package Y2021.M04.D03;
import java.util.*;
import java.io.*;
public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tok;
	public static void main(String[] args) throws Exception {
		solution4();
	}
	public static void solution4() throws Exception {
		int n = Integer.parseInt(rd.readLine());
		boolean[][][] check = new boolean[n][n][3];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				int c = sol4_query();
			}
		}
	}
	
	public static void sol4_BFScheck(int y, int x) {
		
	}
	
	public static int sol4_findc(int color, int y, int x) {
		
		return 0;
	}
	
	public static int sol4_query() throws Exception {
		System.out.flush();
		return Integer.parseInt(rd.readLine());
	}
	
	public static void solution3() throws Exception {
		int TestCase = Integer.parseInt(rd.readLine());
		for(int TT=0;TT<TestCase;TT++) {
			int n = Integer.parseInt(rd.readLine());
			char[] s = rd.readLine().toCharArray();
			StringBuilder a = new StringBuilder("");
			StringBuilder b = new StringBuilder("");
			boolean pass = false;
			
			
			if(s[0] == s[n-1] && s[0] == '1') {
				// �� �հ� �� �ڰ� 1�� �ƴ϶��, �� ���ڿ����� ���۰� ���� ��ȣ�� ���� �ʰԵȴ�.
				// ���⼭ ���۰� ���� ��ȣ�� ( �� ) �� ���� �������ֱ� ������, �� ���� �޶����� �� �� �ϳ��� �뷱���� �������� �ǹǷ� NO�̴�.
				int[] cnt = new int[2];
				for(int i=1;i<n-1;i++) cnt[s[i] - '0']++;
				// �� �հ� �� �ڸ� ������ 0�� 1�� ������ ���ش�.
				if(cnt[0]%2 == 0 && cnt[1]%2 == 0) pass = true;
				// 0�� 1�� ������ ��� ¦�������Ѵ�.
				// �ϴ� ���� ��ü�� Ȧ���̸� �������� �����Ƿ� ���̴� ������ ¦���̴�.
				// ���⼭ �� �� �ϳ��� Ȧ����� ������ �ϳ��� Ȧ���� �ȴ�.
				// �׷��� �Ѵ� Ȧ���̸�, 1���� �����ϴ� 1�� ���� ���� ��ȣ�� 0�� ���ؼ� �����ټ��� ����.
				// �׷��Ƿ� 0�� 1�� ������ ��� ¦������ �����Ѵ�.
				
				if(pass) {
					char c = ')';
					a.append('(');
					b.append('(');
					// �ʱⰪ�� ���� ��ȣ�� �����Ѵ�.
					int cntOne = 0;
					
					for(int i=1;i<n-1;i++) {
						if(s[i] == '0') {	// �迭���� 0�̶�� ���� �ٸ����� �־���Ѵ�.
							// ���⼭ ���� ������ �̹� �޼��ߴٸ� ��� �� ��ȣ�� �����ư��鼭 �����ϸ� �ȴ�.
							// �����ư��鼭 �����ϱ� ���ؼ� c���� �ʱ�ȭ�ѵ� �����Ѵ�.
							a.append(c);	// a�� c���� �����ϰ�
							b.append(c == ')' ? '(' : ')'); // b���� c���� �ݴ� ��ȣ�� �����Ѵ�.
							c = c == ')' ? '(' : ')';	// �����ư��鼭 �������ֱ� ���ؼ� c���� �ݴ밪���� �ٲ��ش�.
						} else if(s[i] == '1') {
							char oc = '(';		// 1�� ��쿡�� ���������� ���°�ȣ, ������ ������ �ݴ°�ȣ�� �������ش�.
							if(cntOne < cnt[1]/2) oc = '(';	// ī��Ʈ�� ���ָ�, ī��Ʈ�� ���ݺ��� ������ ���°�ȣ�� ����
							else oc = ')';	// ī��Ʈ�� ���ݺ��� ������ �ݴ°�ȣ�� �������ش�.
							a.append(oc);
							b.append(oc);
							cntOne++;
						}	// �� ���� �������ְ� ī��Ʈ�� �÷��ָ� �ȴ�.
 					}
					a.append(')');
					b.append(')');
					// ���������� �ݴ°�ȣ�� ������.
				}
			}
			if(pass) {
				wr.write("YES");
				wr.newLine();
				wr.write(a.toString());
				wr.newLine();
				wr.write(b.toString());
				wr.newLine();
			} else {
				wr.write("NO");
				wr.newLine();
			}
		}
		wr.flush();
	}
	
	public static void solution2() throws Exception {
		int TestCase = Integer.parseInt(rd.readLine());
		for(int TT=0;TT<TestCase;TT++) {
			int n = Integer.parseInt(rd.readLine());
			char[] a = rd.readLine().toCharArray();
			char[] b = rd.readLine().toCharArray();
			
			boolean start = false;
			boolean chgau = true;
			boolean pass = true;
			int[] cnt = new int[2];
			for(int i=n-1;i>=0;i--) {
				if(!start && a[i] == b[i]) continue;	// ���ǰ����� �̹� ���� ������ �н��Ѵ�.
				else if(!start) start = true;
				
				if(chgau) {
					if(cnt[0] != cnt[1] && a[i] == b[i]) {
						pass = false;
						break;	// 0�� ������ 1�� ������ ���� ������ �������� �������� NO
					}
					else if(a[i] == b[i]) {
						chgau = false;
//						wr.write(Arrays.toString(a)+" "+Arrays.toString(cnt) +" "+i);
//						wr.newLine();
						i++;
						cnt[0] = 0;
						cnt[1] = 0;
						continue;
					} // 0�� ������ 1�� ������ ������ �������� ������ �ٽ� ������ ���� ���� üũ�ϵ��� �Ѵ�.
					else {
						cnt[a[i]-'0']++;
					}	// �׳� �ٸ����� ���� ��� ī��Ʈ�� �÷��ش�.
				} else {
					if(cnt[0] != cnt[1] && a[i] != b[i]) {
						pass = false;
						break;	// 0�� ������ 1�� ������ ���� ������ �ٸ����� �������� NO
					}
					else if(a[i] != b[i]) {
						chgau = true;
//						wr.write(Arrays.toString(a)+" "+Arrays.toString(cnt) +" "+i);
//						wr.newLine();
						i++;
						cnt[0] = 0;
						cnt[1] = 0;
						continue;
					} // 0�� ������ 1�� ������ ������ �ٸ����� ������ �ٽ� ������ ���� ���� üũ�ϵ��� �Ѵ�.
					else {
						cnt[a[i]-'0']++;
					}	// �׳� ���� ���� ���� ��� ī��Ʈ�� �÷��ش�.
				}
				
				if(i == 0 && cnt[0] != cnt[1]) pass = false;		// �Ǿտ��� ���� ī��Ʈ�� ������ ���� ������ NO
			}
			wr.write(pass ? "YES" : "NO");
			wr.newLine();
		}
		wr.flush();
	}
	
	public static void solution() throws Exception {
		int TestCase = Integer.parseInt(rd.readLine());
		for(int TT=0;TT<TestCase;TT++) {
			StringBuilder str = new StringBuilder(rd.readLine());
			int n = str.length();
			boolean yes = false;
			for(int i=0;i<n;i++) {
				if(str.charAt(i) != 'a') {
					str.insert(n-i, 'a');
					yes = true;
					break;
				}
			}
			if(yes) {
				System.out.println("YES");
				System.out.println(str);
			} else System.out.println("NO");
		}
	}
}