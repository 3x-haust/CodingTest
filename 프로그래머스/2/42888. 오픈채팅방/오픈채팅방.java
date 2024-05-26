import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> idMap = new HashMap<>();
        int count = 0;
        
        for(int i = 0; i < record.length; i++){
            String[] info = record[i].split(" ");
            
            switch(info[0]) {
                case "Leave":
                    continue;
                case "Enter":
                    idMap.put(info[1], info[2]);
                    break;
                default:
                    idMap.put(info[1], info[2]);
                    count++;
                    break;
            }
        }
        
        String[] answer = new String[record.length - count];
        int idx = 0;
        
        for(int i = 0; i < record.length; i++){
            String[] info = record[i].split(" ");
            String nickname = idMap.get(info[1]);
            
            switch(info[0]) {
                case "Enter":
                    answer[idx++] = nickname + "님이 들어왔습니다.";
                    break;
                    
                case "Leave":
                    answer[idx++] = nickname + "님이 나갔습니다.";
                    break;

            }
        }
        
        return answer;
    }
}