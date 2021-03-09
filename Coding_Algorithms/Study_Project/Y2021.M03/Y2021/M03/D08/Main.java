package Y2021.M03.D08;
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
			tok = new StringTokenizer(rd.readLine());
			int n = Integer.parseInt(tok.nextToken());
			int m = Integer.parseInt(tok.nextToken());
			tok = new StringTokenizer(rd.readLine());
			ArrayList<Integer> box = new ArrayList<>();
			ArrayList<Integer> spp = new ArrayList<>();
			int NegBoxPoint = -1;
			int NegSboxPoint = -1;
			
			for(int i=0;i<n;i++) {
				box.add(Integer.parseInt(tok.nextToken()));
				if(NegBoxPoint == -1 && box.get(i) > 0) NegBoxPoint = i;
			}
			
			
			if(NegBoxPoint == -1) NegBoxPoint = n;
			int[] posBox = new int[n-NegBoxPoint];
			int[] negBox = new int[NegBoxPoint];
			int[] POSspd = new int[n-NegBoxPoint];
			int[] NEGspd = new int[NegBoxPoint];
			
			for(int i=0;i<n;i++) {
				if(i<NegBoxPoint) negBox[NegBoxPoint-i-1] = -box.get(i);
				else posBox[i-NegBoxPoint] = box.get(i);
			}
			
			tok = new StringTokenizer(rd.readLine());
			for(int i=0;i<m;i++) {
				spp.add(Integer.parseInt(tok.nextToken()));
				if(NegSboxPoint == -1 && spp.get(i) > 0) NegSboxPoint = i;
			}
			if(NegSboxPoint == -1) NegSboxPoint = m;
			int[] posPos = new int[m-NegSboxPoint];
			int[] negPos = new int[NegSboxPoint];
			for(int i=0;i<m;i++) {
				if(i<NegSboxPoint) negPos[NegSboxPoint-i-1] = -spp.get(i);
				else posPos[i-NegSboxPoint] = spp.get(i);
			}
			solution_count(posBox,posPos,POSspd);
			solution_count(negBox,negPos,NEGspd);
			System.out.println(Arrays.toString(POSspd));
			System.out.println(Arrays.toString(NEGspd));
////			System.out.println(solution_s(negBox, negPos,neg,NEGspd));
////			System.out.println(pos+" "+neg);
//			System.out.println(Math.max(pos, solution_s(posBox, posPos,pos,POSspd,0))+
//					Math.max(neg, solution_s(negBox, negPos,neg,NEGspd,0)));
		}
	}
	public static int solution_s(int[] p, int[] t, int spt, boolean[] spd, int TargetPoint) {
		if(p.length<1 || t.length < 1) return 0;
		if(TargetPoint == t.length) return 0;
		else if(t[TargetPoint] < p[0]) return solution_s(p,t,spt,spd,TargetPoint+1);
		if(p[0] == t[t.length-1]) return 1;
		if(spd[0]) { spd[0] = false; spt--; }	// 첫값이 이미 체크된 값이라면 체크값을 빼줌
		
		int lastPoint = t[TargetPoint];
		int index = 1;
		while(index < p.length && lastPoint >= p[index]) {
			if(spd[index]) { spd[index]=false; spt--; }
			index++;
			lastPoint++;
		}
		int ts = Arrays.binarySearch(t, lastPoint);  
		int plsPoint = (ts<0?(-2-ts):ts) - TargetPoint + spt +1;
//		System.out.println(Arrays.toString(p) +" "+ (ts<0?(-2-ts):ts) +" "+TargetPoint+" "+spt +" "+plsPoint +" "+lastPoint);
		return Math.max(plsPoint, solution_s(p,t,spt,spd,TargetPoint+1));
	}
	
	public static void solution_count(int[] p, int[] t, int[] spd) {
		int i = p.length-1, j = t.length-1;
		spd[i] = p[i] == t[j] ? 1 : 0;		// 끝값이 같은지 확인
		i--;
		while(i>=0) {
			spd[i] = spd[i+1];		// dp적용을 통해 현재 값을 다음값만큼 정의
			if(j>=0 && p[i] == t[j]) {
				spd[i--]++;				// 수가 같다면 dp값 증가
				j--;
			} else if(j>=0 && p[i] < t[j]) j--;
			else i--;
		}
	}
}