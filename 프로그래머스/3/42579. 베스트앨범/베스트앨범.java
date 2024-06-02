import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        HashMap<String, Integer> genresMap = new HashMap();

        for (int i = 0; i < genres.length; i++) {
            if (genresMap.containsKey(genres[i])) genresMap.put(genres[i], genresMap.get(genres[i]) + plays[i]); 
            else genresMap.put(genres[i], plays[i]);
        }

        List<String> genresList = new ArrayList<>(genresMap.keySet());
        Collections.sort(genresList, (o1, o2) -> (genresMap.get(o2).compareTo(genresMap.get(o1))));

        List<Integer> answerList = new ArrayList<>();
        genresList.forEach(li -> {
            HashMap<Integer, Integer> playsMap = new HashMap<>();
            for (int i = 0; i < genres.length; i++) {
                if (li.equals(genres[i])) playsMap.put(i, plays[i]);
                
            }

            List<Integer> playsList = new ArrayList<>(playsMap.keySet());
            Collections.sort(playsList, (o1, o2) -> (playsMap.get(o2).compareTo(playsMap.get(o1))));

            answerList.add(playsList.get(0));
            if (playsList.size() != 1) answerList.add(playsList.get(1));
            
        });
        
        answer = new int[answerList.size()];

        for (int idx = 0; idx < answerList.size(); idx++) 
            answer[idx] = answerList.get(idx);
        
        return answer;
    }
}