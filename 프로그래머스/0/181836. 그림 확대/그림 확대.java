class Solution {
    public String[] solution(String[] picture, int k) {
        String[] answer = new String[k * picture.length];
        
        int index = 0;
        for(String str : picture){
            StringBuilder sb = new StringBuilder();
            
            for(char c : str.toCharArray()) sb.append(Character.toString(c).repeat(k));
            
            String result = sb.toString();
            
            for(int i = 0; i < k; i++){
                answer[index++] = result;
            }
        }
        
        return answer;
    }
}