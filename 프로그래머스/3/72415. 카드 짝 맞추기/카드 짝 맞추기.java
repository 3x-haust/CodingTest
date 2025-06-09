import java.util.*;

class Solution {
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int solution(int[][] board, int r, int c) {
        List<Integer> cardTypes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0 && !cardTypes.contains(board[i][j])) cardTypes.add(board[i][j]);
            }
        }
        
        List<List<Integer>> permutations = getPermutations(cardTypes);
        int minMoves = Integer.MAX_VALUE;
        
        for (List<Integer> perm : permutations) {
            int moves = calculateMoves(board, r, c, perm);
            minMoves = Math.min(minMoves, moves);
        }
        
        return minMoves;
    }
    
    private List<List<Integer>> getPermutations(List<Integer> list) {
        List<List<Integer>> result = new ArrayList<>();
        permute(list, 0, result);
        return result;
    }
    
    private void permute(List<Integer> list, int start, List<List<Integer>> result) {
        if (start == list.size()) {
            result.add(new ArrayList<>(list));
            return;
        }
        
        for (int i = start; i < list.size(); i++) {
            Collections.swap(list, start, i);
            permute(list, start + 1, result);
            Collections.swap(list, start, i);
        }
    }
    
    private int calculateMoves(int[][] board, int startR, int startC, List<Integer> order) {
        int[][] tempBoard = new int[4][4];
        for (int i = 0; i < 4; i++) {
            System.arraycopy(board[i], 0, tempBoard[i], 0, 4);
        }
        
        int totalMoves = 0;
        int curR = startR, curC = startC;
        
        for (int cardType : order) {
            List<int[]> positions = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (tempBoard[i][j] == cardType) positions.add(new int[]{i, j});
                }
            }
            
            int[] pos1 = positions.get(0);
            int[] pos2 = positions.get(1);
            
            int moves1 = getMinDistance(tempBoard, curR, curC, pos1[0], pos1[1]) + 1 + getMinDistance(tempBoard, pos1[0], pos1[1], pos2[0], pos2[1]) + 1;
            
            int moves2 = getMinDistance(tempBoard, curR, curC, pos2[0], pos2[1]) + 1 + getMinDistance(tempBoard, pos2[0], pos2[1], pos1[0], pos1[1]) + 1;
            
            if (moves1 <= moves2) {
                totalMoves += moves1;
                curR = pos2[0];
                curC = pos2[1];
            } else {
                totalMoves += moves2;
                curR = pos1[0];
                curC = pos1[1];
            }
            
            tempBoard[pos1[0]][pos1[1]] = 0;
            tempBoard[pos2[0]][pos2[1]] = 0;
        }
        
        return totalMoves;
    }
    
    private int getMinDistance(int[][] board, int r1, int c1, int r2, int c2) {
        if (r1 == r2 && c1 == c2) return 0;
        
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[4][4];
        queue.offer(new int[]{r1, c1, 0});
        visited[r1][c1] = true;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0], c = current[1], moves = current[2];
            
            for (int i = 0; i < 4; i++) {
                int nr = r + directions[i][0];
                int nc = c + directions[i][1];
                
                if (nr >= 0 && nr < 4 && nc >= 0 && nc < 4 && !visited[nr][nc]) {
                    if (nr == r2 && nc == c2) return moves + 1;
                    
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc, moves + 1});
                }
                
                int[] ctrlPos = getCtrlMove(board, r, c, i);
                if (ctrlPos[0] != r || ctrlPos[1] != c) {
                    if (!visited[ctrlPos[0]][ctrlPos[1]]) {
                        if (ctrlPos[0] == r2 && ctrlPos[1] == c2) return moves + 1;
                        
                        visited[ctrlPos[0]][ctrlPos[1]] = true;
                        queue.offer(new int[]{ctrlPos[0], ctrlPos[1], moves + 1});
                    }
                }
            }
        }
        
        return Integer.MAX_VALUE;
    }
    
    private int[] getCtrlMove(int[][] board, int r, int c, int direction) {
        int dr = directions[direction][0];
        int dc = directions[direction][1];
        
        int nr = r + dr;
        int nc = c + dc;
        
        while (nr >= 0 && nr < 4 && nc >= 0 && nc < 4) {
            if (board[nr][nc] != 0) return new int[]{nr, nc};
            
            nr += dr;
            nc += dc;
        }
        
        nr -= dr;
        nc -= dc;
        
        if (nr == r && nc == c) return new int[]{r, c};
        
        return new int[]{nr, nc};
    }
}