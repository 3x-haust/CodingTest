import java.util.*;

class Solution {
    public int solution(String[] arr) {
        int n = arr.length / 2 + 1;
        int[] num = new int[n];
        char[] op = new char[n-1];
        int numIdx = 0, opIdx = 0;
        
        for (String s : arr) {
            if (s.equals("+") || s.equals("-")) op[opIdx++] = s.charAt(0);
            else num[numIdx++] = Integer.parseInt(s);
        }
        
        int[][][] dp = new int[n][n][2]; // [시작][끝][0: 최소값, 1: 최대값]
        
        for (int i = 0; i < n; i++) {
            dp[i][i][0] = dp[i][i][1] = num[i];
        }
        
        for (int len = 2; len <= n; len++) { // 부분 문제의 길이
            for (int start = 0; start <= n-len; start++) {
                int end = start + len - 1;
                dp[start][end][0] = Integer.MAX_VALUE;
                dp[start][end][1] = Integer.MIN_VALUE;
                for (int k = start; k < end; k++) { // 분할 위치
                    int[] temp = new int[2]; // [0: 최소값, 1: 최대값]
                    if (op[k] == '+') {
                        temp[0] = dp[start][k][0] + dp[k+1][end][0];
                        temp[1] = dp[start][k][1] + dp[k+1][end][1];
                    } else {
                        temp[0] = dp[start][k][0] - dp[k+1][end][1];
                        temp[1] = dp[start][k][1] - dp[k+1][end][0];
                    }
                    dp[start][end][0] = Math.min(dp[start][end][0], temp[0]);
                    dp[start][end][1] = Math.max(dp[start][end][1], temp[1]);
                }
            }
        }
        return dp[0][n-1][1];
    }
}
