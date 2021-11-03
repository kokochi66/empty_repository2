package Y2021.M03.D016;
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
		sal = new int[n][n];
		chk = new boolean[n][n];
		for(int i=0;i<n;i++) {
			tok = new StringTokenizer(rd.readLine());
			chk[i][i] = true;
			for(int j=0;j<n;j++) {
				sal[i][j] = Integer.parseInt(tok.nextToken());
			}
			rec.add(new Integer[] {i+1, i+1, sal[i][i]});		// ����Լ� ���� ���� Leaf ����� ������ ����, Leaf��ġ��, ������ġ��, ����Sal���� ������
			num_sal.add(sal[i][i]);
		} // �Էº�
		int root = sol_rec(n+1);
		System.out.println(root);
		for(int i=0;i<num_sal.size();i++) System.out.print(num_sal.get(i)+" ");
		System.out.println();
		System.out.println(root);
		for(int i=0;i<edge.size();i++) {
			Integer[] c = edge.get(i);
			System.out.println(c[0]+" "+c[1]);
		}
	}
	static ArrayList<Integer[]> rec = new ArrayList<Integer[]>();			// ����Լ��� ���� ����Ʈ
	static ArrayList<Integer> num_sal = new ArrayList<Integer>();		// ���1, �� ������ ��ȣ�� ������ ���� 
	static ArrayList<Integer[]> edge = new ArrayList<Integer[]>();		// ���2, ������ ���赵�� ����
	static int[][] sal;
	static boolean[][] chk;
	
	public static int sol_rec(int indexing) {
		if(rec.size() <= 2) return rec.get(0)[1];
		System.out.println("���" +" "+rec.size());
		int n = rec.size();
		int m = sal.length;
		Integer[][] rec_save = new Integer[n][3];
		int[][] data = new int[m][3];
		boolean[] chs = new boolean[m];
		for(int i=0;i<n;i++) {
			Integer[] c = rec.remove(0);
			for(int j=0;j<3;j++) rec_save[i][j] = c[j];
			int min = min_sal(c[0]-1);			//  Leaf ��ġ������ ���� sal�� �������� ã�´�.
			data[c[0]-1][0] = min;						// ��ġ���� 0���� ����
			data[c[0]-1][1] = sal[c[0]-1][min];		//�ش� sal���� 1���� ����
			data[c[0]-1][2] = i;							//�ε��� ���� 2���� ����
			chs[c[0]-1] = true;						// ó��üũ�� ���� �Ҹ���
		}	// O(m^2) = 25000 ����
		
		for(int i=0;i<m;i++) {
			System.out.println("data :: " + Arrays.toString(data[i]));
			if(chs[i] && i == data[data[i][0]][0] && data[i][1] == data[data[i][0]][1]) {
				chs[i] = false;
				chs[data[i][0]] = false;
				chk[i][data[i][0]] = true;
				chk[data[i][0]][i] = true;
				rec.add(new Integer[] {Math.min(data[i][0]+1 , data[data[i][0]][0]+1) , indexing, data[i][1]});
				num_sal.add(data[i][1]);
				edge.add(new Integer[] {rec_save[data[i][2]][1], indexing});
				edge.add(new Integer[] {rec_save[data[data[i][0]][2]][1], indexing});
				indexing++;
			}
		} // O(m) = 500 ����
		
		for(int i=0;i<m;i++) {
			if(chs[i]) {
				rec.add(rec_save[data[i][2]]);
			}
		} // O(m) = 500 ����
		
		return sol_rec(indexing);
	}	// O(log m) * O(m^2) = 25000 * 8 ����
	
	public static int min_sal(int a) {
		int index = 0, minsal = 9999;
		for(int i=0;i<sal.length;i++) {
			if(!chk[a][i] && minsal > sal[a][i]) {
				index = i;
				minsal = sal[a][i];
			}
		}
		return index;
	}	// �Էµ� a��°�� ����� ������ ��Ī���� �ʾ�����, ���� sal�� �������� index�� �����Ѵ�. O(n) ����
}