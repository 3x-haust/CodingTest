class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        int[] answer = arr;
        
        for(int[] query : queries){
            int s = query[0];
            int e = query[1];
            
            for(int i = s; i <= e; i++){
               answer[i] += 1;        
            }
        }
        
        return answer;
    }
}