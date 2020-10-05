import java.util.*;

public class pro_Level2_7 {
    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> truck = new LinkedList<Integer>();
        Queue<Integer> InBridge = new LinkedList<Integer>();
        for(int i=0;i<truck_weights.length;i++) truck.add(truck_weights[i]);
        int CurrWeight = 0, time = 0;
        while(!truck.isEmpty()){
            while(!truck.isEmpty() && CurrWeight + truck.peek() <= weight){
                InBridge.add(truck.peek());
                CurrWeight += truck.peek();
                truck.poll();
            }
            time += bridge_length;
            while(!InBridge.isEmpty() && !(!truck.isEmpty() && CurrWeight + truck.peek() <= weight)){
                System.out.println(CurrWeight +" "+InBridge.peek());
                CurrWeight -= InBridge.peek();
                InBridge.poll();
                if(!(!truck.isEmpty() && CurrWeight + truck.peek() <= weight)) time++;
            }
            System.out.println("The Time = "+time);
        }
        return time;
    }
    public static void main(String[] args){
        int[] a = {1,1,1,1,1,1};
        int weight = 5;
        int length = 10;
        // 110
        int weight2 = 99;
        // 202

        int[] b = {10};
        int weight3 = 10;
        int length2 = 2;
        System.out.println(solution(10,5,a));
        // 10 + 1 + 10 + 1 + 10 + 1 + 10 + 1 + 10 + 1
    }
    
}
