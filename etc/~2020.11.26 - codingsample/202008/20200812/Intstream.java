package Edge_of_the_Ocean;

import java.util.stream.IntStream;

public class Intstream {
	public static void main(String[] args) {
		int[] a = {1,2,3,4,5};
		//Intstream�� �Ϻ� ������ ���� ����
		System.out.println(IntStream.range(1,5).sum());
		// range->sum a~b-1���� �ݺ�/ sum= ��
		System.out.println(IntStream.rangeClosed(1, 5).sum());
		// rangeClosed->sum a~b���� �ݺ�
		System.out.println(IntStream.range(1,5).map(i->a[i]*a[i-1]).max().getAsInt());
		System.out.println(IntStream.range(1,5).map(i->a[i]*a[i-1]).min().getAsInt());
		// map i->[] range�������� map���� �����Ǵ� ���� �ϳ��� ����
		// max -> �� ���� �� �ִ��� ����, min-> ���� �� �ּڰ��� ����
		// getAsInt �ش� ���� ������ �޾ƿ�.
		
	}

}
