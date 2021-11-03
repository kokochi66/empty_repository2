package Algorithms.Graph;

import java.util.*;
public class AL_Depth_First_Search {
	public static boolean DFS_recursive_array(int[][] edge, boolean[] used,int ci, int target) {
		if(ci == target) return true;
		//2���� �迭�� �־��� �׷����� ���̿켱Ž���� ��ͷ�
		//a���� b���� ���� ��ΰ� �����ϴ��� Ž��.
		int n = edge.length;
		for(int i=0;i<n;i++) {
			if(edge[ci][i] == 1 && !used[i]) {
				used[i] = true;
				if(DFS_recursive_array(edge,used,i,target)) return true;
				used[i] = false;
			}
		}
		return false;
	}
	public static boolean DFS_stack_array(int[][] edge, boolean[] used,int ci, int target) {
		//2���� �迭�� �־��� �׷����� ���̿켱Ž���� ��������
		//a���� b���� ���� ��ΰ� �����ϴ��� Ž��.
		int n = edge.length;
		Stack<Integer> st = new Stack<Integer>();
		st.push(ci);
		used[ci] = true;
		while(!st.isEmpty()) {
			int c = st.pop();
			if(c == target) return true;
			for(int i=0;i<n;i++) if(!used[i] && edge[c][i] == 1) {
				used[i] = true;
				st.push(i);
			}
		}
		return false;
	}
	
	
	
	static int[][] arr = {
			{0,1,1,1,0},
			{1,0,1,0,0},
			{1,1,0,0,1},
			{0,1,0,0,1},
			{0,1,0,0,0}
	};
	static boolean[] used;
	public static void main(String[] args) {
		used = new boolean[arr.length];
//		System.out.println(DFS_recursive_array(arr,used,0,4));
		System.out.println(DFS_stack_array(arr,used,0,4));
	}
}
