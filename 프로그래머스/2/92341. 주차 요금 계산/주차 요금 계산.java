import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        final int defaultTime = fees[0];
        final int defaultFee = fees[1];
        final int unitTime = fees[2];
        final int unitFee = fees[3];

        Map<String, Integer> inMap = new HashMap<>();
        Map<String, Integer> timeMap = new TreeMap<>();

        // 입차, 출차 기록 처리
        for (String record : records) {
            String[] parts = record.split(" ");
            String timeStr = parts[0];
            String carNumber = parts[1];
            String status = parts[2];

            int time = Integer.parseInt(timeStr.substring(0, 2)) * 60 + Integer.parseInt(timeStr.substring(3));

            if (status.equals("IN")) {
                inMap.put(carNumber, time);
            } else { // "OUT"
                timeMap.put(carNumber, timeMap.getOrDefault(carNumber, 0) + time - inMap.remove(carNumber));
            }
        }

        // 출차되지 않은 차량 처리
        inMap.forEach((carNumber, time) -> timeMap.put(carNumber, timeMap.getOrDefault(carNumber, 0) + 1439 - time));

        return timeMap.entrySet().stream()
                .mapToInt(entry -> {
                    String carNumber = entry.getKey();
                    int totalTime = entry.getValue();
                    // 주차 요금 계산
                    return calculateFee(totalTime, defaultTime, defaultFee, unitTime, unitFee);
                })
                .toArray();
    }

    private int calculateFee(int totalTime, int defaultTime, int defaultFee, int unitTime, int unitFee) {
        if (totalTime <= defaultTime) {
            return defaultFee;
        } else {
            return defaultFee + (int) Math.ceil((double) (totalTime - defaultTime) / unitTime) * unitFee;
        }
    }
}
