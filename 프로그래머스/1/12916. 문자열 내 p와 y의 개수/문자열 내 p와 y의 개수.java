class Solution {
    boolean solution(String s) {
        boolean answer = true;

        int p = 0;
        int y = 0;
        
        for(char c : s.toCharArray()){
            if(c == 'P' || c == 'p'){
                p++;
            }else if(c == 'Y' || c == 'y'){
                y++;
            }
        }

        return p == y ? true : false;
    }
}