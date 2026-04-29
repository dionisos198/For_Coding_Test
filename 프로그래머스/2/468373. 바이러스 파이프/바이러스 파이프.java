import java.util.*;
class Solution {
    
    List<Node> [] graph;
    class Node{
        int dest;
        int type;
        
        public Node(int dest, int type){
            this.dest = dest;
            this.type = type;
        }
    }
    
    int ans = 0;
    public int solution(int n, int infection, int[][] edges, int k) {
        graph = new List[n+1];
        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int i=0;i<edges.length;i++){
            graph[edges[i][0]].add(new Node(edges[i][1],edges[i][2]));
            graph[edges[i][1]].add(new Node(edges[i][0], edges[i][2]));
        }
        
        Set<Integer> infected = new HashSet<>();
        infected.add(infection);
        
        BT(infected, k, 0 , 4);
        
        return ans;
        
        
    }
    
    public void BT(Set<Integer> infected, int k, int count, int beforeType){
        
        if(count == k){
            ans = Math.max(ans, infected.size());
            return;
        }
        
        if(beforeType == 1){
            
            BT(BFS(infected, 2),k,count+1,2);
            BT(BFS(infected, 3),k,count+1,3);
        }
        else if(beforeType == 2){
            BT(BFS(infected, 1),k,count+1,1);
            BT(BFS(infected, 3),k,count+1,3);
        }
        else if(beforeType == 3){
            BT(BFS(infected, 1),k,count+1,1);
            BT(BFS(infected, 2),k,count+1,2);
        }
        else{
            BT(BFS(infected, 1),k,count+1,1);
            BT(BFS(infected, 2),k,count+1,2);
            BT(BFS(infected, 3),k,count+1,3);
        }
        
    }
    
    public Set<Integer> BFS(Set<Integer> infected, int type){
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> newInfected = new HashSet<>();
        
        for(int virus: infected){
            queue.add(virus);
        }
        
        while(!queue.isEmpty()){
            
            int current = queue.poll();
            for(Node next: graph[current]){
                if(next.type == type && !infected.contains(next.dest)
                   && !newInfected.contains(next.dest)){
                    
                    queue.add(next.dest);
                    newInfected.add(next.dest);
                }
            }
        }
        
        Set<Integer> sum = new HashSet<>();
        
        // System.out.println(infected);
        // System.out.println(newInfected);
        
        for(int virus: infected){
            sum.add(virus);
        }
        
        for(int virus: newInfected){
            sum.add(virus);
        }
        
        return sum;
    }
}