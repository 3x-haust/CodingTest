import java.util.*;

public class Solution {
    public String solution(String s) {
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        charCount.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> sb.append(entry.getKey()));

        return sb.toString();
    }
}
