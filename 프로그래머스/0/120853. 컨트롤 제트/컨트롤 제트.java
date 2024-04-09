class Solution {
    public int solution(String s) {
        int answer = 0;
        int cal = 0;
        
        String[] str = s.split(" ");
        
        for(int i = 0; i < str.length; i++){
            if(str[i].equals("Z")){
                answer -= cal;
            }else {
                answer += Integer.parseInt(str[i]);
                cal = Integer.parseInt(str[i]);
            }
        }
        
        return answer;
    }
}