class Solution {
    public int solution(int[][] sizes) {
        int max_v = 0;
        int max_h = 0;
        
        for(int[] size : sizes){
            max_v = Math.max(max_v, Math.max(size[0], size[1]));
            max_h = Math.max(max_h, Math.min(size[0], size[1]));
        }
        
        return max_v * max_h;
    }
}