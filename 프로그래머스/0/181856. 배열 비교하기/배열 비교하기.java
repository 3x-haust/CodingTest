class Solution {
    public int solution(int[] arr1, int[] arr2) {
        int answer = 0;
        if(arr1.length == arr2.length){
            int a1 = 0;
            int a2 = 0;
            
            for(int i = 0; i < arr1.length; i++){
                a1 += arr1[i];
            }
            
            for(int i = 0; i < arr2.length; i++){
                a2 += arr2[i];
            }
            
            answer = a1 > a2 ? 1 : a1 == a2 ? 0 : -1;
        }else {
            answer = arr1.length > arr2.length ? 1 : -1;
        }
        return answer;
    }
}