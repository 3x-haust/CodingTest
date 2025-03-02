import java.util.*;

class Solution {
    public int[] solution(long begin, long end) {
        int[] answer = new int[(int)(end - begin) + 1];
        
        for(long i = begin; i <= end; i++) answer[(int)(i - begin)] = (int) maxDivisor(i);
        
        return answer;
    }
    
    public static long maxDivisor(long num) {
        int result = 1;

        if(num == 1) return 0;

        for(int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                System.out.println(i);
                if (num / i <= 10000000) {
                    result = Math.max(result, (int) (num / i));
                    break;
                } else result = Math.max(result, i);
            }
        }
        
        return result;
    }
}