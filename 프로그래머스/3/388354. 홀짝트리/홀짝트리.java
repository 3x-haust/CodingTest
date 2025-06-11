import java.util.*;

class Solution {
    public int[] solution(int[] nodes, int[][] edges) {
        int n = nodes.length;
        Map<Integer, Integer> nodeToIndex = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            nodeToIndex.put(nodes[i], i);
        }
        
        List<List<Integer>> adj = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        int[] degree = new int[n];
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            int idxA = nodeToIndex.get(a);
            int idxB = nodeToIndex.get(b);
            adj.get(idxA).add(idxB);
            adj.get(idxB).add(idxA);
            degree[idxA]++;
            degree[idxB]++;
        }
        
        boolean[] visited = new boolean[n];
        int oddEvenCount = 0;
        int reverseOddEvenCount = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int countNotGood = 0;
                int countNotReverseGood = 0;
                Queue<Integer> queue = new ArrayDeque<>();
                queue.add(i);
                visited[i] = true;
                
                while (!queue.isEmpty()) {
                    int current = queue.poll();
                    boolean isOdd = (nodes[current] % 2 == 1);
                    boolean degreeEven = (degree[current] % 2 == 0);
                    boolean isGood = (isOdd && degreeEven) || (!isOdd && !degreeEven);
                    
                    if (!isGood) countNotGood++;

                    boolean isReverseGood = (isOdd && !degreeEven) || (!isOdd && degreeEven);
                    
                    if (!isReverseGood) countNotReverseGood++;
                    
                    for (int neighbor : adj.get(current)) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.add(neighbor);
                        }
                    }
                }
                
                if (countNotGood == 1) oddEvenCount++;
                if (countNotReverseGood == 1) reverseOddEvenCount++;
            }
        }
        
        return new int[]{oddEvenCount, reverseOddEvenCount};
    }
}