import java.util.*;
import java.util.regex.*;

class Solution {
    public int solution(String word, String[] pages) {
        int n = pages.length;
        String[] urls = new String[n];
        int[] basicScores = new int[n];
        int[] externalLinks = new int[n];
        List<String>[] outLinks = new List[n];
        Map<String, Integer> urlToIndex = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            outLinks[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < n; i++) {
            String page = pages[i];
            
            Pattern urlPattern = Pattern.compile("<meta property=\"og:url\" content=\"([^\"]+)\"");
            Matcher urlMatcher = urlPattern.matcher(page);
            if (urlMatcher.find()) {
                urls[i] = urlMatcher.group(1);
                urlToIndex.put(urls[i], i);
            }
            
            Pattern linkPattern = Pattern.compile("<a href=\"([^\"]+)\"");
            Matcher linkMatcher = linkPattern.matcher(page);
            while (linkMatcher.find()) {
                String linkUrl = linkMatcher.group(1);
                outLinks[i].add(linkUrl);
                externalLinks[i]++;
            }
            
            String bodyContent = extractBodyContent(page);
            basicScores[i] = countWordOccurrences(bodyContent, word);
        }
        
        double[] linkScores = new double[n];
        for (int i = 0; i < n; i++) {
            for (String outLink : outLinks[i]) {
                Integer targetIndex = urlToIndex.get(outLink);
                if (targetIndex != null && externalLinks[i] > 0) {
                    linkScores[targetIndex] += (double) basicScores[i] / externalLinks[i];
                }
            }
        }
        
        double maxScore = -1;
        int result = 0;
        for (int i = 0; i < n; i++) {
            double matchingScore = basicScores[i] + linkScores[i];
            if (matchingScore > maxScore) {
                maxScore = matchingScore;
                result = i;
            }
        }
        
        return result;
    }
    
    private String extractBodyContent(String html) {
        Pattern bodyPattern = Pattern.compile("<body[^>]*>(.*?)</body>", Pattern.DOTALL);
        Matcher matcher = bodyPattern.matcher(html);
        if (matcher.find()) {
            String bodyContent = matcher.group(1);
            bodyContent = bodyContent.replaceAll("<[^>]*>", "");
            return bodyContent;
        }
        return "";
    }
    
    private int countWordOccurrences(String text, String word) {
        String[] words = text.split("[^a-zA-Z]+");
        int count = 0;
        for (String w : words) {
            if (w.equalsIgnoreCase(word)) {
                count++;
            }
        }
        return count;
    }
}