import java.util.*;
class Solution {
    
    class PathRecord{
        int beforeY;
        int beforeX;
        int afterY;
        int afterX;
        public PathRecord(int beforeY, int beforeX, int afterY, int afterX){
            
            if(beforeY< afterY || (beforeY==afterY && beforeX < afterX)){
                
              this.beforeY = beforeY;
              this.beforeX = beforeX;
              this.afterY = afterY;
              this.afterX = afterX;  
            }
            else{
                this.beforeY = afterY;
                this.beforeX = afterX;
                this.afterY = beforeY;
                this.afterX = beforeX;
            }
        }
        
        @Override 
        public boolean equals(Object o){
            PathRecord pr = (PathRecord) o;
            
            return (this.beforeY == pr.beforeY && this.beforeX == pr.beforeX && 
                   this.afterY == pr.afterY && this.afterX == pr.afterX);
        }
        
        @Override
        public int hashCode(){
            return Objects.hash(this.beforeY, this.beforeX, this.afterY, this.afterX);
        }
    }
    HashSet<PathRecord> set = new HashSet<>();
    
    public int solution(String dirs) {
        int currentY = 0;
        int currentX = 0;
        
        for(int i=0;i<dirs.length();i++){
            char dir = dirs.charAt(i);
            
            int next[] = getNext(dir, currentY, currentX);
            
            if(next[0]>=-5 && next[0]<=5 && next[1]>=-5 && next[1]<=5){
                set.add(new PathRecord(currentY,currentX,next[0],next[1]));
                currentY = next[0];
                currentX = next[1];
            }
        }
        
        return set.size();
    }
    
    public int[] getNext(char dir, int currentY, int currentX){
        if(dir =='U'){
            return new int[]{currentY+1,currentX};
        }
        else if(dir == 'L'){
            return new int[]{currentY,currentX-1};
        }
        else if(dir == 'R'){
            return new int[]{currentY,currentX+1};
        }
        
        return new int[]{currentY-1,currentX};
    }
}