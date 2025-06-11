import java.util.Arrays;

class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long left = 0;
        long right = 1_000_000_000_000_000L;
        long answer = right;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (canTransport(a, b, g, s, w, t, mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    private boolean canTransport(int a, int b, int[] g, int[] s, int[] w, int[] t, long time) {
        int n = g.length;
        long totalGold = 0;
        long totalSilver = 0;
        long totalMinerals = 0;

        for (int i = 0; i < n; i++) {
            long trips = time / (2 * t[i]);
            if (time % (2 * t[i]) >= t[i]) trips++;
            long maxGold = Math.min(g[i], trips * w[i]);
            long maxSilver = Math.min(s[i], trips * w[i]);
            long maxMinerals = Math.min(g[i] + s[i], trips * w[i]);

            totalGold += maxGold;
            totalSilver += maxSilver;
            totalMinerals += maxMinerals;
        }

        return totalGold >= a && totalSilver >= b && totalMinerals >= a + b;
    }
}