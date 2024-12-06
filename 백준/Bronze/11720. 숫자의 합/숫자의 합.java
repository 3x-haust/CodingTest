import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int answer = 0;
        
        String[] s = sc.next().split("");
        
        for (int i = 0; i < s.length; i++) {
            answer += Integer.valueOf(s[i]);
        }
        
        System.out.println(answer);
    }
}