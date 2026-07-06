import java.util.*;
class Solution {
    
    int dp[];
    int store;
    Queue<int[]> queue = new LinkedList<>();
    public int solution(int storey) {
        long answer = 0;
        while (storey > 0) {
            long r = storey % 10;
            storey /= 10;
            if (r > 5) {                 // 올림이 이득
                answer += 10 - r;
                storey += 1;
            } else if (r < 5) {          // 내림
                answer += r;
            } else {                     // r == 5, 윗자리 보고 결정
                answer += 5;
                if (storey % 10 >= 5) storey += 1;
            }
        }
        return (int) answer;
    }
    
    public void BFS(int start){
        
        queue.add(new int[]{start,0});
        
        while(!queue.isEmpty()){
            
            int cur[] = queue.poll();
            
            if(dp[cur[0]] < cur[1]){
                continue;
            }
            
            dp[cur[0]] = cur[1];
            
            int k = 0;
            while(true){
                int value = (int)Math.pow(10,k);
                if(cur[0]-value<0 && cur[0]+value> store*2){
                    break;
                }
                
                if(cur[0]-value>=0 && (dp[cur[0]-value] == Integer.MAX_VALUE || dp[cur[0]-value] >cur[1]+1 )){
                    dp[cur[0]-value] = cur[1]+1;
                    queue.add(new int[]{cur[0]-value,cur[1]+1});
                }
                   
                if(cur[0]+value<=store*2 && (dp[cur[0]+value] == Integer.MAX_VALUE || dp[cur[0]+value] >cur[1]+1 )){
                    dp[cur[0]+value] = cur[1]+1;
                    queue.add(new int[]{cur[0]+value,cur[1]+1});
                }
                
                k+=1;
                
            }
            
        }
        
        
        
    }
}