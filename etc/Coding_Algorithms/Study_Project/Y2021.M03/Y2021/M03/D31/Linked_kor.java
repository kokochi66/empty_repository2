package Y2021.M03.D31;

public class Linked_kor {
	public static void main(String[] args) {
		LinkedList_kr list = new LinkedList_kr();
		for(int i=0;i<10;i++) {
			list.add(i);
		}
		System.out.println(list.get(10));
	}
	
	static class LinkedList_kr {
		Node root;
		Node tail;
		int size;
		static class Node {
			int value;
			Node next;
			public Node(int v) {
				this.value = v;
				this.next = null;
			}	//��� ���� �Լ�
			public void setNext(Node n) {
				this.next = n;
			}	// ���� ��尪�� �����ϴ� �Լ�
		}	// �Ѱ��� ��� Ŭ����
		
		public LinkedList_kr() {
			this.root = null;
			this.tail = null;
			this.size = 0;
		}	// LinkedList �����Լ�
		
		
		
		public void add(int n) {
			if(size == 0) addFirst(n);
			else {
				this.tail.next = new Node(n);
				this.tail = this.tail.next;
			}
		}
		
		
		private void addFirst(int n) {
			this.root = new Node(n);
			this.tail = this.root;
			size++;
		}	// ù��° ���� �߰��� ��
		
		public int get(int n) {
			Node node = root;
			for(int i=0;i<n;i++) {
				node = node.next;
			}
			return node.value;
		}
		
		public void remove(int n) {
			Node node = root;
			for(int i=0;i<n-1;i++) {
				node = node.next;
			}
			
			
		}

	}
}
