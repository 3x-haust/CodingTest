class Solution {
    public int solution(int[] num_list) {
        int answer = 0;
        int add = 0;
        int multiply = 0;
        
        for(int i = 0; i < num_list.length; i++){
            add += num_list[i];
            if(i == 0){
                multiply = num_list[i];
            }else {
                multiply *= num_list[i];
            }
        
        }
        
        if(multiply < Math.pow(add,2)) answer = 1;
        
        return answer;
    }
}