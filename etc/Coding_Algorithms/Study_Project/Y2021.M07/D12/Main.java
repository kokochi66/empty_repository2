package D12;
import java.util.*;
import java.io.*;
public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tok;
	public static void main(String[] args) throws Exception {
		System.out.println(solution(3,	3, new String[] { "DBA", 
														  "C*A", 
														  "CDB" }	)); // ABCD
		
//		System.out.println(solution(2,	4, new String[] { "NRYN", 
//														  "ARYA"}	)); // RYAN
//		
//		System.out.println(solution(4,	4, new String[] { ".ZI.", 
//														  "M.**", 
//														  "MZU.", 
//														  ".IU."})); // MUZI
//		
//		System.out.println(solution(2,	2, new String[] { "AB", 
//														  "BA"})); // IMPOSSIBLE
	}
	
    public static String solution(int m, int n, String[] board) {
    	// �Է°����� .�� ��ĭ, *�� ��ֹ�(����ĭ), ���ĺ� �빮�ڴ� �� Ÿ���� ��Ÿ����, ���� Ÿ�ϳ��� �������־���Ѵ�. ���⼭ Ÿ���� ���� �Է��� �־����� �ʴ´�.
    	// ��¿���, �ذ� �����ϴ� ���(��� Ÿ���� ������ �� �ִ� ���), Ÿ���� �����ϴ� ������� �� ���ھ� �̷���� ���ڿ���, �ذ� �������� �� ���,���ĺ� ������ ���� ������ ���ڿ��� �����Ѵ�.
    	// �ذ� �������� ������ IMPOSSIBLE�� �����Ѵ�.
    	// ������ ũ�� M * N �� ���� 100 �����̴� (n^3 ����)
    	
    	// 1. �� ���忡��, ���ĺ� ������ ������ ��ġ �� ���� �����ϰ� �ִ� �迭�� �����Ͽ� �����Ѵ�.
    	// 2. ���⼭ �� �� ���� �迭�� ���鼭 ó������ ������, �ʺ�켱 Ž��(BFS)�� �̿��Ͽ�, �ش� ���ڰ� �ٸ� ���ڿ� ��ġ�� ������ ���������� üũ�Ѵ�.
    	// 3. ���� ��ġ�� �����ϴٸ�, ��ġ ���¸� üũ�ϸ�, �ش� ���� �� ��ġ�� �� ����(.)���� �����ϰ�, �ٽ� �迭�� ó������ ���ư� ó������ �ݺ��Ѵ�.
    	// 4. ��ġ ���� üũ�� check boolean �迭�� �̿��Ͽ� �̹� üũ�� ���� �ٽ� üũ���� �ʵ��� ���ش�.
    	
    	cboard = new char[m][n];
    	tm = m;
    	tn = n;
    	// ������Ǹ� ���� ��������ȭ
    	for(int i=0;i<m;i++) cboard[i] = board[i].toCharArray();	// ����ϱ� �����ϰ� char �迭�� ��������
    	
    	
    	//1. �迭����(�� ���ڴ� �ϳ��� üũ�ؾ� �ϹǷ�, �̸� �����ϰ� �����ϱ� ���ؼ� HashSet�� �̿��Ѵ�. n^2 ������ ���.)
    	HashSet<Character> set = new HashSet<>();
    	ArrayList<boardPOS> list = new ArrayList<boardPOS>();	// ���� ���°� �����Ƿ�, �迭�� �ƴ� ����Ʈ�� �����Ѵ�.
    	for(int i=0;i<m;i++) {
    		for(int j=0;j<n;j++) {
    			if(cboard[i][j] != '.' && cboard[i][j] != '*' && !set.contains(cboard[i][j])) {
    				set.add(cboard[i][j]);
    				list.add(new boardPOS(cboard[i][j], i, j));
    			}
    		}
    	}
    	Collections.sort(list); // �־��� ���� �������ش�.
//    	for(int i=0;i<list.size();i++) System.out.println(list.get(i).toString());
    	
    	
    	boolean[] check = new boolean[list.size()];
    	int checkCnt = 0;
    	String answer = "";
    	
    	// 2. ���� ���鼭, �ʺ�켱Ž���� �����Ѵ�.
    	for(int k=0;k<list.size();k++) {
        	for(int i=0;i<list.size();i++) {
        		if(check[i]) continue;
//        		System.out.println(list.get(i).tile);
        		if(boardBFS(list.get(i).h, list.get(i).w)) {
//        			System.out.println(list.get(i).tile);
        			cboard[list.get(i).h][list.get(i).w] = '.';
        			check[i] = true;
        			checkCnt++;
        			answer += list.get(i).tile;
//        			for(int q=0;q<m;q++) System.out.println(Arrays.toString(cboard[q]));
        			break;
        			// 3. ��ġ�Ǿ��ٸ�, �ش� ���� '.'���� ���� ��, ����Ʈ�� ó������ �ٽ� �ݺ��Ѵ�.
        		}
        	}
    	}
    	if(checkCnt < list.size()) return "IMPOSSIBLE";
        return answer;
    }
    static char[][] cboard;
    static int tm, tn;
    
    public static boolean boardBFS(int ch, int cw) {
    	// �ʺ�켱 Ž���� �����ϸ鼭, Ÿ�ϰ��� �Ÿ����� ������ �� �� �̻� ���̸� �ȵǸ�, �߰��� �ٸ� Ÿ���̳� ��ֹ��� �־�� �ȵȴ�.
    	// ���� ���Ⱚ�� �־��ְ�, ������ ��� Ʋ���������� ���� ī��Ʈ���� BFS�� ���ԵǾ�� �Ѵ�.
    	// �� ���⿡�� ��ġ�� �κ��� ������ ������� �޶����Ƿ�, ���⸶�� ������ ������ֱ� ���ؼ� �ݺ����� ������.
    	// ��� ���Ǹ� ���ؼ� �ݺ����� �°� �迭�� �����Ѵ�.
    	
    	int[][] op = { {1,0}, {-1,0}, {0,1}, {0,-1} };
    	char target = cboard[ch][cw];
    	
    	for(int i=0;i<4;i++) {
        	Queue<Integer[]> q = new LinkedList<>();
        	boolean[][] check = new boolean[tm][tn];
        	check[ch][cw] = true;
        	
        	// �ʱ� �̵����� ť�� �־��ش�. ť�� ���� ���� ����, ch, cw, cd(�������), dcnt(����ī��Ʈ) �̴�.
        	// ������ 0(down), 1(up), 2(right), 3(left) �̴�.
        	if(findTarget(ch + op[i][0], cw + op[i][1], i, 0, target, q, check)) return true;
        	
        	while(!q.isEmpty()) {
        		Integer[] c = q.poll();
//        		System.out.println(Arrays.toString(c));
        		
        		for(int j=0;j<4;j++) {
        			if(findTarget(c[0] + op[j][0], c[1] + op[j][1], j, c[2] == j ? c[3] : c[3]+1, target, q, check)) return true;
        		}
        	}
    	}

    	
    	
    	return false;
    }
    public static boolean findTarget(int ch, int cw, int cd, int dcnt, char target, Queue<Integer[]> q, boolean[][] check) {
    	if(ch >= tm || ch < 0 || cw >= tn || cw < 0 || dcnt >= 2 || check[ch][cw]) return false;
    	if(cboard[ch][cw] == target) {
    		cboard[ch][cw] = '.';
    		return true;
    	}
    	else if(cboard[ch][cw] == '.') {
    		q.add(new Integer[] {ch, cw, cd, dcnt}); 
    		check[ch][cw] = true;
    	}
    	
    	return false;
    }
    
    static class boardPOS implements Comparable<boardPOS> {
    	char tile;
    	int h, w;
    	public boardPOS(char t, int h, int w) {
    		this.tile = t;
    		this.h = h;
    		this.w = w;
    	}
		@Override
		public int compareTo(boardPOS o) {
			return this.tile < o.tile ? -1 : 1;
		}
		@Override
		public String toString() {
			return "boardPOS [tile=" + tile + ", h=" + h + ", w"
					+ "=" + w + "]";
		}
		
    }
	
    public static int solution3(int n, int[][] computers) {
    	// �� ��ǻ�Ͱ� ������谡 �����ϸ� ���� ����Ǿ��ִٰ� �� �� �ְ�, ������ ��ȯ�� �� �ִ�. = ���� ��Ʈ��ũ�� �ִٰ� �� �� �ִ�
    	// ������踦 Ȯ���Ͽ� �� �и��� ��Ʈ��ũ�� � �����ϴ����� Ȯ���Ѵ�.
    	// ��ǻ�� ������ n �� 200������ (3���� ����)
    	// �ڱ� �ڽŰ��� �׻� ����Ǿ��ִٰ� ǥ���Ѵ�.
    	
    	int[] parent = new int[n];	// �� ��ǻ���� ��Ʈ���� üũ
    	for(int i=0;i<n;i++) parent[i] = i; 	// �⺻ ��Ʈ���� �ڱ� �ڽ����� ����
    	
    	for(int i=0;i<n;i++) {
    		for(int j=i+1;j<n;j++) {
    			if(computers[i][j] == 1) {
    				unip(parent, i, j);
    			}
    		}
    	} // n^2
    	
    	
    	// �� �θ��� �������־, ��Ե� ��� ��Ʈ�迭�� Ȯ���Ͽ�, ��Ʈ���� ���� �ٸ� ���� ������ ���� ��Ʈ��ũ�� ������ �ȴ�.
    	// �����ϰ� ����ϱ� ���ؼ� HashSet�� �̿��Ѵ�.
    	HashSet<Integer> set = new HashSet<Integer>();
    	for(int i=0;i<n;i++) {
    		if(!set.contains(getp(parent, i))) {
    			set.add(getp(parent, i));
    		}
    	}
    	
    	System.out.println(Arrays.toString(parent));
    	
        return set.size();
    }
    public static int getp(int[] p, int x){
        if(p[x] == x) return x;
        return p[x] = getp(p,p[x]);
    }
    public static void unip(int[] p, int a, int b){
        a = getp(p, a);
        b = getp(p, b);
        if(a<b) p[b] = a;
        else p[a] = b;
    }
	
    public static int solution2(int[][] jobs) {
    	// �ϵ��ũ�� �ѹ��� �ϳ��� �۾��� ���� ���� -> ���� �Ϲ����� ����� ��û�� ���� ������� ó��
    	// �۾��� ��û���� ������� �ɸ� �ð��� ����� ���� ���̴� ������� ó���ϸ� ����� �󸶰� �Ǵ����� �����ؾ��Ѵ�.
    	// ���� ��û ���� ���̴� ����� ã�ƾ��� -> ��� ��� ���? -> �ð�������
    	// jobs�� ���̴� 1 �̻� 500 ���� (���� ����)
    	// �۾��� �����ϴ� �ܰ�� �۾� �����ϴ� �ܰ谡 ������ ���������.
    	// �۾��� �����ϴ� �ܰ迡��, ���� ������� �۾� �߿��� �۾� �ð��� ���� ª�� ���� ���� �����ؾ���.
    	// ���� ª������ �����ϱ� ���ؼ� �켱����ť�� �̿���.
    	
    	int n = jobs.length, answer = 0;
    	Arrays.sort(jobs, (a,b) -> a[0] > b[0] ? 1 : -1); // �ԷµǴ� �迭�� ��û�Ǵ� �ð� ������� �����Ѵ�. log N
    	
    	int CurrTime = 0, CurrIndex = 0;
    	PriorityQueue<Job> pq = new PriorityQueue<Job>();
    	// ���⼭ �켱���� ť���� �۾��� �� �ð��� �־������ν�, �۾��� �ɸ��ð� ����� ����� �� �ֵ��� �ؾ���.
    	
    	
    	while(CurrIndex < n || !pq.isEmpty()) {
    		if(CurrIndex < n) {
    			// ���� �����ִ� �۾��� �ִٸ�, ���� �ð��� üũ�Ͽ�, �۾��� ť �ȿ� �־������� �ľ���.
    			while(CurrIndex < n && jobs[CurrIndex][0] <= CurrTime) {
    				pq.add(new Job(jobs[CurrIndex][0], jobs[CurrIndex][1]));
    				CurrIndex++;
    			}	// ���� �ð� ���Ķ��, ť�� �۾��� �־��ش�.
    			if(pq.isEmpty()) {
    				// ���� ���� �۾��� �� �����ִµ� ť�� �۾��� ���� �ʴ´ٸ�, �ð��� ���� �۾��� �ð���ŭ �帣�� ���ش�.
    				CurrTime = jobs[CurrIndex][0];
    			}
    		}
    		
    		if(!pq.isEmpty()) {
    			// ť�� ���� �۾��� �ϳ��� ó���Ѵ�. �켱���� ť���� ���� ������� �۾� �� ���� �۾� �ð��� ª������ ������ �����Ѵ�.
    			Job j = pq.poll();
//    			System.out.println(j.toString());
    			CurrTime += j.time;
    			answer += CurrTime - j.start;
    		}
    	}

        return answer/n;
    } 
    static class Job implements Comparable<Job> {
    	int start;
    	int time;
    	
    	public Job(int s, int t) {
    		this.start = s;
    		this.time = t;
    	}

		@Override
		public int compareTo(Job o) {
			// TODO Auto-generated method stub
			return this.time < o.time ? -1 : 1;
		}

		@Override
		public String toString() {
			return "Job [start=" + start + ", time=" + time + "]";
		}
		
    }
    
}