class Solution {
    public int romanToInt(String s) {
        HashMap<Character, Integer> symbol = new HashMap<>() {{
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }};

        int result = 0;
        char[] c = s.toCharArray();

        for (int i = 0; i < c.length; i++) {
            if (i > 0 && symbol.get(c[i]) > symbol.get(c[i - 1])) {
                result += symbol.get(c[i]) - 2 * symbol.get(c[i - 1]);
            } else result += symbol.get(c[i]);
        }

        return result;
    }
}