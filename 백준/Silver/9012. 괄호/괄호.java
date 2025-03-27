import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            String str = sc.nextLine();
            System.out.println(isVPS(str) ? "YES" : "NO");
        }
    }
    
    public static boolean isVPS(String str) {
        int balance = 0;
        for (char c : str.toCharArray()) {
            if (c == '(') balance++;
            else {
                if (balance == 0) return false;
                balance--;
            };
        }
        return balance == 0;
    }
}