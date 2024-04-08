class Solution {
    public int solution(int[] num_list) {
        int answer = 0;
        
        for(int i = 0; i < num_list.length; i++){
            if(num_list.length >= 11){
                answer += num_list[i];
            }else if(num_list.length <= 10){
                if(i == 0) {
                    answer = num_list[i];
                }else {
                    answer *= num_list[i];
                }
            }
        
        }
        return answer;
    }
}