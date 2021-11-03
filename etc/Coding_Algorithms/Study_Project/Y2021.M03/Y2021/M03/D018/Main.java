package Y2021.M03.D018;
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
		tok = new StringTokenizer(rd.readLine());
		int n = Integer.parseInt(tok.nextToken());
		int k = Integer.parseInt(tok.nextToken());
		tok = new StringTokenizer(rd.readLine());
		int[] arr = new int[n];
		for(int i=0;i<n;i++) {
			arr[i] = Integer.parseInt(tok.nextToken());
		}// �Էº�
		
		int left = -1; // ���� ���´ܰ��� �����Ѵ�. ó������ ���´ܰ��� �����Ƿ� -1�� �ʱ�ȭ
		int max = 0; // ���忬�Ӻκм����� ���� �� ���� ������ max�� 0���� �ʱ�ȭ�Ѵ�.
		panel[] pan = new panel[100001];	//��� ���� Ȯ������ Ŭ�����迭�� �����Ѵ�.
		for(int i=0;i<=100000;i++) {
			pan[i] = new panel();	// �� �迭�� ������ �ʱ�ȭ���ش�.
		}
		
		for(int i=0;i<n;i++) {
			int f = pan[arr[i]].addpan(i, left, k);
			if(f != -1) {
				left = f;
			}
//			System.out.println(i+" "+max+" "+(i-left));
			max = Math.max(max, i - left);
		}
		System.out.println(max);
	}
	
	static class panel {
		List<Integer> pos;
		public panel() {
			pos = new ArrayList<Integer>();
		}
		
		public int addpan(int n, int left, int k) {
			 while(pos.size()>0 && pos.get(0) <= left) {
				 pos.remove(0);
			 } // ������ ���´� �����ǰ����� ���������� �����ϰ� ���� �־��ش�.
			 pos.add(n);
			 
			 if(pos.size() > k) {
				 return pos.remove(0);		// ���Ұ� k�� �ʰ��Ѵٸ� ���� ������ �����ϰ�, �ش� ������ ���� ���´ܰ��� �Ǿ�, �����Ѵ�.
			 }
			 return -1; 	// �ʰ����� �ʴ´ٸ�, -1�� �����Ѵ�. �� ��, -1�� �������� ���Ÿ� �ǹ��ϰ� �ȴ�.
		} 	
	}
}