import java.util.*;

class Solution {
    public int[] solution(String my_string) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        String str = my_string.replaceAll("[^0-9]","");
        
        for(int i = 0; i < str.length(); i++){
            answer.add(Integer.parseInt(String.valueOf(str.charAt(i))));
        }
        
        int[] result = answer.stream().mapToInt(Integer::intValue).toArray();
        
        Arrays.sort(result);
        
        
        return result;
    }
}