import java.util.*;

class Solution {
    public long solution(long n) {
        String answer = "";
        
        String[] str = String.valueOf(n).split("");
        
        Arrays.sort(str);
        
        int index = 0;
        for (int i = str.length - 1; i >= 0; i--) {
            answer += str[i];
        }

        
        
        
        return Long.parseLong(answer);
    }
}