import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int[][] land) {
        int r = land.length, c = land[0].length;
        int[] cnt = new int[c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (land[i][j] == 0) continue;

                Set<Integer> candidates = new HashSet<>();
                Queue<int[]> q = new ArrayDeque<>();
                q.offer(new int[]{i, j});
                int area = 1;
                land[i][j] = 0;
                candidates.add(j);

                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    int cr = cur[0], cc = cur[1];

                    for (int a = -1; a <= 1; a++) {
                        for (int b = -1; b <= 1; b++) {
                            if (((a ^ b) & 1) != 1) continue;
                            int nr = cr + a, nc = cc + b;
                            if (nr < 0 || nr >= r || nc < 0 || nc >= c || land[nr][nc] == 0) continue;
                            
                            area++;
                            land[nr][nc] = 0;
                            candidates.add(nc);
                            q.offer(new int[]{nr, nc});
                        }
                    }
                }

                final int finalArea = area;
                candidates.forEach(col -> cnt[col] += finalArea);
            }
        }
        return Arrays.stream(cnt).max().orElse(0);
    }
}
