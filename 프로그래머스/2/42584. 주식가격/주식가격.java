import java.util.stream.*;

class Solution {
    public int[] solution(int[] prices) {
        return IntStream.range(0, prices.length).map(i -> {
                    for (int j = i + 1; j < prices.length; j++) {
                        if (prices[i] > prices[j]) {
                            return j - i;
                        }
                    }
                    return prices.length - 1 - i;
                }).toArray();
    }
}
