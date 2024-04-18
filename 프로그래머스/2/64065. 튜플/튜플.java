import java.util.*;

class Solution {
    public int[] solution(String s) {
        Set<String> set = new HashSet<>();
        String[] arr = s.replace("{", " ").replace("}", " ").trim().split(" , ");
        Arrays.sort(arr, (a, b) -> {return a.length() - b.length();});
        
        int[] answer = new int[arr.length];
        
        int cnt = 0;
        for(String s1 : arr) {
            for(String s2 : s1.split(",")) {
                if(set.add(s2)) answer[cnt++] = Integer.parseInt(s2);
            }
        }
        
        return answer;
    }
}