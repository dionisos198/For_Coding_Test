import java.util.*;
class Solution {
    public int[] solution(String s) {
        
        int deleteZeroCount = 0;
        int binaryCount = 0;
        
        while(!s.equals("1")){
            
            binaryCount++;
            
            int countOfOne = 0;
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)=='1'){
                    countOfOne++;
                }
            }
            
            deleteZeroCount += (s.length() - countOfOne);
            
            if(countOfOne == 1){
                break;
            }
            
            s = makeNewS(countOfOne);
            
        }
        
        return new int[]{binaryCount, deleteZeroCount};
        

    }
    
    public String makeNewS(int countOfOne){
            
      return Integer.toBinaryString(countOfOne);
    }
    
    
    
}