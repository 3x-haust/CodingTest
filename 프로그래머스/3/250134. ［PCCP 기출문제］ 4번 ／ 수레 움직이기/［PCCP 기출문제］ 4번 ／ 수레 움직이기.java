import java.util.*;

class Solution {
    static class State {
        int redRow, redCol, blueRow, blueCol;
        boolean[][] redVisited, blueVisited;
        int turns;
        
        State(int redRow, int redCol, int blueRow, int blueCol, 
              boolean[][] redVisited, boolean[][] blueVisited, int turns) {
            this.redRow = redRow;
            this.redCol = redCol;
            this.blueRow = blueRow;
            this.blueCol = blueCol;
            this.redVisited = copyVisited(redVisited);
            this.blueVisited = copyVisited(blueVisited);
            this.turns = turns;
        }
        
        private boolean[][] copyVisited(boolean[][] visited) {
            boolean[][] copy = new boolean[visited.length][visited[0].length];
            for (int i = 0; i < visited.length; i++) {
                System.arraycopy(visited[i], 0, copy[i], 0, visited[i].length);
            }
            
            return copy;
        }
        
        String getKey() {
            StringBuilder sb = new StringBuilder();
            sb.append(redRow).append(",").append(redCol).append(",")
              .append(blueRow).append(",").append(blueCol).append(",");
            
            for (int i = 0; i < redVisited.length; i++) {
                for (int j = 0; j < redVisited[0].length; j++) {
                    sb.append(redVisited[i][j] ? "1" : "0");
                }
            }
            
            sb.append(",");
            
            for (int i = 0; i < blueVisited.length; i++) {
                for (int j = 0; j < blueVisited[0].length; j++) {
                    sb.append(blueVisited[i][j] ? "1" : "0");
                }
            }
            
            return sb.toString();
        }
    }
    
    public int solution(int[][] maze) {
        int n = maze.length;
        int m = maze[0].length;
        
        int redStartRow = -1, redStartCol = -1, redEndRow = -1, redEndCol = -1;
        int blueStartRow = -1, blueStartCol = -1, blueEndRow = -1, blueEndCol = -1;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maze[i][j] == 1) {
                    redStartRow = i;
                    redStartCol = j;
                } else if (maze[i][j] == 2) {
                    blueStartRow = i;
                    blueStartCol = j;
                } else if (maze[i][j] == 3) {
                    redEndRow = i;
                    redEndCol = j;
                } else if (maze[i][j] == 4) {
                    blueEndRow = i;
                    blueEndCol = j;
                }
            }
        }
        
        boolean[][] redVisited = new boolean[n][m];
        boolean[][] blueVisited = new boolean[n][m];
        redVisited[redStartRow][redStartCol] = true;
        blueVisited[blueStartRow][blueStartCol] = true;
        
        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        
        State initialState = new State(redStartRow, redStartCol, blueStartRow, 
                                       blueStartCol, redVisited, blueVisited, 0);
        queue.offer(initialState);
        visited.add(initialState.getKey());
        
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        while (!queue.isEmpty()) {
            State current = queue.poll();
            
            if (current.redRow == redEndRow && current.redCol == redEndCol &&
                current.blueRow == blueEndRow && current.blueCol == blueEndCol) {
                return current.turns;
            }
            
            boolean redAtEnd = (current.redRow == redEndRow && current.redCol == redEndCol);
            boolean blueAtEnd = (current.blueRow == blueEndRow && current.blueCol == blueEndCol);
            
            List<int[]> redMoves = new ArrayList<>();
            List<int[]> blueMoves = new ArrayList<>();
            
            if (redAtEnd) {
                redMoves.add(new int[]{current.redRow, current.redCol});
            } else {
                for (int i = 0; i < 4; i++) {
                    int newRedRow = current.redRow + dx[i];
                    int newRedCol = current.redCol + dy[i];
                    
                    if (isValid(newRedRow, newRedCol, n, m, maze) && 
                        !current.redVisited[newRedRow][newRedCol]) {
                        redMoves.add(new int[]{newRedRow, newRedCol});
                    }
                }
            }
            
            if (blueAtEnd) {
                blueMoves.add(new int[]{current.blueRow, current.blueCol});
            } else {
                for (int i = 0; i < 4; i++) {
                    int newBlueRow = current.blueRow + dx[i];
                    int newBlueCol = current.blueCol + dy[i];
                    
                    if (isValid(newBlueRow, newBlueCol, n, m, maze) && 
                        !current.blueVisited[newBlueRow][newBlueCol]) {
                        blueMoves.add(new int[]{newBlueRow, newBlueCol});
                    }
                }
            }
            
            for (int[] redMove : redMoves) {
                for (int[] blueMove : blueMoves) {
                    int newRedRow = redMove[0], newRedCol = redMove[1];
                    int newBlueRow = blueMove[0], newBlueCol = blueMove[1];
                    
                    if (newRedRow == newBlueRow && newRedCol == newBlueCol) continue;
                    
                    if (current.redRow == newBlueRow && current.redCol == newBlueCol && 
                        current.blueRow == newRedRow && current.blueCol == newRedCol) continue;
                    
                    
                    boolean[][] newRedVisited = current.redVisited;
                    boolean[][] newBlueVisited = current.blueVisited;
                    
                    if (!redAtEnd) {
                        newRedVisited = copyArray(current.redVisited);
                        newRedVisited[newRedRow][newRedCol] = true;
                    }
                    
                    if (!blueAtEnd) {
                        newBlueVisited = copyArray(current.blueVisited);
                        newBlueVisited[newBlueRow][newBlueCol] = true;
                    }
                    
                    State newState = new State(newRedRow, newRedCol, newBlueRow, newBlueCol,
                                             newRedVisited, newBlueVisited, current.turns + 1);
                    
                    String key = newState.getKey();
                    if (!visited.contains(key)) {
                        visited.add(key);
                        queue.offer(newState);
                    }
                }
            }
        }
        
        return 0;
    }
    
    private boolean isValid(int row, int col, int n, int m, int[][] maze) {
        return row >= 0 && row < n && col >= 0 && col < m && maze[row][col] != 5;
    }
    
    private boolean[][] copyArray(boolean[][] arr) {
        boolean[][] copy = new boolean[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++) {
            System.arraycopy(arr[i], 0, copy[i], 0, arr[i].length);
        }
        
        return copy;
    }
}