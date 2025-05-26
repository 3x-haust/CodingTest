class Solution {
    String[] friends = {"A", "C", "F", "J", "M", "N", "R", "T"};
    int count = 0;
    int n;
    String[] data;

    public int solution(int n, String[] data) {
        this.n = n;
        this.data = data;
        permute(friends, 0);
        return count;
    }

    public void permute(String[] arr, int depth) {
        if (depth == arr.length) {
            if (isValid(arr)) count++;
            return;
        }
        
        for (int i = depth; i < arr.length; i++) {
            swap(arr, i, depth);
            permute(arr, depth + 1);
            swap(arr, i, depth);
        }
    }

    public boolean isValid(String[] arr) {
        for (String cond : data) {
            String a = cond.substring(0, 1);
            String b = cond.substring(2, 3);
            char op = cond.charAt(3);
            int dist = cond.charAt(4) - '0';

            int aIdx = -1, bIdx = -1;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].equals(a)) aIdx = i;
                if (arr[i].equals(b)) bIdx = i;
            }
            
            int diff = Math.abs(aIdx - bIdx) - 1;
            switch (op) {
                case '=' -> {
                    if (diff != dist) return false;
                }
                case '<' -> {
                    if (diff >= dist) return false;
                }
                case '>' -> {
                    if (diff <= dist) return false;
                }
            }
        }
        
        return true;
    }

    public void swap(String[] arr, int i, int j) {
        String tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
