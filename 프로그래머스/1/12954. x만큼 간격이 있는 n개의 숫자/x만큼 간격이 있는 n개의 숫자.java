class Solution {
    public long[] solution(int x, int n) {
        long[] answer = new long[n];
        
        int index = 0;
        for(long i = x; index < n; i += x){
            answer[index++] = i;
        }
        
        return answer;
    }
}