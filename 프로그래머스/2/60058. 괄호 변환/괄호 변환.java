import java.util.Stack;

public class Solution {
    public static String solution(String p) {
        return dfs(p);
    }

    private static String dfs(String w) {
        if (w.isEmpty()) return "";
        else {
            String u = "";
            String v = "";
            String result = "";
            int open = 0;
            // 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다.
            // 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다.
            for (int i = 0; i < w.length(); i++) {
                char c = w.charAt(i);
                if (c == '(') {
                    open++;
                } else {
                    open--;
                }
                if (open == 0) {
                    u = w.substring(0, i + 1);
                    v = w.substring(i + 1);
                    break;
                }
            }
            // 3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
            if (validation(u)) { // 3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
                return u + dfs(v);
            } else { // 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
                result = "(" + dfs(v) + ")";
                for (int i = 1; i < u.length() - 1; i++) {
                    if (u.charAt(i) == '(') {
                        result += ')';
                    } else {
                        result += '(';
                    }
                }
            }
            return result;
        }
    }

    private static boolean validation(String v) {
        Stack<Character> s = new Stack<>();
        for (int i = 0; i < v.length(); i++) {
            char c = v.charAt(i);
            if (c == '(') {
                s.push(c);
            } else {
                if (s.isEmpty()) {
                    return false;
                }
                s.pop();
            }
        }
        return true;
    }
}