import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] fail = new int[N + 1];
        int[] per = new int[N + 1];

        for (int i = 0; i < stages.length; i++) {
            for (int j = 0; j < stages[i]; j++) {
                per[j] += 1;
            }
            fail[stages[i] - 1] += 1;
        }

        Map<Integer, Double> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            if (fail[i] == 0 || per[i] == 0) {
                map.put(i + 1, 0.0);
            } else {
                map.put(i + 1, (double) fail[i] / (double) per[i]);
            }
        }

        List<Integer> list = new ArrayList<>(map.keySet());
        list.sort((o1, o2) -> Double.compare(map.get(o2), map.get(o1)));

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}