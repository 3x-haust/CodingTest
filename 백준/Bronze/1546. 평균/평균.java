import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
                
        List<Double> scores = new ArrayList<>();
        
        for(int i = 0; i < n; i++) {
            scores.add(sc.nextDouble());
        }
        
        Collections.sort(scores);
        
        double max = scores.get(n - 1);
        
        List<Double> normalizedScores = new ArrayList<>();
        for (double score : scores) {
            normalizedScores.add(score / max * 100);
        }
        
        double average = normalizedScores.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        
        System.out.println(average);
        
    }
}