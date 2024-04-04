class Solution {
    public String solution(String code) {
        String answer = "";
        boolean mode = false;
        
        for(int i = 0; i < code.length(); i++){
            if(mode){
                if(String.valueOf(code.charAt(i)).equals("1")){
                    mode = false;
                }else {
                    if(i % 2 == 1){
                        answer += code.charAt(i);
                    }
                }
            }else {
                if(String.valueOf(code.charAt(i)).equals("1")){
                    mode = true;
                }else {
                    if(i % 2 == 0){
                        answer += code.charAt(i);
                    }
                }
            }
        }
        
        return answer.isEmpty() ? "EMPTY" : answer;
    }
}