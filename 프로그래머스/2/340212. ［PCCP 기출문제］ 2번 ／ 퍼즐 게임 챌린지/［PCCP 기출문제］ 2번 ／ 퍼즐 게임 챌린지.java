import java.util.stream.IntStream;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int start = 1;
        int end = 100000;
        int answer = 0;

        while (start <= end) {
            int level = (start + end) / 2;
            long totalTime = getTotalTime(diffs, times, level);

            if (totalTime <= limit) {
                answer = level;
                end = level - 1;
            } else start = level + 1;
        }

        return answer;
    }

    private long getTotalTime(int[] diffs, int[] times, int level) {
        return IntStream.range(0, diffs.length)
                .mapToLong(i -> {
                    int solveTimes = diffs[i] - level;
                    if (solveTimes <= 0) return times[i];
                    long prevTime = (i == 0) ? 0 : times[i - 1];
                    return (long) (prevTime + times[i]) * solveTimes + times[i];
                })
                .sum();
    }
}