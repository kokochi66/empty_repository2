package Smooth_Sailing;

import java.util.Arrays;
import java.util.HashSet;

public class hashset {
	public static void main(String[] args) {
		//���� set�� ������ ���� HashSet
		HashSet<Integer> set1 = new HashSet<Integer>();//HashSet����
		HashSet<Integer> set2 = new HashSet<>();//new���� Ÿ�� �Ķ���� ��������
		HashSet<Integer> set3 = new HashSet<Integer>(set1);//set1�� ��� ���� ���� HashSet����
		HashSet<Integer> set4 = new HashSet<Integer>(10);//�ʱ� �뷮(capacity)����
		HashSet<Integer> set5 = new HashSet<Integer>(10, 0.7f);//�ʱ� capacity,load factor����
		HashSet<Integer> set6 = new HashSet<Integer>(Arrays.asList(1,2,3));//�ʱⰪ ����
		
		set1.add(1);
		System.out.println(set1.contains(1));
		System.out.println(set6.contains(4));
	}

}
