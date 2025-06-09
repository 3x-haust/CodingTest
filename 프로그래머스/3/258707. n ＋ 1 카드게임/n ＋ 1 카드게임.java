import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        int target = n + 1;
        
        Set<Integer> initialHand = new HashSet<>();
        for (int i = 0; i < n / 3; i++) {
            initialHand.add(cards[i]);
        }
        
        Set<Integer> drawnCards = new HashSet<>();
        int round = 1;
        int currentCoins = coin;
        
        for (int i = n / 3; i < n; i += 2) {
            Set<Integer> newCards = new HashSet<>();
            if (i < n) newCards.add(cards[i]);
            if (i + 1 < n) newCards.add(cards[i + 1]);
            
            drawnCards.addAll(newCards);
            
            boolean found = false;
            
            for (int card1 : new HashSet<>(initialHand)) {
                if (initialHand.contains(target - card1) && card1 != target - card1) {
                    initialHand.remove(card1);
                    initialHand.remove(target - card1);
                    found = true;
                    break;
                }
            }
            
            if (!found && currentCoins >= 1) {
                for (int card1 : new HashSet<>(initialHand)) {
                    if (drawnCards.contains(target - card1)) {
                        initialHand.remove(card1);
                        drawnCards.remove(target - card1);
                        currentCoins--;
                        found = true;
                        break;
                    }
                }
            }
            
            if (!found && currentCoins >= 2) {
                for (int card1 : new HashSet<>(drawnCards)) {
                    if (drawnCards.contains(target - card1) && card1 != target - card1) {
                        drawnCards.remove(card1);
                        drawnCards.remove(target - card1);
                        currentCoins -= 2;
                        found = true;
                        break;
                    }
                }
            }
            
            if (!found) break;
            
            round++;
        }
        
        return round;
    }
}