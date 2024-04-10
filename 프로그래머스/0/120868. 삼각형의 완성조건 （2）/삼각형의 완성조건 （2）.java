import java.util.Arrays;

class Solution {
    public int solution(int[] sides) {
        int answer = 0;
        
        Arrays.sort(sides);
        int a = sides[1];
        int b = sides[0];
        
        int low = a - b;
        int high = a + b; 
        
        answer = high - low - 1;
        
        return answer;
    }
}