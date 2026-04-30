import java.util.*;
class Solution {
    
    class Node implements Comparable<Node>{
        int dest;
        int cost;
        
        public Node(int dest, int cost){
            this.dest = dest;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node n){
            return this.cost - n.cost;
        }
    }
    
    List<Node> graph[];
    public int solution(int N, int[][] road, int K) {

        graph = new List[N+1];
        for(int i=1;i<=N;i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0;i<road.length;i++){
            graph[road[i][0]].add(new Node(road[i][1],road[i][2]));
            graph[road[i][1]].add(new Node(road[i][0],road[i][2]));
        }
        
        return djkstra(1,N,K);
    }
    
    public int djkstra(int start, int N, int K){
        
        boolean isVisited[] = new boolean[N+1];
        int dist[] = new int[N+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[start] = 0;
        
        pq.add(new Node(start,0));
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            
            if(isVisited[cur.dest]) continue;
            isVisited[cur.dest] = true;
            
            for(Node next: graph[cur.dest]){
                if(dist[next.dest] > dist[cur.dest] + next.cost){
                    dist[next.dest] = dist[cur.dest] + next.cost;
                    pq.add(new Node(next.dest, dist[next.dest]));
                }
            }
        }
        
        int ans = 0;
        for(int i=1;i<=N;i++){
      //      System.out.println(dist[i]);
            if(dist[i]<=K){
                ans++;
            }
        }
        
        return ans;
        
    }
}