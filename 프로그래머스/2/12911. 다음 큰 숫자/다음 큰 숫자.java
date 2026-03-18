import java.util.*;
class Solution {
    public int solution(int n) {
        
        String binaryN = Integer.toBinaryString(n);
        int countOfN = countOfOne(binaryN);
        
        int tmp = n+1;
        while(true){
            String binaryTmp = Integer.toBinaryString(tmp);
            if(countOfOne(binaryTmp) == countOfN){
                return tmp;
            }
            tmp += 1;
        }
        
        
        
        
    }
    
    public int countOfOne(String binary){
        int result = 0;
        for(int i=0;i<binary.length();i++){
            if(binary.charAt(i)=='1'){
                result++;
            }
        }
        
        return result;
    }
    
    
}