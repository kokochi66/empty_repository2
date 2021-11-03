package Smooth_Sailing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class Stream {
	public static void main(String[] args) {
		String[] inputArray = {"abg","aa","ad","vcd","aba","goaz","hfddfg"};
		
		String[] output = Arrays.stream(inputArray)
		        .filter(i->i.length()==IntStream.range(0,inputArray.length).map(j->inputArray[j].length())
		        .max().getAsInt()).toArray(String[]::new);
	
		for(int i=0;i<output.length;i++) System.out.print(output[i]+" ");
		System.out.println();
		
		int max_len = Arrays.stream(inputArray).max(Comparator.comparingInt(String::length)).get().length();
		output = Arrays.stream(inputArray).filter(x -> x.length() == max_len).toArray(String[]::new);
		for(int i=0;i<output.length;i++) System.out.print(output[i]+" ");
		System.out.println();
		
		String s =Arrays.stream(inputArray).max(Comparator.comparingInt(String::length)).get();
		System.out.println(s);
		
		output = Arrays.stream(inputArray).sorted(Comparator.comparingInt(String::length)).toArray(String[]::new);
		for(int i=0;i<output.length;i++) System.out.print(output[i]+" ");
		System.out.println();
		
//��Ʈ���� �⺻ ����
//���Ҹ� �����ϴ� ��� - map, filter, sorted
//map : ��ҵ��� Ư�� ���ǿ� �ش��ϴ� ������ ��ȯ
//filter : ��ҵ��� ���ǿ� ���� �ɷ����� �۾��� ��
//sorted : ��ҵ��� ������
//������ ���� ���� collect�� ���� ���ϰ��� ������
//���� output�� �ΰ��� ��� filter�� ���� �������.
//
//1�� ������ ���� ��. 1������������ filter�ȿ� map�� ���ִ� ������
//ù��° ����
//Arrays.stream(inputArray).filter
//:: inputArray��� �迭����
//
//i->i.length()==IntStream.range(0,inputArray.length)
//:: IntStream.range�� ����Ͽ�, 0���� inputArray.length���� �ݺ��Ѵ�.
//:: ���⼭���ʹ� IntStream�� �������̴�.
//
//.map(j->inputArray[j].length()).max().getAsInt()
//:: inputArray�� ������ max, �� �ִ밪�� ������ ���� getAsInt�� ���� ���������� ����.
//:: ��, ���� ���ǰ� ���Ͽ� inputArray�� �迭 ������ ���ڿ��� ���̰� �迭 ��ü�� �ݺ��ؼ�, max���� ���� ���� ==�� ��ġ�ϴ� i���ڿ��� ������
//
//toArray(String[]::new)
//:: ���������� �ش簪�� toArray(String[]::new) �� ����
//:: ���ο� ���ڿ� �迭�ν� ��ȯ��.
//:: �̸� ���� ���ڿ��迭 ������ ���� ���̰� �� ���ڿ��� ���� ��ȯ��.
//		
//2�� ����
//2�������� ���� max���� IntStream���� �� �����ȿ� �������� �ʰ�, int������ ����� �ش簪�� ���� �������.
//Arrays.stream(inputArray)
//:: inputArray��� �迭����
//
//.max(Comparator.comparingInt(String::length))
//:: Compartor�� comparingInt�� ����Ͽ�, �ش� �迭���� String�� length�� ���Ͽ� max���� �ش��ϴ� ���ڿ��� ��ȯ��.
//
//.get().length()
//:: �׸��� �ش簪�� get���� ��ȯ�ϰ�, �� ���ڿ��� length�� ������ int max�� ��������.
//
//�� �������� �迭������ ���� filter�� �̿��ؼ�
//.filter(x -> x.length() == max_len)
//�� �ش� max_len�� �ش��ϴ� ���ڿ����� toArray(String[]::new)�� ���� ���ڿ� �迭�ν� ��ȯ��.


	}

}
