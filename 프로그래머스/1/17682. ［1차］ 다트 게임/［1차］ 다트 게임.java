class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        int[] dart = new int[3];

        int n = 0, index = 0;
        String numstr = "";
        
        for(int i = 0; i < dartResult.length(); i++){
            char c = dartResult.charAt(i);
            
            //숫자
            if(c >= '0' && c <= '9'){       
                 numstr += String.valueOf(c);
            }
            
            //보너스
            else if(c == 'S' || c == 'D' || c == 'T'){
                n = Integer.parseInt(numstr);
                
                if(c=='S') dart[index++] = (int) Math.pow(n, 1);
                else if(c=='D') dart[index++] = (int) Math.pow(n, 2);
                else dart[index++] = (int) Math.pow(n, 3);
                
                numstr = "";
            }
            
            //옵션
            else {
                if(c == '*'){
                    dart[index - 1] *= 2;
                    if(index - 2 >= 0) dart[index - 2] *= 2; 
                }
                else dart[index - 1] *= -1;
                
            }
        }
    
        answer=dart[0]+dart[1]+dart[2];
    
        return answer;
    }
}