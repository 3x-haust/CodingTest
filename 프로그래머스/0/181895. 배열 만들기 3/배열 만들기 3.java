import java.util.*;

class Solution {
    public int[] solution(int[] arr, int[][] intervals) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        for(int[] intv : intervals){
            for(int i = intv[0]; i <= intv[1]; i++){
                answer.add(arr[i]);
            }
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}