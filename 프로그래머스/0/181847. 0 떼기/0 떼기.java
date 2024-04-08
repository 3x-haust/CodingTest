class Solution {
    public String solution(String n_str) {
        String answer = "";
        int zero = 0;
        
        for(int i = 0; i < n_str.length(); i++){
            if(n_str.charAt(i) != '0') {
                answer += n_str.charAt(i);
                break;
            }else zero ++;
        }
        
        for(int i = zero + 1; i < n_str.length(); i++){
            answer += n_str.charAt(i);
        }
        
        return answer;
    }
}