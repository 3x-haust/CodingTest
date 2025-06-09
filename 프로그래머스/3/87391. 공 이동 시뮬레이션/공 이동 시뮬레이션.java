class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long minRow = x, maxRow = x;
        long minCol = y, maxCol = y;
        
        for (int i = queries.length - 1; i >= 0; i--) {
            int dir = queries[i][0];
            int dist = queries[i][1];
            
            switch (dir) {
                case 0:
                    maxCol = Math.min(m - 1, maxCol + dist);
                    if (minCol > 0) minCol += dist;
                    break;
                    
                case 1: 
                    minCol = Math.max(0, minCol - dist);
                    if (maxCol < m - 1) maxCol -= dist;

                    break;
                    
                case 2: 
                    maxRow = Math.min(n - 1, maxRow + dist);
                    if (minRow > 0) minRow += dist;
                    break;
                    
                case 3: 
                    minRow = Math.max(0, minRow - dist);
                    
                    if (maxRow < n - 1) maxRow -= dist;
                    
                    break;
            }
            
            if (minRow > maxRow || minCol > maxCol) return 0;
        }
        
        return (maxRow - minRow + 1) * (maxCol - minCol + 1);
    }
}