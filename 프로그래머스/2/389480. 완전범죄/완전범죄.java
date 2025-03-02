public class Solution {
    public int solution(int[][] info, int n, int m) {
        int[][] dp = new int[info.length + 1][m];
        
        for (int i = 0; i <= info.length; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        
        dp[0][0] = 0;
        
        for (int r = 1; r <= info.length; r++) {
            int aScore = info[r - 1][0];
            int bScore = info[r - 1][1];
            
            for (int c = 0; c < m; c++) {
                if (dp[r - 1][c] == Integer.MAX_VALUE) continue;
                
                dp[r][c] = Math.min(dp[r][c], dp[r - 1][c] + aScore);
                
                if (c + bScore < m) dp[r][c + bScore] = Math.min(dp[r][c + bScore], dp[r - 1][c]);
            }
        }
        
        int min = Integer.MAX_VALUE;
        for (int value : dp[info.length])
            if (value < n) min = Math.min(min, value);
        
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}