import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        
        int min = 1000001;
        int max = -1000001;
        
        for(int i = 0; i < n; i++) {
            int val = sc.nextInt();
            if(val < min) min = val;
            if(val > max) max = val;
        }
        
        System.out.println(min + " " + max);      
    }
}