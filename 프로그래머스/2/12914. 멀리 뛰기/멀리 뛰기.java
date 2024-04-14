public class Solution {
    public int solution(int n) {
        if (n <= 1) return n;
        
        int a = 1, b = 2; // 첫 번째 칸과 두 번째 칸에 도달하는 방법의 수 초기화
        for (int i = 2; i < n; i++) {
            int temp = (a + b) % 1234567; // 다음 값 계산
            a = b; // 이전 값 업데이트
            b = temp; // 현재 값 업데이트
        }
        
        return b; // n칸에 도달하는 방법의 수 반환
    }
}
