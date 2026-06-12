import java.util.*;
class Solution {
    public String solution(int n, int t, int m, int p) {
        
        StringBuilder sb = new StringBuilder();
        
        int num = 0;
        int seq = 1;
        
        while(true){
   
            
            String sNum = Integer.toString(num,n);
            
            for(int i=0;i<sNum.length();i++){
                if((m!=p && seq%m==p) || (m==p && seq%m==0)){
                    sb.append(sNum.charAt(i));
                }
                seq++;
                if(sb.length()>=t){
                    break;
                }
            }
            
            if(sb.length()>=t){
                break;
            }
            
            num++;
            
        }
        
        return sb.toString().toUpperCase();
    }
}