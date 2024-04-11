import java.util.HashMap;
 
class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];
        HashMap<String, Integer> keyCount = new HashMap<>();
        
        for (int i = 0; i < keymap.length; i++) {
            String[] str = keymap[i].split("");
            
            for (int j = 0; j < str.length; j++) {
                if (!keyCount.containsKey(str[j])) {
                    keyCount.put(str[j], j + 1);
                    continue;
                }
                
                if (keyCount.get(str[j]) > j + 1) {
                    keyCount.put(str[j], j + 1);
                }
            }
        }
        
        for (int i = 0; i < targets.length; i++) {
            String[] str = targets[i].split("");
            answer[i] = 0;
            for (int j = 0; j < str.length; j++) {
                if (!keyCount.containsKey(str[j])) {
                    answer[i] = -1;
                    break;
                } else {
                    answer[i] += keyCount.get(str[j]);   
                }
            }
        }
        
        return answer;
    }
}