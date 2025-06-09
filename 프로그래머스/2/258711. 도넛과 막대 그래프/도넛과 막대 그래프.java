import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        HashMap<Integer, int[]> degrees = new HashMap<>();
        int[] answer = new int[4];

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            degrees.putIfAbsent(from, new int[2]);
            degrees.get(from)[1]++;

            degrees.putIfAbsent(to, new int[2]);
            degrees.get(to)[0]++;
        }
                
        for (Map.Entry<Integer, int[]> entry : degrees.entrySet()) {
            int node = entry.getKey();
            int inDegree = entry.getValue()[0];
            int outDegree = entry.getValue()[1];

            if (outDegree >= 2 && inDegree == 0) {
                answer[0] = node;
            }
            
            if (outDegree == 0) {
                answer[2]++;
            }
            
            if (outDegree >= 2 && inDegree >= 2) {
                answer[3]++;
            }
        }
        
        answer[1] = degrees.get(answer[0])[1] - answer[2] - answer[3];
        return answer;
    }
}
