class Solution {
    public int solution(String my_string) {
        return java.util.Arrays.stream(my_string.replaceAll("[a-zA-Z]", "").split("")).mapToInt(Integer::parseInt).sum();
    }
}
