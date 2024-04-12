class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        
        int zNum = 0;
        int cnt = 0;
        
        while(s.length() > 1){
            for(int i = 0; i < s.length(); i++)
                if(s.charAt(i) == '0') zNum++;
            
            s = s.replace("0", "");
            cnt++;
            
            s = Integer.toBinaryString(s.length());
        }
        
        return new int[]{cnt, zNum};
    }
}