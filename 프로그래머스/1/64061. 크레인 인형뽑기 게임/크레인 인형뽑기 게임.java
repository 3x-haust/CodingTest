import java.util.*;
class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int len = board[0].length;
         Stack<Integer> st = new Stack<>();
         
        for(int mv : moves){
            mv -= 1;
            for(int i=0; i < len; i++){  
                if(board[i][mv] != 0){ //인형이 있는경우
                    if(st.size() > 0 && st.peek() == board[i][mv] ){//뽑은 인형 == 마지막 인형
                        st.pop();
                        answer += 2;
                    }else st.push(board[i][mv]); //인형 넣기 
                    
                    board[i][mv] = 0; //뽑은 뒤 0
                    break; 
                }
            }  
        } 
        
        return answer;
    }
}