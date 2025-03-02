import java.util.*;

class Solution {
    public int solution(int n, int[] cores) {
        int answer = 0;
        int len = cores.length;
        
        if(n <= len) return n;
    
        int low = 1;
        int high = 10000 * n;
        int time = 0;
        int work = 0;
        
        while (low <= high) {
            int mid = (low+high) / 2;
            int count = cal(mid, cores);
            
            if (count >= n) {
                high = mid - 1;
                time = mid;
                work = count;
            } else low = mid + 1;

        }
        
        work -= n;
        for (int i = cores.length - 1; i >= 0; i--) {
            if (time % cores[i] == 0) {
                if (work == 0) {
                    answer = i + 1;
                    break;
                }
                
                work--;
            }
        }
        
        return answer;
    }
    
    public int cal(int mid, int[] cores){
        int count = cores.length;
        
        for (int i = 0; i < cores.length; i++) count += mid / cores[i];
        
        return count;
    }
}