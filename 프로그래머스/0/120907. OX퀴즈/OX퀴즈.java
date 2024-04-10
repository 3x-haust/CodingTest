class Solution {
    public String[] solution(String[] quiz) {
        String[] answer = new String[quiz.length];
        
        for(int i = 0; i < quiz.length; i++){
            String[] q = quiz[i].split(" ");
            
            int a = Integer.parseInt(q[0]);
            int b = Integer.parseInt(q[2]);
            int c = Integer.parseInt(q[4]);
            
            switch(q[1]){
                case "+":
                    if(a + b == c) answer[i] = "O";
                    else answer[i] = "X";
                    break;
                case "-":
                    if(a - b == c) answer[i] = "O";
                    else answer[i] = "X";
                    break;
            }
        }
        
        return answer;
    }
}