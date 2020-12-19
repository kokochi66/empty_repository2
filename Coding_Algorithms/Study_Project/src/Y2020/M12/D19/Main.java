package Y2020.M12.D19;
import java.util.*;
import java.io.*;
public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tok;
	public static void main(String[] args) throws Exception {
//		solution();
		System.out.println(ea(31,127));
	}
	public static void solution() throws Exception {
		tok = new StringTokenizer(rd.readLine());
		int n = Integer.parseInt(tok.nextToken());
		int m = Integer.parseInt(tok.nextToken());
		long[] arr = new long[n+5];
		long[] brr = new long[m+5];
		boolean allOdd = true;
		tok = new StringTokenizer(rd.readLine());
		for(int i=0;i<n;i++) {
			arr[i] = Long.parseLong(tok.nextToken());
			if(arr[i]%2!=0) allOdd = false;
		}
		Arrays.sort(arr);
		tok = new StringTokenizer(rd.readLine());
		for(int i=0;i<m;i++) {
			brr[i] = Long.parseLong(tok.nextToken());
		}
		
		long[] sub = new long[n];
		for(int i=1;i<n;i++) {
			sub[i-1] = arr[i] - arr[i-1];
		}
		long gcd = n>2 ? ea(Math.max(sub[0],sub[1]),
				Math.min(sub[0],sub[1])) : sub[0];
		for(int j=2;j<n;j++) {
			gcd = ea(Math.max(sub[j],gcd),Math.min(sub[j],gcd));
		}
		
		for(int i=0;i<m;i++) {
			wr.write((gcd+brr[i])+" ");
		}
		wr.flush();
//		System.out.println(Arrays.toString(arr));
//		System.out.println(Arrays.toString(brr));
	}
	
	public static long ea(long x, long y) {
		if (y==0) return x;
		else return ea(y, x%y);
	}
//	public static void solution() throws Exception {
//		int n = Integer.parseInt(rd.readLine());
//		int[] dp = new int[n+5];
//		dp[0] = 1;
//		dp[1] = 4;
//		for(int i=2;i<=n;i++) {
//			if(i%2==0) {
//				int c = ((i/2)+1);
//				dp[i] = c*c;
//			} else {
//				int c = (((i-1)/2)+1);
//				dp[i] = c*(c+1)*2;
//			}
//		}
//		System.out.println(dp[n]);
////		System.out.println(Arrays.toString(dp));
//	}
//	public static void solution() throws Exception {
//		int TestCase = Integer.parseInt(rd.readLine());
//		for(int TT=0;TT<TestCase;TT++) {
//			int n = Integer.parseInt(rd.readLine());
//			char[] red = rd.readLine().toCharArray();
//			char[] blue = rd.readLine().toCharArray();
//			int[] redc = new int[10];
//			int[] bluec = new int[10];
//			int rw = 0;
//			int bw = 0;
//			for(int i=n-1;i>=0;i--) {
//				if(red[i] > blue[i]) rw++;
//				else if(red[i] < blue[i]) bw++;
//			}
//			if(rw > bw) System.out.println("RED");
//			else if ( rw < bw ) System.out.println("BLUE");
//			else System.out.println("EQUAL");
////			for(int i=0;i<n;i++) {
////				redc[red[i]-'0']++;
////				bluec[blue[i]-'0']++;
////			}
////			for(int i=9;i>=0;i--) {
////				if(redc[i] > bluec[i]) {
////					System.out.println("RED");
////					break;
////				}
////				else if(redc[i] < bluec[i]) {
////					System.out.println("BLUE");
////					break;
////				}
////				if(i==0) System.out.println("EQUAL");
////			}
////			System.out.println(Arrays.toString(redc));
////			System.out.println(Arrays.toString(bluec));
////			System.out.println();
//		}
//	}
}