package D13;
import java.util.*;
import java.io.*;
public class Main {
	static BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer tok;
	public static void main(String[] args) throws Exception {
		
		System.out.println("res = " + solution("aAbAbAbAa"));
		
//		System.out.println(solution("gHaEaLaLaOgbWORLDb"));
//		System.out.println(solution("SpIpGpOpNpGaJqOqAa"));
//		System.out.println(solution("AxAxAxAoBoBoB"));
	}
	
	static boolean[] checkArr;
	static ArrayList<String> words;
	public static String solution(String sentence) {
		// ���α׷��ӽ� ����̾��� ��� ����
		// �Ű���� ���� ����������� ��ڰ� �Ǵ��Ͽ� ����, 
		// 1. Ư�� �ܾ �����Ͽ� ���� ���̸��� ���� ��ȣ�� ����
		// 2. Ư�� �ܾ �����Ͽ� �ܾ� �յڿ� ���� ��ȣ�� ����
		// �ΰ��� ��Ģ�� �� �ܾ ��� ����� �� ������, ���� ��Ģ�� �ι� ������� ����.
		// �ѹ� ���� �ҹ���(Ư����ȣ)�� �ٽ� ������ ����. -> Ư����ȣ�� �ҹ��ڷθ� ǥ�õǸ�, �Ϲ� ���ڴ� �빮��, Ư����ȣ�� �ҹ��ڷ� ����Ѵ�.
		// ������ ���ؼ�, ��Ģ�� ������� ���� �ܾ ���� ���� �ִ�.
		
		
		// �ҹ����� ������ 3�� �̻��̰ų� 1����� ������ ù��° �������� ó���ȴ�. ���⼭ ù��° �������� ó���� �Ұ����ϴٸ� invalid�� ����Ѵ�.
		// ������ �ΰ���� �ι�°�� �켱ó���Ѵ�. ���⼭ �켱ó�� ������ ù��° ������ ó���� �κа� ������ ��ġ�ų�, ������ ��ġ�� �ʾƾ��Ѵ�. (���ļ� ��ġ�� invalid�� ����Ѵ�.)
		// ���������� �ι�° ó���� �ȵ� ������ �ΰ�¥���� ù��° �������� ó���Ѵ�. ���⼭ �̹� ù��° �������� ó���Ǿ��ų�, ��ġ�� ��ġ�ų�, ù��° �ʰ� ó���� �Ұ����ϸ� invalid�� ����Ѵ�.
		StringBuffer str = new StringBuffer(sentence);
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for(int i=0;i<str.length();i++) {
			if(isLower(str.charAt(i))) {
				if(map.containsKey(str.charAt(i))) {
					map.replace(str.charAt(i), map.get(str.charAt(i)) + 1);
				}
				else map.put(str.charAt(i), 1);
			}
		} // map���� ���� ��������.
		
		words = new ArrayList<>();
		checkArr = new boolean[str.length()];
		// ��µǴ� �ܾ ���� ����Ʈ�� üũ�迭
		
		Iterator<Character> iter = map.keySet().iterator();
		while(iter.hasNext()) {
			char key = iter.next();
			int cnt = map.get(key);
			if(cnt >=3 || cnt == 1) {
				// ù��° ���� ó��
				map.remove(key);
				int finalIdx = check_one(str, key, cnt);
				System.out.println("finalIdx = " + finalIdx+" "+str+" "+str.substring(finalIdx-(cnt*2), finalIdx+1));
				if(finalIdx == -1 || hasLower(str.substring(finalIdx-(cnt*2), finalIdx+1), key)) return "invalid";
				// ù��° ������ ó���ϸ鼭 ����, ù��° ������ ó���Ͽ��µ� �� �յڿ� �ҹ��ڰ� ���� �� �̿ܿ� �ٸ� �ҹ��ڰ� ���� �����Ѵٸ�, invalid�� ����ؾ��Ѵ�.
				// Ȥ�� �Լ����� �ùٸ��� ���� ó������� �����ϸ� finalIdx�� -1�� �ȴ�. �� ��쿡�� invalid�� ����Ѵ�.
				
				if(finalIdx-(cnt*2)-1 >= 0 && finalIdx+1 < str.length() && str.charAt(finalIdx-(cnt*2)-1) == str.charAt(finalIdx+1) 
						&& isLower(str.charAt(finalIdx+1)) && map.get(str.charAt(finalIdx+1)) == 2) {
					map.remove(str.charAt(finalIdx+1));
					Arrays.fill(checkArr, finalIdx-(cnt*2)-1, finalIdx+2, true);
				}
				else Arrays.fill(checkArr, finalIdx-(cnt*2), finalIdx+1, true);
				// �� �հ� �� �ڿ� �ҹ��ڰ� �پ�������, �ش� �ҹ����� ������ �ΰ����, �ι�° �������� ó�����ش�.
				// �� ���̳� �� �ڿ� �ҹ��ڰ� �ϳ��� ���� ��쿡�� �׳� �����Ѵ�.
				
				addWord(str.substring(finalIdx-(cnt*2), finalIdx+1));
			}
		}
		
		iter = map.keySet().iterator();
		while(iter.hasNext()) {
			char key = iter.next();
			int cnt = map.get(key);
			// �ι�° ������ �켱ó���Ѵ�.
			// ����� ���⼭ ù��° ó�� ���ǿ� �ѹ� �ɷ��� ������, ù��° ó���� ��ġ�� �ι�° ������ �Ϻΰ� �����ִ� ���� �����Ƿ� ������ �ʿ䰡 ����.
			// �ι�° ������ ó���� ��, �ι�° ������ ó���� ����� �Ǿհ� �ڿ� ���������� �ΰ�¥�� �ҹ��ڰ� �����ִٸ� �ش� ������ �ι�° �������� �����Ͽ� ó�����ָ� �ȴ�.
			// �̸� ���� ����°�� ������ �������� �ʰ� ó���� �����ϴ�.
			
			// ���������� ���� �������� ��, �ι�° ���� ó���� �ƿ� �Ұ����� ���(�ҹ��� �� ���� ���� �پ��ְų�, ���̻� ó���� �� �ִ� �ܾ �������� �������) invalid�� ����Ѵ�.
			
		}
		
		for(int i=0;i<words.size();i++) {
			System.out.println(words.get(i));
		}
		
		System.out.println(Arrays.toString(checkArr));
		
	    return str.toString();
    }
	
