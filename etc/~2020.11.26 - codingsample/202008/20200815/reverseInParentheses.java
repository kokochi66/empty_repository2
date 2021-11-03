package Smooth_Sailing;

public class reverseInParentheses {
	static String reverseInParentheses(String inputString) {
	    StringBuilder str = new StringBuilder(inputString);
	    int start, end;
	    while(str.indexOf("(") != -1){
	        start = str.lastIndexOf("(");
	        end = str.indexOf(")", start);
	        str.replace(start, end + 1, new StringBuilder(str.substring(start+1, end)).reverse().toString());
	    }
	    return str.toString();
	}
	//���ο� ��� -> StringBuilder
	public static void main(String[] args) {
		String input = "(abc)d(efg)";
		StringBuilder str = new StringBuilder(input);
		int a = str.lastIndexOf("(");
		System.out.println(str.lastIndexOf("("));
		// '('���� �� ���� �������� ������ ��ġ�� ��µǴ� lastIndexof �Լ�
		System.out.println(str.indexOf(")",a));
		// '('���� �� ���� ���ʿ� ������ ��ġ�� ��µǴ� lastIndexof �Լ�
		// �ι�°���� �ָ鼭 �ش� ��ġ���� �˻��� ������
		// ���ڿ� ������Ű�� replace�� ���� �ش�κ��� ������Ű�� �Լ�����.
	}

}
