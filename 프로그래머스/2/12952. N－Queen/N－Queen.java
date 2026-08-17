import java.util.*;
class Solution {
    
    int map[][];
    int ans = 0;
    public int solution(int n) {
        
        map = new int[n+1][n+1];
        
        BT(1,n);
        
        return ans;
        
        
    }
    
    public void BT(int seq, int n){
        
        if(seq == n+1){
            ans++;
            return;
        }
        
        for(int i=1;i<=n;i++){
            if(canGo(seq,i,n)){
                map[seq][i] = 1;
                BT(seq+1,n);
                map[seq][i] = 0;
            }
        }
    }
    
    public boolean canGo(int seq, int i, int n){
        int k = 1;
        while(true){
            
            if(seq+k>=1 && seq+k<=n && i+k>=1 && i+k<=n){
              if(map[seq+k][i+k]==1){
                return false;
              }
            }
            else{
                break;
            }
            
            k++;
        }
        
        k = 1;
        while(true){
            
            if(seq-k>=1 && seq-k<=n && i-k>=1 && i-k<=n){
              if(map[seq-k][i-k]==1){
                return false;
              }
            }
            else{
                break;
            }
            
            k++;
        }
        
        k = 1;
        while(true){
            
            if(seq+k>=1 && seq+k<=n && i-k>=1 && i-k<=n){
              if(map[seq+k][i-k]==1){
                return false;
              }
            }
            else{
                break;
            }
            
            k++;
        }
        
        k = 1;
        while(true){
            
            if(seq-k>=1 && seq-k<=n && i+k>=1 && i+k<=n){
              if(map[seq-k][i+k]==1){
                return false;
              }
            }
            else{
                break;
            }
            
            k++;
        }
        
        k = 1;
        while(true){
            
            if(seq+k>=1 && seq+k<=n){
              if(map[seq+k][i]==1){
                return false;
              }
            }
            else{
                break;
            }
            
            k++;
        }
        
        k = 1;
        while(true){
            
            if(seq-k>=1 && seq-k<=n){
              if(map[seq-k][i]==1){
                return false;
              }
            }
            else{
                break;
            }
            
            k++;
        }
        
        return true;
    }
}