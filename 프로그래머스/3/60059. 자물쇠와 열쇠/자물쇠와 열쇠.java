class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int point = key.length - 1;
        for (int x = 0; x < point + lock.length; x++) {
            for (int y = 0; y < point + lock.length; y++) {
                for (int r = 0; r < 4; r++) {
                    int[][] newLock = new int[lock.length + key.length * 2][lock.length + key.length * 2];
                    for (int i = 0; i < lock.length; i++) {
                        System.arraycopy(lock[i], 0, newLock[i + point], point, lock.length);
                    }
                    match(newLock, key, r, x, y);
                    if (check(newLock, point, lock.length)) return true;
                }
            }
        }
        return false;
    }

    private void match(int[][] newLock, int[][] key, int rot, int x, int y) {
        int len = key.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int keyValue = (rot == 0) ? key[i][j] :
                               (rot == 1) ? key[j][len - i - 1] :
                               (rot == 2) ? key[len - i - 1][len - j - 1] :
                                            key[len - j - 1][i];
                newLock[x + i][y + j] += keyValue;
            }
        }
    }

    private boolean check(int[][] newLock, int point, int len) {
        return java.util.stream.IntStream.range(0, len)
                .allMatch(i -> java.util.stream.IntStream.range(0, len)
                        .allMatch(j -> newLock[point + i][point + j] == 1));
    }
}