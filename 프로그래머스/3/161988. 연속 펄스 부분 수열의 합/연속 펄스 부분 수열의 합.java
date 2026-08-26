import java.util.*;
class Solution {
    public long solution(int[] sequence) {
        
        long ans = 0;
        long sum = 0;
        int start = -1;
        int startIndex = 0;
        int endIndex = 0;
        int tmp[] = new int[sequence.length];
        
        while(true){
            if(endIndex == sequence.length){
                break;
            }
            
            if(startIndex==endIndex){
                tmp[endIndex] = sequence[endIndex] * start;
                sum+=tmp[endIndex];
                endIndex++;
                start *= -1;
            }
            else{
                if(sum >= 0){                
                    tmp[endIndex] = sequence[endIndex] * start;
                    sum+=tmp[endIndex];
                    endIndex++;
                    start*=-1;
                }
                else{
                    sum -= tmp[startIndex];
                    startIndex++;
                }
            }
            
            ans = Math.max(sum,ans);
        }
        
        
        start = 1;
        startIndex = 0;
        endIndex = 0;
        sum = 0;                                   
        tmp = new int[sequence.length];
        
        while(true){
            if(endIndex == sequence.length){         
                break;
            }
            
            if(startIndex==endIndex){
                tmp[endIndex] = sequence[endIndex] * start;
                sum+=tmp[endIndex];
                endIndex++;
                start *= -1;
            }
            else{
                if(sum >= 0){            
                    tmp[endIndex] = sequence[endIndex] * start;
                    sum+=tmp[endIndex];
                    endIndex++;
                    start*=-1;
                }
                else{
                    sum -= tmp[startIndex];
                    startIndex++;
                }
            }
            
            ans = Math.max(sum,ans);
        }
        
        return ans;
    }
}