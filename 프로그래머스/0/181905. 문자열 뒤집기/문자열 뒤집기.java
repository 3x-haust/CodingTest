class Solution {
    public String solution(String my_string, int s, int e) {
        StringBuffer sb = new StringBuffer(my_string.substring(s, e + 1));
        return my_string.substring(0, s) + sb.reverse().toString() + my_string.substring(e+1, my_string.length());
    }
}