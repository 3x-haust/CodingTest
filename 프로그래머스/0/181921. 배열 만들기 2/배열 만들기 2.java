import java.util.ArrayList;

class Solution {
    public int[] solution(int l, int r) {
        ArrayList<Integer> answer = new ArrayList<>();
        int num;
        
        for(int i = 1; i <= 63; i++){
            num = Integer.valueOf(Integer.toBinaryString(i)) * 5;
            
            if(num >= l && num <= r){
                 answer.add(num);
            }
        }
        
        return answer.size() == 0 ? new int[]{-1} : answer.stream().mapToInt(Integer::intValue).toArray();
    }
}