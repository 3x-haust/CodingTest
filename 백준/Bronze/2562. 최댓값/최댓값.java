import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[]{sc.nextInt(), sc.nextInt(), sc.nextInt(), 
                             sc.nextInt(), sc.nextInt(), sc.nextInt(), 
                             sc.nextInt(), sc.nextInt(), sc.nextInt() };
        int max = 0;
        int index = 0;
        
        for(int i = 0; i < 9; i++) {
            int n = arr[i];
           if(n > max) {
		        max = n;
		        index = (i + 1);
	        }
        }
        
        System.out.println(max);
        System.out.println(index);
    }
}