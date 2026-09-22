import java.util.*;
class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        
        return findLevelByBinarySearch(diffs, times, limit);
    }
    
    public int findLevelByBinarySearch(int []diffs, int []times, long limit){
        
        int start = 1;
        int end = 100000;
        
        while(start<=end){
            int mid = (start + end ) / 2;
            
            if(calculateTimeByLevel(diffs, times,mid)<=limit){
                
                end = mid-1;
            }
            else{
                start = mid + 1;
            }
        }
        
        return end+1;
    }
    
    public long calculateTimeByLevel(int []diffs, int []times, int level){
        
        long totalTime = 0;
        for(int i=0;i<diffs.length;i++){
            
            if(level>= diffs[i]){
                
                totalTime += times[i];
             //   System.out.println(totalTime+" "+level);
                continue;
            }
            
            totalTime += (diffs[i]-level) * (times[i-1] + times[i]) + times[i];
         //   System.out.println(totalTime+" "+level);
            
        }
        
    //    System.out.println(totalTime);
        
        return totalTime;
        
    }
}