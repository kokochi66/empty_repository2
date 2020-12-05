package Algorithms.Graph;

public class AL_Tree_test {
	
	static class Node {
		int data;
		Node left;
		Node right;
		public Node(int a) {
			this.data = a;
			this.left = null;
			this.right = null;
		}
		public Node(int a, Node l, Node r) {
			this.data = a;
			this.left = l;
			this.right = r;
		}
	}

}
