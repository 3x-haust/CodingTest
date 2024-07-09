import java.util.*;
 
public class Main {
    static int n, m;
    static int[][] board;
    static int min = Integer.MAX_VALUE;
    static ArrayList<Node> chickenList = new ArrayList<>(); // 치킨집 위치
    static ArrayList<Node> houseList = new ArrayList<>(); // 집의 위치
    static boolean[] chickenVisited; // 뽑은 치킨집
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
 
        n = scan.nextInt();
        m = scan.nextInt();
        
        board = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                board[i][j] = scan.nextInt();
                if(board[i][j] == 1) houseList.add(new Node(i, j));
                else if(board[i][j] == 2) chickenList.add(new Node(i, j));
            }
        }
        
        chickenVisited = new boolean[chickenList.size()];
        backtracking(0, 0); // m개의 치킨집 뽑기
        System.out.println(min);
    }
    
    public static void backtracking(int count, int idx) {
        if(count == m) { // 치킨 거리 최솟값
            int total = 0; // 도시의 치킨거리
            
            for(int i = 0; i < houseList.size(); i++) {
                int sum = Integer.MAX_VALUE;
                
                for(int j = 0; j < chickenList.size(); j++) {
                    if(chickenVisited[j] == true) { // i번째 집 ~ j번째 치킨집 거리 중 최소값
                        int dist = Math.abs(houseList.get(i).x - chickenList.get(j).x) 
                            + Math.abs(houseList.get(i).y - chickenList.get(j).y);
                        sum = Math.min(sum, dist);
                    }
                }
                
                total += sum;
            }
            
            min = Math.min(total, min);
            return;
        }
        
        for(int i = idx; i < chickenList.size(); i++) {
            if(chickenVisited[i] == false) {
                chickenVisited[i] = true;
                backtracking(count + 1, i + 1);
                chickenVisited[i] = false;
            }
        }
    }
    
    public static class Node {
        int x;
        int y;
        
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}