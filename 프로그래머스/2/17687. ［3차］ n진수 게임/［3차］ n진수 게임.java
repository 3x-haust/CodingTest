class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        
        for(int i = 0, count = 0; answer.length() < t; i++) {
            String baseN = Integer.toString(i, n).toUpperCase();
            
            for(char c : baseN.toCharArray()) {
                if (count % m == p - 1 && answer.length() < t) answer.append(c);
                count++;
            }
        }
        return answer.toString();
    }
}
