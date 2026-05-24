import java.util.*;
class Solution {
    int solution(int[][] land) {
        
        int dp[][] = new int[land.length][4];
        dp[0][0] = land[0][0];
        dp[0][1] = land[0][1];
        dp[0][2] = land[0][2];
        dp[0][3] = land[0][3];
        
        for(int i=1;i<land.length;i++){
            for(int j=0;j<=3;j++){
                int max = -1;
                for(int k=0;k<=3;k++){
                    if(j==k){
                        continue;
                    }
                    max = Math.max(max, dp[i-1][k]);
                }
                dp[i][j] = land[i][j] + max;
            }
        }
        
        int ans = -1;
        for(int j=0;j<=3;j++){
            ans = Math.max(ans, dp[land.length-1][j]);
        }
        
        return ans; 
    }
}