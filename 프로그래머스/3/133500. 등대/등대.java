import java.util.HashSet;

public class Solution {
	
	// 등대
	
	public static int solution(int n, int[][] lighthouse) {
		int answer = 0;
		int[] linkedCntArr; // 등대에 연결된 등대의 수
		
		HashSet<Integer> edgeHs = new HashSet<>(); // 가장자리 등대 번호
		HashSet<Integer> turnOnHs = new HashSet<>(); // 가장자리 등대와 연결된, 반드시 켜야 하는 등대의 번호
		int[][] remainingLightHouse; // 남은 등대 쌍
		int remainingCnt; // 등대 쌍의 수
		
		
		for (int a = 0; a < n; a++) { 
			linkedCntArr = new int[n + 1];
			remainingLightHouse = new int[lighthouse.length][2]; 
			remainingCnt = 0; 
			
			for (int i = 0; i < lighthouse.length; i++) {
				linkedCntArr[lighthouse[i][0]]++;
				linkedCntArr[lighthouse[i][1]]++;
			}
            
			for (int i = 0; i < linkedCntArr.length; i++) {
				if (linkedCntArr[i] == 1) edgeHs.add(i);	
			}
			
			
			for (int i = 0; i < lighthouse.length; i++) {
				if (edgeHs.contains(lighthouse[i][0]) || edgeHs.contains(lighthouse[i][1])) {
					
					if (edgeHs.contains(lighthouse[i][0])) turnOnHs.add(lighthouse[i][1]);
					else turnOnHs.add(lighthouse[i][0]);
				}
			}
			
			for (int i = 0; i < lighthouse.length; i++) {
				if (!turnOnHs.contains(lighthouse[i][0]) && !turnOnHs.contains(lighthouse[i][1])) {
					remainingLightHouse[remainingCnt] = lighthouse[i];
					remainingCnt++;
				}
			}
				
			if (remainingCnt == 0) break;
			if (remainingCnt == 1) { 
				answer += 1;
				break;
			}
			
			if (remainingCnt < lighthouse.length) {
				lighthouse = new int[remainingCnt][2]; 
				for (int i = 0; i < remainingCnt; i++) {
					lighthouse[i] = remainingLightHouse[i];
				}
			}
		}
		
		answer += turnOnHs.size(); // 켜진 등대의 수 더하기
		
		return answer;
	}
}