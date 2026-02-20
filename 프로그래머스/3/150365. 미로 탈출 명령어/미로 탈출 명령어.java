import java.util.*;
class Solution {
    
    int dy[]={1,0,0,-1};
    int dx[]={0,-1,1,0};
    String dir[]={"d","l","r","u"};
    String answer = "";
    String realAnswer = "";
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        
        if(getDistance(x,y,r,c)>k || getDistance(x,y,r,c)%2!=k%2){
            return "impossible";
        }
        
        BT(x,y,r,c,k,n,m);
        
        if(realAnswer.equals("")){
            return "impossible";
        }
        return realAnswer;
        
        
    }
    

    public int getDistance(int x1, int y1, int x2, int y2){
        return Math.abs(x1-x2)+Math.abs(y1-y2);
    }
    
    public void BT(int currentY, int currentX ,int destY, int destX, int k, int n, int m){
        
        // System.out.println("cy: "+currentY+"cx: "+currentX+"size: "+answer.length()+"ans: "+answer);
        if(!realAnswer.equals("")){
            return;
        }
        if(currentY == destY && currentX ==destX && answer.length()==k){
            realAnswer = answer;
            return;
        }
        else if(answer.length()>=k){
            return;
        }
        if(k-answer.length()<Math.abs(currentY-destY)+Math.abs(currentX-destX)){
            return;
        }
        
        for(int i=0;i<4;i++){
            int nextY = currentY + dy[i];
            int nextX = currentX + dx[i];
            
            if(nextY>=1 && nextY<=n && nextX>=1 && nextX<=m){
                answer+=dir[i];
                BT(nextY, nextX, destY, destX , k,n,m);
                answer= answer.substring(0,answer.length()-1);
            }
        }
        
        
    }
}