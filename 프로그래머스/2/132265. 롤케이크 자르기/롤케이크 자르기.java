import java.util.*;
class Solution {
    public int solution(int[] topping) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        Map<Integer,Integer> map = new HashMap<>();
        
        for(int i = 0;i<topping.length;i++){
            set2.add(topping[i]);
            if(!map.containsKey(topping[i])){
                map.put(topping[i],0);
            }
            map.put(topping[i],map.get(topping[i])+1);
        }
        
        int ans = 0;
        
        for(int i = 0;i<topping.length;i++){
            set1.add(topping[i]);
            
            map.put(topping[i],map.get(topping[i])-1);
            
            if(map.get(topping[i])==0){
                set2.remove(topping[i]);
            }
            
            // System.out.println(set1);
            // System.out.println(set2);
            
            if(set1.size() == set2.size()){
                ans++;
            }
        }
        
        return ans;
    }
}