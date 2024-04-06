import java.util.Arrays;

class Solution {
    public int solution(int[] sides) {
        Arrays.sort(sides);
        
        int answer = sides[sides.length - 1] < sides[sides.length - 2] + sides[sides.length - 3] ? 1 : 
        sides[sides.length - 1] > sides[sides.length - 2] + sides[sides.length - 3] || sides[sides.length - 1] == sides[sides.length - 2] + sides[sides.length - 3] ? 2 : 0;
        
        return answer;
    }
}