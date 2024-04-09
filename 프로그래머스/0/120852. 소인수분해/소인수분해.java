import java.util.*;

public class Solution {
    public List<Integer> solution(int n) {
        List<Integer> answer = new ArrayList<>();
        for (int i = 2; n > 1; i++) {
            while (n % i == 0) {
                if (!answer.contains(i)) {
                    answer.add(i);
                }
                n /= i;
            }
        }
        return answer;
    }
}