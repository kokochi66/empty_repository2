package N6_Rains_of_Reason;

public class zzCheck_matches {

	public static void main(String[] args) {
		String str = "abcd";
		System.out.println(str.matches("(a|b|c)*"));
		//(| |)* �� �ش� �ȿ� �ִ� ����θ� ��Ʈ���� �̷�����ִ°��� Ȯ��
		str = "abcgccc";
		System.out.println(str.matches("[a|b]+"));
		//[||]+ �� ��ǻ� ������
		System.out.println(str.matches("[a|b|c|abcg]*"));
		// �ƴ� ��� ���� �ٸ��� �� �𸣰ڴ�
		str = "0_abcAZgccc";
		System.out.println(str.matches("[a-z0-9][a-zA-Z_]*"));
		// [][]* ���ȣ �ΰ��� ���ΰ�� ù��° ���ȣ�� ù��°����, �ι�°���� �������� �ش�
	}

}
