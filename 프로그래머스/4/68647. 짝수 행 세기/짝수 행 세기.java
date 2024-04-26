class Solution {
    public int solution(int[][] a) {
        final int MOD = 10000019;
        int rows = a.length;
        int cols = a[0].length;

        // 조합을 저장할 배열 초기화
        long[][] comb = new long[rows + 1][rows + 1];
        for (int i = 0; i <= rows; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        // 각 열의 합을 계산
        int[] columnSums = new int[cols];
        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                columnSums[j] += a[i][j];
            }
        }

        // 동적 프로그래밍을 위한 배열 초기화
        long[][] dp = new long[rows + 1][cols];
        dp[columnSums[0]][0] = comb[rows][columnSums[0]];

        for (int j = 1; j < cols; j++) {
            int currentSum = columnSums[j];
            for (int i = 0; i <= rows; i++) {
                int minPossible = Math.min(currentSum, i);
                int maxPossible = rows - Math.max(currentSum, i);
                int middle = rows - minPossible - maxPossible;
                for (int k = 0; k <= Math.min(minPossible, maxPossible); k++) {
                    long combProduct = (comb[i][minPossible - k] * comb[rows - i][currentSum - (minPossible - k)]) % MOD;
                    dp[middle + 2 * k][j] += (dp[i][j - 1] * combProduct) % MOD;
                    dp[middle + 2 * k][j] %= MOD;
                }
            }
        }

        return (int) (dp[0][cols - 1] % MOD);
    }
}
