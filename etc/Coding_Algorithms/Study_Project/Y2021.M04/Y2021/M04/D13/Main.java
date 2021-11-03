package Y2021.M04.D13;
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
		int m = Integer.parseInt(tok.nextToken());
		String s = rd.readLine();
		String t = rd.readLine();
		Seq[] pos = new Seq[m]; 	// ��� ������ ���� ��ġ�� �� max������ ������ Seq �迭
		int i=0, j=0, k=m-1; 	// �ε���
		while(s.charAt(j) != t.charAt(i)) j++; // ���� ù��° ��ġ�� ã�´�.
		pos[0] = new Seq(0,j); // ù��° ������ġ ����, ù��°���� i-1�� ���� �����Ƿ� �ִ��� �������� ����.
		for(i=n-1;i>j;i--) {
			if(k > 0 && s.charAt(i) == t.charAt(k)) {
				pos[k] = new Seq(0,i); 	// �ִ��� �迭 �ϼ��� �Ŀ� �ٽ� �����.
				k--;
			}
		} // ù��° ���� ������ ������ ���ڵ��� ������ ���������� ã�Ƴ��´�.
		for(i=1;i<m;i++) pos[i].setMax(pos[i].cindex - pos[i-1].cindex);	 // �迭�ϼ��� �� �ʱ� �ִ��� ����Ͽ� ������.
		i =1;		// i�ε����� �ݺ������� ���� t���ڿ��� ��ġ�� �ǹ���.
		j++; 	// j�ε����� �ݺ������� ���� s���ڿ��� ��ġ�� �ǹ���.
		while(i < m-1 && j < n) {
			if(s.charAt(j) == t.charAt(i)) {
				pos[i].setIndex(j);
				pos[i].setMax(pos[i].cindex - pos[i-1].cindex);
				pos[i+1].setMax(pos[i+1].cindex - pos[i].cindex); 	// �ִ��� �ٲﰪ�� ���簪�� �� ū������ �����Ѵ�.
				i++;
			}	// s���ڿ����� �̹����� �������� ���̸鼭  t���ڿ��� ��ġ�� ã�Ƴ���.
			j++;
		}
		Arrays.sort(pos);
		System.out.println(pos[0].max);
//		System.out.println(Arrays.toString(pos));
	}
	static class Seq implements Comparable<Seq> {
		int max;
		int cindex;
		public Seq(int m, int c) {
			this.max = m;
			this.cindex=c;
		}
		public void setMax(int m) {
			this.max = Math.max(this.max, m);
		}
		public void setIndex(int c) {
			this.cindex = c;
		}
		@Override
		public String toString() {
			return "{max=" + max + ", cindex=" + cindex + "}";
		}
		@Override
		public int compareTo(Seq o) {
			return o.max - this.max;
		}
		
	}
}