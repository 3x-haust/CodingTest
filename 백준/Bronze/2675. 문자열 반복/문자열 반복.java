import java.util.*;
import java.lang.StringBuilder;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        
        for (int i = 0; i < n; i++) {
            int r = sc.nextInt();
            String s = sc.next();
            
            StringBuilder sb = new StringBuilder();
            
            for (char c : s.toCharArray()) {
                sb.append(String.valueOf(c).repeat(r));
            }
            
            System.out.println(sb.toString());
        }
    }
}