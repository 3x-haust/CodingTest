import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        Arrays.sort(rocks);
        
        int l = 1;
        int r = distance;
        
        while(l <= r){
            int mid = (l + r) / 2;
            
            if(bsa(rocks, mid, distance) <= n){
                answer = mid;
                l = mid + 1;
            }else r = mid - 1; 
            
        }
        
        
        return answer;
    }
    
    public int bsa(int[] rocks, int mid, int distance){
        int before = 0;
        
        int cnt = 0;
        for(int i = 0; i < rocks.length; i++){
            if(rocks[i] - before < mid) {
                cnt++;
                continue;
            }
            
            before = rocks[i];
        }
        
        if(distance - before < mid) cnt++;
        
        return cnt;
    }
}