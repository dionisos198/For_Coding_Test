import java.util.*;
class Solution {
    public int solution(int[] players, int m, int k) {
        
        int currentServer = 0;
        Map<Integer, Integer> deleteServerMap = new HashMap<>();
        
        int answer = 0;
        for(int time = 0; time< players.length; time++){
            int currentPlayerNum = players[time];
       //     System.out.println("게임 이용자의 수: "+currentPlayerNum);
            // 서버 제거 
            if(deleteServerMap.containsKey(time)){
                currentServer -= deleteServerMap.get(time);
            }
            //서버 보충 
            int minumumNeedServer= currentPlayerNum / m;
            if(currentServer < minumumNeedServer){
                answer += minumumNeedServer - currentServer;
                deleteServerMap.put(time+k, minumumNeedServer - currentServer);
                currentServer = minumumNeedServer;
            }
      //      System.out.println("현재 서버 수: "+currentServer);
        }
        
        return answer;
        
    }
}