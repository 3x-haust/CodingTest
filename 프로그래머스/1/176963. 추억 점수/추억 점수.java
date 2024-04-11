import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];
        HashMap<String, Integer> nameMap = new HashMap<>();
        
        for(int i = 0; i < name.length; i++){
            nameMap.put(name[i], yearning[i]);
        }

        for(int i = 0; i < photo.length; i++){
            for(int j = 0; j < photo[i].length; j++){
                if(nameMap.containsKey(photo[i][j])){
                    answer[i] = answer[i] + nameMap.get(photo[i][j]);
                }
            }
        }

        return answer;
    }
}