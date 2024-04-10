import java.util.*;

class Solution {
    public String[] solution(String my_string) {
        String[] answer = my_string.split(" ");
        
        List<String> answerList = new ArrayList<>(Arrays.asList(answer));
        for(int i = 0; i < answer.length; i++){
            if(answer[i].isEmpty()) {
                answerList.remove(answer[i]);
            }
        }
        answer = answerList.toArray(String[]::new);
        
        return answer;
    }
}