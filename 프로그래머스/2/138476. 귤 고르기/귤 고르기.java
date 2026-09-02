import java.util.*;
class Solution {
    
    Map<Integer,Integer> map = new HashMap<>();
    
    
    public int solution(int k, int[] tangerine) {
        
        mapInit(tangerine);
        List<Integer> keyList = new ArrayList<>(map.keySet());
        
        Collections.sort(keyList,(o1,o2)->{
            return map.get(o2)-map.get(o1);
        });
        
        int answer = 0;
        for(int i=0;i<keyList.size();i++){
            
           // System.out.println(keyList.get(i)+" "+map.get(keyList.get(i)));
            k-=map.get(keyList.get(i));
            answer+=1;
            if(k<=0){
                break;
            }
        }
        
        return answer;
        
    }
    
    public void mapInit(int[] tangerine){
        
        for(int i=0;i<tangerine.length;i++){
            
            map.put(tangerine[i],map.getOrDefault(tangerine[i],0)+1);
        }
    }
}