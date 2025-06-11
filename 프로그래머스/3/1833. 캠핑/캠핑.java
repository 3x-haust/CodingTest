import java.util.*;

class Solution {
    public int solution(int n, int[][] data) {
        Set<Integer> xSet = new HashSet<>();
        Set<Integer> ySet = new HashSet<>();
        
        for (int[] point : data) {
            xSet.add(point[0]);
            ySet.add(point[1]);
        }
        
        List<Integer> xList = new ArrayList<>(xSet);
        List<Integer> yList = new ArrayList<>(ySet);
        Collections.sort(xList);
        Collections.sort(yList);
        
        Map<Integer, Integer> xMap = new HashMap<>();
        Map<Integer, Integer> yMap = new HashMap<>();
        for (int i = 0; i < xList.size(); i++) {
            xMap.put(xList.get(i), i);
        }
        for (int i = 0; i < yList.size(); i++) {
            yMap.put(yList.get(i), i);
        }
        
        int[][] grid = new int[xList.size()][yList.size()];
        for (int[] point : data) {
            int x = xMap.get(point[0]);
            int y = yMap.get(point[1]);
            grid[x][y] = 1;
        }
        
        int[][] prefixSum = new int[xList.size() + 1][yList.size() + 1];
        for (int i = 1; i <= xList.size(); i++) {
            for (int j = 1; j <= yList.size(); j++) {
                prefixSum[i][j] = grid[i-1][j-1] + prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1];
            }
        }
        
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x1 = data[i][0], y1 = data[i][1];
                int x2 = data[j][0], y2 = data[j][1];
                
                if (x1 == x2 || y1 == y2) continue;
                
                int minX = Math.min(x1, x2);
                int maxX = Math.max(x1, x2);
                int minY = Math.min(y1, y2);
                int maxY = Math.max(y1, y2);
                
                int x1Idx = xMap.get(minX);
                int x2Idx = xMap.get(maxX);
                int y1Idx = yMap.get(minY);
                int y2Idx = yMap.get(maxY);
                
                int innerSum = prefixSum[x2Idx][y2Idx] - prefixSum[x1Idx + 1][y2Idx] 
                             - prefixSum[x2Idx][y1Idx + 1] + prefixSum[x1Idx + 1][y1Idx + 1];
                
                if (innerSum == 0) {
                    count++;
                }
            }
        }
        
        return count;
    }
}