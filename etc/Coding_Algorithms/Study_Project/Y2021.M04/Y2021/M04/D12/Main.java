package Y2021.M04.D12;
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
		int TestCase = Integer.parseInt(rd.readLine());
		for(int TT=0;TT<TestCase;TT++) {
			int n = Integer.parseInt(rd.readLine());
			tok = new StringTokenizer(rd.readLine());
			int[] origin = new int[n];
			for(int i=0;i<n;i++) origin[i] = Integer.parseInt(tok.nextToken());
			// �Էº�
			
			// greedy���Ĺ��� -> ����Ʈ�� �Է¹޾Ƽ� ���ǰ����� ���� ���� �� �ִ� �ִ񰪱��� ��������.
			int max = n, maxTemp = n-1; 	// ������ �ִ��� max�� �����ϰ�, maxTemp�� ���� �ִ��� �� �������� �̸� ��Ƶд�.
			int pos = n;	// ��� ����Ʈ�� �־�� �Ǵ� ��ġ�� ������.
			boolean[] maxbox = new boolean[n+1];	// ���� �ִ��� ���Դ����� üũ���� boolean �迭�� �����Ѵ�.
			maxbox[n] = true;
			for(int i=n-1;i>=0;i--) {
				maxbox[origin[i]] = true;
				while(maxbox[maxTemp]) maxTemp--;	// ������� ���� ������ ���� ����� ���� �ִ��� �����Ѵ�.
				if(origin[i] != max) { 
				} // �ִ��� �ƴ϶�� �������Ʈ�� pos��ġ�� origin�� �������� �ϳ��� �־��ش�.
				else {
					for(int j=i;j<pos;j++) wr.write(origin[j]+" ");
					pos = i;
					max = maxTemp;
				} // �ִ��̶��, pos��ġ�� �ִ��� �־��� ��, pos��ġ�� ���� res����Ʈ�� ����ġ�� �����Ѵ�.
			}
			
			// ����Ʈ�� �������� Log N ������ �ð����⵵�� ������ �߻��� ���� ���� ������ �����ϸ� �迭������ ����.
			wr.newLine();
		}
		wr.flush();
	}
}