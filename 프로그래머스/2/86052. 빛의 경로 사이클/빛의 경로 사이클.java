import java.util.ArrayList;
 
class Solution {
    static int R, C;
    static int[] dr = { -1, 0, 1, 0 }, dc = { 0, -1, 0, 1 }; 
    static boolean[][][] isVisited;
 
    public int[] solution(String[] grid) {
        ArrayList<Integer> answer = new ArrayList<Integer>();
 
        R = grid.length;
        C = grid[0].length();
 
        isVisited = new boolean[R][C][4];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                for (int d = 0; d < 4; d++) {
                    if (!isVisited[i][j][d])
                        answer.add(light(grid, i, j, d ));
                }
            }
        }
 
        return answer.stream().sorted().mapToInt(i -> i).toArray();
    }
 
    private static int light(String[] grid, int r, int c, int d) {
        int cnt = 0;
 
        while (true) {
            if (isVisited[r][c][d])
                break;
 
            cnt++;
            isVisited[r][c][d] = true;
 
            if (grid[r].charAt(c) == 'L')
                d = d == 0 ? 3 : d - 1;
            else if (grid[r].charAt(c) == 'R')
                d = d == 3 ? 0 : d + 1;
 
            r = (r + dr[d] + R) % R;
            c = (c + dc[d] + C) % C;
        }
 
        return cnt;
    }
}