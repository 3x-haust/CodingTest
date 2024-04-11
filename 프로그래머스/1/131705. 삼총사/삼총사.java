import java.util.Arrays;

class Solution {
    static int answer = 0;
    
    public int solution(int[] number) {
        combination(number, 3, 0, 0, new int[3]);
        return answer;
    }
    
    public static void combination(int[] number, int limit, int startIdx, int cnt, int[] selectArr) {
        if (cnt == limit) {
            answer += Arrays.stream(selectArr).sum() == 0 ? 1 : 0;
            return;
        }
        
        for (int i=startIdx; i<number.length; i++) {
            selectArr[cnt] = number[i];
            combination(number, limit, i + 1, cnt + 1, selectArr);
        }
    }
}