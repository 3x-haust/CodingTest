class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        for (int i = 0; i < schedules.length; i++) {
            int time = schedules[i], hour = time / 100, minute = time % 100 + 10;
            if (minute >= 60) {
                hour++;
                minute -= 60; 
            }
            
            int goal = hour * 100 + minute;
            boolean meets = true;
            for (int j = 0; j < 7; j++) {
                int day = (startday + j) % 7;
                if (day == 0 || day == 6) continue;
                if (goal < timelogs[i][j]) { meets = false; break; }
            }
            
            if (meets) answer++;
        }
        
        return answer;
    }
}