import java.util.*;
class Solution {
    public int solution(int[] players, int m, int k) {
        
        int currentServerCount = 0;
        int reservedDelete[] = new int[25];
        Arrays.fill(reservedDelete,0);
        int answer = 0;
        
        for(int i=0;i<players.length;i++){
            
            
            currentServerCount -= reservedDelete[i];
            int needServerCount = players[i] / m;
          //  System.out.println("필요 서버 수: "+ needServerCount);
            
            if(needServerCount > currentServerCount){
                
                int needCount = needServerCount - currentServerCount;
                if(i+k<=24){
                    reservedDelete[i+k] += needCount;
                }
                answer += needCount;
                currentServerCount = needServerCount;
            }
        
         //   System.out.println("현재 서버 수: "+currentServerCount);
            
        }
        
        return answer;
    }
}