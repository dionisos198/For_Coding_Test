import java.util.*;
class Solution {
    public int solution(int n, int k) {
        
        String num = makeKDigit(n, k);
        
        int zeroIndex = -1;
        int count = 0;
        
        for(int i=0;i<num.length();i++){
            if(num.charAt(i)=='0'){
                String decideNum = num.substring(zeroIndex+1,i);
              //  System.out.println(decideNum);
            
                if(!decideNum.equals("") && isSosu(Long.valueOf(decideNum))){
                    count++;
                }
                zeroIndex = i;
            }
            
            if(i==num.length()-1 && num.charAt(i)!='0'){
                String decideNum = num.substring(zeroIndex+1, i+1);
             //   System.out.println(decideNum);
                if(!decideNum.equals("") && isSosu(Long.valueOf(decideNum))){
                    count++;
                }
            }
        }
        
        
        return count;
    }
    
    public String makeKDigit(int n, int k){
        
        StringBuilder sb = new StringBuilder();
        
        while(n/k!=0){
            
            sb.insert(0, n%k);
            n = n/k;
        }
        
        sb.insert(0,n%k);
        
        return sb.toString();
        
        
    }
    
    
    public boolean isSosu(long n){
        
        if(n==0 || n==1) return false;
        
        for(int i=2;i<=Math.sqrt(n);i++){
            if(n%i==0){
                return false;
            }
        }
        
        return true;
    }
}