import java.util.*;
class Solution {
    int answer = 0;
    public int solution(int n, int[][] q, int[] ans) {
        
        BT(new ArrayList<>(), q, ans, 1, n);
        return answer;
    }
    
    public void BT(List<Integer> hubo, int [][]q, int []ans, int current, int n){
        
        if(hubo.size()==5){
            if(checkAnswer(hubo, q,ans)){
                answer++;
            }
            return;
        }
        
        for(int i=current;i<=n;i++){
            hubo.add(i);
            BT(hubo, q, ans, i+1, n);
            hubo.remove(hubo.size()-1);
        }
        
    }
    
    private boolean checkAnswer(List<Integer> hubo, int [][]q, int []ans){
        
        for(int i=0;i<q.length;i++){
            int count = 0;
            for(int j=0;j<5;j++){
                if(hubo.contains(q[i][j])){
                    count++;
                }
            }
            
            if(count != ans[i]) return false;
        }
        
        
        return true;
    }
}