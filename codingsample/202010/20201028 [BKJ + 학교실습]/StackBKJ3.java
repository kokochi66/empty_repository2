
import java.io.*;
import java.util.*;

public class StackBKJ3 {
    // 1. https://www.acmicpc.net/problem/2493 탑
    public static void main(String[] args) throws Exception{
        Solution();
    }
    
    public static void Solution() throws Exception{
        BufferedReader Read = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter Write = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = Read.readLine();
        while(!input.equals("0")){
            String[] tok = input.split(" ");
            int[] arr = new int[tok.length];
            for(int i=0;i<tok.length;i++) arr[i] = Integer.parseInt(tok[i]);
            long output = StackBKJ3_2_func(arr,0,0);
            Write.write(output+"");
            Write.newLine();
            input = Read.readLine();
        }
        
        Write.flush();
        Write.close();
        Read.close();
    }
    public static long StackBKJ3_2_func(int[] arr, int min, int swit){
        Stack<Integer[]> MinStack = new Stack<Integer[]>();
        int length = 1;
        long maxSum = 0;
        for(int i=swit;i<arr.length;i++){
            if(MinStack.isEmpty()) MinStack.push(new Integer[]{arr[i],i});
            else if(MinStack.peek()[0] <= arr[i]) {
                MinStack.push(new Integer[]{arr[i],i});
            } else if(MinStack.peek()[0] > arr[i]) {
                while(!MinStack.isEmpty() && MinStack.peek()[0] > arr[i]){
                    Integer[] data = MinStack.pop();
                    if(maxSum < data[0]*(i-data[1])) maxSum = (long)data[0]*(long)(i-data[1]);
                }
                MinStack.push(new Integer[]{arr[i],i});
                length = 1;
            }
            length++;
        }
        while(!MinStack.isEmpty()){
            Integer[] data = MinStack.pop();
            if(maxSum < data[0]*(arr.length-data[1])) maxSum = (long)data[0]*(long)(arr.length-data[1]);
        }

        return maxSum;
    }

    public static void StackBKJ3_1() throws Exception{
        BufferedReader Read = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter Write = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Integer[]> MinStack = new Stack<Integer[]>();

        // 입력되는 값들을 각각 MinStack에 저장하며 자신보다 큰 탑이 나타날때까지 0으로 유지한다.
        // 스택에는 2개짜리 배열이 저장되며, 0에는 탑의 높이, 1에는 탑의 위치값이 저장된다.
        String input = Read.readLine();
        int n = Integer.parseInt(input);
        int[] arr = new int[n];
        input = Read.readLine();
        String[] tok = input.split(" ");
        // n개의 값을 입력받는다.

        for(int i=n-1;i>=0;i--){
            //끝자리에서부터 반복
            
            int Curr = Integer.parseInt(tok[i]);
            while(!MinStack.isEmpty() && MinStack.peek()[0]<=Curr) {
                //탑의 신호를 받을 탑이 나온경우
                arr[MinStack.pop()[1]] = i+1;
            } 
            MinStack.push(new Integer[]{Curr,i});
        }
        for(int i=0;i<n;i++) Write.write(arr[i]+" ");
        Write.flush();
        Write.close();
        Read.close();
    }
}
