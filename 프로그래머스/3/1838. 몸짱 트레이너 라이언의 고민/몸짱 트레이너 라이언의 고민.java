import java.util.*;

class Solution {

    public class Point {
        int x, y;

        public Point (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int solution(int n, int m, int[][] timetable) {
        int[] preSum = new int[722];

        for (int i = 0; i < m; i++) {
            preSum[timetable[i][0] - 600]++;
            preSum[timetable[i][1] - 600 + 1]--;
        }

        int sum = 0;
        int max = 0; 
        for (int i = 0; i <= 720; i++) {
            sum += preSum[i];
            preSum[i] = sum;
            max = Math.max(max, preSum[i]);
        }

        if (max <= 1) return 0;

        ArrayList<Point> list = new ArrayList<>();
        for (int dist = 2 * (n - 1); dist >= 1; dist--) {
            for (int sy = 0; sy < n; sy++) {
                list.clear();
                int cnt = 0;

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        
                        if (i == 0 && j < sy) continue;
                        
                        boolean flag = true;
                        for (Point p : list) {
                            if (Math.abs(p.x - i) + Math.abs(p.y - j) >= dist) continue;
                            flag = false;
                            break;
                        }
                        
                        if (flag) {
                            if (++cnt == max) return dist;
                            list.add(new Point(i, j));
                        }
                    }
                }
            }
        }
        
        return 0;
    }
}