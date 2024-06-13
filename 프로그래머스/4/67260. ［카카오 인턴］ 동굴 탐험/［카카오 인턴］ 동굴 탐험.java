import java.util.*;

class Solution {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
    static Map<Integer, Integer> before = new HashMap<Integer,Integer>(); // 먼저 방문
    static Map<Integer, Integer> after = new HashMap<Integer,Integer>(); // 방문조건 만족
    static ArrayList<Integer> node = new ArrayList<Integer>(); // 방문한 노드
    public boolean solution(int n, int[][] path, int[][] order) {
        boolean answer = true;
        
        //graph 초기화
        for(int i = 0; i < n; i++) 
            graph.add(new ArrayList<Integer>());
        for(int i=0;i<path.length;i++) {
            graph.get(path[i][1]).add(path[i][0]);
            graph.get(path[i][0]).add(path[i][1]);
        }
        
        // 순서쌍 map에 저장
        for(int i = 0; i < order.length; i++) {
            before.put(order[i][1], order[i][0]);
            after.put(order[i][0], order[i][1]);
        }
        
        // 그래프 방문
        bfs();
        
        // 모두 방문 되었으면 true
        if(node.size() != n) answer = false;
        
        return answer;
    }
    
    public static void bfs() {
        Queue<Integer> queue = new LinkedList<Integer>();
        boolean[] visit = new boolean[graph.size()];
        
        queue.offer(0);
        node.add(0);
        visit[0] = true;
        
        while(!queue.isEmpty()) {
            int p = queue.poll();
            for(Integer i:graph.get(p)) {
                // 선수조건이 없음 or 선수가 방문된 노드 추가
                if((before.get(i) == null || visit[before.get(i)]) && !visit[i]) {
                    node.add(i);
                    queue.offer(i);

                    if(after.get(i) != null && visit[after.get(i)]) {
                        node.add(after.get(i));
                        queue.offer(after.get(i));
                    }
                }
                visit[i] = true;
            }
        }
    }
}