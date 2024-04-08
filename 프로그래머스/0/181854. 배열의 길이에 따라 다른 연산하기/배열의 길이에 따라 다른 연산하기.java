class Solution {
    public int[] solution(int[] arr, int n) {
        int[] answer = arr;
        for(int i = 0; i < answer.length; i++){
            if(answer.length % 2 != 0 && i % 2 == 0){
                answer[i] += n;
            }else if(answer.length % 2 == 0 && i % 2 != 0){
                answer[i] += n;
            }
        }
        return answer;
    }
}