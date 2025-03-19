import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        String str = sc.next();
        String str1 = sc.next();
        
        String revStr = new StringBuilder(str).reverse().toString();
        String revStr1 = new StringBuilder(str1).reverse().toString();
        
        int num = Integer.parseInt(revStr);
        int num1 = Integer.parseInt(revStr1);
        
        System.out.print(num > num1 ? revStr : revStr1);
        
        sc.close();
    }
}
