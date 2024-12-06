import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        
        List<Integer> answer = IntStream.rangeClosed(1, N)
                                         .boxed()
                                         .collect(Collectors.toList());
        
        for (int k = 0; k < M; k++) {
            int i = sc.nextInt();
            int j = sc.nextInt();
            
            Collections.reverse(answer.subList(i - 1, j));
        }
        
        answer.forEach(i -> System.out.print(i + " "));
        sc.close();
    }
}
