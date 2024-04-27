class Solution {
    public int solution(int n) {
        return n == Math.pow((long) Math.sqrt(n), 2) ? 1 : 2;
    }
}