public class Solution {
    public int[][] solution(int n) {
        int[][] answer = new int[n][n];
        
        int x = 0, y = 0, num = 1;
        
        int direction = 0;
        
        for (int i = 0; i < n * n; i++) {
            answer[y][x] = num++;
            
            if (direction == 0) {
                if (x + 1 < n && answer[y][x + 1] == 0) x++;
                else {
                    direction = 1;
                    y++;
                }
            } else if (direction == 1) {
                if (y + 1 < n && answer[y + 1][x] == 0) y++;
                else {
                    direction = 2;
                    x--;
                }
            } else if (direction == 2) {
                if (x - 1 >= 0 && answer[y][x - 1] == 0) x--;
                else {
                    direction = 3;
                    y--;
                }
            } else if (direction == 3) {
                if (y - 1 >= 0 && answer[y - 1][x] == 0) y--;
                else {
                    direction = 0;
                    x++;
                }
            }
        }
        return answer;
    }
}
