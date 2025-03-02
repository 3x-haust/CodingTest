import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public int solution(int n, int[][] q, int[] ans) {
        List<int[]> possibleCodes = generateCombinations(n, 5);
        int totalCnt = 0;
        
        for (int[] code : possibleCodes) {
            boolean makeCode = true;
            for (int i = 0; i < q.length; i++) {
                if (countCommonElements(code, q[i]) != ans[i]) {
                    makeCode = false;
                    break;
                }
            }
            
            if (makeCode) totalCnt++;
        }
        
        return totalCnt;
    }
    
    private List<int[]> generateCombinations(int n, int r) {
        List<int[]> combinations = new ArrayList<>();
        generateCombinationsHelper(combinations, new int[r], 0, 1, n, r);
        return combinations;
    }
    
    private void generateCombinationsHelper(List<int[]> combinations, int[] current, int index, int start, int n, int r) {
        if (index == r) {
            combinations.add(current.clone());
            return;
        }
        
        for (int i = start; i <= n; i++) {
            current[index] = i;
            generateCombinationsHelper(combinations, current, index + 1, i + 1, n, r);
        }
    }
    
    private int countCommonElements(int[] arr1, int[] arr2) {
        Set<Integer> set = new HashSet<>();
        
        for (int num : arr1) set.add(num);
        
        int count = 0;
        for (int num : arr2) {
            if (set.contains(num)) count++;
        }
        
        return count;
    }
}