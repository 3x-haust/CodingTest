import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    private static int[] x_move = {1, 0, -1, 0};
    private static int[] y_move = {0, 1, 0, -1};
    private int n;

    public int solution(int[][] land, int height) {
        int answer = 0;
        n = land.length;
        boolean[][] visited = new boolean[n][n];

        Queue<Point> queue = new PriorityQueue<>();
        queue.add(new Point(0, 0, 0));

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            
            if (visited[current.x][current.y]) {
                continue;
            }
            
            visited[current.x][current.y] = true;
            answer += current.value;

            for (int i = 0; i < 4; i++) {
                int nextX = current.x + x_move[i];
                int nextY = current.y + y_move[i];
                
                if (!isInArea(nextX, nextY)) {
                    continue;
                }
                
                int cost = Math.abs(land[current.x][current.y] - land[nextX][nextY]);
                if (cost > height) {
                    queue.add(new Point(nextX, nextY, cost));
                    continue;
                }
                
                queue.add(new Point(nextX, nextY, 0));
            }
        }

        return answer;
    }

    private boolean isInArea(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }

    class Point implements Comparable<Point> {
        int x;
        int y;
        int value;

        public Point(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }


        @Override
        public int compareTo(Point o) {
            return this.value - o.value;
        }
    }
}