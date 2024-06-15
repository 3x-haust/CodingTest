import java.math.BigInteger;
import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads) {
        Map<Integer, Map<Integer, Integer>> c = getConnections(roads);
        long [] distFromStart = getDistances(c, roads, n, 1);
        long [] distToEnd = getDistances(c, roads, n, n);
        long originalMin = distFromStart[n];
        long [] minWithRoad = getMinWithRoad(roads, distFromStart, distToEnd);
        Set<Integer> minEdgeIndices = getMinEdgeIndices(minWithRoad, originalMin);
        BigInteger [] combFromStart = getCombinations(c, roads, minEdgeIndices, distFromStart, n, 1, n);
        BigInteger [] combToEnd = getCombinations(c, roads, minEdgeIndices, distToEnd, n, n, 1);
        // System.out.println(Arrays.toString(combFromStart));
        // System.out.println(Arrays.toString(combToEnd));
        BigInteger allComb = combFromStart[1].multiply(combToEnd[1]);
        List<Integer> targets = new ArrayList<>();
        for (int i = 0; i < roads.length; i++) {
            int [] road = roads[i];
            if (minWithRoad[i] - road[3] < originalMin) {
                // 해당 길을 지날때 가능한의 최소 소요 시간(교통량이 0일때) 이 전체 최소 소요시간 보다 작을 경우
                // 교통량을 줄였을때 최소 소요시간이 변경될 수 있다
                targets.add(i + 1);
            } else if (minEdgeIndices.contains(i)) {
                // 해당 길을 제외한 최소 소요시간의 대안 경로가 없을 경우 해당 길의 교통량을 늘리면 최소 소요시간이 변경 될 수 있다
                BigInteger comb1 = combFromStart[road[0]].multiply(combToEnd[road[0]]);
                BigInteger comb2 = combFromStart[road[1]].multiply(combToEnd[road[1]]);
                if (Objects.equals(comb1, allComb) && Objects.equals(comb2, allComb) && Objects.equals(combFromStart[road[0]], combFromStart[road[1]]) && Objects.equals(combToEnd[road[0]], combToEnd[road[1]])) {
                    targets.add(i + 1);
                }
            }
        }
        int[] answer = targets.isEmpty() ? new int[]{-1} : targets.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }

    private Map<Integer, Map<Integer, Integer>> getConnections(int[][] roads) {
        Map<Integer, Map<Integer, Integer>> c = new HashMap<>();
        for (int i = 0; i < roads.length; i++) {
            int [] road = roads[i];
            c.computeIfAbsent(road[0], x -> new HashMap()).put(road[1], i);
            c.computeIfAbsent(road[1], x -> new HashMap()).put(road[0], i);
        }
        return c;
    }

    private long [] getDistances(Map<Integer, Map<Integer, Integer>> c, int[][] roads, int count, int start) {
        long [] d = new long [count + 1];
        Arrays.fill(d, -1L);
        TreeMap<Long, Set<Integer>> targets = new TreeMap<>();
        d[start] = 0;
        targets.computeIfAbsent(d[start], x -> new HashSet<>()).add(start);
        while (!targets.isEmpty()) {
            Long minDistance = targets.firstKey();
            Set<Integer> distanceGroup = targets.get(minDistance);
            int minNode = distanceGroup.iterator().next();
            distanceGroup.remove(minNode);
            if (distanceGroup.isEmpty()) {
                targets.remove(minDistance);
            }
            Map<Integer, Integer> edges = c.get(minNode);
            for (Map.Entry<Integer, Integer> entry : edges.entrySet()) {
                int adj = entry.getKey();
                int [] road = roads[entry.getValue()];
                long newDistance = minDistance + road[2] + road[3];
                if (d[adj] == -1L) {
                    targets.computeIfAbsent(newDistance, x -> new HashSet<>()).add(adj);
                    d[adj] = newDistance;
                } else {
                    if (d[adj] > newDistance) {
                        Set<Integer> dg = targets.get(d[adj]);
                        dg.remove(adj);
                        if (dg.isEmpty()) {
                            targets.remove(d[adj]);
                        }
                        targets.computeIfAbsent(newDistance, x -> new HashSet<>()).add(adj);
                        d[adj] = newDistance;
                    }
                }
            }
        }
        return d;
    }

    private long [] getMinWithRoad(int [][] roads, long [] distFromStart, long [] distToEnd) {
        long [] minWithRoad = new long[roads.length];
        for (int i = 0; i < roads.length; i++) {
            int [] road = roads[i];
            minWithRoad[i] = Math.min(distFromStart[road[0]] + distToEnd[road[1]], distFromStart[road[1]] + distToEnd[road[0]]) + road[2] + road[3];
        }
        return minWithRoad;
    }

    private Set<Integer> getMinEdgeIndices(long [] minWithRoad, long originalMin) {
        Set<Integer> minEdgeIndices = new HashSet<>();
        for (int i = 0; i < minWithRoad.length; i++) {
            if (minWithRoad[i] == originalMin) {
                minEdgeIndices.add(i);
            }
        }
        return minEdgeIndices;
    }

    private BigInteger [] getCombinations(Map<Integer, Map<Integer, Integer>> c, int[][] roads, Set<Integer> minEdgeIndices, long [] distFromStart, int count, int start, int end) {
        BigInteger [] comb = new BigInteger [count + 1];
        Arrays.fill(comb, BigInteger.ZERO);

        Set<Integer> notOrdered = new HashSet<>();
        for (int e : minEdgeIndices) {
            notOrdered.add(roads[e][0]);
            notOrdered.add(roads[e][1]);
        }
        List<Integer> ordered = new ArrayList<>(notOrdered);
        ordered.sort(Comparator.comparing(x -> distFromStart[x]));

        boolean [] visited = new boolean[count + 1];
        comb[start] = BigInteger.ONE;

        for (int n : ordered) {
            visited[n] = true;
            Map<Integer, Integer> edges = c.get(n);
            for (Map.Entry<Integer, Integer> entry : edges.entrySet()) {
                int adj = entry.getKey();
                int roadIndex = entry.getValue();
                if (minEdgeIndices.contains(roadIndex)) {
                    if (!visited[adj]) {
                        comb[adj] = comb[adj].add(comb[n]);
                    }
                }
            }
        }
        return comb;
    }
}