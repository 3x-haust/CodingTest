class Solution {
    public String solution(String cipher, int code) {    
        return java.util.stream.IntStream.range(1, cipher.length() / code + 1).mapToObj(i -> String.valueOf(cipher.charAt(code * i - 1))).collect(java.util.stream.Collectors.joining());
    }
}