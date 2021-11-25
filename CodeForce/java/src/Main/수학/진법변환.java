package src.Main.수학;

public class 진법변환 {

    public static int 진법변환3(int n) {
        int oper = 1;
        int res = 0;
        while(n > 0) {
            res += (n%3) * oper;
            n/=3;
            oper *= 10;
        }

        return res;
    }
}
