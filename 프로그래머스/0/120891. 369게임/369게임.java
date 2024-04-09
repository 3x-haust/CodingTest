class Solution {
    public int solution(int order) {
        int answer = 0;
        for(int i = 0; i < String.valueOf(order).length(); i++){
            if(String.valueOf(order).charAt(i) == '3' || 
               String.valueOf(order).charAt(i) == '6' || 
               String.valueOf(order).charAt(i) == '9'){
                answer++;
            }
        }
        return answer;
    }
}