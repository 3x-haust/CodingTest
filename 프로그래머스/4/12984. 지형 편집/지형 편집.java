public class Solution {
	public long solution(int[][] land, int P, int Q) {
		long answer = -1;
        long maxHeight = 0;
        long minHeight = Long.MAX_VALUE;

        for(int i = 0; i < land.length; ++i){
            for(int j = 0; j < land[i].length; ++j){
                maxHeight = Math.max(maxHeight, land[i][j]);
                minHeight = Math.min(minHeight, land[i][j]);
            }
        }

        long front = minHeight;
        long rear = maxHeight;                
        while (front <= rear){
            long mid = (front + rear) / 2;                    

            long cost1 = getCost(land, mid, P, Q);
            long cost2 = getCost(land, mid + 1, P, Q);            

            //왼쪽 범위에 최소 비용이 존재한다
            if(cost1 <= cost2){                        
                answer = cost1;                
                rear = mid - 1;
            }

            else {
                answer = cost2;                
                front = mid + 1;
            }
        }

        return answer;
	}
    
    private static long getCost(int[][] land, long height, int P, int Q) {
        long cost = 0;
        for(int i = 0; i < land.length; ++i){
            for(int j = 0; j < land[i].length; ++j){
                if(land[i][j] < height) cost += (height - land[i][j]) * P;
                else if(land[i][j] > height) cost += (land[i][j] - height) * Q;
            }
        }

        return cost;
    }
}