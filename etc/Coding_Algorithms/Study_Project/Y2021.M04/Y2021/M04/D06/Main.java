package Y2021.M04.D06;
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
		int n = Integer.parseInt(rd.readLine());
		int[][] arr = new int[n][n];
		int[] pos  = new int[2];
		for(int i=0;i<n;i++) {
			tok = new StringTokenizer(rd.readLine());
			for(int j=0;j<n;j++) {
				arr[i][j] = Integer.parseInt(tok.nextToken());
				if(arr[i][j] == 9) {
					pos[0] = i;
					pos[1] = j;
				}
			}
		}
		
		
		
	}
	
	public static int[] sol2_BFS(int[][] arr, int y, int x, int shrk, int dist) {
		if(arr[y][x] == 0 && (y == 0 || y == arr.length-1) && (x==0 || x==arr.length-1)) return new int[] {y,x,-1};
		if(arr[y][x] > 0 && arr[y][x] < shrk) return new int[] {y,x,dist};
		int res = -1;
		int[][] temp  = new int[4][3];
		for(int i=0;i<4;i++) Arrays.fill(temp[i], -1);
		int n = arr.length;
		if(y+1 < n) temp[0] = sol2_BFS(arr,y+1,x,shrk,dist+1);
		if(y-1 >= 0) temp[1] = sol2_BFS(arr,y-1,x,shrk,dist+1);
		if(x+1 < n) temp[2] = sol2_BFS(arr,y,x+1,shrk,dist+1);
		if(x-1 >= 0) temp[3] = sol2_BFS(arr,y,x-1,shrk,dist+1);
		Arrays.sort(temp, (int[] a, int[] b) -> {
			return a[2] < b[2] ? -1 : (a[2] == b[2] ? (a[0] < b[0] ? -1 : (a[0] == b[0] && a[1] < b[1] ? -1 : 1)) : 1);
		});
		for(int i=0;i<4;i++) System.out.println(Arrays.toString(temp[i]));
		return temp[0];
	}
	
	public static void solution() throws Exception {
//		arr = new int[]{9,1,2,3,4,10,5,6,7,8};
//		int n = arr.length;
		
		int n = Integer.parseInt(rd.readLine());
		
		int res = sol_rec(1,n);
		System.out.println("! "+res);
	}
	public static int sol_rec(int left, int right) throws Exception {
		
		int Mid = sol_query(left,right);
		int fMid = -1, fLeft = left, fRight = Mid-1;
		// ù��°�� �߰����� ã�´�.
		
		if(Mid > left) fMid = sol_query(left,Mid);
		// ã�� �߰����� �� ���ʰ��� �ƴ϶��, ���ʰ��߿� �ִ��� �ִ��� Ž���Ѵ�.
		while(fMid == Mid) {
			// �ִ��� �ִ°�� ���ʰ� ���η� Ž���Ѵ�.
			int gLeft = fLeft;
			fLeft = (fLeft + fRight)/2;
			// ����Ž���� ���Ͽ� ���ذ��� �������� ������.
			fMid = sol_query(fLeft, Mid);
			// �ش� ���ݰ��� �ִ��� �����ϴ��� Ȯ���Ѵ�.
			if(fMid != Mid) {
				// ���ݰ��� ���ٸ� ���ݰ� �ٱ��ʿ� �ִ� ���̹Ƿ� �ٱ����� ���� ����üũ�� ���� ���� �����Ѵ�.
				fRight = fLeft-1;
				fLeft = gLeft;
				fMid = Mid;
			}
			if(fRight == fLeft) return fLeft;
			else if (fRight - fLeft <= 1) return sol_res2(fLeft,fRight);
			// ���� �پ�� Ư���� �� �ְԵǸ� ����Ѵ�.
		}
		fLeft = Mid+1;
		fRight = right;
		while(true) {
			int gRight = fRight;
			fRight = (fLeft+fRight)/2;
			fMid = sol_query(Mid, fRight);
			if(fMid != Mid) {
				fLeft = fRight+1;
				fRight = gRight;
				fMid = Mid;
			}
			if(fRight == fLeft) return fLeft;
			else if(fRight - fLeft <= 1) return sol_res2(fLeft,fRight);
		}
	}
	public static int sol_query(int left, int right) throws Exception {
		System.out.println("? "+left +" "+right);
		System.out.flush();
		return Integer.parseInt(rd.readLine());
		
//		System.out.println(left+" "+right);
//		int[] copy = new int[right-left+1];
//		int copc = 0;
//		for(int i=left-1;i<right;i++) copy[copc++] = arr[i];
//		Arrays.sort(copy);
//		int sec = copy[copy.length-2];
//		for(int i=left-1;i<right;i++) {
//			if( sec == arr[i]) return i+1;
//		}
//		return -1;
	}
	public static int sol_res2(int left, int right) throws Exception {
		int q = sol_query(left,right);
		return q == left ? right : left;
	}
	static int[] arr;
	
}