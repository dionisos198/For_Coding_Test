import java.util.*;
class Solution {
    int answer = Integer.MAX_VALUE;
    public int solution(int[][] cost, int[][] hint) {
   
        BT(0, cost, hint, 0, new int[cost.length]);
        
        return answer;
    }
    
    public void BT(int stage, int [][]cost, int [][]hint, int costPrice,int count[]){
        
        int usedHints = Math.min(count[stage], cost[stage].length - 1);
        costPrice += cost[stage][usedHints];
        
        if(stage == cost.length-1){
            answer = Math.min(answer, costPrice);
            return;
        }
        
        // 힌트 번들 구매
        for(int i=1;i<hint[stage].length;i++){
            count[hint[stage][i]-1]++;
        }
        BT(stage+1,cost,hint, costPrice+hint[stage][0],count);
        
        for(int i=1;i<hint[stage].length;i++){
            count[hint[stage][i]-1]--;
        }
        
        // 힌트번들 구매 X
        BT(stage+1,cost, hint, costPrice,count);
    }
}