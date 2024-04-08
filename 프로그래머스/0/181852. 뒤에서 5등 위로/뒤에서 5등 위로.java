import java.util.Arrays;

class Solution {
    public int[] solution(int[] num_list) {
        int[] answer = num_list;
        Arrays.sort(answer);
        
        answer = Arrays.copyOfRange(answer, 5, num_list.length);
        
        return answer;
    }
}