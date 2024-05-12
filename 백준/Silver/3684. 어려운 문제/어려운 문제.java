import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        int[] a = new int[T];

        for(int i = 0; i < T; i++) a[i] = sc.nextInt();
        
        boolean f = false;
        int r1, r2;
        r1 = r2 = 0;

        loop:
        for(int i = 0; i < 10000; i++) {
            for(int j = 0; j < 10000; j++) {
                int cnt = 0;
                for(int k = 0; k < T - 1; k++) {
                    int n = (i * a[k] + j) % 10001;
                    if (a[k + 1] == (i * n + j) % 10001) cnt++;
                    else break;
                }
                
                if (cnt == T - 1) {
                    f = true;
                    r1 = i;
                    r2 = j;
                    break loop;
                }
            }
        }

        for(int i = 0; i < T; i++) {
            System.out.println((r1 * a[i] + r2) % 10001);
        }
        
        sc.close();
    }
}