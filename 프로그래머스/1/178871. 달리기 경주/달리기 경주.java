import java.util.Map;
import java.util.HashMap;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        Map<String, Integer> rankMap = new HashMap<>();
        
        for(int i = 0; i < players.length; i++) {
            rankMap.put(players[i], i);
        }

        for(String player : callings) {
            // 등수
            int ownRank = rankMap.get(player);
            // 먼저 가는 선수
            String beforePlayer = players[ownRank - 1];
            
            players[ownRank - 1] = player;
            players[ownRank] = beforePlayer;
            
            rankMap.put(player, ownRank - 1);
            rankMap.put(beforePlayer, ownRank);
        }
        
        
        return players;
    }
}