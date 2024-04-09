class Solution {
    public int solution(String[] order) {
        int answer = 0;
        for(String str : order){
            switch(str){
                case "iceamericano":
                case "americanoice":
                case "hotamericano":  
                case "americanohot": 
                case "americano":
                case "anything": 
                    answer += 4500;
                    break;
                case "icecafelatte":
                case "cafelatteice":
                case "hotcafelatte":
                case "cafelattehot": 
                case "cafelatte":
                    answer += 5000;
                    break;
            }
        }
        return answer;
    }
}