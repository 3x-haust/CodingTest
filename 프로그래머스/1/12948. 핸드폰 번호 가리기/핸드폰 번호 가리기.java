class Solution {
    public String solution(String phone_number) {
        String answer = "";
        
        // 11 
        //     8
        
        for(int i = 0; i < phone_number.length(); i++){
            if(i < phone_number.length() - 4){
                answer += "*";
            }else {
                answer += String.valueOf(phone_number.charAt(i));
            }
        }
        
        return answer;
    }
}