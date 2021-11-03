package D14;
import java.util.*;
import java.io.*;
public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tok;
	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(rd.readLine());
		List<String> list = new ArrayList<String>();
		for(int i=0;i<n;i++) {
			list.add(rd.readLine());
		}
		String encoded = rd.readLine();
		System.out.println(decode(list, encoded));
		
//		StringBuffer str = new StringBuffer("abc");
//		str.append("\n");
//		str.append("abcd");
//		System.out.println(str);
	}
	
    public static String decode(List<String> codes, String encoded) {
    	// ������ �ڵ�� ���� �󵵼��� ���ڸ� ª�� �����ϴ� ������ �Ѵ�.
    	// ������ �ڵ��� ���̴� ���� �ٸ� �� ������, �ձ��ڰ� ��ġ�ϴ� ���� �������� �ʴ´�. �� 01�� a��� ������������ b�� 011�� �� �� ����.
    	// ������ �ڵ� ���� �迭�� ���ڵ��� ������ ���ڿ��� �־����� ��, �ش� ���ڿ��� �ص����� ã�ƶ�.
    	// �� ���� ������ �и��Ǿ��ִ�. ���ο� ������ [newline]���� ǥ�õǾ��ִ�.
    	
    	// ���ڿ� ��ü�� ����Ʈ�� ����, ����Ž���� ���ؼ� ã���� �Ѵ�.
    	// ���ڿ��� �񱳿� �ð��� ���̵�� ������ StringBuffer�� ������ش�.
    	int n = codes.size();
    	ArrayList<StringBuffer> list = new ArrayList<>();
    	HashMap<StringBuffer, String> map = new HashMap<StringBuffer, String>();
    	for(int i=0;i<n;i++) {
    		tok = new StringTokenizer(codes.get(i), "\t");
    		String s = tok.nextToken();
    		StringBuffer code = new StringBuffer(tok.nextToken());
    		list.add(code);
    		map.put(code, s);
    	}
    	
    	Collections.sort(list, (a,b) -> compareBuff(a,b)); // ����Ž���� ���ؼ� ����Ʈ�� �������ش�.
    	
    	char[] encode = encoded.toCharArray();
    	StringBuffer c = new StringBuffer("");
    	StringBuffer res = new StringBuffer("");
    	for(int i=0;i<encode.length;i++) {
    		c.append(encode[i]);
//    		System.out.println("c = " +c +" bin ="+bin);
    		int search = Collections.binarySearch(list, c, (a,b) -> compareBuff(a,b));
    		
    		if(search < 0) continue;	// ����Ž���� ���� �������� ������ �������� �Ѿ��.
    		else {
    			c = new StringBuffer("");
    			String op = map.get(list.get(search));
//    			System.out.println("decode :: " + op);
    			if(op.equals("[newline]")) res.append("\n");
    			else res.append(op);
    			
    		}
    	}
    	
    	
    	return res.toString();
    }
    
    public static int compareBuff(StringBuffer a, StringBuffer b) {
    	if(a.length() != b.length()) return a.length() < b.length() ? -1 : 1;
    	for(int i=0;i<a.length();i++) {
    		if(a.charAt(i) != b.charAt(i)) return a.charAt(i) < b.charAt(i) ? -1 : 1;
    	}
    	return 0;
    }
	
    public static int maxEvents(List<Integer> arrival, List<Integer> duration) {
    	// ȸ���� �����ð��� ����ð��� �־����� ��, �۾��� ����Ǵ� ���������̼��� �ִ�ġ�� ����϶�
    	
    	// n = 1000
    	// 1. �۾��� �þ����, �ٸ� �۾��� �ð��밡 ��ġ�� �ʴ� �۾��� ���� �߰���Ų��. (n)
    	// 2. ��ġ�� �۾� ��, ��ġ�� ������ ���� �۾��� ������� ���ܽ�Ű��, ���ܽ�Ű�� ���, �۾��� ���� ������ش�.
    	// 3. ��ġ�� �۾��� ���������� �ݺ��ϰ�, ��ġ�� �۾��� �������� �ش� �۾� ������ ����Ѵ�.
    	
    	int cntJob = 0;
    	ArrayList<Job> jobs = new ArrayList<>();
    	for(int i=0;i<arrival.size();i++) {
    		jobs.add(new Job(arrival.get(i), duration.get(i)));
    	} // ���ǻ� Ŭ���� ����Ʈ�� ��ȯ
    	
    	int n = jobs.size();
    	for(int i=0;i<n;i++) {
    		for(int j=0;j<n;j++) {
    			if(i != j) {
    				jobs.get(i).calconf(jobs.get(j));
    			}
    		}
    	}
    	
    	while(!notConf(jobs)) {	// ��ġ�� �۾��� 1���� �ִٸ� �ݺ��Ѵ�. = 50
    		
    		removeMost(jobs);	// ���� ���� ��ġ�� �۾��� �������ش�.
    		n = jobs.size();
        	for(int i=0;i<n;i++) {
        		jobs.get(i).resetConf();
        		for(int j=0;j<n;j++) {
        			if(i != j) {
        				jobs.get(i).calconf(jobs.get(j));
        			}
        		}
        	}
    		
//        	for(int i=0;i<jobs.size();i++) System.out.println(jobs.get(i).toString());
//        	System.out.println("====");
    	}
    	
    	return jobs.size();
    }
    
    static class Job {
    	int arr, dur, end, conf;
    	
    	public Job(int a,int d) {
    		this.arr = a;
    		this.dur = d;
    		this.end = a+d;
    		this.conf = 0;
    	}
    	
    	public void resetConf() {
    		this.conf = 0;
    	}
    	
    	public void calconf(Job j) {
    		if(isconf(j)) this.conf++;
    	}
    	
    	public boolean isconf(Job j) {
    		if(this.arr <= j.arr && this.end > j.arr || this.arr < j.end && this.end > j.end) {
//    			System.out.println("conf == "+ this.toString() +" "+j.toString());
    			return true;
    		}
    		return false;
    	}

		@Override
		public String toString() {
			return "Job [arr=" + arr + ", dur=" + dur + ", end=" + end + ", conf=" + conf + "]";
		}
    	
    }

    public static boolean notConf(ArrayList<Job> jobs) {
    	for(int i=0;i<jobs.size();i++) {
    		if(jobs.get(i).conf >= 1) return false;
    	}
    	return true;
    }
    
    public static void removeMost(ArrayList<Job> jobs) {
    	int maxconf = 0, maxidx = 0, maxdur = 0;
    	for(int i=0;i<jobs.size();i++) {
    		if(maxconf < jobs.get(i).conf) {
    			maxconf = jobs.get(i).conf;
    			maxidx = i;
    		} 
    	}
    	jobs.remove(maxidx);
    }
	
    public static String reachTheEnd(List<String> grid, int maxTime) {
    	// 2���� �迭�� ����, ����� ���� #, ������� �������� .�� ǥ����
    	// ������ġ�� ���� �� ������. ������ġ�� ������� ������ �����Ѵ�. ���� ���� ������ �Ʒ��� ������� ������ �����Ѵ�.
    	// �� �ʸ��� ������ ��(�����¿�)�� ������ �� �ִ�. k�ʾȿ� ������ �Ʒ��� ������ �� �ִٸ� Yes, �ƴԳ� No�� ����϶�.
    	
    	// �ִܰŸ��� ã�¹���, BFS�� �̿��Ͽ� Ǯ ����.
    	int n = grid.size(), m = grid.get(0).length();
    	char[][] gridr = new char[n][m];
    	for(int i=0;i<n;i++) {
    		gridr[i] = grid.get(i).toCharArray();
    	}	// ���ǻ� char[]�� ��ȯ����.
    	if(n-1 == 0 && m-1 == 0) return "Yes";
    	
    	Queue<Integer[]> q = new LinkedList<>();
    	q.add(new Integer[] {0,0,0});	// �ʱ���ġ ť�� �Է�
    	boolean[][] check = new boolean[n][m];	// �̹� �̵��� ���� �ٽ� ���� �ʰ� üũ.
    	check[0][0] = true;
    	int[][] op = { {1,0}, {-1,0}, {0,1}, {0,-1} };
    	
    	while(!q.isEmpty()) {
    		Integer[] c = q.poll();
    		if(c[2] > maxTime) return "No";
    		for(int i=0;i<4;i++) {
    			if(move3(c[0] + op[i][0], c[1] + op[i][1], c[2], n, m, gridr, check, q)) return "Yes";
    		}
    	}
    	
    	
    	return "No";
    }
    
    public static boolean move3(int ch, int cw, int cnt, int n, int m, char[][] grid, boolean[][] check, Queue<Integer[]> q) {
    	if(ch < 0 || ch >= n || cw < 0 || cw >= m || grid[ch][cw] == '#' || check[ch][cw]) return false;
    	else if(ch == n-1 && cw == m-1) return true;
    	q.add(new Integer[] {ch,cw,cnt+1});
    	check[ch][cw] = true;
    	return false;
    }
	
    public static int getMinDeletions(String s) {
    	char[] str = s.toCharArray(); // ���ǻ� char[]�� ��ȯ
    	Arrays.sort(str);
    	int cnt = 1;
    	for(int i=1;i<str.length;i++) {
    		if(str[i] != str[i-1]) cnt++;
    	}	// �� ���ĺ��� ������ ���ش�.
    	
    	return str.length - cnt;	// �� ������ ���� - ���ĺ��� ���� ��ŭ �������־�� �Ѵ�.
    }
	
    static int maxTickets(List<Integer> tickets) {
    	Collections.sort(tickets);
    	int maxCnt = 1, cnt = 1;
    	for(int i=1;i<tickets.size();i++) {
    		if(tickets.get(i) - tickets.get(i-1) <= 1) {
    			cnt++;
    		} else {
    			if(maxCnt < cnt) maxCnt = cnt;
    			cnt = 1;
    		}
    	}
    	return maxCnt;
    }
    
    
}