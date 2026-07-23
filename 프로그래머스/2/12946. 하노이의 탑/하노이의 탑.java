import java.util.*;
class Solution {
    List<int[]> list = new ArrayList<>();
    
    public int[][] solution(int n) {
        
        recursive(1,2,3,n);
        
        int answer[][] = new int[list.size()][2];
        
        for(int i=0;i<list.size();i++){
            int []l = list.get(i);
            
            answer[i][0] = l[0];
            answer[i][1] = l[1];
        }
        
        
        return answer;
    }
    
    public void recursive(int start, int tmp, int end, int n){
        
        if(n==0){
            return;
        }
        
        recursive(start,end,tmp,n-1);
        list.add(new int[]{start,end});
        recursive(tmp,start,end,n-1);
        
        
    }
}