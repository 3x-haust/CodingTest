class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        return java.util.stream.IntStream.range(0, arr1.length).mapToObj(i -> java.util.stream.IntStream.range(0, arr1[i].length).map(j -> arr1[i][j] + arr2[i][j]).toArray()).toArray(int[][]::new);
    }
}
