import java.util.*;
class Solution {
    Set<String> set = new HashSet<>();
    public int[] solution(int n, String[] words) {
        
        int seq = 2;
        set.add(words[0]);
        for(int i=1;i<words.length;i++){
            if(out(words[i-1],words[i])){
                int number = seq % n;
                int cha = seq/n+1;
                if(number ==0){
                    number = n;
                    cha = seq/n;
                }
                return new int[]{number, cha};
                
            }
            set.add(words[i]);
            seq++;
        }
        
        return new int[]{0,0};
    }
    
    public boolean out(String before, String after){
        if(before.charAt(before.length()-1) != after.charAt(0) || 
           set.contains(after)){
            return true;
        }
        
        return false;
    }
}