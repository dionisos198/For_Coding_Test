import java.util.*;
class Solution {
    
    int totalSize = 0;
    Set<String> set = new HashSet<>();
    HashMap<String,Integer> map = new HashMap<>();
    Set<String> tmpSet = new HashSet<>();
    
    public int[] solution(String[] gems) {
        
        int ans[] = new int[]{0,Integer.MAX_VALUE};
        
        for(String gem: gems){
            set.add(gem);
        }
        
        totalSize = set.size();
        
        int start = 0;
        int end = 0;
        
        tmpSet.add(gems[end]);
        map.put(gems[end],map.getOrDefault(gems[end],0)+1);
        
        while(true){
         //   System.out.println("start: "+start+"end: "+end);
            if(tmpSet.size() == totalSize){
              //  System.out.println("첫 진입");
                while(true){
                    if(ans[1]-ans[0]>end-start){
                    ans[1] = end;
                    ans[0] = start;
                    }
                    map.put(gems[start],map.get(gems[start])-1);
                    if(map.get(gems[start])==0){
                        tmpSet.remove(gems[start]);
                    }
                    start++;
                    if(tmpSet.size()!=totalSize){
                        break;
                    }
                }
                
            }
            else{
              //  System.out.println("둘 진입");
                end++;
                if(end>=gems.length){
                    break;
                }
                tmpSet.add(gems[end]);
                map.put(gems[end],map.getOrDefault(gems[end],0)+1);
            }
            
            
        }
        
        ans[0]++;
        ans[1]++;
        
        return ans;
        
        
    }
}