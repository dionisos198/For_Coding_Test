import java.util.*;
class Solution {
    public int solution(int x, int y, int n) {
        int dp[] = new int[y+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[x] = 0; 
        for(int i =x+1;i<=y;i++){
            int min = Integer.MAX_VALUE;
            if(i-n>=0 && dp[i-n]!=Integer.MAX_VALUE){
                min = Math.min(min, dp[i-n]+1);
            }
            
            if(i%2 ==0 && dp[i/2]!=Integer.MAX_VALUE){
                min = Math.min(min,dp[i/2]+1);
            }
            
            if(i%3 == 0 && dp[i/3]!=Integer.MAX_VALUE){
                min = Math.min(min, dp[i/3]+1);
            }
            
            dp[i] = min;
        }
        
        if(dp[y]==Integer.MAX_VALUE){
            return -1;
        }
        
        return dp[y];
    }
}