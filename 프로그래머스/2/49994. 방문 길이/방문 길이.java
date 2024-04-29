import java.util.*;

class Solution {
    public static int solution(String dirs) {
        HashSet<String> visited = new HashSet<>();

        // 시작 위치
        int currentX = 0;
        int currentY = 0;

        for (int i = 0; i < dirs.length(); i++) {
            int nextX = currentX;
            int nextY = currentY;
            
            String move = "";
            
            switch(dirs.charAt(i)){
                case 'U': // 위
                    nextY++;
                    move += currentX + "" + currentY + "->" + nextX +  nextY;
                    break;
                case 'D': // 밑
                    nextY--; 
                    move += nextX + "" + nextY + "->" + currentX + currentY;
                    break;
                case 'R': // 오
                    nextX++;
                    move += currentX + "" + currentY + "->" + nextX + nextY;
                    break;
                case 'L': // 왼
                    nextX--; 
                    move += nextX + "" + nextY + "->" + currentX + currentY;
                    break;
            }


            // 좌표평면을 넘어가면 추가하지 않는다.
            if (nextX < -5 || nextY < -5 || nextX > 5 || nextY > 5) {
                continue;
            }
            
            visited.add(move);

            currentX = nextX;
            currentY = nextY;
        }

        return visited.size();
    }
}