import java.util.*;

public class Solution {
    public int solution(int n) {
        int ans = 0;
        while(n/10>0){
            ans += n%10;
            n = n/10;
        }
        
        ans+=n;
        return ans;
    }
}