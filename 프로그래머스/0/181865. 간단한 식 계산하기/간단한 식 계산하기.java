class Solution {
    public int solution(String binomial) {
        int answer = 0;
        String[] parts = binomial.split(" ");
        
        String op = parts[1];
        int a = Integer.parseInt(parts[0]);
        int b = Integer.parseInt(parts[2]);
    
        switch(op){
            case "+":
                answer = a + b;
                break;
            case "-":
                answer = a - b;
                break;
            case "*":
                answer = a * b;
                break;
            case "/":
                answer = a / b;
                break;
        }
        return answer;
    }
}