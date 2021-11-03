package Y2021.M03.D24;
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
		ArrayList<Integer> lst = new ArrayList<Integer>();
		int a = 3;
		int b = 4;
		int c = 5;
		while(c <= 1000000000) {
			lst.add(c);
			a += 2;
			b = (((int)Math.pow(a,2)) - 1) / 2;
			c = b+1;
		}
		int TestCase = Integer.parseInt(rd.readLine());
		for(int TT=0;TT<TestCase;TT++) {
			int n = Integer.parseInt(rd.readLine());
			int index = 0;
			while(index < lst.size() &&lst.get(index) <= n) index++;
			wr.write(index+"");
			wr.newLine();
		}
		wr.flush();
	}
	public static void solution() throws Exception {
		int TestCase = Integer.parseInt(rd.readLine());
		for(int TT=0;TT<TestCase;TT++) {
			int n = Integer.parseInt(rd.readLine());
			// �Է�
			
			// �� ��� ���� n���� ������ ����������, �������� �ʴ��� üũ�Ѵ�.
			// �������� ������ �������� n/2�̹Ƿ�, ��� ���� ���� �ѹ��� ���ºο� playCount���� �¸��� ������ �ؾ��Ѵ�.
			int[] arr = new int[n+1]; 		// 0�� �������� �ʰ�, 1~n�� ����Ʈ�� ������ �迭
			int playCount = (n * (n-1)) / 2 ;
			int tarPoint = 0;
			if(playCount % n == 0) tarPoint = (playCount / n)*3;
			else tarPoint = ((playCount / n)*3) + 1;
			
			for(int i=1;i<n;i++) {
				for(int j=i+1;j<=n;j++) {
					if(arr[i] <= tarPoint - 3) {
						wr.write("1 ");
						arr[i] += 3;
					} else if(arr[i] < tarPoint) {
						wr.write("0 ");
						arr[i] += 1;
						arr[j] += 1;
					} else {
						wr.write("-1 ");
						arr[j] += 3;
					}
				}
			}
			wr.newLine();
		}
		wr.flush();
	}
}