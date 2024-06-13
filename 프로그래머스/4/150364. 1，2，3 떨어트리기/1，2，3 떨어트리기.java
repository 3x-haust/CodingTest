import java.util.*;

class Solution{
	List<Integer>[] graph;
    List<Integer> order;
    int[] count;         

    public int[] solution(int[][] edges, int[] target) {
        graph = new List[target.length];
        count = new int[target.length];
        order = new ArrayList<>();
        
        for(int i = 0; i < target.length; i++) graph[i] = new LinkedList<>();
        
        for(int[] edge: edges) graph[edge[0] - 1].add(edge[1] - 1);
        for(int i = 0; i < graph.length; i++) Collections.sort(graph[i]);
        

        int flag = 0;
        while(flag == 0){
            int leafNode = findLeaf();

            count[leafNode]++;
            order.add(leafNode);

            flag = isPossible(target);
        }
        if(flag == -1) return new int[]{-1};

        List<Integer>[] orderIndex = new List[target.length];
        for(int i = 0; i < orderIndex.length; i++){
            orderIndex[i] = new ArrayList();
        }
        for(int i = 0; i < order.size(); i++){
            int leafNode = order.get(i);
            orderIndex[leafNode].add(i);
        }

        int[] answer = new int[order.size()];

        for(int i = 0; i < target.length; i++){
            int[] blocks = calculate(target[i], count[i]);
            for(int j = 0; j < blocks.length; j++)
                answer[orderIndex[i].get(j)] = blocks[j];
        }
        return answer;
    }

    int findLeaf(){
        int node = 0;
        while(!graph[node].isEmpty()){
            int nxt = graph[node].get(0);
            graph[node].remove(0);
            graph[node].add(nxt);
            node = nxt;
        }
        return node;
    }

    int isPossible(int[] target){
        for(int i = 0; i < count.length; i++){
            if(target[i] == 0 && count[i] > 0) return -1;
            if(target[i] != 0 && target[i] < count[i]) return -1;
            if(target[i] > 0 && target[i] > count[i] * 3) return 0;
        }
        return 1;
    }

    int[] calculate(int target, int count){
        int[] blocks = new int[count];
        Arrays.fill(blocks, 0, blocks.length, 1);

        int remain = target - count;
        int idx = blocks.length - 1;
        while(remain != 0){
            if(remain >= 2){
                blocks[idx] += 2;
                remain -= 2;
            } else{
                blocks[idx] += 1;
                remain -= 1;
            }
            
            idx = (idx - 1 + blocks.length) % blocks.length;
        }
        return blocks;
    }

}