import java.util.*;
class Solution {
    public int solution(int[] stones, int k) {
        
        int left = 0;
        int right = 200000000;
        
        while(left<=right){
            int mid = (left + right) / 2;
            
            if(isProper(stones, mid, k)){
                left = mid + 1;
            }
            else{
               right = mid - 1;
            }
        }
        
        return left-1;
        
    }
    
    public boolean isProper(int []stones, int distance, int k){
        
        int maxLength = 0;
        int seq = 0;
        for(int i=0;i<stones.length;i++){
            
            if(stones[i]-distance<0){
                seq++;
                maxLength = Math.max(maxLength, seq);
                continue;
            }
            else{
                seq = 0;
            }
        }
        
        if(maxLength>=k){
            return false;
        }
        
        return true;
    }
}