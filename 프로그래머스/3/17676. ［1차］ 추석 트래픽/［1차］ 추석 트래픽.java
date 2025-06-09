import java.text.*;
import java.util.*;

class Solution {
    public int solution(String[] lines) throws ParseException {
        int answer = 0;
        ArrayList<Date[]> times = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        
        for (String line : lines) {
            String time = line.split(" ")[0] + " " + line.split(" ")[1];
            Date end_time = formatter.parse(time);

            double durationInSeconds = Double.parseDouble(line.split(" ")[2].replace("s", ""));
            long durationInMillis = (long)(durationInSeconds * 1000);

            Date start_time = new Date(end_time.getTime() - durationInMillis + 1);
            times.add(new Date[]{start_time, end_time});
        }

        for (Date[] base : times) {
            long startWindow = base[1].getTime();
            long endWindow = startWindow + 999;

            int count = 0;
            for (Date[] target : times) {
                long targetStart = target[0].getTime();
                long targetEnd = target[1].getTime();

                if (targetStart <= endWindow && targetEnd >= startWindow) {
                    count++;
                }
            }

            answer = Math.max(answer, count);
        }

        return answer;
    }
}
