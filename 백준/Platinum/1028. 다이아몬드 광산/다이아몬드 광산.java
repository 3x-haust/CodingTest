import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map, d1, d2, d3, d4;

    public static void main(String[] args) throws IOException {
        init();
        pro();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        d1 = new int[n][m]; // 북동
        d2 = new int[n][m]; // 남서
        d3 = new int[n][m]; // 북서
        d4 = new int[n][m]; // 남동

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
    }

    static void pro() {
        if (n == 1 && m == 1) {
            System.out.println(map[0][0]);
            return;
        }

        for (int i = 0; i < n + m - 2; i++) {
            for (int r = 0; r < n; r++) {
                int c = i - r;
                if (OOB(r, c)) continue;
                d1[r][c] = OOB(r - 1, c + 1) ? map[r][c] : map[r][c] * (d1[r - 1][c + 1] + 1);
            }
            for (int c = 0; c < m; c++) {
                int r = i - c;
                if (OOB(r, c)) continue;
                d2[r][c] = OOB(r + 1, c - 1) ? map[r][c] : map[r][c] * (d2[r + 1][c - 1] + 1);
            }
        }

        for (int i = 1 - m; i <= n - 1; i++) {
            for (int r = 0; r < n; r++) {
                int c = r - i;
                if (OOB(r, c)) continue;
                d4[r][c] = OOB(r - 1, c - 1) ? map[r][c] : map[r][c] * (d4[r - 1][c - 1] + 1);
            }
            for (int r = n - 1; r >= 0; r--) {
                int c = r - i;
                if (OOB(r, c)) continue;
                d3[r][c] = OOB(r + 1, c + 1) ? map[r][c] : map[r][c] * (d3[r + 1][c + 1] + 1);
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int side = Math.min(d1[i][j], d3[i][j]);
                if (max > side) continue;

                for (int size = side; size >= 1; size--) {
                    if (j + 2 * (size - 1) >= m) continue;
                    if (size <= max) break;
                    if (Math.min(d2[i][j + 2 * (size - 1)], d4[i][j + 2 * (size - 1)]) >= size) {
                        max = size;
                        break;
                    }
                }
            }
        }
        System.out.println(max);
    }

    static boolean OOB(int r, int c) {
        return r < 0 || c < 0 || r >= n || c >= m;
    }
}
