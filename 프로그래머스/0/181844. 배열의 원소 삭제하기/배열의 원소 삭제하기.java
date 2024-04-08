import java.util.*;

class Solution {
    public int[] solution(int[] arr, int[] delete_list) {
        List<Integer> answer = new ArrayList<>();
        for (int num : arr) {
            answer.add(num);
        }
        
        List<Integer> list = new ArrayList<>();
        for(int num : delete_list){
            list.add(num);
        }
        
         answer.removeAll(list);
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}