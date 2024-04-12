class Solution {
    public int[] solution(String[] park, String[] routes) {
        int sx = 0;
        int sy = 0;
        
        char[][] arr = new char[park.length][park[0].length()];
        
        for(int i = 0; i < park.length; i++){
            arr[i] = park[i].toCharArray();
            
            if(park[i].contains("S")){
                sy = i;
                sx = park[i].indexOf("S");
            }
        }
    
        for(String st : routes){
            String way = st.split(" ")[0];
            int len = Integer.parseInt(st.split(" ")[1]);
            
            int nx = sx;
            int ny = sy;
            
            for(int i = 0; i < len; i++){
                switch(way) {
                    case "E": nx++; break;
                    case "W": nx--; break;
                    case "S": ny++; break;
                    case "N": ny--; break;
                        
                }
                
                if(nx >= 0 && ny >= 0 && ny < arr.length && nx < arr[0].length){
                    if(arr[ny][nx] == 'X'){
                        break;
                    }
                    // 범위 안 && 장애물 x
                    if(i == len - 1){
                        sx = nx;
                        sy = ny;
                    }
                }
            }
        }       
        
        int[] answer = {sy, sx};
        
        return answer;
    }
}