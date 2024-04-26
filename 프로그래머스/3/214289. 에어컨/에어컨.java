class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int[][] dp = new int[51][onboard.length + 1];
        temperature += 10; t1 += 10; t2 += 10;

        for(int i = onboard.length - 1; i >= 0; i--) {
            for(int t = 0; t < 51; t++) {
                dp[t][i] = Integer.MAX_VALUE - Math.max(a, b);
                
                if(onboard[i] == 1 && (t < t1 || t > t2)) continue;
                
                if(t == temperature) dp[t][i] = Math.min(dp[t][i], dp[t][i+1]);
                else if(t > 0 && t > temperature) dp[t][i] = Math.min(dp[t][i], dp[t - 1][i + 1]);
                else if(t < 50 && t < temperature) dp[t][i] = Math.min(dp[t][i], dp[t + 1][i + 1]);

                if(t < 50) dp[t][i] = Math.min(dp[t][i], dp[t + 1][i + 1] + a);
                if(t > 0) dp[t][i] = Math.min(dp[t][i], dp[t - 1][i + 1] + a);
                dp[t][i] = Math.min(dp[t][i], dp[t][i + 1] + b);
            }
        }

        return dp[temperature][0];
    }
}