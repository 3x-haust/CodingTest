class Solution {
    public int solution(int n, long l, long r) {
        int answer = 0;
        for (long i = l - 1; i < r; i++) {
            if (getValue(n, i) == 1) answer++;
        }
        return answer;
    }

    private int getValue(int n, long idx) {
        if (n == 0) return 1;
        long len = (long)Math.pow(5, n - 1);
        long pos = idx / len;
        
        if (pos == 2) return 0;
        return getValue(n - 1, idx % len);
    }
}
