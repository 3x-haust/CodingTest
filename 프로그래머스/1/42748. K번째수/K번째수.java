import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        int cnt = 0;
        for(int[] command : commands){
            int start = command[0] - 1;
            int end = command[1] - 1;
            
            
            int[] c = new int[end - start + 1];
            int index = 0;
            for(int i = start; i <= end; i++){
                c[index++] = array[i];
            }
            
            Arrays.sort(c);
            
            answer[cnt++] = c[command[2] - 1];
        }
        
        return answer;
    }
}