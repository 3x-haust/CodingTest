import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        int n = arr.length;
        int index = 1;
        
        while (index < n) {
            index *= 2;
        }

        int[] answer = new int[index];
        Arrays.fill(answer, 0);
        
        System.arraycopy(arr, 0, answer, 0, n);
        
        return answer;
    }
}