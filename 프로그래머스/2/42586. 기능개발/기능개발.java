import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] dayList = new int[100];
        int day = -1;
        
        for(int i = 0; i < progresses.length; i++) {
            while(progresses[i] + (day * speeds[i]) < 100) day++;
            
            dayList[day]++;
        }
        return Arrays.stream(dayList).filter(i -> i != 0).toArray();
    }
}