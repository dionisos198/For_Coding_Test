import java.util.*;
class Solution {
    
    class Info{
        int areaNum;
        int totalCount;
        
        public Info(int areaNum, int totalCount){
            this.areaNum = areaNum;
            this.totalCount = totalCount;
        }
    }
    
    Info info[][];
    
    
    public int solution(int[][] land) {
        info = new Info[land.length][land[0].length];
        
        for(int i=0;i<land.length;i++){
            for(int j=0;j<land[0].length;j++){
                info[i][j] = new Info(0,0);
            }
        }
        int areaNum = 1;
        
        for(int i=0;i<land.length;i++){
            for(int j=0;j<land[0].length;j++){
                if(land[i][j]==0) continue;
                if(info[i][j].areaNum==0){
                    //hello again
                    bfs(i,j,areaNum, land);
                    areaNum++;
                }
            }
        }
        
    //    print();
        
        int answer = 0;
        for(int j=0;j<land[0].length;j++){
            
            Set<Integer> areaNumSet = new HashSet<>();
            int tmp = 0;
            for(int i=0;i<land.length;i++){
                
                if(land[i][j]==1 && !areaNumSet.contains(info[i][j].areaNum)){
                    areaNumSet.add(info[i][j].areaNum);
                    tmp+=info[i][j].totalCount;
                }
            }
            
        //    System.out.println(tmp);
            answer = Math.max(tmp,answer);
        }
        
        return answer;
    }
    
    int dy[]={-1,1,0,0};
    int dx[]={0,0,-1,1};
    
    public void bfs(int i, int j, int areaNum, int [][]land){
        
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{i,j});
        info[i][j].areaNum = areaNum;
        int sum = 0;
        
        while(!queue.isEmpty()){
            
            int cur[] = queue.poll();
            sum++;
            
            for(int k=0;k<4;k++){
                
                int nextY = cur[0] + dy[k];
                int nextX = cur[1] + dx[k];
                
                if(nextY>=0 && nextY<info.length &&nextX>=0 && nextX<info[0].length && 
                   land[nextY][nextX]==1 && info[nextY][nextX].areaNum == 0){
                    
                    info[nextY][nextX].areaNum = areaNum;
                    queue.add(new int[]{nextY,nextX});
                }
                
                
            }
            
        }
        
        queue.add(new int[]{i,j});
        info[i][j].totalCount = sum;
        
        while(!queue.isEmpty()){
            
            int cur[] = queue.poll();
            
            for(int k=0;k<4;k++){
                
                int nextY = cur[0] + dy[k];
                int nextX = cur[1] + dx[k];
                
                if(nextY>=0 && nextY<info.length &&nextX>=0 && nextX<info[0].length && 
                   land[nextY][nextX]==1 && info[nextY][nextX].totalCount != sum){
                    
                    info[nextY][nextX].totalCount = sum;
                    queue.add(new int[]{nextY,nextX});
                }
                
                
            }
            
        }
    }
    
    public void print(){
        
        for(int i=0;i<info.length;i++){
            for(int j=0;j<info[0].length;j++){
                System.out.print(info[i][j].totalCount+" ");
            }
            System.out.println();
        }
    }
}