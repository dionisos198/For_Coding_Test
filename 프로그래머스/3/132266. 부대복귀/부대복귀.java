
import java.util.*;
class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<Integer> graph[] = new List[n+1];
        
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0;i<roads.length;i++){
            graph[roads[i][0]].add(roads[i][1]);
            graph[roads[i][1]].add(roads[i][0]);
        }
        
        int dist[] = new int[n+1];
        Arrays.fill(dist,-1);
        dist[destination] = 0;
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(destination);
        
        while(!queue.isEmpty()){
            int cur = queue.poll();
            
            for(int next: graph[cur]){
                if(dist[next]==-1){
                    dist[next] = dist[cur]+1;
                    queue.add(next);
                }
            }
        }
        
        int answer[] = new int[sources.length];
        for(int i=0;i<sources.length;i++){
            
            answer[i] = dist[sources[i]];
        }
        
        return answer;
    }
}