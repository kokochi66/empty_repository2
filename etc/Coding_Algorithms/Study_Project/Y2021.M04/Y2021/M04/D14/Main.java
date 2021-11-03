package Y2021.M04.D14;
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
		int a = Integer.parseInt(tok.nextToken());
		int b = Integer.parseInt(tok.nextToken());
		int k = Integer.parseInt(tok.nextToken());
		if((k <= a+b-2 && a > 0 && b > 1) || (k == 0 && (a == 0 || b == 1))) {
			wr.write("Yes");
			wr.newLine();
			int n = a+b, cIndex = 0;
			char[] x = new char[n];
			char[] y = new char[n];
			for(int i=0;i<n;i++) {
				if(i < b) {
					x[i] = '1';
					y[i] = '1';
					cIndex = i;		//  ���峡���ִ� 1�� ��ġ�� �����Ѵ�.
				} else {
					x[i] = '0';
					y[i] = '0';
				}
			} // �� ����� �迭�� �����. << �ϴ� �ִ����� ������.
			boolean[] pos = new boolean[n];
			for(int i=1;i<n;i++) if(x[i] == '1') pos[i] = true; 	// y�� 1�� ������ �� �ִ� ��ġ�� ǥ���ϱ����� pos �迭�� �ʱ�ȭ 
			
			int kCount = 0 ,nIndex = cIndex; // ���� kCount�� ��ǥ�� k�� ���������� �˰����� �ݺ��ؾ���.
			while(kCount < k) {
//				System.out.println(Arrays.toString(y) +" ����  :: " + kCount);
				if(y[n-1] != '1') {
					y[cIndex++] = '0';
					y[cIndex] = '1';
					if(cIndex == n-1) {
						cIndex = nIndex;
						nIndex--;
					}
				} // �ʱ갪�� ���� ���� ���� �ʾҴٸ� �ʱ갪�� �������־���Ѵ�.
				else {
					if(y[cIndex+1] == '0' && pos[cIndex+1]) {
						y[cIndex++] = '0';
						y[cIndex] = '1';
					} // ���� ������ �� �ִ� ������ �����ִ� ���
					else {
						cIndex = nIndex;
						nIndex--;
						y[cIndex++] = '0';
						y[cIndex] = '1';
					} 	// ������ �� �ִ� ������ ������ nIndex�� ������ġ�� ������ ��, ���� �������ش�.
				} // �ʱ갪 ������ ������ pos ��ġ�� ���� ���������� �ʿ��ϴ�.
				kCount++;
			}
			for(int i=0;i<n;i++) wr.write(x[i]);
			wr.newLine();
			for(int i=0;i<n;i++) wr.write(y[i]);
			wr.newLine();
		} else wr.write("No");
		wr.flush();
	}
}