class Solution {
    public String solution(String myString) {
        String answer = "";
        for(int i = 0; i < myString.length(); i++){
            answer += String.valueOf(myString.charAt(i)).toLowerCase();
        }
        return answer;
    }
}