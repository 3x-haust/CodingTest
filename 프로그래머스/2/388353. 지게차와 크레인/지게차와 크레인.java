import java.util.*;
 
class Solution {
    static int[][] dires = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
 
    public int solution(String[] storage, String[] requests) {
        int H = storage.length + 2;
        int W = storage[0].length() + 2;
 
        char[][] arr = new char[H][W];
        for (int i = 0; i < H; i++) Arrays.fill(arr[i], '-');

        for (int i = 1; i < H - 1; i++) {
            for (int j = 1; j < W - 1; j++) {
                arr[i][j] = storage[i - 1].charAt(j - 1);
            }
        }
 
        for (String request : requests) {
            char target = request.charAt(0);
            List<int[]> removeList = new ArrayList<>();
 
            if (request.length() == 2) {
                for (int i = 0; i < H; i++) {
                    for (int j = 0; j < W; j++) {
                        if (arr[i][j] == target) arr[i][j] = '-';
                    }
                }
            } else {
                boolean[][] boundary = bfs(arr, H, W);
                for (int i = 1; i < H - 1; i++) {
                    for (int j = 1; j < W - 1; j++) {
                        if (arr[i][j] == target) {
                            for (int[] dire : dires) {
                                int ni = i + dire[0], nj = j + dire[1];
                                if (boundary[ni][nj]) {
                                    removeList.add(new int[]{i,j});
                                    break;
                                }
                            }
                        }
                    }
                }
                for (int[] loc : removeList) arr[loc[0]][loc[1]] = '-';
            }
        }

        int answer = 0;
        for (int i = 1; i < H - 1; i++) {
            for (int j = 1; j < W - 1; j++) {
                if (arr[i][j] != '-') answer++;
            }
        }
        return answer;
    }
 
    public boolean[][] bfs(char[][] arr, int H, int W) {
        boolean[][] V = new boolean[H][W];
        ArrayDeque<int[]> Q = new ArrayDeque<>();
        Q.offer(new int[]{0,0});
        V[0][0] = true;
 
        while (!Q.isEmpty()) {
            int[] cur = Q.poll();
            int x = cur[0], y = cur[1];
 
            for (int[] d : dires) {
                int nx = x + d[0], ny = y + d[1];
                if (nx >= 0 && nx < H && ny >= 0 && ny < W && arr[nx][ny] == '-' && !V[nx][ny]) {
                    V[nx][ny] = true;
                    Q.offer(new int[]{nx,ny});
                }
            }
        }
        
        return V;
    }
}
