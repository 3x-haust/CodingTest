class Solution {
    public int solution(int[] arr) {
       int[] cop ;
        int answer = -1;
        int cnt =0 ;
        
        while(cnt != arr.length){
            cnt = 0;
            cop = arr.clone();
            
            for(int i = 0; i < arr.length; i++){
                if(arr[i] < 50 && arr[i] % 2 != 0){
                    arr[i]= arr[i] * 2 + 1;
                }else if(arr[i] >= 50 && arr[i] % 2 == 0){
                    arr[i] = arr[i] / 2;
                }
                
                if(arr[i] == cop[i]) cnt++;
            }
            
            answer++;
        }
        
        return answer;
    }
}