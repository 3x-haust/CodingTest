class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        int[] answer = arr;
        
        for(int i = 0; i < queries.length; i++){
            for(int j = 0; j < queries[i].length; j++){
                if(j != 0){
                    int a = arr[queries[i][j]];
                    int b = arr[queries[i][j-1]];
                    
                    answer[queries[i][j]] = b;
                    answer[queries[i][j-1]] = a;
                    
                    
                }
            }
        }
        
        return answer;
    }
}