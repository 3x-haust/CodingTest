class Solution {
    public int solution(int[][] arr) {
        int answer = 0;
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                if(arr[j][i] == arr[i][j]) answer++;
            }
        }
        return answer == Math.pow(arr.length, 2) ? 1 : 0;
    }
}