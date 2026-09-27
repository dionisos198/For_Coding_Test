import java.util.*;
class Solution {
    int answer = Integer.MAX_VALUE;
    public int solution(int[][] cost, int[][] hint) {
        
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<cost.length;i++){
            map.put(i,0);
        }
        
        BT(0, cost, hint, map, 0);
        
        return answer;
    }
    
    public void BT(int currentStage, int [][]cost, int [][]hint, 
                   Map<Integer, Integer> map, int sum){
        
        if(currentStage == cost.length){
            
            answer = Math.min(sum, answer);
            return;
        }
        
        int idx = Math.min(map.get(currentStage), cost[currentStage].length - 1);
        int price = cost[currentStage][idx];
        // 힌트권 구매 
        
        if(currentStage<hint.length){
            int hintPrice = hint[currentStage][0];
            for(int i=1;i<hint[currentStage].length;i++){
              map.put(hint[currentStage][i]-1,map.get(hint[currentStage][i]-1)+1);
            }
            
            BT(currentStage+1,cost,hint, map, sum + price + hintPrice);
            
            for(int i=1;i<hint[currentStage].length;i++){
              map.put(hint[currentStage][i]-1,map.get(hint[currentStage][i]-1)-1);
            }  
            
        }
        
        
        BT(currentStage+1, cost, hint, map, sum + price);
        
        
        
    }
}