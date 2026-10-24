import java.util.*;
class Solution {
    public int solution(int[] players, int m, int k) {
        
        int increaseCount = 0;
        int increasedServer = 0;
        Map<Integer, Integer> removeServerCount = new HashMap<>();
        
        for(int i = 0; i < players.length; i++){
            int player = players[i];
            
            if(removeServerCount.containsKey(i)){
                increasedServer -= removeServerCount.get(i);
            }
            
            int needServerCount = player / m;
            if(increasedServer < needServerCount){
                increaseCount += needServerCount - increasedServer;
                removeServerCount.put(i+k, needServerCount - increasedServer);
                increasedServer = needServerCount;
            }
            
        }
        
        return increaseCount;
    }
}