class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int num = brown + yellow;
        
        for(int i = 3; i < num; i++){
            int j = num / i;
            
            if(num % i == 0 && j >= 3){
                int col = Math.max(i, j);
                int row = Math.min(i, j);
                int center = (col - 2) * (row - 2);
                
                if(center == yellow){
                    answer[0] = col;
                    answer[1] = row;
                    
                    return answer;
                }
            }
        }
        
        return answer;
    }
}