import java.util.*;
import java.util.stream.*;

class Solution {
    private int countsum(int a, int b) {
        a = a < 0 ? a + 1000000007 : a;
        b = b < 0 ? b + 1000000007 : b;
        return (a + b) % 1000000007;
    }

    private int findCount(int [] xs, int [] counts, int x) {
        int index = Arrays.binarySearch(xs, x);
        
        if (index >= 0) return counts[index];
        else {
            index = -index - 2;
            if (index < 0 || index >= counts.length - 1)return 0;
            else return counts[index];
        }
    }

    private int findCount(int [] xs, int [] sum, int sx, int ex) {
        int si = Arrays.binarySearch(xs, sx);
        int ei = Arrays.binarySearch(xs, ex);
        si = si < 0 ? -si - 2 : si - 1;
        ei = ei < 0 ? -ei - 2 : ei;
        int sums = si < 0 ? 0 : sum[si];
        int sume = ei < 0 ? 0 : sum[ei];
        
        return sume - sums;
    }

    public int[] solution(int n, int[][] point, int[][] query) {
        Map<Integer, List<Integer>> depthIndexMap = new HashMap<>();
        Map<Integer, List<Integer>> nextDepthIndexMap = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            int depth = depth(point[i]);
            depthIndexMap.computeIfAbsent(depth, x -> new ArrayList<>()).add(i);
            nextDepthIndexMap.computeIfAbsent(nextDepth(point[i]), x -> new ArrayList<>()).add(i);
        }

        for (List<Integer> depthIndices : depthIndexMap.values())
            depthIndices.sort(Comparator.comparingInt(a -> point[a][0]));

        int [] counts1 = new int[n];
        int [] counts2 = new int[n];
        int[] sortedDepths = depthIndexMap.keySet().stream().sorted().mapToInt(Integer::intValue).toArray();
        int start = depthIndexMap.get(sortedDepths[0]).get(0);
        counts1[start] = 1;
        int end = depthIndexMap.get(sortedDepths[sortedDepths.length - 1]).get(0);
        counts2[end] = 1;

        Map<Integer, int[]> depthXsMap = new HashMap<>();
        Map<Integer, int[]> depthCountMap = new HashMap<>();

        Map<Integer, int[]> nextDepthXsMap = new HashMap<>();
        Map<Integer, int[]> nextDepthSumMap = new HashMap<>();

        for (int i = 1, c = sortedDepths.length; i < c; i++) {
            int depth = sortedDepths[i];
            List<Integer> curtIndices = depthIndexMap.get(depth);
            List<Integer> prevIndices = nextDepthIndexMap.get(depth);
            
            if (curtIndices != null && prevIndices != null) {
                Map<Integer, Integer> dmap = new HashMap<>();
                
                for (Integer prevIndex : prevIndices) {
                    int xs = point[prevIndex][0] - point[prevIndex][2] + 1;
                    int xe = point[prevIndex][0];
                    dmap.merge(xs, counts1[prevIndex], this::countsum);
                    dmap.merge(xe, -counts1[prevIndex], this::countsum);
                }
                
                int[] xs = dmap.keySet().stream().filter(k -> dmap.get(k) != 0).mapToInt(Integer::intValue).toArray();
                
                Arrays.sort(xs);
                
                int[] counts = new int[xs.length];
                counts[0] = dmap.get(xs[0]);
                
                for (int j = 1; j < xs.length; j++)
                    counts[j] = countsum(counts[j - 1], dmap.get(xs[j]));
                
                depthXsMap.put(depth, xs);
                depthCountMap.put(depth, counts);

                for (Integer curtIndex : curtIndices)
                    counts1[curtIndex] = findCount(xs, counts, point[curtIndex][0]);
            }
        }
        for (int i = sortedDepths.length - 1; i >= 1; i--) {
            int nextDepth = sortedDepths[i];
            
            List<Integer> curtIndices = nextDepthIndexMap.get(nextDepth);
            List<Integer> nextIndices = depthIndexMap.get(nextDepth);
            
            if (curtIndices != null && nextIndices != null) {
                Map<Integer, Integer> smap = new HashMap<>();
                
                for (Integer nextIndex : nextIndices) {
                    int x = point[nextIndex][0];
                    smap.merge(x, counts2[nextIndex], this::countsum);
                }
                
                int[] xs = smap.keySet().stream().filter(k -> smap.get(k) != 0).mapToInt(Integer::intValue).toArray();
                
                Arrays.sort(xs);
                
                int[] sum = new int[xs.length];
                sum[0] = smap.get(xs[0]);
                for (int j = 1; j < xs.length; j++) 
                    sum[j] = countsum(sum[j - 1], smap.get(xs[j]));
                nextDepthXsMap.put(nextDepth, xs);
                nextDepthSumMap.put(nextDepth, sum);

                for (Integer curtIndex : curtIndices) {
                    counts2[curtIndex] = findCount(xs, sum, point[curtIndex][0] - point[curtIndex][2] + 1, point[curtIndex][0] - 1);
                }
            }
        }

        List<Integer> answer = new ArrayList<>();
        answer.add(counts1[end]);

        for (int[] q : query) {
            int [] qp = {q[1], q[2], q.length < 4 ? 0 : q[3]};
            if (q[0] == 0) {
                int depth = depth(qp);
                int nextDepth = nextDepth(qp);

                int prevCount = 0;
                int nextCount = 0;

                if (nextDepthXsMap.containsKey(nextDepth)) {
                    int[] xs = nextDepthXsMap.get(nextDepth);
                    int[] sum = nextDepthSumMap.get(nextDepth);
                    nextCount = findCount(xs, sum, qp[0] - qp[2] + 1, qp[0] - 1);
                }

                if (depthXsMap.containsKey(depth)) {
                    int[] xs = depthXsMap.get(depth);
                    int[] counts = depthCountMap.get(depth);
                    prevCount = findCount(xs, counts, qp[0]);
                }

                int change = (int)((long)prevCount * (long)nextCount % 1000000007L);
                int qcount = (counts1[end] + change) % 1000000007;
                answer.add(qcount);

            } else {
                int qcount;
                if (point[start][0] == qp[0] || point[end][0] == qp[0]) qcount = 0;
                else {
                    List<Integer> sortedIndices = depthIndexMap.get(depth(qp));
                    int p = findx(point, sortedIndices, qp[0]);
                    int index = sortedIndices.get(p);
                    int change = (int)((long)counts1[index] * (long)counts2[index] % 1000000007L);
                    qcount = (counts1[end] + ((1000000007 - change) % 1000000007)) % 1000000007;
                }
                answer.add(qcount);
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private int depth(int [] point) {
        return point[1] - point[0];
    }

    private int nextDepth(int [] point) {
        return point[1] - point[0] + point[2];
    }

    private int findx(int[][] point, List<Integer> sortedIndices, int x) {
        int low = 0;
        int high = sortedIndices.size() - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = point[sortedIndices.get(mid)][0];

            if (midVal < x) low = mid + 1;
            else if (midVal > x) high = mid - 1;
            else return mid;
        }
        
        return -(low + 1);
    }
}
