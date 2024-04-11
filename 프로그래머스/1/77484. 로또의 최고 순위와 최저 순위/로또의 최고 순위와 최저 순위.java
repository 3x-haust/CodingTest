class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int zero = 0;
        int matched = 0;
        
        for (int lotto : lottos) {
            if (lotto == 0) zero++;
            else {
                for (int win_num : win_nums) {
                    if (lotto == win_num) {
                        matched++;
                        break;
                    }
                }
            }
        }
        
        int min = matched;
        int max = matched + zero;
        
        int[] answer = { Math.min(7 - max, 6), Math.min(7 - min, 6) };
        return answer;
    }
}