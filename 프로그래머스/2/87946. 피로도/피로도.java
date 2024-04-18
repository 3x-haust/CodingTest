class Solution {
    public boolean check[];
    public int answer = 0;

    public int solution(int k, int[][] dungeons) {
        check = new boolean[dungeons.length];

        dfs(k, dungeons, 0);

        return answer;
    }
    
    public void dfs(int k, int[][] dungeons, int cnt){
        for(int i = 0; i < dungeons.length; i++){
            if(!check[i] && dungeons[i][0] <= k){
                check[i] = true;
                
                dfs(k - dungeons[i][1], dungeons, cnt + 1);
                
                check[i] = false;
            }
        }
        
        answer = Math.max(answer, cnt);
    }
}