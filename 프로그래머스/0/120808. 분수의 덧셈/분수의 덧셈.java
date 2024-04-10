public class Solution {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int lcmDenom = lcm(denom1, denom2);
        int totalNumer = (numer1 * (lcmDenom / denom1)) + (numer2 * (lcmDenom / denom2));
        int gcd = gcd(totalNumer, lcmDenom);
        return new int[]{totalNumer / gcd, lcmDenom / gcd};
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
}
