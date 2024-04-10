import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        for(int i = 0; i < arr.length; i++){
            if(answer.isEmpty()) {
                answer.add(arr[i]);
            }else if(!answer.isEmpty() && answer.get(answer.size() - 1) == arr[i]){
                answer.remove(answer.size() - 1);
            }else if(!answer.isEmpty() && answer.get(answer.size() - 1) != arr[i])
                answer.add(arr[i]);
        }
        
        int[] result = answer.stream().mapToInt(Integer::intValue).toArray();
        
        return result.length == 0 ? new int[]{-1} : result;
    }
}