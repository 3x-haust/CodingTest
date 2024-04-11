import java.util.*;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        for(int i = 0; i < arr.length; i++){
            if(arr[i] % divisor == 0) answer.add(arr[i]);
        }
        
        int[] a = answer.stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(a);
        
        return a.length == 0 ? new int[]{-1} : a;
    }
}