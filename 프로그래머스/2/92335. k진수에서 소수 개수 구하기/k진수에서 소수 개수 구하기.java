class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        String[] prime= Integer.toString(n, k).split("0");
        
        Loop: for(String t : prime){
            if(t.length() == 0) continue;
            
            long l = Long.parseLong(t);
            
            if(l == 1) continue;
            
            for(int i = 2; i <= Math.sqrt(l); i++){
                if(l % i == 0) continue Loop;
            }
            
            
            answer++;
        }
        
        return answer;
    }
}