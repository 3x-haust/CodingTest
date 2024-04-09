class Solution {
    public int solution(int i, int j, int k) {
        int answer = 0;
        for(int l = i; l <= j; l++){
            String strL = String.valueOf(l);
            String strK = String.valueOf(k);
            
            if(strL.contains(strK)) {
                String[] array = strL.split("");
                
                for (String alpha : array) {
                    if (alpha.equals(strK)) answer++;
                }
            }
        }
        return answer;
    }
}