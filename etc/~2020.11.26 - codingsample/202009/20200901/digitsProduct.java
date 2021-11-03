package javaSpeedCoding20200901;

import java.util.Arrays;

public class digitsProduct {
	// �־��� ������ ������ ������ ǥ���� �� �ִ� �ּҰ��� ����ϴ� �Լ��� ����ÿ�.
	// �־������� 16�̸� 28, 82, 44 �� �ּڰ��� 28�� ��µǸ� ��.
	// ���ڸ����ڴ� �״�� ���.
	static int minlength(String s) {	//�Էµ� ������� �ּҷ� ���� �� �ִ� ũ�⸦ ��ȯ
		int num2 = 0, num3 = 0;
		int count = s.length();
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='2') num2++;
			else if(s.charAt(i)=='3') num3++;
			if(num2>=3) {
				num2 -= 3;
				count -= 2;
			}
		}
		count -= (num2+num3)/2;
		return count;
	}
	static int minArray(int[] a) {	//�迭 �������� ���� �� �ִ� �ּڰ��� ��ȯ
		int temp = 0;
		Arrays.sort(a);
		temp += a[0];
		for(int i=1;i<a.length;i++) {
			temp *= 10;
			temp += a[i];
		}
		return temp;
	}
	static int caseArray(int[] a, String s) {	// �������� �迭�� ���̽��� �м��Ͽ�, �ּڰ��� ��ȯ
		int num2 = 0, num3 = 0, aNum = 0;
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='2') num2++;
			else if(s.charAt(i)=='3') num3++;
			else a[aNum++] = (int)(s.charAt(i)-'0');
			if(num2>=3) {
				a[aNum++] = 8;
				num2 -= 3;
			}
		}
		if(num2==1) {
			if(num3%2==0) {
				a[aNum++] = 2;
				num2--;
				while(num3>0) {
						a[aNum++] = 9;
						num3 -= 2;
				}
			}
			else {
				a[aNum++] = 6;
				num2--;
				num3--;
				while(num3>0) {
					a[aNum++] = 9;
					num3 -= 2;
				}
			}
		}
		else if(num2==2) {
			if(num3%2==0) {
				a[aNum++] = 4;
				num2-=2;
				while(num3>0) {
					a[aNum++] = 9;
					num3 -= 2;
				}
			}
			else {
				a[aNum++] = 2;
				a[aNum++] = 6;
				num2-=2;
				num3--;
				while(num3>0) {
					a[aNum++] = 9;
					num3 -= 2;
				}
				
			}
			
		}
		else {
			if(num3%2==0) {
				while(num3>0) {
					a[aNum++] = 9;
					num3 -= 2;
				}
			}
			else {
				a[aNum++] = 3;
				num3--;
				while(num3>0) {
					a[aNum++] = 9;
					num3 -= 2;
				}
				
			}
			
		}
		return minArray(a);
	}
	static int minmeasure(int a) {	//�Էµ� ���� �ּҾ������ ��ȯ��. 10�̻��̸� -1�� ��ȯ
		for(int i=2;i<=9;i++) if(a%i==0) return i;
		return -1;
	}

	static int digitsProduct(int product) {
		if(product==0) return 10;
		else if(product==1) return 1;
		String output = "";
		int outputNum = 0;
		int temp;
		while(product>1) {
			temp = minmeasure(product);
			if(temp>9||temp==-1) return -1;
			output += (char)(temp+'0');
			product /= temp;
		}
		int[] print = new int[minlength(output)];
		return caseArray(print,output);
	}
	//���ڿ��� ����� �ϳ��� �����Ͽ� ���ʰ��� ���� ��, �ּҰ��� ����.
	//����� ���������� �ʴ� ���ڸ���(�Ҽ�)���� �ִٸ�, -1�� ��ȯ
	public static void main(String[] args) {
		int a = 4;
		System.out.println(digitsProduct(a));
	}

}
