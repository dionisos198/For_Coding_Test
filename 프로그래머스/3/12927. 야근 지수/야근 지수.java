import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i=0;i<works.length;i++){
            pq.add(works[i]);
        }
        
        for(int i=0;i<n;i++){
            int leftTime = pq.poll();
            if(leftTime == 0){
                return 0;
            }
            leftTime -= 1;
            pq.add(leftTime);
        }
        
        long answer = 0;
        while(!pq.isEmpty()){
            int cur = pq.poll();
            answer += (cur * cur);
        }
        
        return answer;
    }
}