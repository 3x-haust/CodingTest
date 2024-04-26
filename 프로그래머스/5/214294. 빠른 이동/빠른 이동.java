import java.util.*;

class Solution {

    int timeCounter, groupCount, groups[], discoveryTime[], matchingArray[], edgeMatrix[][];
    List<Integer> adjacencyList[];
    Stack<Integer> stack;
    boolean visited[];

    int tarjan(int currentNode) {
        int returnValue = discoveryTime[currentNode] = ++timeCounter;
        stack.push(currentNode);

        for (int nextNode : adjacencyList[currentNode]) {
            if (discoveryTime[nextNode] == 0)
                returnValue = Math.min(returnValue, tarjan(nextNode));
            else if (groups[nextNode] == 0)
                returnValue = Math.min(returnValue, discoveryTime[nextNode]);
        }

        if (returnValue == discoveryTime[currentNode]) {
            groupCount++;
            do {
                groups[stack.peek()] = groupCount;
            } while (stack.pop() != currentNode);
        }
        return returnValue;
    }

    boolean findMaxMatching(int a) {
        for (int b = 1; b <= groupCount; b++) {
            if (edgeMatrix[a][b] > 0 && !visited[b]) {
                visited[b] = true;
                if (matchingArray[b] == 0 || findMaxMatching(matchingArray[b])) {
                    matchingArray[b] = a;
                    return true;
                }
            }
        }
        return false;
    }

    public int solution(int n, int[][] roads) {

        stack = new Stack<Integer>();
        discoveryTime = new int[n];
        groups = new int[n];
        adjacencyList = new ArrayList[n];

        for (int i = 0; i < n; i++)
            adjacencyList[i] = new ArrayList<>();

        for (int road[] : roads)
            adjacencyList[road[0] - 1].add(road[1] - 1);

        tarjan(0);
        edgeMatrix = new int[groupCount + 1][groupCount + 1];
        matchingArray = new int[groupCount + 1];
        visited = new boolean[groupCount + 1];

        for (int i = 0; i < n; i++) {
            for (int j : adjacencyList[i]) {
                if (groups[j] != groups[i]) {
                    edgeMatrix[groups[i]][groups[j]]++;
                }
            }
        }

        for (int k = 1; k <= groupCount; k++) {
            for (int i = 1; i <= groupCount; i++) {
                for (int j = 1; j <= groupCount; j++) {
                    if (edgeMatrix[i][k] > 0 && edgeMatrix[k][j] > 0) {
                        edgeMatrix[i][j] = 1;
                    }
                }
            }
        }

        int result = groupCount - 1;
        for (int i = 1; i <= groupCount; i++) {
            if (findMaxMatching(i)) {
                result--;
                visited = new boolean[groupCount + 1];
            }
        }
        return result;
    }
}