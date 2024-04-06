class Solution {
    public int solution(int n) {
        long root = (long) Math.sqrt(n);
        
        return n == Math.pow(root, 2) ? 1 : 2;
    }
}