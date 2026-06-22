import java.util.*;
class Solution {
    char map[][];
    public int solution(int m, int n, String[] board) {
        
        map = new char[m][board[0].length()];
        init(board,m);
        int answer = 0;
 
        while(true){
            
            Set<Position> deleteSet = getDeleteSet();
            
            if(deleteSet.size()==0){
                break;
            }
            
            answer += deleteSet.size();
            
            makeEmpty(deleteSet);
            
            falling();
        }
        
        return answer;
        
        
        
    }
    
    public void init(String []board, int m){
        
        for(int i=0;i<m;i++){
            for(int j=0;j<board[0].length();j++){
                map[i][j] = board[i].charAt(j);
            }
        }
    }
    
    public void print(){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    public class Position{
        
        int y;
        int x;
        
        public Position(int y, int x){
            this.y = y;
            this.x = x;
        }
        
        @Override
        public boolean equals(Object o){
            
            Position p = (Position) o;
            
            return this.y==p.y && this.x==p.x;
        }
        
        @Override
        public int hashCode(){
            return Objects.hash(y,x);
        }
    }
    
    public Set<Position> getDeleteSet(){
        
        Set<Position> set = new HashSet<>();
        
        for(int i=0;i<map.length-1;i++){
            for(int j=0;j<map[0].length-1;j++){
                
                if(map[i][j]==' '){
                    continue;
                }
                
                if(map[i][j]==map[i+1][j] && 
                   map[i][j]==map[i][j+1] && 
                   map[i][j]==map[i+1][j+1]){
                    
                
                  set.add(new Position(i,j));
                  set.add(new Position(i+1,j));
                  set.add(new Position(i+1,j+1));
                  set.add(new Position(i,j+1));
                    
                }
            }
        }
        
        return set;
    }
    
    public void makeEmpty(Set<Position> set){
        
        for(Position p: set){
            map[p.y][p.x] = ' ';
        }
    }
    
    public void falling(){
        
        for(int i=map.length-1;i>=1;i--){
            
            for(int j=0;j<map[0].length;j++){
                
                if(map[i][j]==' '){
                    for(int k=i-1;k>=0;k--){
                        if(map[k][j]!=' '){
                            map[i][j]=map[k][j];
                            map[k][j]=' ';
                            break;
                        }
                        
                    }
                }
            }
        }
    }
}