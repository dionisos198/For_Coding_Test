class Solution {
    public long solution(int a, int b) {
        
        long ans = 0;
        for(int i=Math.min(a,b);i<=Math.max(a,b);i++){
            ans+=i;
        }
        
        return ans;
    }
}