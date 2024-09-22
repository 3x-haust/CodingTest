class Solution {
    public static int solution(String dartResult) {
        int[] scores = new int[3];
        String[] turns = dartResult.split("(?<=\\D)(?=\\d)");

        for (int i = 0; i < turns.length; i++) {
            String turn = turns[i];
            int score = Integer.parseInt(turn.replaceAll("\\D", ""));
            score = turn.contains("D") ? score * score : turn.contains("T") ? score * score * score : score;

            if (turn.contains("*")) {
                score *= 2;
                if (i > 0) scores[i - 1] *= 2;
            }
            
            if (turn.contains("#")) score *= -1;
        

            scores[i] = score;
        }

        return java.util.Arrays.stream(scores).sum();
    }
}