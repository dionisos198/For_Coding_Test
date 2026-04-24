import java.util.*;
class Solution {
    
    char map[][];
    boolean isVisited[][];
    public int[] solution(String[] maps) {
        map = new char[maps.length][maps[0].length()];
        
        for(int i =0;i<maps.length;i++){
            for(int j=0;j<maps[0].length();j++){
                map[i][j] = maps[i].charAt(j);
            }
        }
        
        isVisited= new boolean[maps.length][maps[0].length()];
        
        List<Integer> answerList = new ArrayList<>();        
        for(int i=0;i<maps.length;i++){
            for(int j=0;j<maps[0].length();j++){
                if(map[i][j]=='X') continue;
                if(map[i][j]>='0' && map[i][j]<='9' && !isVisited[i][j]){
                    answerList.add(BFS(i, j));
                }
            }
        }
        
        Collections.sort(answerList);
        
        if(answerList.size()==0){
            return new int[]{-1};
        }
        
        return answerList.stream().mapToInt(i->i).toArray();
    }
    
    int dy[] = new int[]{-1,1,0,0};
    int dx[] = new int[]{0,0,-1,1};
    
    
    public int BFS(int i, int j){
        Queue<int[]> queue = new LinkedList<>();
        
        queue.add(new int[]{i,j});
        isVisited[i][j] = true;
        int sum = 0;
        
        while(!queue.isEmpty()){
            int cur[] = queue.poll();
            
            sum += map[cur[0]][cur[1]]-'0';
            
            for(int k=0;k<4;k++){
                int nextY = cur[0] + dy[k];
                int nextX = cur[1] + dx[k];
                
                if(nextY>=0 && nextY<map.length && nextX>=0 && nextX<map[0].length&&
                   map[nextY][nextX]>='0' && map[nextY][nextX]<='9' &&
                   !isVisited[nextY][nextX]){
                    isVisited[nextY][nextX] = true;
                    
                    queue.add(new int[]{nextY,nextX});
                }
            }
            
            
        }
        
        return sum;
        
    }
}