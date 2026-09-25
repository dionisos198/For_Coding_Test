import java.util.*;
class Solution {
    int answer = 0;
    public int solution(int n, int[][] q, int[] ans) {
        BT(1, n, new ArrayList<>(), q,ans);
        return answer;
    }
    
    public void BT(int start,int n,List<Integer> list, int [][]q, int []ans){
        
        if(list.size()==5){
            if(isAllMatch(list,q,ans)) answer++;
            return;
        }
        
        for(int i=start;i<=n;i++){
            list.add(i);
            BT(i+1,n,list,q,ans);
            list.remove(list.size()-1);
        }
    }
    
    public boolean isAllMatch(List<Integer> list, int[][]q, int []ans){
        
        for(int i=0;i<q.length;i++){
            
            int count = 0;
            for(int j=0;j<5;j++){
                if(list.contains(q[i][j])){
                    count++;
                }
            }
            
            if(ans[i]!=count){
                return false;
            }
        }
        
        return true;
        
    }
}