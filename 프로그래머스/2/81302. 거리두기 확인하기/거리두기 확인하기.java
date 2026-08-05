import java.util.*;
class Solution {
    public int[] solution(String[][] places) {
        
        List<Integer> answerList = new ArrayList<>();
        
        for(String []waitingRoom: places){
            char map[][] = new char[waitingRoom.length][waitingRoom[0].length()];
            init(map,waitingRoom);
                        
            if(checkDistance(map)){
                answerList.add(1);
            }
            else{
                answerList.add(0);
            }
            
            
        }
        
        return answerList.stream().mapToInt(i->i).toArray();
    }
    
    private boolean checkDistance(char map[][]){
        
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                
                if(map[i][j]=='P'){
                    checkInDistance = false;
                    checkByDFS(i,j,0,false, i,j,map);
                    if(checkInDistance){
                 //       System.out.println("i: "+i+"j: "+j);
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
    
    int dy[] = new int[]{-1,1,0,0};
    int dx[] = new int[]{0,0,-1,1};
    
    boolean checkInDistance = false;
    
    private void checkByDFS(int i, int j, int depth,boolean hasPartition,
                               int sI,int sJ,char map[][]){
        
        if(depth>=2){
            return;
        }
        
        for(int k=0;k<4;k++){
            int newI = i + dy[k];
            int newJ = j + dx[k];
            
            if(newI>=0 && newI<map.length && newJ>=0 && newJ<map[0].length){
                //1. 사람일 때 
                if(map[newI][newJ]=='P' && !(newI==sI &&newJ==sJ)){
                    if(!hasPartition){
                   //     System.out.println("newI: "+newI+"newJ: "+newJ);
                        checkInDistance = true;
                        return;
                    }
                }
                
                //2. partition 일 때
                boolean changed = false;
                boolean beforePartition = hasPartition;
                if(map[newI][newJ]=='X'){
                    changed = true;
                    hasPartition = true;
                }
                
                checkByDFS(newI,newJ,depth+1,hasPartition, sI,sJ,map);
                if(changed){
                    hasPartition = beforePartition;
                }
            }
        }
    }
    
    private void init(char map[][], String []waitingRoom){
        
        for(int i=0;i<waitingRoom.length;i++){
            for(int j=0;j<waitingRoom[0].length();j++){
                map[i][j] = waitingRoom[i].charAt(j);
            }
        }
    }
}