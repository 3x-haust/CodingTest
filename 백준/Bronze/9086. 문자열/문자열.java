import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        
        for (int i = 0; i < n; i++) {
            String s = sc.next();
            
            System.out.println(String.valueOf(s.charAt(0)) + String.valueOf(s.charAt(s.length() - 1)));
        }
    }
}