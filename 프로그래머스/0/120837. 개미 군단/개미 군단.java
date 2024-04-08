class Solution {
    public int solution(int hp) {
        int answer = 0;
        answer += hp / 5;
        if(hp % 5 != 0)
            answer += hp % 5 / 3;
        if(hp % 5 % 3 != 0)
            answer += hp % 5 % 3 / 1;
        return answer;
    }
}