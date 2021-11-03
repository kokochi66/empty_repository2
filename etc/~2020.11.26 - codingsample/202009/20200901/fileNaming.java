package javaSpeedCoding20200901;

import java.util.Arrays;

public class fileNaming {
	// �����̸��� ���� ���� �����ִ����� Ȯ���ϴ� boolean �Լ�
	static int checkEqualNone(String[] n) {
		for(int i=0;i<n.length-1;i++) {
			for(int j=i+1;j<n.length;j++) {
				if(n[i].equals(n[j])) return i;
			}
		}
		return -1;
	}
	// �����̸��� ���� ���� ������ �� ������, �� �������� ����ؼ� ��ȯ��.
	static String StringCount(String[] n, String comp, int count, int swit) {
		String temp = comp + '(' + Integer.toString(count) + ')';
		for(int i=0;i<swit;i++) {
			if(temp.equals(n[i])) return StringCount(n, comp, count+1, swit);
		}
		return temp;
	}
	// ���� �̸��� ���� ������ ������, (1), (2)�� ��ȣ�� �������� �Űܳ����� �Լ�.
	static String[] fileNaming(String[] names) {
		int count = 0, temp = checkEqualNone(names);
		while(temp!=-1) {
			count = 1;
			for(int i=temp+1;i<names.length;i++) {
				if(names[temp].equals(names[i])) {
					names[i] = StringCount(names,names[i],count, i);
				}
			}
			temp = checkEqualNone(names);
		}
		return names;
	}

	public static void main(String[] args) {
		String[] input = {"a", 
				 "b", 
				 "cd", 
				 "b ", 
				 "a(3)"};
		input = fileNaming(input);
		System.out.println(Arrays.toString(input));

	}

}
