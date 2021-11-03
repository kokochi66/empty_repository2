package Smooth_Sailing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.IntStream;

public class func {
	String[] allLongestStrings(String[] inputArray) {
		String output="";
		int max=0;
		for(String s : inputArray) {
			if(max<s.length()) {
				max=s.length();
				output=s+"-";
			}
			else if(max==s.length()) output+=s+"-";
		}
		return output.split("-");
	}
//	���� ���
	String[] allLongestStrings2(String[] inputArray) {
	    return Arrays.stream(inputArray)
	        .filter(i->i.length()==IntStream.range(0,inputArray.length).map(j->inputArray[j].length())
	        .max().getAsInt()).toArray(String[]::new);}
//	��Ʈ���� �̿��� ������1
	String[] allLongestStrings3(String arr[]) {
	    int max_len = Arrays.stream(arr).max(Comparator.comparingInt(String::length)).get().length();
	    return Arrays.stream(arr).filter(x -> x.length() == max_len).toArray(String[]::new);
	}
// �񱳱�(Comparator)�� �̿��� ������2
// �� ������ Stream.java���� ����	
	
	int commonCharacterCount(String s1, String s2) {
	    int[] a = new int[26], 
	            b = new int[26];
	        for (char c: s1.toCharArray())
	            a[c - 'a']++;
	        for (char c: s2.toCharArray())
	            b[c - 'a']++;
	        int s = 0;
	        for (int i = 0; i < 26; ++i) 
	            s +=Math.min(a[i],b[i]);
	        return s;
	}
//c-'a'��� ������ ���� ���ĺ��� ������ �����ϴ� �迭�� ���� ���ĺ� ���밳���� Ȯ���ϴ� �Լ��� ����
	int commonCharacterCount2(String s1, String s2) {
	    int counter = 0;
	    for(int i = 0; i < s1.length(); i++){
	        if(s2.contains(String.valueOf(s1.charAt(i)))){
	            counter++;
	            s2 = s2.replaceFirst(String.valueOf(s1.charAt(i)), "");
	        }
	    }
	    return counter;
	}
//�Ϲ����� �Լ��� ����� ���.
//String���� Ư�� String�� �����ִ��� �����ϴ� contains
//char�� String���� ��ȯ�ϴ� String.valueOf���� contain�� �������
//String���� ���ϴ� �ش� ���Ұ��� �ι��� ���ڰ����� ��ü�ϴ� replaceFirst
	int commonCharacterCount3(String s1, String s2) {
	    Object[] a = new HashSet<String>(Arrays.asList(s1.split(""))).toArray();
	    return IntStream.range(0,a.length).map(i->Math.min(
	           (int)Arrays.stream(s1.split("")).filter(x->x.equals(a[i].toString())).count(),
	           (int)Arrays.stream(s2.split("")).filter(x->x.equals(a[i].toString())).count())).sum();}
//�����غ�������, ������ �̿��ϴ� Hashset�� s1��Ҹ� �־�ΰ�,
//IntStream-> range �ݺ��� map, Math.min�� ���� ���տ� �ִ� ���� ��ġ�ϴ� ������ ����(count)�� �� sum�� ���ϴ� ���� �̷���ִ�.
//���̽㿡�� �̿��� ������ �ڹ� Hashset�� �̿��� ������.
	boolean isLucky(int n) {
		String a = String.valueOf(n);
		return IntStream.range((a.length()/2),a.length()).map(i->Integer.parseInt(String.valueOf(a.charAt(i)))).sum() == IntStream.range(0,(a.length())/2).map(i->Integer.parseInt(String.valueOf(a.charAt(i)))).sum();
	}
	int[] sortByHeight(int[] a) {
		int[] out = new int[a.length];
		int outNum=0;
		for(int s: a) {
			if(s!=-1) {
				out[outNum]=s;
				outNum++;
			}
		}
		Arrays.parallelSort(out);
		outNum=out.length-outNum;
		for(int i =0;i<a.length;i++) {
			if(a[i]!=-1) {
				a[i]=out[outNum];
				outNum++;
			}
		}
		return a;
	}


}
