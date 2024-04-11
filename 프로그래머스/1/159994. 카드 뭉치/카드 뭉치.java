class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "Yes";
 
        int idxCards1 = 0;
        int idxCards2 = 0;
 
        for (int i = 0; i < goal.length; i++) {
            if(idxCards1 < cards1.length && goal[i].equals(cards1[idxCards1])) {
                idxCards1++;
                continue;
            }
            
            if(idxCards2 < cards2.length && goal[i].equals(cards2[idxCards2])) {
                idxCards2++;
                continue;
            }
 
            answer = "No"; 
            return answer;
        }
 
        return answer;
    }
}