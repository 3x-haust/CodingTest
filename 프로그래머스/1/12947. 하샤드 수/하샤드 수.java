class Solution {
    public boolean solution(int x) {
        int add = 0;
        
        String s = String.valueOf(x);
        
        for(int i = 0; i < s.length(); i++) {
            add += Integer.parseInt(String.valueOf(s.charAt(i)));
        }
        
        return x % add == 0 ? true : false;
    }
}