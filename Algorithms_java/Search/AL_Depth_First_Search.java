import java.util.*;
public class AL_Depth_First_Search {
    public static void main(String[] args){
        int[][] a = {
            {0,1,0,0,1,0,0,0,1,0,0},
            {1,0,1,1,0,0,0,0,0,0,0},
            {0,1,0,0,0,0,0,0,0,0,0},
            {0,1,0,0,0,0,0,0,0,0,0},
            {1,0,0,0,0,1,0,0,0,0,0},
            {0,0,0,0,1,0,1,1,0,0,0},
            {0,0,0,0,0,1,0,0,0,0,0},
            {0,0,0,0,0,1,0,0,0,0,0},
            {1,0,0,0,0,0,0,0,0,1,1},
            {0,0,0,0,0,0,0,0,1,0,0},
            {0,0,0,0,0,0,0,0,1,0,0},
        };
        init(a);
        DFS(a,0);
    }

    static int n;
    static Stack<Integer> st;
    static boolean[] used;
    // 깊이우선탐색은 Stack을 이용해서 탐색을 이어나간다.
    // 재귀호출을 이용하는 방식이 나은 편이라서 전역변수 선언을 이용하는게 좋다.
    public static void init(int[][] arr) {
        n = arr.length;
        st= new Stack<>();
        used = new boolean[n];
    }
    public static void DFS(int[][] arr, int f) {
        used[f] = true;
        System.out.println(f);
        for(int i=0;i<n;i++){
            if(!used[i]&&arr[f][i]==1){
                DFS(arr,i);
            }
        }
        // 반복문을 돌면서 재귀호출을 통해 깊이를 탐색한다.
        // 시간복잡도 = Θ(n^2)
    }
}
