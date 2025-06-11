import java.util.*;

class Solution {
    private static final int[][] KEYPAD = {
        {3, 1}, {0, 0}, {0, 1}, {0, 2}, {1, 0}, 
        {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}
    };
    
    public int solution(String numbers) {
        int n = numbers.length();
        int[][][] dp = new int[n + 1][10][10];
        
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
        }
        
        dp[0][4][6] = 0;
        
        for (int i = 0; i < n; i++) {
            int target = numbers.charAt(i) - '0';
            
            for (int left = 0; left < 10; left++) {
                for (int right = 0; right < 10; right++) {
                    if (dp[i][left][right] == Integer.MAX_VALUE) continue;
                    
                    boolean leftOnTarget = (left == target);
                    boolean rightOnTarget = (right == target);
                    
                    if (leftOnTarget && rightOnTarget) {
                        continue;
                    } else if (leftOnTarget) {
                        int cost = getCost(left, target);
                        dp[i + 1][target][right] = Math.min(
                            dp[i + 1][target][right], 
                            dp[i][left][right] + cost
                        );
                    } else if (rightOnTarget) {
                        int cost = getCost(right, target);
                        dp[i + 1][left][target] = Math.min(
                            dp[i + 1][left][target], 
                            dp[i][left][right] + cost
                        );
                    } else {
                        int leftCost = getCost(left, target);
                        dp[i + 1][target][right] = Math.min(
                            dp[i + 1][target][right], 
                            dp[i][left][right] + leftCost
                        );
                        
                        int rightCost = getCost(right, target);
                        dp[i + 1][left][target] = Math.min(
                            dp[i + 1][left][target], 
                            dp[i][left][right] + rightCost
                        );
                    }
                }
            }
        }
        
        int result = Integer.MAX_VALUE;
        for (int left = 0; left < 10; left++) {
            for (int right = 0; right < 10; right++) {
                result = Math.min(result, dp[n][left][right]);
            }
        }
        
        return result;
    }
    
    private int getCost(int from, int to) {
        if (from == to) return 1;
        
        int[] fromPos = KEYPAD[from];
        int[] toPos = KEYPAD[to];
        
        int dx = Math.abs(fromPos[0] - toPos[0]);  
        int dy = Math.abs(fromPos[1] - toPos[1]);
        
        int diagonal = Math.min(dx, dy);
        int straight = Math.abs(dx - dy);
        
        return diagonal * 3 + straight * 2;
    }
}