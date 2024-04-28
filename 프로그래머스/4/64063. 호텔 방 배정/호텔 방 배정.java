import java.util.*;
import java.util.stream.*;

class Solution {
    static HashMap<Long, Long> hashMap = new HashMap<>();
    
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        
        IntStream.range(0, room_number.length).forEach(i -> answer[i] = findEmptyRoom(room_number[i]));
        
        return answer;
    }
    
    static Long findEmptyRoom(long num) {
        if (!hashMap.containsKey(num)) { // 빈 방이면 다음 방 번호 추가
            hashMap.put(num, num + 1);
            return num;
        }
        
        long empty = findEmptyRoom(hashMap.get(num));
        hashMap.put(num, empty);
        
        return empty;
    }
}