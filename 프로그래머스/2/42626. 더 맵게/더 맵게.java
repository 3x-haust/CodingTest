import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        IntStream.range(0, scoville.length).forEach(i -> queue.add(scoville[i]));
        
        
        while(queue.peek() < K) {
            if(queue.size() == 1) return -1;
            
            queue.add(queue.poll() + queue.poll() * 2);
            answer++;
        }
        
        return answer;
    }
}