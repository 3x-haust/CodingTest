import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        str1 = str1.toUpperCase();
		str2 = str2.toUpperCase();
		
		ArrayList<String> list1 = new ArrayList<>();
		ArrayList<String> list2 = new ArrayList<>();

		ArrayList<String> union = new ArrayList<>();
		ArrayList<String> intersection = new ArrayList<>();

		// str1 다중 집합 
		for(int i = 0; i < str1.length()-1; i++) {
			char a = str1.charAt(i);
			char b = str1.charAt(i+1);

			// 문자만 가진 경우 추가
			if(Character.isLetter(a) && Character.isLetter(b)) {
				String str = Character.toString(a) + Character.toString(b);
				list1.add(str);
			}
		}

		// str2 다중 집합
		for(int i = 0; i < str2.length()-1; i++) {
			char a = str2.charAt(i);
			char b = str2.charAt(i+1);

			// 문자만 가진 경우 추가 
			if(Character.isLetter(a) && Character.isLetter(b)) {
				list2.add(Character.toString(a) + Character.toString(b));
			}
        }

		// 중복 원소 처리 -> 두 집합 정렬
		Collections.sort(list1);
		Collections.sort(list2);
		
		// 교집합 구하기 
		for(String s : list1) {
			if(list2.remove(s)) { // 집합1에 집합2가 포함 -> 삭제 -> 교집합에 추가 
				intersection.add(s);
			}
			union.add(s);
		}
		
		// 합집합 구하기 
		for(String s : list2) { // 교집합에서 제외된 것 뺀 나머지 -> 합집합에 추가 
			union.add(s);
		}
        
		// 자카드 유사도 구하기 
		double a = intersection.size();
		double b = union.size();

   		double jakard = 0;
	
		if(union.size() == 0)
			jakard = 1;	// 공집합일 경우 1
		else
			jakard = (double) intersection.size() / (double) union.size();

		return (int) (jakard * 65536);
    }
}