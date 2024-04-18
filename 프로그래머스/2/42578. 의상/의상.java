import java.util.*;
import static java.util.stream.Collectors.*;

class Solution {
    public int solution(String[][] clothes) {
//         int answer = 1;
        
//         HashMap<String, Integer> map = new HashMap<>();
        
//         for(int i = 0; i < clothes.length; i++) {
//             // 의상 종류 별로 몇개 인지 map에 저장 
//             map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 0) + 1); 
//         }
        
//         for(String key : map.keySet()) {
//             answer *= (map.get(key) + 1);  // 조합 -> 안입는 경우도 고려 -> + 1
//         }
        
//         answer -= 1;
        
//         return answer;
        
        return Arrays.stream(clothes)
            .collect(groupingBy(p -> p[1], mapping(p -> p[0], counting())))
            .values()
            .stream()
            .collect(reducing(1L, (x, y) -> x * (y + 1))).intValue() - 1;
    }
}