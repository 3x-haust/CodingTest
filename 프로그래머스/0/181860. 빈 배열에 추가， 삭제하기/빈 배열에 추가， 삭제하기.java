import java.util.*;

class Solution {
    public int[] solution(int[] arr, boolean[] flag) {
        List<Integer> x = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            if (flag[i]) {
                int num = arr[i];
                for (int j = 0; j < num * 2; j++) {
                    x.add(num);
                }
            } else {
                int num = arr[i];
                int size = x.size();
                if (size >= num) {
                    for (int j = 0; j < num; j++) {
                        x.remove(size - 1 - j);
                    }
                }
            }
        }
        
        return x.stream().mapToInt(Integer::intValue).toArray();
    }
}