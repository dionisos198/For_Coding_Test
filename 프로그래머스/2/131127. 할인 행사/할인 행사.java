import java.util.*;
class Solution {
    Map<String,Integer> countMap = new HashMap<>();
    
    public boolean isMapped(String[] want, int []number){
        
        for(int i=0;i<want.length;i++){
            if(countMap.containsKey(want[i]) && countMap.get(want[i]) == number[i]){
                continue;
            }
            return false;
        }
        
        return true;
    }
    
    public int solution(String[] want, int[] number, String[] discount) {
        
        int day = 0;
        for(int i=0;i<10;i++){
            day = i;
            countMap.put(discount[i], countMap.getOrDefault(discount[i],0)+1);
        }
        
        int answer = 0;
        int end = day;
        int start = 0;
        
        for(int i=day;i<discount.length;i++){
            
            if(isMapped(want, number)){
              answer++;   
            }
            
            end++;
            if(end>=discount.length) continue;
            countMap.put(discount[end], countMap.getOrDefault(discount[end],0)+1);
            countMap.put(discount[start], countMap.get(discount[start])-1);
            start++;
            
        }
        
        return answer;
        
        
    }
}