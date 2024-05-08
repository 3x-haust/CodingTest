import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        Queue<Integer> maxQueue = new PriorityQueue<>((i1, i2) -> i2 - i1),
                       minQueue = new PriorityQueue<>();
        
        
        for(int i = 0; i < operations.length; i++) {
            String[] strs = operations[i].split(" ");
            switch(strs[0]){
                case "I":
                    minQueue.offer(Integer.valueOf(strs[1]));
                    maxQueue.offer(Integer.valueOf(strs[1]));
                    break;
                case "D":
                    if(strs[1].equals("1")){
                        if(!maxQueue.isEmpty()) 
                            minQueue.remove(maxQueue.poll());
                    }else if(strs[1].equals("-1")){
                        if(!minQueue.isEmpty())
                        maxQueue.remove(minQueue.poll());
                    }

                    break;
            }
        }
        
        int min = minQueue.isEmpty() ? 0 : minQueue.poll(),
            max = maxQueue.isEmpty() ? 0 : maxQueue.poll();
        
        return new int[]{max, min};
    }
}