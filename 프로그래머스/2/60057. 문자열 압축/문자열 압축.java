class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;

        if(s.length() == 1) return 1;

        for (int i = 1; i <= s.length() / 2; i++) {
            StringBuffer sb = new StringBuffer();
            String prevStr = "";                   
            int count = 1;                        

            for (int j = 0; j <= s.length() - i; j += i) {
                String curStr = s.substring(j, j + i);
                
                if (prevStr.equals(curStr)) {
                    count++;
                    continue;
                } else if (count > 1) {
                    sb.append(count + prevStr); 
                    count = 1;
                } else sb.append(prevStr);
                

                prevStr = curStr;
            }

            sb.append(count > 1 ? count + prevStr : prevStr);

            if (s.length() % i != 0) 
                sb.append(s.substring(s.length() - s.length() % i, s.length()));
            
            answer = Math.min(answer, sb.length());
            sb = new StringBuffer();
        }

        return answer;
    }
}