class Solution {
    public int solution(String my_string) {
        int answer = 0;
        
        my_string = my_string.replaceAll("[a-zA-Z]", ""); //매개변수의 숫자만 남기기
        String[] string = my_string.split("");
        
        for(String i : string) {
            answer += Integer.parseInt(i);
        }
        
        return answer;
    }
}