	public static boolean isLower(char c) {
		if(c >= 'a' && c <= 'z') return true;
		return false;
	}
	public static boolean hasLower(String str, char c) {
		for(int i=0;i<str.length();i++) {
			if(isLower(str.charAt(i)) && str.charAt(i) != c) return true;
		}
		return false;
	}
	
	public static int check_one(StringBuffer str, char c, int count) {
		int startidx = str.indexOf(Character.toString(c));
		int finalidx = startidx;
		if(finalidx == 0) return -1; 
		
		for(int i=0;i<count;i++) {
			if(finalidx >= str.length()-1 || str.charAt(finalidx) != c) return -1;
			else finalidx += 2;
		}
		finalidx--;
		if(!check_arr(startidx, finalidx)) return -1;
		
		return finalidx;
	}
	
	public static int check_two(StringBuffer str, char c) {
		int finalidx = str.indexOf(Character.toString(c));
		str.deleteCharAt(finalidx);
		finalidx = str.indexOf(Character.toString(c));
		str.deleteCharAt(finalidx);
		
		return finalidx-1;
	}
	
	public static void addWord(String strs) {
		StringBuffer str = new StringBuffer(strs);
		for(int i=0;i<str.length();i++) {
			if(isLower(str.charAt(i))) {
				str.deleteCharAt(i);
				i--;
			}
		}
		words.add(str.toString());
	}
	
	public static boolean check_arr(int start, int end) {
		for(int i=start;i<end;i++) {
			if(checkArr[i]) return false;
		}
		return true;
	}

	
}