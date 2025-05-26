function solution(board, moves) {
    let answer = 0;
    let stack = [];

    for (let move of moves) {
        let mv = move - 1;
        
        for (let i = 0; i < board.length; i++) {
            let doll = board[i][mv];
            
            if (doll !== 0) {
                if (stack[stack.length - 1] === doll) {
                    stack.pop();
                    answer += 2;
                } else {
                    stack.push(doll);
                }

                board[i][mv] = 0;
                break;
            }
        }
    }

    return answer;
}
