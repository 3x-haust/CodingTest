import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        int[] arr = new int[1440];
        
        for(String[] book : book_time) {
            int start = changeMin(book[0]);
            int end = Math.min(1439, changeMin(book[1]) + 10);
            for(int j = start; j < end; j++) {
                arr[j]++;
            }
        }
        for(int i = 0; i < 1440; i++)
            answer = Math.max(answer, arr[i]);
        
        
        return answer;
    }

    public int changeMin(String time){
        int h = Integer.parseInt(time.split(":")[0]);
        int m = Integer.parseInt(time.split(":")[1]);
        int min = h * 60 + m;
        return min;
    }
}

